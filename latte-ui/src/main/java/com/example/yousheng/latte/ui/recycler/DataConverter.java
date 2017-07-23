package com.example.yousheng.latte.ui.recycler;

import java.util.ArrayList;

/**
 * @function 数据转换类, 把json转换,抽象基类
 * Created by yousheng on 17/7/22.
 */

public abstract class DataConverter {
    protected final ArrayList<MultipleItemEntity> ENTITIES = new ArrayList<>();
    private String mJsonData = null;

    public abstract ArrayList<MultipleItemEntity> convert();

    public DataConverter setJsonData(String json) {
        this.mJsonData = json;
        return this;
    }

    protected String getJsonData() {
        if (mJsonData == null || mJsonData.isEmpty()) {
            throw new NullPointerException("DATA IS NULL!");
        }
        return mJsonData;
    }

    public void clearData() {
        ENTITIES.clear();
    }
}
