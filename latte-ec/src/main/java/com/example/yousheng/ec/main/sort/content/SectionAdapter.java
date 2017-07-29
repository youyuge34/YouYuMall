package com.example.yousheng.ec.main.sort.content;

import android.support.v7.widget.AppCompatImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.yousheng.ec.R;

import java.util.List;

/**
 * @function 分类页面右侧的列表adapter
 * Created by 尤晟 on 2017-07-29.
 */

public class SectionAdapter extends BaseSectionQuickAdapter<SectionBean, BaseViewHolder> {

    public SectionAdapter(int layoutResId, int sectionHeadResId, List<SectionBean> data) {
        super(layoutResId, sectionHeadResId, data);
    }

    //对于头数据的转化
    @Override
    protected void convertHead(BaseViewHolder helper, SectionBean item) {
        helper.setText(R.id.header, item.header);
        helper.setVisible(R.id.more, item.ismIsMore());
    }

    //对于商品goods的数据转换
    @Override
    protected void convert(BaseViewHolder helper, SectionBean item) {
        //item.t返回SectionContentItemEntity类型
        final String thumb = item.t.getmGoodsThumb();
        final String name = item.t.getmGoodsName();
        final int goodsId = item.t.getmGoodsId();
        final SectionContentItemEntity entity = item.t;
        helper.setText(R.id.tv, name);
        final AppCompatImageView goodsImageView = helper.getView(R.id.iv);
        Glide.with(mContext).load(thumb).diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate().into(goodsImageView);
    }
}
