package com.bw.movie.fmk.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.fmk.base.BasefActivity;
import com.bw.movie.fmk.bean.BannerBean;
import com.bw.movie.fmk.bean.RYingBean;
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
    private List<XiangQingZhuYeBean.ResultBean> xiangQingZhuYeBean = new ArrayList<>();
    private PInterface.PInterfacegetXiangQingZhuYe pInterfacegetXiangQingZhuYe;
    private TextView dianying_xiangqing;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_xiang_qing;
    }

    @Override
    protected void initView() {
        xiangqing_guanzhu = fvd(R.id.xiangqing_guanzhu);
        xiangqing_xiang = fvd(R.id.xiangqing_xiang);
        xiangqing_yugao = fvd(R.id.xiangqing_yugao);
        xiangqing_juzhao = fvd(R.id.xiangqing_juzhao);
        xiangqing_yingping = fvd(R.id.xiangqing_yingping);
        xiangqing_fan = fvd(R.id.xiangqing_fan);
        xiangqing_gou = fvd(R.id.xiangqing_gou);
        xiangqing_name = fvd(R.id.xiangqing_name);
        xaingqing_image = fvd(R.id.xaingqing_image);
        dianying_xiangqing = fvd(R.id.dianying_xiangqing);
        pInterfacegetXiangQingZhuYe = new MyPenster(this);
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
        XiangQingZhuYeBean.ResultBean result = xiangQingZhuYeBeans.getResult();

        xiangqing_name.setText(result.getName());

        xaingqing_image.setImageURI(result.getImageUrl());

        xiangQingZhuYeBean.add(result);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        pInterfacegetXiangQingZhuYe.onDsply();
        pInterfacegetXiangQingZhuYe=null;
    }
}
