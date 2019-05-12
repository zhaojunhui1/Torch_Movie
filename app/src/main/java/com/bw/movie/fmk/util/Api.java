package com.bw.movie.fmk.util;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import rx.Completable;
import rx.Observable;

/**
 * @Auther: 付明锟
 * @Date: 2019/5/9 15:58
 * @Description:
 */
public interface Api {


    @GET
    public Observable<ResponseBody> getapi(@Url String url);

    @FormUrlEncoded
    @POST
    public Observable<ResponseBody> getpost(@Url String url, @FieldMap Map<String, String> parmas);

}
