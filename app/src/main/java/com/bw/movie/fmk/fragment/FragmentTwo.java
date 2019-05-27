package com.bw.movie.fmk.fragment;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.transition.AutoTransition;
import android.support.transition.TransitionManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.style.AbsoluteSizeSpan;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.bw.movie.R;
import com.bw.movie.zjh.module.base.BaseFragment;
import com.bw.movie.zjh.module.beans.cinema.SearchBean;
import com.bw.movie.zjh.module.ui.cinema.AddEvent;
import com.bw.movie.zjh.module.ui.cinema.CinemaNearbyFragment;
import com.bw.movie.zjh.module.ui.cinema.CinemaRecommendFragment;
import com.bw.movie.zjh.module.utils.location.Const;
import com.bw.movie.zjh.module.utils.mvp.presenter.IPresenterImpl;
import com.bw.movie.zjh.module.utils.mvp.view.IView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @Auther: 付明锟
 * @Date: 2019/5/12 18:42
 * @Description:
 */
public class FragmentTwo extends BaseFragment implements IView {

    private Unbinder bind;
    @BindView(R.id.home_search_bg)
    RelativeLayout home_search_bg;
    @BindView(R.id.et_home_search)  //  搜索输入框
            EditText et_home_search;

    @BindView(R.id.tv_search)
    TextView tv_search;
    @BindView(R.id.cinema_group)
    RadioGroup cinema_group;
    @BindView(R.id.cinema_pager)
    ViewPager cinema_pager;
    @BindView(R.id.home_replace_flyt)
    FrameLayout home_replace_flyt;

    @BindView(R.id.location_address)
    TextView location_address;
    Fragment currentFragment = null;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;

    private IPresenterImpl iPresenter;
    private AutoTransition mSet;
    private boolean isExpand = true;
    private List<Fragment> fragments;
    private CinemaRecommendFragment recommendFragment;
    private CinemaNearbyFragment nearbyFragment;

    @Override
    protected int setView() {
        return R.layout.fragment_two;
    }


