package com.example.yousheng.latte.wechat;

import android.app.Activity;

import com.example.yousheng.latte.app.ConfigKeys;
import com.example.yousheng.latte.app.Latte;
import com.example.yousheng.latte.wechat.callbacks.IWeChatSignInCallback;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * Created by yousheng on 17/7/20.
 */

public class LatteWeChat  {
    public static final String APP_ID = Latte.getLatteConfiguration(ConfigKeys.WE_CHAT_APP_ID);
    public static final String APP_SECRET = Latte.getLatteConfiguration(ConfigKeys.WE_CHAT_APP_SECRET);
    private final IWXAPI WXAPI;
    private IWeChatSignInCallback mSignInCallback = null;

    private static final class Holder {
        private static final LatteWeChat INSTANCE = new LatteWeChat();
    }

    public static LatteWeChat getInstance() {
        return Holder.INSTANCE;
    }

    private LatteWeChat() {
        final Activity activity = Latte.getLatteConfiguration(ConfigKeys.ACTIVITY);
        WXAPI = WXAPIFactory.createWXAPI(activity, APP_ID, true);
        WXAPI.registerApp(APP_ID);
    }


    public final IWXAPI getWXAPI() {
        return WXAPI;
    }

    public LatteWeChat onSignSuccess(IWeChatSignInCallback callback) {
        this.mSignInCallback = callback;
        return this;
    }

    public IWeChatSignInCallback getSignInCallback() {
        return mSignInCallback;
    }


    public final void signIn() {
        final SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "random_state";
        WXAPI.sendReq(req);
    }


}
