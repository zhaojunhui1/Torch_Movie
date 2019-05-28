package com.bw.movie.fmk.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.bw.movie.R;
import com.bw.movie.fmk.base.BasefActivity;
import com.bw.movie.zjh.module.utils.location.Const;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends BasefActivity implements AMapLocationListener {
    private static final int MY_PERMISSIONS_REQUEST_CALL_LOCATION = 1;
    public AMapLocationClient mlocationClient;
    public AMapLocationClientOption mLocationOption = null;

   // private TextView miao;
    private SharedPreferences sp;
    private int time = 3;
    private Timer timer;
    private int i;

    @SuppressLint("HandlerLeak")
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
       /* BDLocationUtils bdLocationUtils = new BDLocationUtils(MainActivity.this);
        bdLocationUtils.doLocation();//开启定位
        bdLocationUtils.mLocationClient.start();//开始定位*/
        //检查版本是否大于M
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                        MY_PERMISSIONS_REQUEST_CALL_LOCATION);
            } else {
                //"权限已申请";
                showLocation();
            }
        }
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


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == MY_PERMISSIONS_REQUEST_CALL_LOCATION) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //"权限已申请"
                showLocation();
            } else {
                showToast("权限已拒绝,不能定位");

            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    // TODO:
    private void showLocation() {
        try {
            mlocationClient = new AMapLocationClient(this);
            mLocationOption = new AMapLocationClientOption();
            mlocationClient.setLocationListener(this);
            //设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
            mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            mLocationOption.setInterval(5000);
            //设置定位参数
            mlocationClient.setLocationOption(mLocationOption);
            //启动定位
            mlocationClient.startLocation();
        } catch (Exception e) {

        }
    }

    @Override
    public void onLocationChanged(AMapLocation amapLocation) {
        try {
            if (amapLocation != null) {
                if (amapLocation.getErrorCode() == 0) {
                    //定位成功回调信息，设置相关消息

                    //获取当前定位结果来源，如网络定位结果，详见定位类型表
                    Log.e("location", "定位类型"+amapLocation.getLocationType());
                    Log.e("location", "获取纬度"+amapLocation.getLatitude());
                    Log.e("location", "获取经度"+amapLocation.getLongitude());
                    Log.e("location", "获取精度信息"+amapLocation.getAccuracy());

                    //如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
                    Log.e("location", "地址"+amapLocation.getAddress());
                    Log.e("location", "国家信息"+amapLocation.getCountry());
                    Log.e("location", "省信息"+amapLocation.getProvince());
                    Log.e("location", "城市信息"+amapLocation.getCity());
                    Log.e("location", "城区信息"+amapLocation.getDistrict());
                    Log.e("location", "街道信息"+amapLocation.getStreet());
                    Log.e("location", "街道门牌号信息"+amapLocation.getStreetNum());
                    Log.e("location", "城市编码"+amapLocation.getCityCode());
                    Log.e("location", "地区编码"+amapLocation.getAdCode());
                    Log.e("location", "获取当前定位点的AOI信息"+amapLocation.getAoiName());
                    Log.e("location", "获取当前室内定位的建筑物Id"+amapLocation.getBuildingId());
                    Log.e("location", "获取当前室内定位的楼层"+amapLocation.getFloor());
                    Log.e("location", "获取GPS的当前状态"+amapLocation.getGpsAccuracyStatus() + "");

                    //获取定位时间
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date date = new Date(amapLocation.getTime());

                    Log.e("location", "获取定位时间"+df.format(date));

                    showToast("地址"+amapLocation.getAddress());

                    Const.ADDRESS = amapLocation.getAddress();
                    Const.LONGITUDE = amapLocation.getLongitude();
                    Const.LATITUDE = amapLocation.getLatitude();
                    // 停止定位
                    mlocationClient.stopLocation();
                } else {
                    //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                    Log.e("AmapError", "location Error, ErrCode:"
                            + amapLocation.getErrorCode() + ", errInfo:"
                            + amapLocation.getErrorInfo());
                }
            }
        } catch (Exception e) {
        }
    }


    @Override
    protected void onStop() {
        super.onStop();
        // 停止定位
        if (null != mlocationClient) {
            mlocationClient.stopLocation();
        }
    }

    /**
     * 销毁定位
     */
    private void destroyLocation() {
        if (null != mlocationClient) {
            /**
             * 如果AMapLocationClient是在当前Activity实例化的，
             * 在Activity的onDestroy中一定要执行AMapLocationClient的onDestroy
             */
            mlocationClient.onDestroy();
            mlocationClient = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        destroyLocation();
    }

    private void showToast(String string) {
        Toast.makeText(MainActivity.this, string, Toast.LENGTH_LONG).show();
    }


}
