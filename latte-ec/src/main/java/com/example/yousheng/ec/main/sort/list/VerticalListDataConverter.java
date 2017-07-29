package com.example.yousheng.ec.main.sort.list;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.yousheng.latte.ui.recycler.DataConverter;
import com.example.yousheng.latte.ui.recycler.ItemType;
import com.example.yousheng.latte.ui.recycler.MultipleFields;
import com.example.yousheng.latte.ui.recycler.MultipleItemEntity;

import java.util.ArrayList;

/**
 * @function 分类菜单左侧的数据转换类
 * Created by 尤晟 on 2017-07-29.
 */

public final class VerticalListDataConverter extends DataConverter {

    @Override
    public ArrayList<MultipleItemEntity> convert() {
        final JSONArray dataArray = JSON
                .parseObject(getJsonData())
                .getJSONObject("data")
                .getJSONArray("list");

        final int size = dataArray.size();
        for (int i = 0; i < size; i++) {
            final JSONObject data = dataArray.getJSONObject(i);
            final int id = data.getInteger("id");
            final String name = data.getString("name");

            final MultipleItemEntity entity = new MultipleItemEntity.builder()
                    .setValue(MultipleFields.ID, id)
                    .setValue(MultipleFields.TEXT, name)
                    .setValue(MultipleFields.ITEM_TYPE, ItemType.VERTICAL_MENU_LIST)
                    .setValue(MultipleFields.TAG, false)
                    .build();

            ENTITIES.add(entity);
        }
        //设置第一个被选中
        if(size>0){
            ENTITIES.get(0).setField(MultipleFields.TAG, true);
        }
        return ENTITIES;
    }
}
