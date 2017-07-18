package com.example.yousheng.latte.util.timer;

import java.util.TimerTask;

/**
 * Created by yousheng on 17/7/18.
 */

public class BaseTimerTask extends TimerTask {

    ITimerListener listener = null;

    public BaseTimerTask(ITimerListener listener) {
        this.listener = listener;
    }

    @Override
    public void run() {
        if (listener != null) {
            listener.onTimer();
        }
    }
}
