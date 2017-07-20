package com.example.yousheng.youyumall.generators;

import com.example.annotations.AppRegisterGenerator;
import com.example.yousheng.latte.wechat.templates.AppRegisterTemplate;

/**
 * Created by yousheng on 17/7/20.
 */

@SuppressWarnings("unused")
@AppRegisterGenerator(
        packageName = "com.example.yousheng.youyumall",
        registerTemplate = AppRegisterTemplate.class
)
public interface AppRegister {
}