    @Override
    protected void init(View view) {
        bind = ButterKnife.bind(this, view);
        iPresenter = new IPresenterImpl(this);
        EventBus.getDefault().register(this);

        fragmentManager = getChildFragmentManager();
        transaction = fragmentManager.beginTransaction();
        transaction.commit();

        fragments = new ArrayList<>();
        recommendFragment = new CinemaRecommendFragment();
        nearbyFragment = new CinemaNearbyFragment();
        fragments.add(recommendFragment);
        fragments.add(nearbyFragment);

        /**
         * 定位权限
         * Manifest.permission.ACCESS_FINE_LOCATION
         * Manifest.permission.ACCESS_COARSE_LOCATION
         * 相机相册权限
         * Manifest.permission.CAMERA
         * 手机状态权限
         * Manifest.permission.READ_CALL_LOG,
         * Manifest.permission.READ_PHONE_STATE,
         * Manifest.permission.CALL_PHONE,
         * Manifest.permission.WRITE_CALL_LOG,
         * Manifest.permission.USE_SIP,
         * Manifest.permission.PROCESS_OUTGOING_CALLS,
         * Manifest.permission.ADD_VOICEMAIL
         * 读写权限
         * Manifest.permission.READ_EXTERNAL_STORAGE,
         * Manifest.permission.WRITE_EXTERNAL_STORAGE
         */
       /* String[] permissions = new String[]{
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.CAMERA,
                Manifest.permission.READ_CALL_LOG,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.CALL_PHONE,
                Manifest.permission.WRITE_CALL_LOG,
                Manifest.permission.USE_SIP,
                Manifest.permission.PROCESS_OUTGOING_CALLS,
                Manifest.permission.ADD_VOICEMAIL,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE};

        //PermissionsUtils.showSystemSetting = false;//是否支持显示系统设置权限设置窗口跳转
        //创建监听权限的接口对象
        PermissionsUtils.IPermissionsResult permissionsResult = new PermissionsUtils.IPermissionsResult() {
            @Override
            public void passPermissons() {
                Toast.makeText(getContext(), "权限通过，可以做其他事情!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void forbitPermissons() {
                Toast.makeText(getContext(), "权限不通过!", Toast.LENGTH_SHORT).show();
            }
        };
        //这里的this不是上下文，是Activity对象！
        PermissionsUtils.getInstance().chekPermissions(getActivity(), permissions, permissionsResult);
*/
        // 设置位置信息
        location_address.setText(Const.ADDRESS);

        /*
         *   展示定位地址
         *   TODO  6.0权限
         * */
        //initLocationOption();

    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        //适配fragment
        cinema_pager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return fragments.get(i);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });
        //viewpager滑动监听
        cinema_pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            //滑动时
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            //页面选中时
            @Override
            public void onPageSelected(int position) {
                cinema_group.check(cinema_group.getChildAt(position).getId());
            }

            //滑动状态改变时
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        //选择监听事件
        cinema_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.cinema_rb1:
                        cinema_pager.setCurrentItem(0);
                        break;
                    case R.id.cinema_rb2:
                        cinema_pager.setCurrentItem(1);
                        break;
                    default:
                        break;
                }
            }
        });


    }

    /*
     *   点击事件 1.定位  2.搜索
     * */
    @OnClick({R.id.home_location, R.id.home_search})
    public void mHomeOnClick(View v) {
        switch (v.getId()) {
            case R.id.home_location: //定位
                /*BottomSheetDialog dialog = new BottomSheetDialog(getActivity());
                dialog.setContentView(R.layout.dialog_view);
                dialog.show();*/
                break;
            case R.id.home_search:   //搜索
                if (isExpand == true) {
                    expand();
                    isExpand = false;
                } else {
                    reduce();
                    isExpand = true;
                }
                break;
            default:
                break;
        }
    }

    /*
     *  设置伸展状态时的布局
     * */
    private void expand() {
        // 新建一个可以添加属性的文本对象
        SpannableString ss = new SpannableString("搜索影院名称");
        // 新建一个属性对象,设置文字的大小
        AbsoluteSizeSpan ass = new AbsoluteSizeSpan(12, true);
        // 附加属性到文本
        ss.setSpan(ass, 0, ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        // 设置hint
        et_home_search.setHint(new SpannedString(ss)); // 一定要进行转换,否则属性会消失
        tv_search.setText("搜索");
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) home_search_bg.getLayoutParams();
        layoutParams.width = dip2px(getActivity(), 330);
        home_search_bg.setLayoutParams(layoutParams);
        //开始动画
        beginDelayedTransition(home_search_bg);

        /*
         *  点击开始搜索
         * */
        tv_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //需要搜索的关键字
                SearchBean searchBean = new SearchBean();
                searchBean.setSearch(et_home_search.getText().toString());
                searchBean.setFlag(isExpand = true);
                EventBus.getDefault().postSticky(searchBean);
                //进入到搜索页
                //EventBus.getDefault().post(new AddEvent(new CinemaSearchFragment()));
            }
        });
    }

    /*
     *   收缩
     *  */
    private void reduce() {
        //设置收缩状态时的布局
        et_home_search.setHint("");
        tv_search.setText("");
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) home_search_bg.getLayoutParams();
        //layoutParams.width = ViewGroup.LayoutParams.WRAP_CONTENT;
        layoutParams.width = dip2px(getActivity(), 90);
        home_search_bg.setLayoutParams(layoutParams);
        //开始动画
        beginDelayedTransition(home_search_bg);
    }

    //开始动画
    void beginDelayedTransition(ViewGroup view) {
        mSet = new AutoTransition();
        mSet.setDuration(300);
        TransitionManager.beginDelayedTransition(view, mSet);
    }

    //px转换dp
    public static int dip2px(Context context, float pxValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    @Subscribe
    public void showFragment(AddEvent event) {
        transaction = fragmentManager.beginTransaction();

        if (currentFragment != null) {
            transaction.hide(currentFragment);
        }
        currentFragment = event.fragment;
        if (!recommendFragment.isHidden()) {
            transaction.hide(recommendFragment);
        }
        if (!nearbyFragment.isHidden()) {
            transaction.hide(nearbyFragment);
        }
        transaction.add(R.id.home_replace_flyt, event.fragment).addToBackStack(null).commit();
    }

    /*
     *   回到方法
     * */
    @Override
    public void viewDataSuccess(Object data) {

    }

/*    private void initLocationOption() {
//定位服务的客户端。宿主程序在客户端声明此类，并调用，目前只支持在主线程中启动
        LocationClient locationClient = new LocationClient(getActivity());
//声明LocationClient类实例并配置定位参数
        LocationClientOption locationOption = new LocationClientOption();
        MyLocationListener myLocationListener = new MyLocationListener();
//注册监听函数
        locationClient.registerLocationListener(myLocationListener);
//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        locationOption.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
//可选，默认gcj02，设置返回的定位结果坐标系，如果配合百度地图使用，建议设置为bd09ll;
        locationOption.setCoorType("gcj02");
//可选，默认0，即仅定位一次，设置发起连续定位请求的间隔需要大于等于1000ms才是有效的
        locationOption.setScanSpan(1000);
//可选，设置是否需要地址信息，默认不需要
        locationOption.setIsNeedAddress(true);
//可选，设置是否需要地址描述
        locationOption.setIsNeedLocationDescribe(true);
//可选，设置是否需要设备方向结果
        locationOption.setNeedDeviceDirect(false);
//可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
        locationOption.setLocationNotify(true);
//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        locationOption.setIgnoreKillProcess(true);
//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        locationOption.setIsNeedLocationDescribe(true);
//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        locationOption.setIsNeedLocationPoiList(true);
//可选，默认false，设置是否收集CRASH信息，默认收集
        locationOption.SetIgnoreCacheException(false);
//可选，默认false，设置是否开启Gps定位
        locationOption.setOpenGps(true);
//可选，默认false，设置定位时是否需要海拔信息，默认不需要，除基础定位版本都可用
        locationOption.setIsNeedAltitude(false);
//设置打开自动回调位置模式，该开关打开后，期间只要定位SDK检测到位置变化就会主动回调给开发者，该模式下开发者无需再关心定位间隔是多少，定位SDK本身发现位置变化就会及时回调给开发者
        locationOption.setOpenAutoNotifyMode();
//设置打开自动回调位置模式，该开关打开后，期间只要定位SDK检测到位置变化就会主动回调给开发者
        locationOption.setOpenAutoNotifyMode(3000, 1, LocationClientOption.LOC_SENSITIVITY_HIGHT);
//需将配置好的LocationClientOption对象，通过setLocOption方法传递给LocationClient对象使用
        locationClient.setLocOption(locationOption);
//开始定位
        locationClient.start();
    }

    *//**
     * 实现定位回调
     *//*
    public class MyLocationListener extends BDAbstractLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            //此处的BDLocation为定位结果信息类，通过它的各种get方法可获取定位相关的全部结果
            //以下只列举部分获取经纬度相关（常用）的结果信息
            //更多结果信息获取说明，请参照类参考中BDLocation类中的说明
            //获取纬度信息
            double latitude = location.getLatitude();
            //获取经度信息
            double longitude = location.getLongitude();
            //获取定位精度，默认值为0.0f
            float radius = location.getRadius();
            //获取经纬度坐标类型，以LocationClientOption中设置过的坐标类型为准
            String coorType = location.getCoorType();
            //获取定位类型、定位错误返回码，具体信息可参照类参考中BDLocation类中的说明
            int errorCode = location.getLocType();
            location_address.setText(location.getAddress().address);
        }
    }*/

    /*
     *  内存处理
     * */
    @Override
    public void onDestroy() {
        super.onDestroy();
        bind.unbind();
        iPresenter.onDetach();
        EventBus.getDefault().unregister(this);
    }

}
