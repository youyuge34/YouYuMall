package com.example.yousheng.ec.main.index;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.yousheng.latte.ui.recycler.DataConverter;
import com.example.yousheng.latte.ui.recycler.ItemType;
import com.example.yousheng.latte.ui.recycler.MultipleFields;
import com.example.yousheng.latte.ui.recycler.MultipleItemEntity;

import java.util.ArrayList;

/** @function 首页数据转换的实现类
 * Created by yousheng on 17/7/23.
 */

public class IndexDataConverter extends DataConverter {
    @Override
    public ArrayList<MultipleItemEntity> convert() {
        final JSONArray array = JSON.parseObject(getJsonData()).getJSONArray("data");
        final int size = array.size();
        for (int i = 0; i < size; i++) {
            JSONObject data = array.getJSONObject(i);

            final String imageUrl = data.getString("imageUrl");
            final String text = data.getString("text");
            final int spanSize = data.getInteger("spanSize");
            final int id = data.getInteger("goodsId");
            final JSONArray banners = data.getJSONArray("banners");

            final ArrayList<String> bannerImages = new ArrayList<>();
            int type = 0;
            if (imageUrl == null && text != null) {
                type = ItemType.TEXT;
            } else if (imageUrl != null && text == null) {
                type = ItemType.IMAGE;
            } else if (imageUrl != null) {
                type = ItemType.TEXT_IMAGE;
            } else if (banners != null) {
                type = ItemType.BANNER;
                //Banner的初始化
                final int bannerSize = banners.size();
                for (int j = 0; j < bannerSize; j++) {
                    final String banner = banners.getString(j);
                    bannerImages.add(banner);
                }
            }

            final MultipleItemEntity entity = new MultipleItemEntity.builder()
                    .setValue(MultipleFields.ITEM_TYPE,type)
                    .setValue(MultipleFields.SPAN_SIZE,spanSize)
                    .setValue(MultipleFields.ID,id)
                    .setValue(MultipleFields.TEXT,text)
                    .setValue(MultipleFields.IMAGE_URL,imageUrl)
                    .setValue(MultipleFields.BANNERS,bannerImages)
                    .build();

            ENTITIES.add(entity);
        }
        return ENTITIES;
    }
}
