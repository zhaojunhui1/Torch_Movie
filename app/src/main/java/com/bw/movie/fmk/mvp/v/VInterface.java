package com.bw.movie.fmk.mvp.v;

import java.util.HashMap;

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

    //电影预告
    public interface VInterfaceYuGao {
        void getYuGao(Object object);
    }

    //电影关注
    public interface VInterfacegetDianYingGuanZhu {
        void getDianYingGuanZhu(Object object);
    }

    //取消电影关注
    public interface VInterfacegetQuXiaoDianYingGuanZhu {
        void getQuXiaoDianYingGuanZhu(Object object);
    }

    //电影评论
    public interface VInterfacegetDianYingPingLun {
        void getDianYingPingLun(Object object);
    }

    //电影评论点赞
    public interface VInterfacegetPingLunDianZan {
        void getPingLunDianZan(Object object);
    }

    //电影评论回复
    public interface VInterfacegetPTianJIaPingLun {
        void getTianJIaPingLun(Object object);
    }

    //电影购票
    public interface VInterfacegetGouPiao {
        void getGouPiao(Object object);
    }

    //根据电影ID和影院ID查询电影排期列表
    public interface VInterfacegetDianYingPaiQi {
        void getDianYingPaiQi(Object object);
    }
}
