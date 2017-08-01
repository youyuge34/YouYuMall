package com.example.yousheng.latte.delegates.web.event;

import com.example.yousheng.latte.util.log.LatteLogger;

/**
 * Created by 尤晟 on 2017-08-01.
 */

public class UndefineEvent extends Event {
    @Override
    public String execute(String params) {
        LatteLogger.e("UndefineEvent",params);
        return null;
    }
}
