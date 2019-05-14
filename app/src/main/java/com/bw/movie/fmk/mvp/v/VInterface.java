package com.bw.movie.fmk.mvp.v;

/**
 * @Auther: 付明锟
 * @Date: 2019/5/9 17:11
 * @Description:
 */
public interface VInterface {

    //登录
    public interface VInterfaceDengLu {
        public void showDengLu(Object object);
    }

    //注册
    public interface VInterfaceZhuCe {
        public void showZhuCe(Object object);
    }

    //轮播
    public interface VInterfaceLunBo {
        public void showLunBo(Object object);
    }

    //热门电影
    public interface VInterfaceRMen {
        public void showRMen(Object object);
    }

    //正在热映
    public interface VInterfaceRYing {
        public void showRYing(Object object);
    }

    //即将上映
    public interface VInterfacegetShangYing {
        public void showShangYing(Object object);
    }

    //根据电影ID查询电影信息,详情主页
    public interface VInterfacegetXiangQingZhuYe {
        public void showXiangQingZhuYe(Object object);
    }
}
