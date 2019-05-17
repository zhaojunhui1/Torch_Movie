package com.bw.movie.zjh.module.ui.cinema;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.util.ArrayMap;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.app.App;
import com.bw.movie.fmk.jiami.EncryptUtil;
import com.bw.movie.fmk.util.Api;
import com.bw.movie.zjh.module.base.BaseActivity;
import com.bw.movie.zjh.module.beans.cinema.BuyMovieTicketBean;
import com.bw.movie.zjh.module.ui.cinema.seak.SeatTable;
import com.bw.movie.zjh.module.utils.md5.MDUtils;
import com.bw.movie.zjh.module.utils.mvp.presenter.IPresenterImpl;
import com.bw.movie.zjh.module.utils.mvp.util.Apis;
import com.bw.movie.zjh.module.utils.mvp.view.IView;
import com.bw.movie.zjh.module.utils.statusbar.StatusBarWindowTop;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ChooseSeatBuyActivity extends Activity implements IView {

    private IPresenterImpl iPresenter;
    private Unbinder bind;
    @BindView(R.id.cinema_name)
    TextView cinema_name;
    @BindView(R.id.cinema_address)
    TextView cinema_address;
    @BindView(R.id.movie_name)
    TextView movie_name;
    @BindView(R.id.datatime)
    TextView datatime;
    @BindView(R.id.hall)
    TextView hall;

    @BindView(R.id.seat_Table)  //座位View
            SeatTable seat_Table;
    @BindView(R.id.priceAll)
    TextView priceAll;      //合计
    private PopupWindow mPop;
    private RadioButton radio_weixin;   //微信支付
    private RadioButton radio_zifubao;  //支付宝支付
    private RelativeLayout pay_submit;  //提交支付
    private TextView type_priceAll;     //支付消费总价
    private String hallName;
    private String scheduleId;
    private int amount = 0;

    // 定义一个变量，来标识是否退出
   /* private static boolean isExit = false;
    @SuppressLint("HandlerLeak")
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }
    };*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_seat_buy);

        initView();
        //initData();

    }

    /*
    *  初始化View
    * */
    private void initView() {
        //透明状态栏
        StatusBarWindowTop.setStatusBarFullTransparent(this);
        bind = ButterKnife.bind(this);
        iPresenter = new IPresenterImpl(this);
        //排期id
        scheduleId = getIntent().getStringExtra("id");
        //显示排期情况
        cinema_name.setText(getIntent().getStringExtra("name"));
        cinema_address.setText(getIntent().getStringExtra("address"));
        movie_name.setText(getIntent().getStringExtra("moviename"));
        datatime.setText(getIntent().getStringExtra("begin")+"-"+getIntent().getStringExtra("end"));
        hallName = getIntent().getStringExtra("hall");
        this.hall.setText(hallName);

        initSeat();
        //上下文
        //Activity activity = new Activity();
        //View decorView = activity.getWindow().getDecorView();
    }

    /*
     * 初始化数据
     * */
    private void initData() {

    }

    /*
     *   绑定  点击事件
     *    1. √ 确认
     *    2. × 取消
     *    3.
     *    4.
     * */
    @OnClick({R.id.ok, R.id.no})
    public void seatClick(View v) {
        switch (v.getId()) {
            case R.id.ok:
                //购票下单 、展示付款的选择框
                initBuyTicket();
                payChooseType(v);
                break;
            case R.id.no:
                onCreate(null);  //取消选择并刷新
                break;
            default:
                break;
        }
    }



    /*
    *   购票下单
    * */
    private void initBuyTicket() {
        SharedPreferences sharedPreferences = App.getApplication().getSharedPreferences("token", Context.MODE_PRIVATE);
        String userId = sharedPreferences.getString("userId", "");
        String s = MDUtils.MD5(userId+scheduleId+amount+"movie");

        Map<String, String> map = new ArrayMap<>();
        map.put("scheduleId", scheduleId);
        map.put("amount", amount+"");
        map.put("sign", s);
        iPresenter.postLoginPresenterData(Apis.BUY_MOVIE_TICKET, map, BuyMovieTicketBean.class);
    }

    /*
     * 支付选择的popwindow
     * 微信，支付宝
     * */
    private void payChooseType(View v) {
        View view = View.inflate(this, R.layout.pay_choose_type_view, null);
        radio_weixin = view.findViewById(R.id.radio_weixin);
        radio_zifubao = view.findViewById(R.id.radio_zifubao);
        pay_submit = view.findViewById(R.id.pay_submit);
        type_priceAll = view.findViewById(R.id.type_priceAll);
        mPop = new PopupWindow(view, ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
        //设置焦点
        mPop.setFocusable(true);
        //设置是否可以触摸
        mPop.setTouchable(true);
        mPop.setBackgroundDrawable(new BitmapDrawable());
        mPop.setOutsideTouchable(true);

        //收回 取消
        view.findViewById(R.id.dismiss_down).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPop.dismiss();
            }
        });
        //弹出
        mPop.showAtLocation(v, Gravity.BOTTOM, 0, 0);
    }

    /*
     *  回调函数
     * */
    @Override
    public void viewDataSuccess(Object data) {
        if (data instanceof BuyMovieTicketBean){
            BuyMovieTicketBean buyMovieTicketBean = (BuyMovieTicketBean) data;
            Toast.makeText(this, buyMovieTicketBean.getMessage(), Toast.LENGTH_SHORT).show();
            if (buyMovieTicketBean.getStatus().equals("0000")){
                Toast.makeText(this, "选择支付方式", Toast.LENGTH_SHORT).show();
            }
        }

    }


    /*
     *  电影院选座
     * */
    private void initSeat() {
        seat_Table.setScreenName(hallName+"荧幕");//设置屏幕名称
        seat_Table.setMaxSelected(3);//设置最多选中
        // 监听座位
        seat_Table.setSeatChecker(new SeatTable.SeatChecker() {
            @Override
            public boolean isValidSeat(int row, int column) {
                // 隐藏 排  或  列
                /*if(row == 4) {
                    return false;
                }*/
                return true;
            }

            // 已售的座位 (几排几列)
            @Override
            public boolean isSold(int row, int column) {
                if (row == 6 && column == 6) {
                    return true;
                }
                return false;
            }

            //  选中的 排  列
            @Override
            public void checked(int row, int column) {
                amount ++;
            }

            //  未选中的 排  列
            @Override
            public void unCheck(int row, int column) {

            }

            // 选中座位的文字颜色 (已默认白色)
            @Override
            public String[] checkedSeatTxt(int row, int column) {
                return null;
            }

        });
        /*   总排数，   总列数   */
        seat_Table.setData(7, 8);
    }

    /*
     *   返回退出选座
     * */
  /*  @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exit() {
        if (!isExit) {
            isExit = true;
            Toast.makeText(getApplicationContext(), "再按一次退出选座",
                    Toast.LENGTH_SHORT).show();
            // 利用handler延迟发送更改状态信息
            mHandler.sendEmptyMessageDelayed(0, 2000);
        } else {
            finish();
            System.exit(0);
        }
    }*/

    /*
     *  内存处理
     * */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
        iPresenter.onDetach();
    }

}