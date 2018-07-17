package com.liyahong.stickylayoutdemo.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.liyahong.stickylayoutdemo.R;

/**
 * author: LiYaHong
 * email: happyboy183@163.com
 * create time: 2018/5/17 0017 10:44
 *
 * 圆角图片处理
 */

@SuppressLint("AppCompatCustomView")
public class RoundImageView extends ImageView {

    //圆角弧度
    private float[] rids;
    private Path mPath;
    private RectF mRectF;
    private float leftRadius, topRadius, rightRadius,bottomRadius;

    public RoundImageView(Context context) {
        this(context, null);
    }

    public RoundImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RoundImageView, defStyleAttr, 0);

        leftRadius = typedArray.getDimensionPixelSize(R.styleable.RoundImageView_leftRadius, 0);
        topRadius = typedArray.getDimensionPixelSize(R.styleable.RoundImageView_topRadius, 0);
        rightRadius = typedArray.getDimensionPixelSize(R.styleable.RoundImageView_rightRadius, 0);
        bottomRadius = typedArray.getDimensionPixelSize(R.styleable.RoundImageView_bottomRadius, 0);

        typedArray.recycle();

        init();
    }

    private void init(){
        rids = new float[]{leftRadius, leftRadius, topRadius, topRadius, bottomRadius, bottomRadius, rightRadius, rightRadius,};
        mPath = new Path();
        mRectF = new RectF();
    }

    protected void onDraw(Canvas canvas) {

        int w = this.getWidth();
        int h = this.getHeight();
        mRectF.right = w;
        mRectF.bottom = h;

        //绘制圆角ImageView
        mPath.addRoundRect(mRectF, rids, Path.Direction.CW);
        canvas.clipPath(mPath);

        super.onDraw(canvas);
    }
}
