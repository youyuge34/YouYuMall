package com.example.yousheng.ec.main.sort.content;

/** @function 分类右侧列表页一个页面Bean中的图片文字子Bean
 * Created by 尤晟 on 2017-07-29.
 */

public class SectionContentItemEntity {
    private int mGoodsId = 0;
    private String mGoodsName = null;
    //缩略图
    private String mGoodsThumb = null;

    public int getmGoodsId() {
        return mGoodsId;
    }

    public void setmGoodsId(int mGoodsId) {
        this.mGoodsId = mGoodsId;
    }

    public String getmGoodsName() {
        return mGoodsName;
    }

    public void setmGoodsName(String mGoodsName) {
        this.mGoodsName = mGoodsName;
    }

    public String getmGoodsThumb() {
        return mGoodsThumb;
    }

    public void setmGoodsThumb(String mGoodsThumb) {
        this.mGoodsThumb = mGoodsThumb;
    }
}
