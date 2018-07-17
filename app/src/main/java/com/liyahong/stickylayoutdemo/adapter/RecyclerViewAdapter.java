package com.liyahong.stickylayoutdemo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.liyahong.stickylayoutdemo.R;
import com.liyahong.stickylayoutdemo.adapter.base.RecyclerViewBaseAdapter;
import com.liyahong.stickylayoutdemo.bean.GridItemBean;
import com.liyahong.stickylayoutdemo.bean.RecyclerItemBean;
import com.liyahong.stickylayoutdemo.interfaces.OnItemClickListener;
import com.liyahong.stickylayoutdemo.widget.GridViewMore;
import java.util.List;

/**
 * author: LiYaHong
 * email: happyboy183@163.com
 * create time: 2018/7/17 0017 09:39
 */

public class RecyclerViewAdapter extends RecyclerViewBaseAdapter<RecyclerItemBean> {

    private OnItemClickListener<GridItemBean> onItemClickListener;

    public RecyclerViewAdapter(int resId, Context context, List<RecyclerItemBean> list) {
        super(resId, context, list);
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
            recyclerHolder.gv_recycler_item.setAdapter(new GridViewAdapter(context, gridItemBean, R.layout.adapter_grid_item));
            recyclerHolder.gv_recycler_item.setOnItemClickListener(new OnItemClick(gridItemBean));

            /**
             * 根据支付宝效果模仿的思路
             * 思路是这样的：如果是最后一条就设置为充满全屏，否则就是自适应。
             * 解决的问题：如果都是自适应会发现根据滑动无法切换最后两条，如果数据过少有可能就不能切换。
             */
            ViewGroup.LayoutParams layoutParams = recyclerHolder.ll_recycler_item_group.getLayoutParams();
            if (position == getItemCount() - 1) {
                layoutParams.height = RecyclerView.LayoutParams.MATCH_PARENT;
            } else {
                layoutParams.height = RecyclerView.LayoutParams.WRAP_CONTENT;
            }
            recyclerHolder.ll_recycler_item_group.setLayoutParams(layoutParams);
        }
    }

    private class RecyclerHolder extends RecyclerView.ViewHolder{

        TextView tv_title;
        GridViewMore gv_recycler_item;
        LinearLayout ll_recycler_item_group;

        private RecyclerHolder(View itemView) {
            super(itemView);

            tv_title = itemView.findViewById(R.id.tv_title);
            gv_recycler_item = itemView.findViewById(R.id.gv_recycler_item);
            ll_recycler_item_group = itemView.findViewById(R.id.ll_recycler_item_group);
        }
    }

    private class OnItemClick implements GridView.OnItemClickListener{

        private List<GridItemBean> datas;

        private OnItemClick(List<GridItemBean> datas) {
            this.datas = datas;
        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(datas.get(position));
            }
        }
    }
}
