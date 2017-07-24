package com.example.yousheng.latte.ui.recycler.Decration;

import com.choices.divider.Divider;
import com.choices.divider.DividerItemDecoration;

/**
 * @function 可以自定义颜色，和分隔线粗细
 * if mRecyclerView used mGridLayoutManager, you must Override both of them.
 * Created by yousheng on 17/7/24.
 */

public class DividerLookupImpl implements DividerItemDecoration.DividerLookup {

    private int color;
    private int size;

    public DividerLookupImpl(int color, int size) {
        this.color = color;
        this.size = size;
    }

    @Override
    public Divider getVerticalDivider(int position) {
        return new Divider.Builder()
                .color(color)
                .size(size)
                .build();
    }

    @Override
    public Divider getHorizontalDivider(int position) {
        return new Divider.Builder()
                .color(color)
                .size(size)
                .build();
    }
}
