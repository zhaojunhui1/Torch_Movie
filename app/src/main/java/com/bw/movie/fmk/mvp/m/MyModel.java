package com.bw.movie.fmk.mvp.m;

import android.util.Log;

import com.bw.movie.fmk.bean.BannerBean;
import com.bw.movie.fmk.bean.LoginBean;
import com.bw.movie.fmk.bean.RMenBean;
import com.bw.movie.fmk.bean.RYingBean;
import com.bw.movie.fmk.bean.ShangYingBean;
import com.bw.movie.fmk.bean.XiangQingZhuYeBean;
import com.bw.movie.fmk.bean.ZhuBean;
import com.bw.movie.fmk.util.Api;
import com.bw.movie.fmk.util.RetroFitUtil;
import com.bw.movie.fmk.util.Url;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.Map;

import okhttp3.ResponseBody;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * @Auther: 付明锟
 * @Date: 2019/5/9 16:28
 * @Description:
 */
public class MyModel {

    public MyCallBack myCallBack;


    public void getpostDeng(String url, final Map<String,String> map){
        RetroFitUtil inRetroFitUtil = RetroFitUtil.getInRetroFitUtil();
        Api api = inRetroFitUtil.setRtrofit(Api.class);
        api.getpost(url,map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ResponseBody>() {
                    @Override
                    public void call(ResponseBody responseBody) {
                        try {
                            String string = responseBody.string();
                            Log.e("tab","string=="+string);
                            Gson gson = new Gson();
                            LoginBean loginBean = gson.fromJson(string, LoginBean.class);
//                            JSONObject object=new JSONObject(string);
                           // String m = object.getString("message");

                           // Log.e("tab","model=="+m);
                            myCallBack.succer(loginBean);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
    }


    public void getpostZhu(String url, final Map<String,String> map){
         RetroFitUtil inRetroFitUtil = RetroFitUtil.getInRetroFitUtil();
         Api api = inRetroFitUtil.setRtrofit(Api.class);
         api.getpost(url,map)
                 .subscribeOn(Schedulers.io())
                 .observeOn(AndroidSchedulers.mainThread())
                 .subscribe(new Action1<ResponseBody>() {
                     @Override
                     public void call(ResponseBody responseBody) {
                         try {
                             String string = responseBody.string();
                             Log.e("tab","string=="+string);
                             Gson gson = new Gson();
                             ZhuBean zhuBean = gson.fromJson(string, ZhuBean.class);
                             myCallBack.succer(zhuBean);
                         } catch (Exception e) {
                             e.printStackTrace();
                         }
                     }
                 });
     }

     //轮播图
     public void getBanner(){
         RetroFitUtil inRetroFitUtil = RetroFitUtil.getInRetroFitUtil();
         Api api = inRetroFitUtil.setRtrofit(Api.class);
         api.getapi(Url.LUNBOU)
                 .subscribeOn(Schedulers.io())
                 .observeOn(AndroidSchedulers.mainThread())
                 .subscribe(new Action1<ResponseBody>() {
                     @Override
                     public void call(ResponseBody responseBody) {
                         try {
                             String string = responseBody.string();
                             Gson gson = new Gson();
                             BannerBean bannerBean = gson.fromJson(string, BannerBean.class);
                             Log.e("tag","轮播M=="+bannerBean);
                             myCallBack.succer(bannerBean);
                         } catch (Exception e) {
                             e.printStackTrace();
                         }
                     }
                 });
     }

    //热门电影
    public void getRMenDianYing(){
        RetroFitUtil inRetroFitUtil = RetroFitUtil.getInRetroFitUtil();
        Api api = inRetroFitUtil.setRtrofit(Api.class);
        api.getapi(Url.RMENDIANYING)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ResponseBody>() {
                    @Override
                    public void call(ResponseBody responseBody) {
                        try {
                            String string = responseBody.string();
                            Gson gson = new Gson();
                            RMenBean rMenBean = gson.fromJson(string, RMenBean.class);
                            myCallBack.succer(rMenBean);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    //正在热映
    public void getZhengZaiRYing(){
        RetroFitUtil inRetroFitUtil = RetroFitUtil.getInRetroFitUtil();
        Api api = inRetroFitUtil.setRtrofit(Api.class);
        api.getapi(Url.ZHENGZAIRYING)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ResponseBody>() {
                    @Override
                    public void call(ResponseBody responseBody) {
                        try {
                            String string = responseBody.string();
                            Gson gson = new Gson();
                            RYingBean rYingBean = gson.fromJson(string, RYingBean.class);
                            myCallBack.succer(rYingBean);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    //正在热映
    public void getJiJangShangYing(){
        RetroFitUtil inRetroFitUtil = RetroFitUtil.getInRetroFitUtil();
        Api api = inRetroFitUtil.setRtrofit(Api.class);
        api.getapi(Url.JIJANGSAHNGYING)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ResponseBody>() {
                    @Override
                    public void call(ResponseBody responseBody) {
                        try {
                            String string = responseBody.string();
                            Gson gson = new Gson();
                            ShangYingBean shangYingBean = gson.fromJson(string, ShangYingBean.class);
                            myCallBack.succer(shangYingBean);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    //根据电影ID查询电影信息,详情主页
    public void getXiangQingZhuYe(String url, final Map<String,String> map){
        RetroFitUtil inRetroFitUtil = RetroFitUtil.getInRetroFitUtil();
        Api api = inRetroFitUtil.setRtrofit(Api.class);
        api.getapi2(url,map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ResponseBody>() {
                    @Override
                    public void call(ResponseBody responseBody) {
                        try {
                            String string = responseBody.string();
                            Gson gson = new Gson();
                            XiangQingZhuYeBean xiangQingZhuYeBean = gson.fromJson(string, XiangQingZhuYeBean.class);
                            myCallBack.succer(xiangQingZhuYeBean);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    public void setMyModel(MyCallBack myCallBack) {
            this.myCallBack = myCallBack;
    }

    //内部接口
    public interface MyCallBack{
        public void succer(Object object);
    }
}
