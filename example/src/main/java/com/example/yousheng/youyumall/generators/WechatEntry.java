package com.example.yousheng.youyumall.generators;

import com.example.annotations.EntryGenerator;
import com.example.yousheng.latte.wechat.templates.WXEntryTemplate;

/**
 * Created by yousheng on 17/7/20.
 */

@SuppressWarnings("unused")
@EntryGenerator(
        packageName = "com.example.yousheng.youyumall",
        entryTemplate = WXEntryTemplate.class
)
public interface WechatEntry {
}
