package com.bw.movie.zjh.module.beans.cinema;

/**
 * author : zjh
 * e-mail : zjh@163.com
 * date   : 2019/5/17 10:32
 * desc   :
 * version: 1.0
 */
public class PayTicketBean {
    /**
     * appId : wxb3852e6a6b7d9516
     * message : 支付成功
     * nonceStr : ZOMeAQ556lFcnWEU
     * packageValue : Sign=WXPay
     * partnerId : 1510865081
     * prepayId : wx23101935566088cd81ca73a52849721896
     * sign : 2A10E3760961CD71E71FE7ED20FD151F
     * status : 0000
     * timeStamp : 1558577968
     */

    private String appId;
    private String message;
    private String nonceStr;
    private String packageValue;
    private String partnerId;
    private String prepayId;
    private String sign;
    private String status;
    private String timeStamp;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getPackageValue() {
        return packageValue;
    }

    public void setPackageValue(String packageValue) {
        this.packageValue = packageValue;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getPrepayId() {
        return prepayId;
    }

    public void setPrepayId(String prepayId) {
        this.prepayId = prepayId;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
}
