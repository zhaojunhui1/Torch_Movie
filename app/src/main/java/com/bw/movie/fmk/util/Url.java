package com.bw.movie.fmk.util;

/**
 * @Auther: 付明锟
 * @Date: 2019/5/9 15:58
 * @Description:
 */
public class Url {

    //mobile.bwstudent.com

    public static String TOU = "http://172.17.8.100/";

    //登录，http://172.17.8.100/movieApi/user/v1/login
    public static String DENGLU = "movieApi/user/v1/login";

    //注册，http://172.17.8.100/movieApi/user/v1/registerUser
    public static String ZHUCE = "movieApi/user/v1/registerUser";

    //轮播，//http://172.17.8.100/movieApi/movie/v1/findMovieListByCinemaId?cinemaId=5
    public static String LUNBOU="movieApi/movie/v1/findMovieListByCinemaId?cinemaId=5";

    //热门电影 http://172.17.8.100/movieApi/movie/v1/findHotMovieList
    public static String RMENDIANYING="movieApi/movie/v1/findHotMovieList?page=1&count=10";

    //正在热映 http://172.17.8.100/movieApi/movie/v1/findReleaseMovieList?page=1&count=10
    public static String ZHENGZAIRYING="movieApi/movie/v1/findReleaseMovieList?page=1&count=10";

    //即将上映 http://172.17.8.100/movieApi/movie/v1/findComingSoonMovieList?page=1&count=10
    public static String JIJANGSAHNGYING="movieApi/movie/v1/findComingSoonMovieList?page=1&count=10";

    //电影ID查询电影信息，http://172.17.8.100/movieApi/movie/v1/findMoviesById?movieId=12
    public static String DIANYINGID="movieApi/movie/v1/findMoviesById";

    //电影预告，http://172.17.8.100/movieApi/movie/v1/findMoviesDetail?movieId=12
    public static String DIANYINGYUGAO="movieApi/movie/v1/findMoviesDetail";

    //关注电影，http://172.17.8.100/movieApi/movie/v1/verify/followMovie
    public static String GUANZHUDIANYING="movieApi/movie/v1/verify/followMovie";

    //取消关注，http://172.17.8.100/movieApi/movie/v1/verify/cancelFollowMovie
    public static String QUXIAOGUANZHU="movieApi/movie/v1/verify/cancelFollowMovie";

    //查看电影评论，http://172.17.8.100/movieApi/movie/v1/findAllMovieComment?movieId=1&page=1&count=5
    public static String DIANYINGPINGLUN="movieApi/movie/v1/findAllMovieComment";

}
