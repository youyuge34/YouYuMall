package com.example.yousheng.latte.wechat;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.yousheng.latte.net.RestClient;
import com.example.yousheng.latte.net.callback.IError;
import com.example.yousheng.latte.net.callback.IFailure;
import com.example.yousheng.latte.net.callback.ISuccess;
import com.example.yousheng.latte.util.log.LatteLogger;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;

/**
 * Created by yousheng on 17/7/20.
 */

public abstract class BaseWXEntryActivity extends BaseWXActivity {

    //用户登录成功后回调
    protected abstract void onSignInSuccess(String userInfo);

    //微信发送请求到第三方应用后的回调
    @Override
    public void onReq(BaseReq baseReq) {

    }

    //第三方应用发送请求到微信后的回调
    @Override
    public void onResp(BaseResp baseResp) {
        //获取code，能进行第一次请求，获取auth,为了获取用户信息的一个url
        final String code = ((SendAuth.Resp) baseResp).code;

        final StringBuilder authUrl = new StringBuilder();
        authUrl.append("https://api.weixin.qq.com/sns/oauth2/access_token?appid=")
                .append(LatteWeChat.APP_ID)
                .append("&secret=")
                .append(LatteWeChat.APP_SECRET)
                .append("&code=")
                .append(code)
                .append("&grant_type=authorization_code");

        LatteLogger.d("authUrl",authUrl.toString());
        getAuth(authUrl.toString());
    }


    private void getAuth(String authUrl) {
        new RestClient.Builder()
                .url(authUrl)
                .loader(this)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {

                        final JSONObject authObj = JSON.parseObject(response);
                        final String accessToken = authObj.getString("access_token");
                        final String openId = authObj.getString("openid");

                        final StringBuilder userInfoUrl = new StringBuilder();

                        //去获取真正的用户信息：头像，昵称
                        userInfoUrl
                                .append("https://api.weixin.qq.com/sns/userinfo?access_token=")
                                .append(accessToken)
                                .append("&openid=")
                                .append(openId)
                                .append("&lang=")
                                .append("zh_CN");

                        LatteLogger.d("userInfoUrl", userInfoUrl.toString());
                        getUserInfo(userInfoUrl.toString());

                    }
                })
                .build()
                .get();
    }

    private void getUserInfo(String s){
        new RestClient.Builder()
                .url(s)
                .loader(this)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        onSignInSuccess(response);
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {

                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {

                    }
                })
                .build()
                .get();
    }

}
