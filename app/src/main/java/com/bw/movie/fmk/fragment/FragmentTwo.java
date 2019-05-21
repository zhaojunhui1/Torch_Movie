package com.bw.movie.fmk.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.transition.AutoTransition;
import android.support.transition.TransitionManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
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

import com.bw.movie.R;
import com.bw.movie.zjh.module.base.BaseFragment;
import com.bw.movie.zjh.module.beans.cinema.SearchBean;
import com.bw.movie.zjh.module.ui.cinema.AddEvent;
import com.bw.movie.zjh.module.ui.cinema.AddEventFragment;
import com.bw.movie.zjh.module.ui.cinema.CinemaNearbyFragment;
import com.bw.movie.zjh.module.ui.cinema.CinemaRecommendFragment;
import com.bw.movie.zjh.module.ui.cinema.CinemaSearchFragment;
import com.bw.movie.zjh.module.ui.cinema.seak.Const;
import com.bw.movie.zjh.module.utils.location.BDLocationUtils;
import com.bw.movie.zjh.module.utils.mvp.presenter.IPresenterImpl;
import com.bw.movie.zjh.module.utils.mvp.view.IView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
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

        //Log.e("location", Const.LONGITUDE + "===" + Const.LATITUDE + "===" + Const.ADDRESS);
        // 位置信息
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
