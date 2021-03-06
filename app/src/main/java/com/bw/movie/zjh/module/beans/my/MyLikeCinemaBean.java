package com.bw.movie.zjh.module.beans.my;

import java.util.List;

/**
 * author : zjh
 * e-mail : zjh@163.com
 * date   : 2019/5/22 16:05
 * desc   :
 * version: 1.0
 */
public class MyLikeCinemaBean {

    /**
     * result : [{"address":"北京海淀区悦秀路99号二层大地影院","commentTotal":0,"distance":0,"followCinema":0,"id":19,"logo":"http://172.17.8.100/images/movie/logo/ddyy.jpg","name":"大地影院-北京海淀西三旗物美"}]
     * message : 查询成功
     * status : 0000
     */

    private String message;
    private String status;
    private List<ResultBean> result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * address : 北京海淀区悦秀路99号二层大地影院
         * commentTotal : 0
         * distance : 0
         * followCinema : 0
         * id : 19
         * logo : http://172.17.8.100/images/movie/logo/ddyy.jpg
         * name : 大地影院-北京海淀西三旗物美
         */

        private String address;
        private int commentTotal;
        private int distance;
        private int followCinema;
        private int id;
        private String logo;
        private String name;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getCommentTotal() {
            return commentTotal;
        }

        public void setCommentTotal(int commentTotal) {
            this.commentTotal = commentTotal;
        }

        public int getDistance() {
            return distance;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }

        public int getFollowCinema() {
            return followCinema;
        }

        public void setFollowCinema(int followCinema) {
            this.followCinema = followCinema;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
