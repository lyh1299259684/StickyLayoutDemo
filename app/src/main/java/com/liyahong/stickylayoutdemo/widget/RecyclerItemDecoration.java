package com.liyahong.stickylayoutdemo.widget;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * author: LiYaHong
 * email: happyboy183@163.com
 * create time: 2018/7/18 0018 09:11
 *
 * GridView间距设置
 */

public class RecyclerItemDecoration extends RecyclerView.ItemDecoration {

    private int itemSpace;
    private int itemNum;

    /**
     * @param itemSpace item间隔
     * @param itemNum 每行item的个数
     */
    public RecyclerItemDecoration(int itemSpace, int itemNum) {
        this.itemSpace = itemSpace;
        this.itemNum = itemNum;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.bottom = itemSpace;
        if (parent.getChildLayoutPosition(view) % itemNum == 0){
            outRect.left = 0;
        } else {
            outRect.left = itemSpace;
        }
    }
}
