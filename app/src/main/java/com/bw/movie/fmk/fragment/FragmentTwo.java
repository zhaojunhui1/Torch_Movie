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
import com.bw.movie.zjh.module.ui.cinema.AddEventFragment;
import com.bw.movie.zjh.module.ui.cinema.CinemaDetailsFragment;
import com.bw.movie.zjh.module.ui.cinema.CinemaNearbyFragment;
import com.bw.movie.zjh.module.ui.cinema.CinemaRecommendFragment;
import com.bw.movie.zjh.module.ui.cinema.CinemaSearchFragment;
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
    @BindView(R.id.cinema_group)
    RadioGroup cinema_group;
    @BindView(R.id.cinema_pager)
    ViewPager cinema_pager;
    @BindView(R.id.home_replace_flyt)
    FrameLayout home_replace_flyt;

    @BindView(R.id.location_address)
    TextView location_address;
    @BindView(R.id.edit_value)
    EditText edit_value;
    private IPresenterImpl iPresenter;
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
    @OnClick({R.id.home_location, R.id.tv_search_btn})
    public void mHomeOnClick(View v) {
        switch (v.getId()) {
            case R.id.home_location: //定位
                /*BottomSheetDialog dialog = new BottomSheetDialog(getActivity());
                dialog.setContentView(R.layout.dialog_view);
                dialog.show();*/
                break;
            case R.id.tv_search_btn:
                if (edit_value.getText().toString().length() != 0) {
                    initSearchData();
                } else {
                    Toast.makeText(getActivity(), "请输入搜索内容", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }

    /*
     *  点击搜索
     * */
    private void initSearchData() {
        //需要搜索的关键字
        SearchBean searchBean = new SearchBean();
        searchBean.setSearch(edit_value.getText().toString());
        EventBus.getDefault().postSticky(searchBean);
        //进入到搜索页
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.home_replace_flyt, new CinemaSearchFragment(), null)
                .addToBackStack(null)
                .commit();
    }


    /*
     *   回到方法
     * */
    @Override
    public void viewDataSuccess(Object data) {

    }

    /*
     *  内存处理
     * */
    @Override
    public void onDestroy() {
        super.onDestroy();
        bind.unbind();
        iPresenter.onDetach();
    }

}
