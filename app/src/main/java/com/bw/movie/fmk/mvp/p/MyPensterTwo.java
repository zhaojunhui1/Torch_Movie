package com.bw.movie.fmk.mvp.p;

import android.util.Log;

import com.bw.movie.fmk.mvp.m.MyModel;
import com.bw.movie.fmk.mvp.v.VInterface;
import com.bw.movie.fmk.util.Url;

import java.util.HashMap;

/**
 * @Auther: 付明锟
 * @Date: 2019/5/20 16:43
 * @Description:
 */
public class MyPensterTwo<T> implements PInterface.PInterfacegetDianYingPaiQi {

    private MyModel myModel;
    public T tt;

    public MyPensterTwo(T tt) {
        myModel = new MyModel();
        this.tt = tt;
    }

    //根据电影ID和影院ID查询电影排期列表
    @Override
    public void getDianYingPaiQi(String url, HashMap<String, String> map) {
        myModel.setMyModel(new MyModel.MyCallBack() {
            @Override
            public void succer(Object object) {
                ((VInterface.VInterfacegetDianYingPaiQi)tt).getDianYingPaiQi(object);
            }
        });
        myModel.getPaiQi(Url.DIANYINGID_YINGYUANID,map);
        Log.e("tab","myModel"+map);
    }

    @Override
    public void onDsply() {
         if (tt!=null){
             tt=null;
         }
    }
}
