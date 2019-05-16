package com.bw.movie.zjh.module.ui.cinema;

import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.ActionBar;
import android.view.Gravity;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.zjh.module.base.BaseActivity;
import com.bw.movie.zjh.module.ui.cinema.seak.SeatTable;
import com.bw.movie.zjh.module.utils.mvp.presenter.IPresenterImpl;
import com.bw.movie.zjh.module.utils.mvp.view.IView;
import com.bw.movie.zjh.module.utils.statusbar.StatusBarWindowTop;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ChooseSeatBuyActivity extends BaseActivity implements IView {

    private IPresenterImpl iPresenter;
    private Unbinder bind;
    @BindView(R.id.seat_Table)  //座位View
            SeatTable seat_Table;
    private PopupWindow mPop;
    private RadioButton radio_weixin;   //微信支付
    private RadioButton radio_zifubao;  //支付宝支付
    private RelativeLayout pay_submit;  //提交支付
    private TextView type_priceAll;     //消费总价

    @Override
    public int bindLayout() {
        return R.layout.activity_choose_seat_buy;
    }

    @Override
    protected void initView() {
        //透明状态栏
        StatusBarWindowTop.setStatusBarFullTransparent(this);

        bind = ButterKnife.bind(this);
        iPresenter = new IPresenterImpl(this);
        initSeat();
        payChooseType();
    }

    /*
     * 初始化数据
     * */
    @Override
    protected void initData() {

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
                mPop.showAtLocation(v, Gravity.BOTTOM, 0, 0);
                break;
            case R.id.no:
                break;
            default:
                break;
        }
    }



    /*
     * 支付选择的popwindow
     * 微信，支付宝
     * */
    private void payChooseType() {
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
    }

    /*
     *  回调函数
     * */
    @Override
    public void viewDataSuccess(Object data) {

    }


    /*
     *  电影院选座
     * */
    private void initSeat() {
        seat_Table.setScreenName("8号厅荧幕");//设置屏幕名称
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
     *  内存处理
     * */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
        iPresenter.onDetach();
    }

}
