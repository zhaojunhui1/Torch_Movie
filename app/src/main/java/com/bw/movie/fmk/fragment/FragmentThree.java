package com.bw.movie.fmk.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.fmk.activity.LogActivity;
import com.bw.movie.fmk.base.BasefFragment;
import com.bw.movie.zjh.module.base.BaseFragment;
import com.bw.movie.zjh.module.beans.my.MyUserSignInBean;
import com.bw.movie.zjh.module.ui.cinema.my.MyBackMessageActivity;
import com.bw.movie.zjh.module.ui.cinema.my.MyFoodedActivity;
import com.bw.movie.zjh.module.ui.cinema.my.MyLookActivity;
import com.bw.movie.zjh.module.ui.cinema.my.MyPresonActivity;
import com.bw.movie.zjh.module.ui.cinema.my.MyRemindActivity;
import com.bw.movie.zjh.module.utils.mvp.presenter.IPresenterImpl;
import com.bw.movie.zjh.module.utils.mvp.util.Apis;
import com.bw.movie.zjh.module.utils.mvp.view.IView;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: 付明锟
 * @Date: 2019/5/12 18:42
 * @Description:
 */
public class FragmentThree extends BaseFragment implements IView, View.OnClickListener {

    private IPresenterImpl iPresenter;

    @Override
    protected int setView() {
        return R.layout.fragment_three;
    }

    @Override
    protected void init(View view) {
        iPresenter = new IPresenterImpl(this);
        view.findViewById(R.id.my_remind).setOnClickListener(this);
        view.findViewById(R.id.my_save_image).setOnClickListener(this);
        view.findViewById(R.id.my_sign_in).setOnClickListener(this);
        view.findViewById(R.id.my_preson).setOnClickListener(this);
        view.findViewById(R.id.my_look).setOnClickListener(this);
        view.findViewById(R.id.my_fooded).setOnClickListener(this);
        view.findViewById(R.id.my_back_message).setOnClickListener(this);
        view.findViewById(R.id.my_newapp).setOnClickListener(this);
        view.findViewById(R.id.my_unlogin).setOnClickListener(this);

    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    /*
     *   点击事件
     * */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.my_remind:      //系统消息
                startActivity(new Intent(getActivity(), MyRemindActivity.class));
                break;
            case R.id.my_save_image:  //上传头像

                break;
            case R.id.my_sign_in:     // 签到
                initUserSignIn();
                break;
            case R.id.my_preson:      // 我的资料
                startActivity(new Intent(getActivity(), MyPresonActivity.class));
                break;
            case R.id.my_look:        //我的关注
                startActivity(new Intent(getActivity(), MyLookActivity.class));
                break;
            case R.id.my_fooded:      //购票记录
                startActivity(new Intent(getActivity(), MyFoodedActivity.class));
                break;
            case R.id.my_back_message:// 意见反馈
                startActivity(new Intent(getActivity(), MyBackMessageActivity.class));
                break;
            case R.id.my_newapp:       // 最新版本

                break;
            case R.id.my_unlogin:      //退出登录
                Intent intent2 = new Intent(getActivity(), LogActivity.class);
                intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent2);
                break;
            default:
                break;
        }
    }

    /*
     *   用户签到
     *  */
    private void initUserSignIn() {
        Map<String, String> map1 = new HashMap<>();
        iPresenter.getPresenterData(Apis.MY_USER_SIGNIN_GET, map1, MyUserSignInBean.class);
    }

    /*
     *   回调函数
     * */
    @Override
    public void viewDataSuccess(Object data) {
        if (data instanceof MyUserSignInBean) {
            MyUserSignInBean signInBean = (MyUserSignInBean) data;
            Toast.makeText(getActivity(), signInBean.getMessage(), Toast.LENGTH_SHORT).show();

        }


    }

    /*
    *  内存处理
    * */

    @Override
    public void onDestroy() {
        super.onDestroy();
        iPresenter.onDetach();
    }

}
