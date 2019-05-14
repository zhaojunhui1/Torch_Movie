package com.bw.movie.fmk.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.fmk.activity.XiangQingActivity;
import com.bw.movie.fmk.adapter.BannerAdapter;
import com.bw.movie.fmk.adapter.RMenDianYingAdapter;
import com.bw.movie.fmk.adapter.RYingAdapter;
import com.bw.movie.fmk.adapter.ShangYingAdapter;
import com.bw.movie.fmk.base.BasefFragment;
import com.bw.movie.fmk.bean.BannerBean;
import com.bw.movie.fmk.bean.RMenBean;
import com.bw.movie.fmk.bean.RYingBean;
import com.bw.movie.fmk.bean.ShangYingBean;
import com.bw.movie.fmk.mvp.p.MyPenster;
import com.bw.movie.fmk.mvp.p.PInterface;
import com.bw.movie.fmk.mvp.v.VInterface;

import java.util.ArrayList;
import java.util.List;

import recycler.coverflow.RecyclerCoverFlow;

/**
 * @Auther: 付明锟
 * @Date: 2019/5/12 18:42
 * @Description:
 */
public class FragmentOne extends BasefFragment implements VInterface.VInterfaceLunBo,VInterface.VInterfaceRYing,VInterface.VInterfacegetShangYing,VInterface.VInterfaceRMen {

    //轮播
    public List<BannerBean.ResultBean> bannerBeans = new ArrayList<>();
    //热门电影
    public List<RMenBean.ResultBean> rmenBean = new ArrayList<>();
    //正在热映
    public List<RYingBean.ResultBean> ryYingBean = new ArrayList<>();
    //即将上映
    public List<ShangYingBean.ResultBean> shangyingBean = new ArrayList<>();
    private PInterface.PInterfaceLunBo pInterfaceLunBo;
    private RecyclerCoverFlow lunb;
    private BannerAdapter bannerAdapter;
    private RecyclerView rmen_id;
    private RMenDianYingAdapter rMenDianYingAdapter;
    private PInterface.PInterfaceRMen pInterfaceRMen;
    private RecyclerView rmen2_id;
    private PInterface.PInterfacegetRYing pInterfaceRYing;
    private RYingAdapter ryingAdapter;
    private PInterface.PInterfacegetShangYing pInterfacegetShangYing;
    private ShangYingAdapter shangYingAdapter;
    private RecyclerView rmen3_id;

    @Override
    protected int initLayout() {
        return R.layout.fragment_one;
    }

    @Override
    protected void ininView() {
        lunb = fvd(R.id.lubo);
        rmen_id = fvd(R.id.rmen_id);
        rmen2_id = fvd(R.id.rmen2_id);
        rmen3_id = fvd(R.id.rmen3_id);
        pInterfaceLunBo = new MyPenster(this);
        pInterfaceRMen = new MyPenster(this);
        pInterfaceRYing = new MyPenster(this);
        pInterfacegetShangYing = new MyPenster(this);
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
        bannerAdapter.setOnItem(new BannerAdapter.OnItem() {
            @Override
            public void onClick(int position) {
                lunb.smoothScrollToPosition(position);
            }
        });


        /*                   热门电影               */

        //实例化布局管理器
        LinearLayoutManager linear = new LinearLayoutManager(getActivity());
        linear.setOrientation(LinearLayoutManager.HORIZONTAL);
        rmen_id.setLayoutManager(linear);

        pInterfaceRMen.getRMen(null);

        //适配器
        rMenDianYingAdapter = new RMenDianYingAdapter(rmenBean,getActivity());
        rmen_id.setAdapter(rMenDianYingAdapter);

        //条目点击
        rMenDianYingAdapter.setOnItem(new RMenDianYingAdapter.OnItem() {
            @Override
            public void onClick(int position, String movieId) {
                Intent intent = new Intent(getActivity(),XiangQingActivity.class);
                intent.putExtra("id",movieId);

                startActivity(intent);
            }
        });


        /*                   正在热映             */

        //实例化布局管理器
        LinearLayoutManager linear2 = new LinearLayoutManager(getActivity());
        linear2.setOrientation(LinearLayoutManager.HORIZONTAL);
        rmen2_id.setLayoutManager(linear2);

        pInterfaceRYing.getRYing(null);

        //适配器
        ryingAdapter = new RYingAdapter(ryYingBean,getActivity());
        rmen2_id.setAdapter(ryingAdapter);

        //条目点击
        ryingAdapter.setOnItem(new RYingAdapter.OnItem() {
            @Override
            public void onClick(int position, String movieId) {
                Toast.makeText(getActivity(),"热门电影",Toast.LENGTH_LONG).show();
            }

        });

        /*                   即将上映             */

        //实例化布局管理器
        LinearLayoutManager linear3 = new LinearLayoutManager(getActivity());
        linear3.setOrientation(LinearLayoutManager.HORIZONTAL);
        rmen3_id.setLayoutManager(linear3);

        pInterfacegetShangYing.getShangYing(null);

        //适配器
        shangYingAdapter = new ShangYingAdapter(shangyingBean,getActivity());
        rmen3_id.setAdapter(shangYingAdapter);

        //条目点击
        shangYingAdapter.setOnItem(new ShangYingAdapter.OnItem() {
            @Override
            public void onClick(int position, String movieId) {
                Toast.makeText(getActivity(),"即将上映",Toast.LENGTH_LONG).show();
            }
        });
    }


    //轮播
    @Override
    public void showLunBo(Object object) {

        BannerBean bannerBean = (BannerBean) object;
        List<BannerBean.ResultBean> result = bannerBean.getResult();
        bannerBeans.addAll(result);
        bannerAdapter.notifyDataSetChanged();

        rMenDianYingAdapter.notifyDataSetChanged();

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

    //正在热映
    @Override
    public void showRYing(Object object) {
        RYingBean ryingBean2 = (RYingBean) object;
        List<RYingBean.ResultBean> result = ryingBean2.getResult();
        ryYingBean.addAll(result);
        ryingAdapter.notifyDataSetChanged();
    }

    //即将上映
    @Override
    public void showShangYing(Object object) {
        ShangYingBean shangYingBean2 = (ShangYingBean) object;
        List<ShangYingBean.ResultBean> result = shangYingBean2.getResult();
        shangyingBean.addAll(result);
        shangYingAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //轮播,热门电影
        pInterfaceLunBo=null;
        pInterfaceLunBo.onDsply();
        //热门电影
//        pInterfaceRMen=null;
//        pInterfaceRMen.onDsply();
        //正在热映
        pInterfaceRYing=null;
        pInterfaceRYing.onDsply();
        //即将上映
        pInterfacegetShangYing=null;
        pInterfacegetShangYing.onDsply();
    }

}
