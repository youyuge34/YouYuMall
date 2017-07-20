package com.example.yousheng.youyumall.generators;

import com.example.annotations.PayEntryGenerator;
import com.example.yousheng.latte.wechat.templates.WXPayEntryTemplate;

/**
 * Created by yousheng on 17/7/20.
 */
@SuppressWarnings("unused")
@PayEntryGenerator(
        packageName = "com.example.yousheng.youyumall",
        payEntryTemplate = WXPayEntryTemplate.class
)
public interface WeChatPayEntry {
}
