package com.example.yousheng.latte.ui.refresh;

/**
 * Created by yousheng on 17/7/23.
 */

public class PageNumberBean {
    //当前是第几页
    private int mPageIndex = 0;
    //总数据条数
    private int mTotal = 0;
    //一页显示几条数据
    private int mPageSize = 0;
    //当前已经显示了几条数据
    private int mCurrentCount = 0;
    //加载延迟
    private int mDelayed = 0;

    PageNumberBean addIndex() {
        mPageIndex++;
        return this;
    }

    public int getmPageIndex() {
        return mPageIndex;
    }

    public PageNumberBean setmPageIndex(int mPageIndex) {
        this.mPageIndex = mPageIndex;
        return this;
    }

    public int getmTotal() {
        return mTotal;
    }

    public PageNumberBean setmTotal(int mTotal) {
        this.mTotal = mTotal;
        return this;
    }

    public int getmPageSize() {
        return mPageSize;
    }

    public PageNumberBean setmPageSize(int mPageSize) {
        this.mPageSize = mPageSize;
        return this;
    }

    public int getmCurrentCount() {
        return mCurrentCount;
    }

    public PageNumberBean setmCurrentCount(int mCurrentCount) {
        this.mCurrentCount = mCurrentCount;
        return this;
    }

    public int getmDelayed() {
        return mDelayed;
    }

    public PageNumberBean setmDelayed(int mDelayed) {
        this.mDelayed = mDelayed;
        return this;
    }
}
