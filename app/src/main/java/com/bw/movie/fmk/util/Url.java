package com.bw.movie.fmk.util;

/**
 * @Auther: 付明锟
 * @Date: 2019/5/9 15:58
 * @Description:
 */
public class Url {


    public static String TOU="http://172.17.8.100/";

    //登录，http://172.17.8.100/movieApi/user/v1/login
    public static String DENGLU="movieApi/user/v1/login";

    //注册，http://172.17.8.100/movieApi/user/v1/registerUser
    public static String ZHUCE="movieApi/user/v1/registerUser";

    //轮播，http://172.17.8.100/movieApi/movie/v1/findHotMovieList
    public static String LUNBOU="movieApi/movie/v1/findHotMovieList?page=1&count=10";

    //热门电影 http://172.17.8.100/movieApi/movie/v1/findHotMovieList
    public static String RMENDIANYING="movieApi/movie/v1/findHotMovieList?page=1&count=10";
}
