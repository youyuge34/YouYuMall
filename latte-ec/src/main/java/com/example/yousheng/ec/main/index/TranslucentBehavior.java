package com.example.yousheng.ec.main.index;

import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;

import com.example.yousheng.ec.R;
import com.example.yousheng.latte.ui.recycler.RgbValue;

/**
 * @function CoordinatorLayout布局中toolbar的behavior，依赖于recyclerView
 * Created by yousheng on 17/7/24.
 */

//通过布局文件，反射，获取具体完整类名，实例化，然后处理
@SuppressWarnings("unused")
public class TranslucentBehavior extends CoordinatorLayout.Behavior<Toolbar> {

    //顶部距离
    private int mDistanceY = 0;
    //颜色变化速度
    private static final int SHOW_SPEED = 3;
    //定义变化的颜色,
    private final RgbValue RGB_VALUE = RgbValue.create(255, 124, 2);

    //一定要有两个参数的构造方法
    public TranslucentBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //依赖的那个view：recyclerView
    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, Toolbar child, View dependency) {
        return dependency.getId() == R.id.rv_index;
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, Toolbar child, View directTargetChild, View target, int nestedScrollAxes) {
        return true;
    }

    //child是依赖的对象，recyclerView是被依赖的对象
    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, Toolbar child, View target, int dx, int dy, int[] consumed) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed);
        //增加滑动距离
        mDistanceY += dy;
        //toolbar的高度
        final int targetHeight = child.getBottom();

        //若向下滑动，且滑动距离小于toolbar的高度，则调整渐变色
        if (mDistanceY > 0 && mDistanceY < targetHeight) {
            final float scale = (float) mDistanceY / targetHeight;
            final int alpha = (int) (scale * 255);
            child.setBackgroundColor(Color.argb(alpha,RGB_VALUE.red(),RGB_VALUE.green(),RGB_VALUE.blue()));
        }else if(mDistanceY > targetHeight){
            child.setBackgroundColor(Color.rgb(RGB_VALUE.red(),RGB_VALUE.green(),RGB_VALUE.blue()));
        }
    }
}
