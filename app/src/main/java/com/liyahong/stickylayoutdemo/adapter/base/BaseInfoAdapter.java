package com.liyahong.stickylayoutdemo.adapter.base;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * author: LiYaHong
 * email: happyboy183@163.com
 * create time: 2018/4/16
 * BaseAdapter的封装
 * @param <T>
 */
public abstract class BaseInfoAdapter<T> extends BaseAdapter {

    protected Context context;
    private List<T> list;
    protected int resId;

    public BaseInfoAdapter(Context context, List<T> list, int resId) {
        this.context = context;
        this.list = list;
        this.resId = resId;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    protected void refreshAdapter(List<T> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        BaseViewHolder<T> baseViewHolder = null;
        if (view == null){
            baseViewHolder =getHolder();
        }else {
            baseViewHolder= (BaseViewHolder) view.getTag();
        }
        T t = list.get(i);
        baseViewHolder.initData(i, t);
        return baseViewHolder.getView();
    }

    public abstract BaseViewHolder<T> getHolder() ;
}
