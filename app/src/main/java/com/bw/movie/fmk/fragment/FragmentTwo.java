package com.bw.movie.fmk.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.transition.AutoTransition;
import android.support.transition.TransitionManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.fmk.base.BasefFragment;
import com.bw.movie.zjh.module.base.BaseFragment;
import com.bw.movie.zjh.module.ui.CinemaNearbyFragment;
import com.bw.movie.zjh.module.ui.CinemaRecommendFragment;
import com.bw.movie.zjh.module.utils.mvp.presenter.IPresenterImpl;
import com.bw.movie.zjh.module.utils.mvp.view.IView;

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
    @BindView(R.id.et_home_search)
    EditText et_home_search;
    @BindView(R.id.tv_search)
    TextView tv_search;
    @BindView(R.id.cinema_group)
    RadioGroup cinema_group;
    @BindView(R.id.cinema_pager)
    ViewPager cinema_pager;

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

        fragments = new ArrayList<>();
        recommendFragment = new CinemaRecommendFragment();
        nearbyFragment = new CinemaNearbyFragment();
        fragments.add(recommendFragment);
        fragments.add(nearbyFragment);
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
     *  展开
     * */
    private void expand() {
        //设置伸展状态时的布局
        et_home_search.setText("搜索电影名称");
        tv_search.setText("搜索");
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) home_search_bg.getLayoutParams();
        layoutParams.width = dip2px(getActivity(), 500);
        home_search_bg.setLayoutParams(layoutParams);
        //开始动画
        beginDelayedTransition(home_search_bg);
    }

    /*
     *   收缩
     *  */
    private void reduce() {
        //设置收缩状态时的布局
        et_home_search.setText("");
        tv_search.setText("");
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) home_search_bg.getLayoutParams();
        //layoutParams.width = ViewGroup.LayoutParams.WRAP_CONTENT;
        layoutParams.width = dip2px(getActivity(), 100);
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


    /*
     *   回到方法
     * */
    @Override
    public void viewDataSuccess(Object data) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        bind.unbind();
        iPresenter.onDetach();
    }

}
