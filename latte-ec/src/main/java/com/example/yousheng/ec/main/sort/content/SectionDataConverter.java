package com.example.yousheng.ec.main.sort.content;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @function 分类页面右侧列表的数据转换类
 * Created by 尤晟 on 2017-07-29.
 */

public class SectionDataConverter {

    final List<SectionBean> convert(String json){
        final List<SectionBean> dataList = new ArrayList<>();
        final JSONArray dataArray = JSON.parseObject(json).getJSONArray("data");

        final int size = dataArray.size();
        for (int i = 0; i < size; i++) {
            final JSONObject data = dataArray.getJSONObject(i);
            final int id = data.getInteger("id");
            final String title = data.getString("section");

            //添加title
            final SectionBean sectionBeanTitle = new SectionBean(true,title);
            sectionBeanTitle.setmId(id);
            sectionBeanTitle.setmIsMore(true);

            dataList.add(sectionBeanTitle);

            final JSONArray goods = data.getJSONArray("goods");
            //商品内容循环解析
            final  int goodSize = goods.size();
            for (int j =0;j<goodSize;j++){
                final JSONObject contentItem = goods.getJSONObject(j);
                final int goodsId = contentItem.getInteger("goods_id");
                final String goodsName = contentItem.getString("goods_name");
                final String goodsThumb = contentItem.getString("goods_thumb");
                //获取内容
                final SectionContentItemEntity itemEntity = new SectionContentItemEntity();
                itemEntity.setmGoodsId(goodsId);
                itemEntity.setmGoodsName(goodsName);
                itemEntity.setmGoodsThumb(goodsThumb);
                //添加内容
                dataList.add(new SectionBean(itemEntity));
            }
            //商品内容循环结束
        }
        //Section循环结束

        return dataList;
    }
}
