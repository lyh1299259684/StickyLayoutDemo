package com.liyahong.stickylayoutdemo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.liyahong.stickylayoutdemo.R;
import com.liyahong.stickylayoutdemo.adapter.base.BaseInfoAdapter;
import com.liyahong.stickylayoutdemo.adapter.base.BaseViewHolder;
import com.liyahong.stickylayoutdemo.adapter.base.RecyclerViewBaseAdapter;
import com.liyahong.stickylayoutdemo.bean.GridItemBean;
import com.liyahong.stickylayoutdemo.interfaces.OnItemClickListener;
import com.liyahong.stickylayoutdemo.utils.GlideUtils;
import com.liyahong.stickylayoutdemo.widget.RoundImageView;

import java.util.List;

/**
 * author: LiYaHong
 * email: happyboy183@163.com
 * create time: 2018/7/17 0017 10:12
 */

public class GridViewAdapter extends RecyclerViewBaseAdapter<GridItemBean> {

    private OnItemClickListener<GridItemBean> onItemClickListener;

    public GridViewAdapter(int resId, Context context, List<GridItemBean> list) {
        super(resId, context, list);
    }

    public GridViewAdapter setOnItemClickListener(OnItemClickListener<GridItemBean> onItemClickListener){
        this.onItemClickListener = onItemClickListener;
        return this;
    }

    @Override
    protected RecyclerView.ViewHolder getViewHolder(View inflate) {
        return new GridHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        GridItemBean data = list.get(position);

        if (data != null) {
            GridHolder gridHolder = (GridHolder) holder;
            GlideUtils.loadImage(context,
                    data.getImageUrl(),
                    gridHolder.iv_image,
                    true,
                    DiskCacheStrategy.ALL);
            gridHolder.tv_desc.setText(data.getDesc());

            gridHolder.itemView.setOnClickListener(new OnItemClick(data));
        }
    }

    private class GridHolder extends RecyclerView.ViewHolder{

        RoundImageView iv_image;
        TextView tv_desc;

        private GridHolder(View itemView) {
            super(itemView);
            iv_image = itemView.findViewById(R.id.iv_image);
            tv_desc = itemView.findViewById(R.id.tv_desc);
        }
    }

    private class OnItemClick implements View.OnClickListener{

        private GridItemBean data;

        private OnItemClick(GridItemBean data) {
            this.data = data;
        }

        @Override
        public void onClick(View v) {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(data);
            }
        }
    }
}
