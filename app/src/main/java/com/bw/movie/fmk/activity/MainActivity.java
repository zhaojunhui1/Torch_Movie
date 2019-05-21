package com.bw.movie.fmk.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.fmk.base.BasefActivity;
import com.bw.movie.fmk.fragment.FragmentActivity;
import com.bw.movie.zjh.module.utils.location.BDLocationUtils;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends BasefActivity {

   // private TextView miao;
    private SharedPreferences sp;
    private int time = 3;
    private Timer timer;
    private int i;

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what){
                case 300:
                    i--;
                    if (i<0) {
                        timer.cancel();
                        startActivity(new Intent(MainActivity.this, LogActivity.class));
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        handler.removeCallbacksAndMessages(null);
                        finish();
                    }
            }
        }
    };

//    Handler handler = new Handler(){
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            if (msg.what == 0) {
//                if (time > 0) {
//                    time--;
//                    miao.setText(time + "s");
//                    handler.sendEmptyMessageDelayed(0, 1000);
//                }
//            }
//        }
//    };

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        //百度地图
        //获取经纬度
        BDLocationUtils bdLocationUtils = new BDLocationUtils(this);
        bdLocationUtils.doLocation();//开启定位
        bdLocationUtils.mLocationClient.start();//开始定位
    }

    @Override
    protected void initData() {
            times();
    }

    private void times() {
        i=3;
        timer=new Timer();

        TimerTask timerTask=new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(300);
            }
        };

        timer.schedule(timerTask,1,1000);
    }
}
