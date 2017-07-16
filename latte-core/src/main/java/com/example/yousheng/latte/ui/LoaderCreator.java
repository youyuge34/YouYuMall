package com.example.yousheng.latte.ui;

import android.content.Context;

import com.wang.avi.AVLoadingIndicatorView;
import com.wang.avi.Indicator;

import java.util.WeakHashMap;

/**
 * @function 缓存创建loading的对象
 * Created by yousheng on 17/7/16.
 */

public final class LoaderCreator {

    private static final WeakHashMap<String, Indicator> LOADING_MAP = new WeakHashMap<>();

    public static final AVLoadingIndicatorView create(String type, Context context) {

        AVLoadingIndicatorView avLoadingIndicatorView = new AVLoadingIndicatorView(context);

        if (LOADING_MAP.get(type) == null) {
            avLoadingIndicatorView.setIndicator(type);
            LOADING_MAP.put(type, avLoadingIndicatorView.getIndicator());
        } else {
            avLoadingIndicatorView.setIndicator(LOADING_MAP.get(type));
        }

        return avLoadingIndicatorView;
    }
}
