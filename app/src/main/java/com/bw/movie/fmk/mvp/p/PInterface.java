package com.bw.movie.fmk.mvp.p;

import java.util.HashMap;

/**
 * @Auther: 付明锟
 * @Date: 2019/5/9 17:11
 * @Description:
 */
public interface PInterface {

    //登录
    public interface PInterfaceDengLu {
        void getDengLu(HashMap<String, String> map);
        void onDsply();
    }
    //注册
    public interface PInterfaceZhuCe {
        void getZhuCe(HashMap<String, String> map);
        void onDsply();
    }

    //轮播
    public interface PInterfaceLunBo {
        void getLunBo(String url);
        void onDsply();
    }

    //热门电影
    public interface PInterfaceRMen {
        void getRMen(String url);
        void onDsply();
    }

    //正在热映
    public interface PInterfacegetRYing {
        void getRYing(String url);
        void onDsply();
    }

    //即将上映
    public interface PInterfacegetShangYing {
        void getShangYing(String url);
        void onDsply();
    }

    //根据电影ID查询电影信息,详情主页
    public interface PInterfacegetXiangQingZhuYe {
        void getXiangQingZhuYe(String url,HashMap<String, String> map);
        void onDsply();
    }

    //电影详情
    public interface PInterfacegetDianYingXiangQing {
        void getXiangDianYingXiangQing(String url);
        void onDsply();
    }
}
