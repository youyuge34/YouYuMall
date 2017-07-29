package com.example.yousheng.ec.main.sort.list;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.example.yousheng.ec.R;
import com.example.yousheng.ec.main.sort.SortDelegate;
import com.example.yousheng.latte.ui.recycler.ItemType;
import com.example.yousheng.latte.ui.recycler.MultipleFields;
import com.example.yousheng.latte.ui.recycler.MultipleItemEntity;
import com.example.yousheng.latte.ui.recycler.MultipleRecyclerAdapter;
import com.example.yousheng.latte.ui.recycler.MultipleViewHolder;

import java.util.List;

/**
 * @function 分类页面的左侧recyclerview的adapter
 * Created by 尤晟 on 2017-07-29.
 */

public class SortRecyclerAdapter extends MultipleRecyclerAdapter {
    private final SortDelegate DELEGATE;
    private int mPressPos = 0;

    protected SortRecyclerAdapter(List<MultipleItemEntity> data, SortDelegate delegate) {
        super(data);
        this.DELEGATE = delegate;
        //添加垂直菜单布局
        addItemType(ItemType.VERTICAL_MENU_LIST, R.layout.item_vertical_menu_list);
    }

    @Override
    protected void convert(final MultipleViewHolder holder, final MultipleItemEntity item) {
        super.convert(holder, item);
        switch (holder.getItemViewType()) {
            case ItemType.VERTICAL_MENU_LIST:
                final String text = item.getValue(MultipleFields.TEXT);
                holder.setText(R.id.tv_vertical_item_name,text);

                final boolean isClicked = item.getValue(MultipleFields.TAG);
                final AppCompatTextView name = holder.getView(R.id.tv_vertical_item_name);
                final View line = holder.getView(R.id.view_line);
                final View itemView = holder.itemView;
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                         final int currentPos = holder.getAdapterPosition();
                        if(mPressPos != currentPos){
                            //还原上一个
                            getData().get(mPressPos).setField(MultipleFields.TAG,false);
                            notifyItemChanged(mPressPos);

                            //更新选中的当前item
                            item.setField(MultipleFields.TAG,true);
                            notifyItemChanged(currentPos);
                            mPressPos = currentPos;

                            final int contentId = getData().get(currentPos).getValue(MultipleFields.ID);
                        }
                    }
                });

                if (!isClicked) {
                    line.setVisibility(View.INVISIBLE);
                    name.setTextColor(ContextCompat.getColor(mContext, R.color.we_chat_black));
                    itemView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.item_background));
                }else {
                    line.setVisibility(View.VISIBLE);
                    name.setTextColor(ContextCompat.getColor(mContext, R.color.app_main));
                    line.setBackgroundColor(ContextCompat.getColor(mContext, R.color.app_main));
                    itemView.setBackgroundColor(Color.WHITE);
                }

                break;
            default:
                break;


        }
    }
}
