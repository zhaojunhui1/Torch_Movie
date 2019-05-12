package com.bw.movie.fmk.util;

import android.os.Environment;
import android.util.Log;

import com.bw.movie.Greendao.dao.GreendaoBeanDao;
import com.bw.movie.app.App;
import com.bw.movie.fmk.bean.GreendaoBean;

import java.io.File;
import java.io.IOException;
import java.util.List;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @Auther: 付明锟
 * @Date: 2019/5/9 15:57
 * @Description:
 */
public class RetroFitUtil {

    public static RetroFitUtil retroFitUtil;
    public Retrofit retrofit;
    public Api api;
    public int userId;
    public String sessionId;
    private final OkHttpClient okHttpClient;


    //添加到数据库中
    public void Dao(){
        GreendaoBeanDao greendaoBeanDao = App.daoSession.getGreendaoBeanDao();
        List<GreendaoBean> greendaoBeans = greendaoBeanDao.loadAll();
        sessionId = greendaoBeans.get(0).getSessionId();
        userId = greendaoBeans.get(0).getUserId();
        Log.e("tab",sessionId+"---"+userId);
    }

    //拦截器
    private RetroFitUtil(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new httplog());

        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        File file = new File(Environment.getExternalStorageDirectory(),"ttttt");

        okHttpClient = new OkHttpClient.Builder()
                .addNetworkInterceptor(interceptor)
                .cache(new Cache(file,10*1024*1024))
//                .addInterceptor(new Interceptor() {
//                    @Override
//                    public Response intercept(Chain chain) throws IOException {
//                        Request request = chain.request()
//                                .newBuilder()
////                                .addHeader("userId",String.valueOf(userId))
////                                .addHeader("sessionId",String.valueOf(sessionId))
//                                .build();
//                        Response proceed = chain.proceed(request);
//                        return proceed;
//                    }
//                })
                .build();
    }


    //打印
    private class httplog implements HttpLoggingInterceptor.Logger {
        @Override
        public void log(String message) {
            Log.e("tab1","拦截=="+message);
        }
    }

    //构造方法私有化
    private Retrofit getRetrofit(){
        retrofit = new Retrofit.Builder()
                .baseUrl(Url.TOU)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        return retrofit;
    }

    //单例模式
    public static synchronized RetroFitUtil getInRetroFitUtil(){
        if (retroFitUtil==null){
            retroFitUtil = new RetroFitUtil();
        }
        return retroFitUtil;
    }


    public <T>T setRtrofit(Class<T> tClass){
        return getRetrofit().create(tClass);
    }
}
