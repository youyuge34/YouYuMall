package com.example.yousheng.latte.ui.recycler;

import com.google.auto.value.AutoValue;

/**
 * @function 使用google的AutoValue,自动生成对应的类，重写很多方法
 * Created by yousheng on 17/7/24.
 */

@AutoValue
public abstract class RgbValue  {

    public abstract int red();

    public abstract int green();

    public abstract int blue();

    public static final RgbValue create(int red,int green,int blue){
        return new AutoValue_RgbValue(red,green,blue);
    }

}
