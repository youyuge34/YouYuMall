package com.example.yousheng.latte.delegates.web.event;

import android.support.annotation.NonNull;

import java.util.HashMap;

/**
 * @function 事件的管理类，规定好一些js里的命令，
 * 代表要执行的Android端的一些事件，这些事件在application初始化的时候就添加进去，
 * 格式为 'action':'规定好的事件命令',比如执行'action':'test'代表了执行TestEvent
 * Created by 尤晟 on 2017-08-01.
 */

public class EventManager {

    private static final HashMap<String, Event> EVENTS = new HashMap<>();

    private EventManager() {
    }

    //惰性单例模式
    private static class Holder {
        private static final EventManager INSTANCE = new EventManager();
    }

    public static EventManager getInstance() {
        return Holder.INSTANCE;
    }

    //在初始化application中添加进对应的事件
    public EventManager addEvent(@NonNull String name, @NonNull Event event) {
        EVENTS.put(name, event);
        return this;
    }

    //获取事件，若不存在事件，则返回UndefineEvent
    public Event getEvent(@NonNull String action) {
        final Event event = EVENTS.get(action);
        if (event == null) {
            return new UndefineEvent();
        }
        return event;
    }

}
