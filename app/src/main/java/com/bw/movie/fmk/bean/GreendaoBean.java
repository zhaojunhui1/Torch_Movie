package com.bw.movie.fmk.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @Auther: 付明锟
 * @Date: 2019/5/9 16:21
 * @Description:
 */
@Entity
public class GreendaoBean {
    public String sessionId ;
    public int userId ;
    @Generated(hash = 1554301529)
    public GreendaoBean(String sessionId, int userId) {
        this.sessionId = sessionId;
        this.userId = userId;
    }
    @Generated(hash = 1389728398)
    public GreendaoBean() {
    }
    public String getSessionId() {
        return this.sessionId;
    }
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
    public int getUserId() {
        return this.userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
}
