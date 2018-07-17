package com.liyahong.stickylayoutdemo.adapter.base;

import android.content.Context;
import android.view.View;

/**
 * author: LiYaHong
 * email: happyboy183@163.com
 * create time: 2018/4/16
 * BaseViewHolder的封装
 */

public abstract  class BaseViewHolder<T> {

    private View view;
    private T data;

    public BaseViewHolder(Context context,int resId){
        this.view=View.inflate(context,resId , null);
        //当new出这个类的子类时就初始化数据
        view.setTag(this);

    }
    public View getView() {
        return view;
    }

    public T getData(){
        return data;
    }

    public void initData(int position, T t){
        data=t;
        //绑定数据
        refreshData(position);
    }

    protected abstract void refreshData(int position);
}
