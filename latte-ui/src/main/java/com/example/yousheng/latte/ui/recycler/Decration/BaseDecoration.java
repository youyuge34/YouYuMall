package com.example.yousheng.latte.ui.recycler.Decration;

import android.support.annotation.ColorInt;

import com.choices.divider.DividerItemDecoration;

/** @function 分割线装饰类，用recyclerView的addItemDecoration添加
 * Created by yousheng on 17/7/24.
 */

public class BaseDecoration extends DividerItemDecoration {
    private BaseDecoration(@ColorInt int color, int size){
        setDividerLookup(new DividerLookupImpl(color,size));
    }

    public static BaseDecoration create(int color,int size){
        return new BaseDecoration(color,size);
    }
}
