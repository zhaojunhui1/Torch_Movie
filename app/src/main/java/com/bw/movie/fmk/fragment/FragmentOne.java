package com.bw.movie.fmk.fragment;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bw.movie.R;
import com.bw.movie.fmk.adapter.BannerAdapter;
import com.bw.movie.fmk.adapter.RMenDianYingAdapter;
import com.bw.movie.fmk.base.BasefFragment;
import com.bw.movie.fmk.bean.BannerBean;
import com.bw.movie.fmk.bean.RMenBean;
import com.bw.movie.fmk.mvp.p.MyPenster;
import com.bw.movie.fmk.mvp.p.PInterface;
import com.bw.movie.fmk.mvp.v.VInterface;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import recycler.coverflow.RecyclerCoverFlow;

/**
 * @Auther: 付明锟
 * @Date: 2019/5/12 18:42
 * @Description:
 */
public class FragmentOne extends BasefFragment implements VInterface.VInterfaceLunBo,VInterface.VInterfaceRMen {

    public List<BannerBean.ResultBean> bannerBeans = new ArrayList<>();
    //热门电影
    public List<RMenBean.ResultBean> rmenBean = new ArrayList<>();
    private PInterface.PInterfaceLunBo pInterfaceLunBo;
    private RecyclerCoverFlow lunb;
    private BannerAdapter bannerAdapter;
    private RecyclerView rmen_id;
    private RMenDianYingAdapter rMenDianYingAdapter;
    private PInterface.PInterfaceRMen pInterfaceRMen;

    @Override
    protected int initLayout() {
        return R.layout.fragment_one;
    }

    @Override
    protected void ininView() {
        lunb = fvd(R.id.lubo);
        rmen_id = fvd(R.id.rmen_id);
        pInterfaceLunBo = new MyPenster(this);
        pInterfaceRMen = new MyPenster(this);
    }

    @Override
    protected void initData() {

        pInterfaceLunBo.getLunBo(null);

        //适配器
        bannerAdapter = new BannerAdapter(bannerBeans,getActivity());
        lunb.setAdapter(bannerAdapter);

        //让轮播图显示中间的图片
         //lunb.smoothScrollToPosition();
        //自定义接口回调，点击图片使它展示到中间
//        bannerAdapter.setOnItemClick(new BannerAdapter.OnItemClick() {
//            @Override
//            public void onItemClick(View view, int position) {
//                lunb.smoothScrollToPosition(position);
//            }
//        });


        /*                   热门电影               */

        //实例化布局管理器
        LinearLayoutManager linear = new LinearLayoutManager(getActivity());
        linear.setOrientation(LinearLayoutManager.HORIZONTAL);
        rmen_id.setLayoutManager(linear);

        pInterfaceRMen.getRMen(null);

        //适配器
        rMenDianYingAdapter = new RMenDianYingAdapter(rmenBean,getActivity());
        rmen_id.setAdapter(rMenDianYingAdapter);
    }


    //轮播
    @Override
    public void showLunBo(Object object) {

        BannerBean bannerBean = (BannerBean) object;
        List<BannerBean.ResultBean> result = bannerBean.getResult();
        bannerBeans.addAll(result);
        bannerAdapter.notifyDataSetChanged();

        Log.e("taggg","轮播V=="+bannerBean.getMessage());
    }

    //热门电影
    @Override
    public void showRMen(Object object) {

        RMenBean rMenBean2 = (RMenBean) object;
        List<RMenBean.ResultBean> result = rMenBean2.getResult();
        rmenBean.addAll(result);
        rMenDianYingAdapter.notifyDataSetChanged();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        pInterfaceLunBo=null;
        pInterfaceLunBo.onDsply();
        pInterfaceRMen=null;
        pInterfaceRMen.onDsply();
    }

}
