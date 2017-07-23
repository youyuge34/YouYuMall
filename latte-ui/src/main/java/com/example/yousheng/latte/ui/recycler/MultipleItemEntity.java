package com.example.yousheng.latte.ui.recycler;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.LinkedHashMap;

/** @function json数据的实例bean，但是数据存储在HashMap中，需要DataConverter解析创建此bean成为一个list
 * @function Created by yousheng on 17/7/22.
 */

public class MultipleItemEntity implements MultiItemEntity {

    private ReferenceQueue<LinkedHashMap<Object, Object>> mQueue = new ReferenceQueue<>();
    private LinkedHashMap<Object, Object> mHashMap = new LinkedHashMap<>();
    private SoftReference<LinkedHashMap<Object, Object>> mSoftReference
            = new SoftReference<LinkedHashMap<Object, Object>>(mHashMap, mQueue);

    private MultipleItemEntity(LinkedHashMap<Object, Object> map) {
        mSoftReference.get().putAll(map);
    }

    //控制列表每个item的样式
    @Override
    public int getItemType() {
        return (int) mSoftReference.get().get(MultipleFields.ITEM_TYPE);
    }

    @SuppressWarnings("unchecked")
    public final <T> T getValue(Object key) {
        return (T) mSoftReference.get().get(key);
    }

    public final LinkedHashMap<?, ?> getValues() {
        return mSoftReference.get();
    }

    public final MultipleItemEntity setField(Object key, Object value) {
        mSoftReference.get().put(key, value);
        return this;
    }


    //内部构建者
    public static class builder {
        private LinkedHashMap<Object, Object> mHashMap = new LinkedHashMap<>();

        public builder() {
            //先清除之前的数据
            mHashMap.clear();
        }

        public MultipleItemEntity.builder setItemType(int type) {
            mHashMap.put(MultipleFields.ITEM_TYPE, type);
            return this;
        }

        public MultipleItemEntity.builder setValue(Object key, Object value) {
            mHashMap.put(key, value);
            return this;
        }

        public MultipleItemEntity.builder setValues(LinkedHashMap<Object, Object> map) {
            mHashMap.putAll(map);
            return this;
        }

        public MultipleItemEntity build() {
            return new MultipleItemEntity(mHashMap);
        }

    }
}
