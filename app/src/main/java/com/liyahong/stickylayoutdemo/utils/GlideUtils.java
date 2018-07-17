package com.liyahong.stickylayoutdemo.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

/**
 * author: LiYaHong
 * email: happyboy183@163.com
 * create time: 2018/5/2 0002 16:19
 *
 * Glide加载图片
 */

public class GlideUtils {

    private static Bitmap bitmap = null;

    /**
     * 加载图片
     * @param context               上下文环境
     * @param url                    图片地址
     * @param imageView             加载到哪个控件
     * @param skipMemoryCache      是否跳过内存缓存（true: 跳过， false: 不跳过）
     * @param diskCacheStrategy    缓存设置
     * @return                       Bitmap
     */
    public static Bitmap loadImage(Context context, String url, final ImageView imageView, boolean skipMemoryCache, DiskCacheStrategy diskCacheStrategy){

        //使用Glide加载图片
        Glide.with(context)
                .asBitmap()
                .load(url)
                .apply(new RequestOptions()
                .diskCacheStrategy(diskCacheStrategy)
                .skipMemoryCache(skipMemoryCache)
                .centerCrop())
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        bitmap = resource;
                        imageView.setImageBitmap(resource);
                    }
                });
        return bitmap;
    }
}
