package com.liyahong.stickylayoutdemo.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.liyahong.stickylayoutdemo.R;
import com.liyahong.stickylayoutdemo.adapter.base.BaseInfoAdapter;
import com.liyahong.stickylayoutdemo.adapter.base.BaseViewHolder;
import com.liyahong.stickylayoutdemo.bean.GridItemBean;
import com.liyahong.stickylayoutdemo.utils.GlideUtils;
import com.liyahong.stickylayoutdemo.widget.RoundImageView;

import java.util.List;

/**
 * author: LiYaHong
 * email: happyboy183@163.com
 * create time: 2018/7/17 0017 10:12
 */

public class GridViewAdapter extends BaseInfoAdapter<GridItemBean> {

    public GridViewAdapter(Context context, List<GridItemBean> list, int resId) {
        super(context, list, resId);
    }

    @Override
    public BaseViewHolder<GridItemBean> getHolder() {
        return new GridHolder(context, resId);
    }

    private class GridHolder extends BaseViewHolder<GridItemBean>{

        private RoundImageView iv_image;
        private TextView tv_desc;

        private GridHolder(Context context, int resId) {
            super(context, resId);
        }

        @Override
        protected void refreshData(int position) {
            View view = getView();
            iv_image = view.findViewById(R.id.iv_image);
            tv_desc = view.findViewById(R.id.tv_desc);

            GridItemBean data = getData();

            GlideUtils.loadImage(context,
                    data.getImageUrl(),
                    iv_image,
                    true,
                    DiskCacheStrategy.ALL);
            tv_desc.setText(data.getDesc());
        }
    }
}
