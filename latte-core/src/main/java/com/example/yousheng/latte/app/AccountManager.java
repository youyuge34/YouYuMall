package com.example.yousheng.latte.app;

import com.example.yousheng.latte.util.storage.LattePreference;

/**
 * @function 管理用户登录
 * Created by yousheng on 17/7/20.
 */

public class AccountManager {


    private enum SignTag {
        SIGN_TAG
    }

    //设置用户登录状态，登陆后调用
    public static void setSignState(boolean state) {
        LattePreference.setAppFlag(SignTag.SIGN_TAG.name(), state);
    }

    //检查登录状态，并执行相关回调
    public static void checkAccount(IUserChecker iUserChecker) {
        if (isSignIn()) {
            iUserChecker.onSignIn();
        } else {
            iUserChecker.onNotSignIn();
        }
    }

    private static boolean isSignIn() {
        return LattePreference.getAppFlag(SignTag.SIGN_TAG.name());
    }

}
