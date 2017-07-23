package com.example.yousheng.youyumall;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.widget.Toast;

import com.example.yousheng.ec.launcher.LauncherDelegate;
import com.example.yousheng.ec.main.EcBottomDelegate;
import com.example.yousheng.ec.sign.ISignListener;
import com.example.yousheng.ec.sign.SignInDelegate;
import com.example.yousheng.latte.activities.ProxyActivity;
import com.example.yousheng.latte.app.Latte;
import com.example.yousheng.latte.delegates.LatteDelegate;
import com.example.yousheng.latte.ui.launcher.ILauncherListener;
import com.example.yousheng.latte.ui.launcher.OnLauncherFinishTag;

public class ExampleActivity extends ProxyActivity
        implements ISignListener,ILauncherListener{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.hide();
        }
        Latte.getConfigurator().withActivity(this);
    }

    @Override
    public LatteDelegate setRootDelegate() {
        return new LauncherDelegate();
    }

    //登录、注册成功后的回调方法
    @Override
    public void onSignInSuccess() {
        Toast.makeText(this,"login ok",Toast.LENGTH_SHORT).show();
        startWithPop(new EcBottomDelegate());
    }

    @Override
    public void onSignUpSuccess() {
        Toast.makeText(this,"register ok",Toast.LENGTH_SHORT).show();
        startWithPop(new EcBottomDelegate());
    }

    //倒计时结束的回调方法
    @Override
    public void onLauncherFinish(OnLauncherFinishTag tag) {
        switch (tag){
            case SIGNED:
                Toast.makeText(this,"用户已经登录",Toast.LENGTH_SHORT).show();
                startWithPop(new EcBottomDelegate());
                break;
            case NOT_SIGNED:
                Toast.makeText(this,"用户未登录",Toast.LENGTH_SHORT).show();
                startWithPop(new SignInDelegate());
                break;
            default:
                break;
        }
    }
}
