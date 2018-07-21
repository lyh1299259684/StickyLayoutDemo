package com.liyahong.stickylayoutdemo.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.liyahong.stickylayoutdemo.R;
import com.liyahong.stickylayoutdemo.adapter.base.RecyclerViewBaseAdapter;
import com.liyahong.stickylayoutdemo.bean.GridItemBean;
import com.liyahong.stickylayoutdemo.bean.RecyclerItemBean;
import com.liyahong.stickylayoutdemo.interfaces.OnItemClickListener;
import com.liyahong.stickylayoutdemo.widget.RecyclerItemDecoration;

import java.util.List;

/**
 * author: LiYaHong
 * email: happyboy183@163.com
 * create time: 2018/7/17 0017 09:39
 */

public class RecyclerViewAdapter extends RecyclerViewBaseAdapter<RecyclerItemBean> {

    private OnItemClickListener<GridItemBean> onItemClickListener;
    private int mRecyclerViewMaxHeight;
    private static final int GRID_ITEM_SPACE = 24;      //gridView 间距
    private static final int GRID_SPACE_COUNT = 2;      //gridView 列数
    private boolean mIsFirstLoad = true;                 //是否第一次加载，如果第一次加载再获取子条目高度，否则不获取，减少线程数
    private View mView = null;                             //获取到的View
    private static Handler mHandler = new Handler(Looper.getMainLooper());

    public RecyclerViewAdapter(int resId, Context context, List<RecyclerItemBean> list, int...layoutBottom) {
        super(resId, context, list);
        if (layoutBottom == null) {
            throw new NullPointerException("This layoutBottom cannot be a null value!");
        }
        if (layoutBottom.length != 2) {
            throw new IllegalArgumentException("This layoutBottom must have two parameters!");
        }
        //计算出RecyclerView的最大高度，即recyclerViewMaxHeight = rootlayout.getHeight- (tabLayout.getHeight + getSupperActionBar.getHeight);
        mRecyclerViewMaxHeight = layoutBottom[0] - layoutBottom[1];
    }

    public void setOnItemClickListener(OnItemClickListener<GridItemBean> onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    protected RecyclerView.ViewHolder getViewHolder(View inflate) {
        return new RecyclerHolder(inflate);
    }

    @Override
    public void refreshAdapter(List<RecyclerItemBean> list) {
        super.refreshAdapter(list);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        RecyclerItemBean recyclerItemBean = list.get(position);

        if (recyclerItemBean != null) {
            RecyclerHolder recyclerHolder = (RecyclerHolder) holder;
            recyclerHolder.tv_title.setText(recyclerItemBean.getTitle());

            List<GridItemBean> gridItemBean = recyclerItemBean.getGridItemBean();
            final GridLayoutManager gridLayoutManager = new GridLayoutManager(context, GRID_SPACE_COUNT);
            recyclerHolder.gv_recycler_item.setLayoutManager(gridLayoutManager);
            recyclerHolder.gv_recycler_item.setAdapter(
                    new GridViewAdapter(R.layout.adapter_grid_item, context, gridItemBean)
                            .setOnItemClickListener(onItemClickListener));
            recyclerHolder.gv_recycler_item.addItemDecoration(new RecyclerItemDecoration(GRID_ITEM_SPACE, 2));

            //获取第一个Item高度，由于所有Item的高度一致，所以获取第一个Item即可
            if (mIsFirstLoad && gridItemBean != null && gridItemBean.size() > 0) {
                Runnable runnable = new Runnable(){
                    @Override
                    public void run() {
                        //这个必须在子线程中获取，否则获取不到
                        mView = gridLayoutManager.findViewByPosition(0);
                        mIsFirstLoad = false;
                    }
                };
                mHandler.postDelayed(runnable, 200);
            }

            /**
             * 根据支付宝效果模仿的思路
             * 思路是这样的：如果是最后一条就设置为充满全屏，否则就是自适应。
             * 解决的问题：如果都是自适应会发现根据滑动无法切换最后两条，如果数据过少有可能就不能切换。
             *
             * 修改
             *     #versionCode 2
             *     #versionName 1.1
             *     1.自动计算最后一条状态，如果计算高度 >= maxHeight 就设置为wrap_content, 否则设置为match_content
             */
            ViewGroup.LayoutParams layoutParams = recyclerHolder.ll_recycler_item_group.getLayoutParams();
            if (position == getItemCount() - 1) {
                if (mView != null) {
                    //获取item总数
                    int count = gridItemBean == null ? 0 : gridItemBean.size();
                    //计算行数
                    int rowCount = count % GRID_SPACE_COUNT == 0 ? count / GRID_SPACE_COUNT : (count / GRID_SPACE_COUNT) + 1;
                    //计算RecyclerView的纯高度，不包括间距
                    int itemHeight = mView.getBottom() * rowCount;
                    //计算间距的总高度
                    int spaceHeight = (rowCount - 1) * GRID_ITEM_SPACE;
                    if ((itemHeight + spaceHeight) >= mRecyclerViewMaxHeight) {  //说明以及超出屏幕范围，需设置warp_content
                        layoutParams.height = RecyclerView.LayoutParams.WRAP_CONTENT;
                    } else {   //未超出，直接设置match_parent
                        layoutParams.height = RecyclerView.LayoutParams.MATCH_PARENT;
                    }
                } else {       //如果为空，直接设置为match_parent
                    layoutParams.height = RecyclerView.LayoutParams.MATCH_PARENT;
                }
            } else {
                layoutParams.height = RecyclerView.LayoutParams.WRAP_CONTENT;
            }
            recyclerHolder.ll_recycler_item_group.setLayoutParams(layoutParams);
        }
    }

    private class RecyclerHolder extends RecyclerView.ViewHolder{

        TextView tv_title;
        RecyclerView gv_recycler_item;
        LinearLayout ll_recycler_item_group;

        private RecyclerHolder(View itemView) {
            super(itemView);

            tv_title = itemView.findViewById(R.id.tv_title);
            gv_recycler_item = itemView.findViewById(R.id.gv_recycler_item);
            ll_recycler_item_group = itemView.findViewById(R.id.ll_recycler_item_group);
        }
    }
}
