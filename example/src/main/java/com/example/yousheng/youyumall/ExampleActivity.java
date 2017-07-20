package com.example.yousheng.youyumall;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.widget.Toast;

import com.example.yousheng.ec.sign.ISignListener;
import com.example.yousheng.ec.sign.SignInDelegate;
import com.example.yousheng.latte.activities.ProxyActivity;
import com.example.yousheng.latte.app.Latte;
import com.example.yousheng.latte.delegates.LatteDelegate;

public class ExampleActivity extends ProxyActivity implements ISignListener{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.hide();
        }
    }

    @Override
    public LatteDelegate setRootDelegate() {
        return new SignInDelegate();
    }

    //登录、注册成功后的回调方法
    @Override
    public void onSignInSuccess() {
        Toast.makeText(Latte.getAppContext(),"login ok",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSignUpSuccess() {
        Toast.makeText(Latte.getAppContext(),"register ok",Toast.LENGTH_SHORT).show();
    }
}
