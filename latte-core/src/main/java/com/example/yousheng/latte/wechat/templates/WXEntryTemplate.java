package com.example.yousheng.latte.wechat.templates;

import com.example.yousheng.latte.wechat.BaseWXEntryActivity;
import com.example.yousheng.latte.wechat.LatteWeChat;

/** @function 模板类
 * Created by yousheng on 17/7/20.
 */

public class WXEntryTemplate extends BaseWXEntryActivity {


    @Override
    protected void onResume() {
        super.onResume();
        finish();
        overridePendingTransition(0, 0);
    }

    @Override
    protected void onSignInSuccess(String userInfo) {
        LatteWeChat.getInstance().getSignInCallback().onSignInSuccess(userInfo);
    }
}
