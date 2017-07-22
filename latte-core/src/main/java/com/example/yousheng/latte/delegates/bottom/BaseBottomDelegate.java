package com.example.yousheng.latte.delegates.bottom;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.yousheng.latte.R;
import com.example.yousheng.latte.R2;
import com.example.yousheng.latte.delegates.LatteDelegate;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import butterknife.BindView;

/**
 * @function 主界面的容器基类
 * Created by yousheng on 17/7/21.
 */

public abstract class BaseBottomDelegate extends LatteDelegate implements View.OnClickListener{
    private final ArrayList<BottomTabBean> TAB_BEANS = new ArrayList<>();
    private final ArrayList<BottomItemDelegate> ITEM_DELEGATES = new ArrayList<>();
    private final LinkedHashMap<BottomTabBean, BottomItemDelegate> ITEMS = new LinkedHashMap<>();

    private int mCurrentDelegate = 0;
    private int mIndexDelegate = 0;
    private int mClickedColor = Color.RED;

    @BindView(R2.id.bottom_bar)
    LinearLayoutCompat mBottomBar = null;


    //给LinkedHashMap赋值
    public abstract LinkedHashMap<BottomTabBean, BottomItemDelegate> setItems(ItemBuilder builder);


    public abstract int setIndexDelegate();

    @ColorInt
    public abstract int setClickedColor();

    @Override
    public Object setLayout() {
        return R.layout.delegate_bottom;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化变量
        mIndexDelegate = setIndexDelegate();
        mCurrentDelegate = mIndexDelegate;
        if (setClickedColor() != 0) {
            mClickedColor = setClickedColor();
        }

        //在抽象方法中传入builder，在子类中用builer构建，取出映射关系,加入此类的ITEMS
        final ItemBuilder builder = ItemBuilder.builder();
        final LinkedHashMap<BottomTabBean, BottomItemDelegate> items = setItems(builder);
        ITEMS.putAll(items);

        for (Map.Entry<BottomTabBean, BottomItemDelegate> entry : ITEMS.entrySet()) {
            final BottomTabBean key = entry.getKey();
            final BottomItemDelegate value = entry.getValue();
            TAB_BEANS.add(key);
            ITEM_DELEGATES.add(value);
        }
    }


    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        int size = ITEMS.size();
        for (int i = 0; i < size; i++) {
            //填充图标文字到底部栏中
            LayoutInflater.from(getContext()).inflate(R.layout.bottom_item_icon_text_layout,mBottomBar);
            //取出单个图标布局
            RelativeLayout item = (RelativeLayout) mBottomBar.getChildAt(i);
            //设置每个item的点击事件
            item.setTag(i);
            item.setOnClickListener(this);
            //获取图标中的图片和文字
            final IconTextView itemIcon = (IconTextView) item.getChildAt(0);
            final AppCompatTextView itemText = (AppCompatTextView) item.getChildAt(1);
            final BottomTabBean bean = TAB_BEANS.get(i);

            //初始化数据
            itemIcon.setText(bean.getIcon());
            itemText.setText(bean.getTitle());
            if (i == mIndexDelegate) {
                itemIcon.setTextColor(mClickedColor);
                itemText.setTextColor(mClickedColor);
            }
        }
    }

    //点击图标后，重置所有颜色为初始色
    private void resetColor() {
        final int count = mBottomBar.getChildCount();
        for (int i = 0; i < count; i++) {
            final RelativeLayout item = (RelativeLayout) mBottomBar.getChildAt(i);
            final IconTextView itemIcon = (IconTextView) item.getChildAt(0);
            itemIcon.setTextColor(Color.GRAY);
            final AppCompatTextView itemTitle = (AppCompatTextView) item.getChildAt(1);
            itemTitle.setTextColor(Color.GRAY);
        }
    }

    @Override
    public void onClick(View v) {
      final int tag = (int) v.getTag();
        //先重置颜色后再变色
        resetColor();
        final RelativeLayout item = (RelativeLayout) v;
        final IconTextView itemIcon = (IconTextView) item.getChildAt(0);
        itemIcon.setTextColor(mClickedColor);
        final AppCompatTextView itemTitle = (AppCompatTextView) item.getChildAt(1);
        itemTitle.setTextColor(mClickedColor);
        getSupportDelegate().showHideFragment(ITEM_DELEGATES.get(tag), ITEM_DELEGATES.get(mCurrentDelegate));
        //注意先后顺序
        mCurrentDelegate = tag;
    }
}
