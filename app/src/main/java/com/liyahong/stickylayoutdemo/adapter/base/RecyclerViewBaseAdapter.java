package com.liyahong.stickylayoutdemo.adapter.base;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * author: LiYaHong
 * email: happyboy183@163.com
 * create time: 2018/4/16
 * RecyclerViewAdapter的封装
 * @param <T>
 */
public abstract class RecyclerViewBaseAdapter<T> extends RecyclerView.Adapter{

    protected Context context;
    protected List<T> list;
    private int resId;

    public RecyclerViewBaseAdapter(int resId, Context context, List<T> list){
        this.context = context;
        this.list = list;
        this.resId = resId;
    }

    protected Object getItem(int position){
        return list.get(position);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(context);
        View inflate = mInflater.inflate(resId, parent,false);
        return getViewHolder(inflate);
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    protected void refreshAdapter(List<T> list){
        this.list = list;
        notifyDataSetChanged();
    }

    protected abstract RecyclerView.ViewHolder getViewHolder(View inflate);

    @Override
    public abstract void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position);
}