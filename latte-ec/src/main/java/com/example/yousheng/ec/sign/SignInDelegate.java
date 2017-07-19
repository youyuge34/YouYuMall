package com.example.yousheng.ec.sign;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.example.yousheng.ec.R;
import com.example.yousheng.ec.R2;
import com.example.yousheng.latte.delegates.LatteDelegate;

import butterknife.BindView;
import butterknife.OnClick;

/** @function 登陆界面
 * Created by yousheng on 17/7/19.
 */

public class SignInDelegate extends LatteDelegate {

    @BindView(R2.id.edit_sign_in_email)
    TextInputEditText mEmail = null;
    @BindView(R2.id.edit_sign_in_password)
    TextInputEditText mPassword = null;

    @OnClick(R2.id.icon_sign_in_wechat)
    void onClickWechat() {
        //微信登陆逻辑
        Toast.makeText(getContext(),"wechat login",Toast.LENGTH_SHORT).show();

    }


    @OnClick(R2.id.tv_link_sign_up)
    void onClickLink() {
        startWithPop(new SignUpDelegate());
    }

    @OnClick(R2.id.btn_sign_in)
    void onClickSignIn() {
        if(checkForm()){
            //登陆成功逻辑
            Toast.makeText(getContext(),"login",Toast.LENGTH_SHORT).show();


        }
    }

    private boolean checkForm() {
        final String email = mEmail.getText().toString();
        final String password = mPassword.getText().toString();

        boolean isPass = true;

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmail.setError("错误的邮箱格式");
            isPass = false;
        } else {
            mEmail.setError(null);
        }

        if (password.isEmpty() || password.length() < 6) {
            mPassword.setError("请填写至少6位数密码");
            isPass = false;
        } else {
            mPassword.setError(null);
        }

        return isPass;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_in;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
