package com.example.yousheng.latte.util;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.example.yousheng.latte.app.Latte;

/** @function 获取屏幕宽高
 * Created by yousheng on 17/7/16.
 */

public class DimenUtil {
    public static final int getScreenWidth() {
        Resources resources = Latte.getAppContext().getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;
    }

    public static final int getScreenHeight() {
        Resources resources = Latte.getAppContext().getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.heightPixels;
    }
}
