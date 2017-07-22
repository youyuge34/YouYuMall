package com.example.yousheng.ec.main.index;

import android.graphics.Color;

import com.example.yousheng.ec.main.sort.SortDelegate;
import com.example.yousheng.latte.delegates.bottom.BaseBottomDelegate;
import com.example.yousheng.latte.delegates.bottom.BottomItemDelegate;
import com.example.yousheng.latte.delegates.bottom.BottomTabBean;
import com.example.yousheng.latte.delegates.bottom.ItemBuilder;

import java.util.LinkedHashMap;

/**
 * @function 详情页面的实现类
 * Created by yousheng on 17/7/22.
 */

public class EcBottomDelegate extends BaseBottomDelegate {

    //通过builder创建一个hashmap，关联的是底部图标和上面的子页面
    @Override
    public LinkedHashMap<BottomTabBean, BottomItemDelegate> setItems(ItemBuilder builder) {
        final LinkedHashMap<BottomTabBean, BottomItemDelegate> map = new LinkedHashMap<>();
        map.put(new BottomTabBean("{fa-home}", "主页"), new IndexDelegate());
        map.put(new BottomTabBean("{fa-sort}", "分类"), new SortDelegate());
        map.put(new BottomTabBean("{fa-compass}", "发现"), new SortDelegate());
        map.put(new BottomTabBean("{fa-shopping-cart}", "购物车"), new SortDelegate());
        map.put(new BottomTabBean("{fa-user}", "我的"), new SortDelegate());

        return builder.addItems(map).build();
    }

    @Override
    public int setIndexDelegate() {
        return 0;
    }

    @Override
    public int setClickedColor() {
        return Color.parseColor("#ffff8800");
    }
}
