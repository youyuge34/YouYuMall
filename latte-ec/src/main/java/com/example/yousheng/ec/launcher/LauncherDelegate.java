package com.example.yousheng.ec.launcher;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.example.yousheng.ec.R;
import com.example.yousheng.ec.R2;
import com.example.yousheng.latte.app.AccountManager;
import com.example.yousheng.latte.app.IUserChecker;
import com.example.yousheng.latte.delegates.LatteDelegate;
import com.example.yousheng.latte.ui.launcher.ILauncherListener;
import com.example.yousheng.latte.ui.launcher.OnLauncherFinishTag;
import com.example.yousheng.latte.ui.launcher.ScrollLauncherTag;
import com.example.yousheng.latte.util.storage.LattePreference;
import com.example.yousheng.latte.util.timer.BaseTimerTask;
import com.example.yousheng.latte.util.timer.ITimerListener;

import java.util.Timer;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @function 启动页
 * Created by yousheng on 17/7/18.
 */

public class LauncherDelegate extends LatteDelegate implements ITimerListener {

    @BindView(R2.id.tv_launcher_timer)
    AppCompatTextView mTvTimer = null;

    private Timer mTimer = null;
    private int mCount = 5;
    private ILauncherListener mILauncherListener = null;

    @OnClick(R2.id.tv_launcher_timer)
    void onTimeClick() {
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
            checkIsShow();
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ILauncherListener) {
            mILauncherListener = (ILauncherListener) activity;
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_launcher;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initTimer();
    }

    private void initTimer() {
        BaseTimerTask timerTask = new BaseTimerTask(this);
        mTimer = new Timer();
        mTimer.schedule(timerTask, 0, 1000);
    }

    //判断是否启动引导页
    private void checkIsShow() {
        if (!LattePreference.getAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name())) {
            startWithPop(new LauncherScrollDelegate());
        } else {
            //检查用户是否登录
            AccountManager.checkAccount(new IUserChecker() {
                @Override
                public void onSignIn() {
                    //倒计时结束的回调调用
                    if (mILauncherListener != null) {
                        mILauncherListener.onLauncherFinish(OnLauncherFinishTag.SIGNED);
                    }
                }

                @Override
                public void onNotSignIn() {
                    if (mILauncherListener != null) {
                        mILauncherListener.onLauncherFinish(OnLauncherFinishTag.NOT_SIGNED);
                    }
                }
            });

        }
    }

    @Override
    public void onTimer() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mTvTimer.setText(String.format("跳过\n%ds", mCount));
                mCount--;
                if (mCount < 0) {
                    if (mTimer != null) {
                        mTimer.cancel();
                        mTimer = null;

                        checkIsShow();
                    }
                }
            }
        });
    }
}
