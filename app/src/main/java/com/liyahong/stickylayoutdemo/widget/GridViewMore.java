package com.liyahong.stickylayoutdemo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * author: LiYaHong
 * email: happyboy183@163.com
 * create time: 2018/4/18 0018 11:13
 *
 * 解决ScrollView 嵌套 GridView 只显示一行的问题
 */

public class GridViewMore extends GridView {

    public GridViewMore(Context context) {
        super(context);
    }

    public GridViewMore(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GridViewMore(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(
                Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
