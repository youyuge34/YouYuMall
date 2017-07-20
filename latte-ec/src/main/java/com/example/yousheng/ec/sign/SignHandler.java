package com.example.yousheng.ec.sign;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.yousheng.ec.database.DatabaseManager;
import com.example.yousheng.ec.database.UserProfile;
import com.example.yousheng.latte.app.AccountManager;
import com.example.yousheng.latte.util.log.LatteLogger;

/**
 * @function 登陆注册，返回用户数据后写入数据库greenDao
 * Created by yousheng on 17/7/19.
 */

public class SignHandler {

    //解析数据存入数据库
    public static void onSignUp(String response, ISignListener mSignListener) {
        JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
        final long userId = profileJson.getLong("userId");
        final String name = profileJson.getString("name");
        final String avatar = profileJson.getString("avatar");
        final String gender = profileJson.getString("gender");
        final String address = profileJson.getString("address");

        UserProfile userProfile = new UserProfile(userId, name, avatar, gender, address);
        long tempId = DatabaseManager.getInstance().getUserDao().insert(userProfile);
        LatteLogger.d("tempId: " + tempId);

        //已经注册并且成功登录了
        AccountManager.setSignState(true);
        mSignListener.onSignUpSuccess();
    }

    //解析数据存入数据库
    public static void onSignIn(String response, ISignListener mSignListener) {
        JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
        final long userId = profileJson.getLong("userId");
        final String name = profileJson.getString("name");
        final String avatar = profileJson.getString("avatar");
        final String gender = profileJson.getString("gender");
        final String address = profileJson.getString("address");

        UserProfile userProfile = new UserProfile(userId, name, avatar, gender, address);
        long tempId = DatabaseManager.getInstance().getUserDao().insert(userProfile);
        LatteLogger.d("tempId: " + tempId);

        //成功登录了
        AccountManager.setSignState(true);
        mSignListener.onSignInSuccess();
    }

}
