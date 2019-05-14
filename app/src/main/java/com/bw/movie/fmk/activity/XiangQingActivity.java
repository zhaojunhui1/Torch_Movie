package com.bw.movie.fmk.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.fmk.base.BasefActivity;
import com.bw.movie.fmk.bean.BannerBean;
import com.bw.movie.fmk.bean.RYingBean;
import com.bw.movie.fmk.bean.XiangQingBean;
import com.bw.movie.fmk.bean.XiangQingZhuYeBean;
import com.bw.movie.fmk.fragment.FragmentOne;
import com.bw.movie.fmk.mvp.p.MyPenster;
import com.bw.movie.fmk.mvp.p.PInterface;
import com.bw.movie.fmk.mvp.v.VInterface;
import com.bw.movie.fmk.util.Url;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class XiangQingActivity extends BasefActivity implements VInterface.VInterfacegetXiangQingZhuYe {

    private SimpleDraweeView xiangqing_guanzhu;
    private TextView xiangqing_xiang;
    private TextView xiangqing_yugao;
    private TextView xiangqing_juzhao;
    private TextView xiangqing_yingping;
    private SimpleDraweeView xiangqing_fan;
    private TextView xiangqing_gou;
    private TextView xiangqing_name;
    private SimpleDraweeView xaingqing_image;
    //主页
    private List<XiangQingZhuYeBean.ResultBean> xiangQingZhuYeBean = new ArrayList<>();
    //电影详情
    private List<XiangQingBean.ResultBean> xiangQingBean = new ArrayList<>();
    private PInterface.PInterfacegetXiangQingZhuYe pInterfacegetXiangQingZhuYe;
    private TextView dianying_xiangqing;
    private PopupWindow popupWindow;
    private SimpleDraweeView xiangqing_pop_image;
    private TextView xiangqing_pop_name;
    private TextView xiangqing_pop_leixing;
    private TextView xiangqing_pop_daoyan;
    private TextView xiangqing_pop_shichang;
    private TextView xiangqing_pop_chandi;
    private TextView xiangqing_pop_juqing;
    private XiangQingZhuYeBean.ResultBean result;


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_xiang_qing;
    }

    @Override
    protected void initView() {
        xiangqing_guanzhu = fvd(R.id.xiangqing_guanzhu);
        //电影详情
        xiangqing_xiang = fvd(R.id.xiangqing_xiang);
        //预告
        xiangqing_yugao = fvd(R.id.xiangqing_yugao);
        //剧照
        xiangqing_juzhao = fvd(R.id.xiangqing_juzhao);
        //影评
        xiangqing_yingping = fvd(R.id.xiangqing_yingping);
        xiangqing_fan = fvd(R.id.xiangqing_fan);
        xiangqing_gou = fvd(R.id.xiangqing_gou);
        xiangqing_name = fvd(R.id.xiangqing_name);
        xaingqing_image = fvd(R.id.xaingqing_image);
        dianying_xiangqing = fvd(R.id.dianying_xiangqing);
        pInterfacegetXiangQingZhuYe = new MyPenster(this);


         //电影详情
         initpop();


//        xiangqing_yugao.setOnClickListener(this);
//        xiangqing_juzhao.setOnClickListener(this);
//        xiangqing_yingping.setOnClickListener(this);

    }

    @Override
    protected void initData() {
        String id = getIntent().getStringExtra("id");

        HashMap<String,String> map = new HashMap<>();
        map.put("movieId",id);

        pInterfacegetXiangQingZhuYe.getXiangQingZhuYe(null,map);




//        xiangqing_fan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(XiangQingActivity.this,FragmentOne.class);
//                startActivity(intent);
//                finish();
//            }
//        });
    }


    @Override
    public void showXiangQingZhuYe(Object object) {

        XiangQingZhuYeBean xiangQingZhuYeBeans = (XiangQingZhuYeBean)object;
        result = xiangQingZhuYeBeans.getResult();

        //首页赋值
        xiangqing_name.setText(result.getName());
        xaingqing_image.setImageURI(result.getImageUrl());

        //详情的赋值
        xiangqing_pop_image.setImageURI(result.getImageUrl());
        xiangqing_pop_name.setText(result.getName());
        xiangqing_pop_daoyan.setText(result.getDirector());
        xiangqing_pop_shichang.setText(result.getDuration());
        xiangqing_pop_chandi.setText(result.getPlaceOrigin());
        xiangqing_pop_juqing.setText(result.getSummary());

        xiangQingZhuYeBean.add(result);

    }



    //pop,详情
    private void initpop() {

        View view = LayoutInflater.from(this).inflate(R.layout.pop_window, null, false);

        xiangqing_pop_image = view.findViewById(R.id.xiangqing_pop_image);


        xiangqing_pop_name = view.findViewById(R.id.xiangqing_pop_name);

       // xiangqing_pop_leixing = view.findViewById(R.id.xiangqing_pop_leixing);
        xiangqing_pop_daoyan = view.findViewById(R.id.xiangqing_pop_daoyan);
        xiangqing_pop_shichang = view.findViewById(R.id.xiangqing_pop_shichang);
        xiangqing_pop_chandi = view.findViewById(R.id.xiangqing_pop_chandi);

        xiangqing_pop_juqing = view.findViewById(R.id.xiangqing_pop_juqing);

        //第一个参数是popWindow中的View,第二个是宽度，第三个是高度，第四个是能否获取焦点
        popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT, true);
        //设置popwindow是否能响应外界事件
        popupWindow.setOutsideTouchable(true);
        // 设置此参数获得焦点，否则无法点击，即：事件拦截消费
        popupWindow.setFocusable(true);
        //实例化一个颜色
        ColorDrawable dw = new ColorDrawable(this.getResources().getColor(R.color.colorpop));
        //弹出窗体的背景
        popupWindow.setBackgroundDrawable(dw);

        xiangqing_xiang.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String id = getIntent().getStringExtra("id");

                        HashMap<String,String> map = new HashMap<>();
                        map.put("movieId",id);

                        pInterfacegetXiangQingZhuYe.getXiangQingZhuYe(null,map);

                        popupWindow.showAtLocation(v,Gravity.BOTTOM,0,120);
                    }
                });
        popupWindow.dismiss();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        pInterfacegetXiangQingZhuYe.onDsply();
        pInterfacegetXiangQingZhuYe=null;
    }
}
