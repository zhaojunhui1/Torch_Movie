package com.bw.movie.fmk.mvp.p;

import com.bw.movie.fmk.mvp.m.MyModel;
import com.bw.movie.fmk.mvp.v.VInterface;
import com.bw.movie.fmk.util.Url;

import java.util.HashMap;

/**
 * @Auther: 付明锟
 * @Date: 2019/5/9 16:29
 * @Description:
 */
public class MyPenster<T> implements PInterface.PInterfaceDengLu,PInterface.PInterfaceZhuCe {

    private MyModel myModel;
    public T tt;

    public MyPenster(T tt) {
        myModel = new MyModel();
        this.tt = tt;
    }

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

    @Override
    public void onDsply() {
        if (tt!=null){
            tt=null;
        }
    }

}
