package com.example.yousheng.ec.main.sort.content;

import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * @function 分类右侧列表的Bean，一个Bean代表一个页面，包含n个子SectionContentItemEntity的Bean
 * Created by 尤晟 on 2017-07-29.
 */

public class SectionBean extends SectionEntity<SectionContentItemEntity> {

    private boolean mIsMore = false;
    private  int mId = -1;

    public SectionBean(SectionContentItemEntity sectionContentItemEntity) {
        super(sectionContentItemEntity);
    }

    public SectionBean(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public boolean ismIsMore() {
        return mIsMore;
    }

    public void setmIsMore(boolean mIsMore) {
        this.mIsMore = mIsMore;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }
}
