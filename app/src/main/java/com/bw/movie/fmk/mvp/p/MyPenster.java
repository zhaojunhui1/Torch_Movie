package com.bw.movie.fmk.mvp.p;

import android.util.Log;

import com.bw.movie.fmk.mvp.m.MyModel;
import com.bw.movie.fmk.mvp.v.VInterface;
import com.bw.movie.fmk.util.Url;

import java.util.HashMap;

/**
 * @Auther: 付明锟
 * @Date: 2019/5/9 16:29
 * @Description:
 */
public class MyPenster<T> implements PInterface.PInterfaceDengLu,PInterface.PInterfaceZhuCe,PInterface.PInterfaceLunBo,PInterface.PInterfaceRMen,PInterface.PInterfacegetRYing,PInterface.PInterfacegetShangYing {

    private MyModel myModel;
    public T tt;

    public MyPenster(T tt) {
        myModel = new MyModel();
        this.tt = tt;
    }

    //登录
    @Override
    public void getDengLu(HashMap<String, String> map) {
        myModel.setMyModel(new MyModel.MyCallBack() {
            @Override
            public void succer(Object object) {
                ((VInterface.VInterfaceDengLu)tt).showDengLu(object);
            }
        });
        myModel.getpostDeng(Url.DENGLU,map);
    }

    //注册
    @Override
    public void getZhuCe(HashMap<String, String> map) {
        myModel.setMyModel(new MyModel.MyCallBack() {
            @Override
            public void succer(Object object) {
                ((VInterface.VInterfaceZhuCe)tt).showZhuCe(object);
            }
        });
        myModel.getpostZhu(Url.ZHUCE,map);
    }

    //轮播
    @Override
    public void getLunBo(String url) {
        myModel.setMyModel(new MyModel.MyCallBack() {
            @Override
            public void succer(Object object) {
                ((VInterface.VInterfaceLunBo)tt).showLunBo(object);
            }
        });
        myModel.getBanner();
        Log.e("tab","轮播P=="+url);
    }

    //热门电影
    @Override
    public void getRMen (String url) {
        myModel.setMyModel(new MyModel.MyCallBack() {
            @Override
            public void succer(Object object) {
                ((VInterface.VInterfaceRMen)tt).showRMen(object);
            }
        });
        myModel.getRMenDianYing();
    }

    //正在热映
    @Override
    public void getRYing(String url) {
        myModel.setMyModel(new MyModel.MyCallBack() {
            @Override
            public void succer(Object object) {
                ((VInterface.VInterfaceRYing)tt).showRYing(object);
            }
        });
        myModel.getZhengZaiRYing();
    }

    //即将上映
    @Override
    public void getShangYing(String url) {
        myModel.setMyModel(new MyModel.MyCallBack() {
            @Override
            public void succer(Object object) {
                ((VInterface.VInterfacegetShangYing)tt).showShangYing(object);
            }
        });
        myModel.getJiJangShangYing();
    }

    @Override
    public void onDsply() {
        if (tt!=null){
            tt=null;
        }
    }

}
