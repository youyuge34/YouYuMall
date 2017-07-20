package com.example.yousheng.latte.app;

/** @function "判断"用户是否登录的接口回调
 * Created by yousheng on 17/7/20.
 */

public interface IUserChecker {

    void onSignIn();

    void onNotSignIn();
}
