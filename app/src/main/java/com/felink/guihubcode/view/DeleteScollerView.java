package com.felink.guihubcode.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Scroller;

import com.felink.guihubcode.utils.ScreenUtil;

/**
 * Created by Administrator on 2016/7/19.
 */
public class DeleteScollerView extends LinearLayout {
    private Scroller mScoller;
    private int mMaxWidth;

    public DeleteScollerView(Context context) {
        super(context);
    }

    public DeleteScollerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mScoller = new Scroller(context);
        mMaxWidth = ScreenUtil.dip2px(context,120);
        setOrientation(LinearLayout.HORIZONTAL);
    }

    public DeleteScollerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec,heightMeasureSpec);
        /**
         * 获得此ViewGroup上级容器为其推荐的宽和高，以及计算模式
         */
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);


        // 计算出所有的childView的宽和高
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        /**
         * 记录如果是wrap_content是设置的宽和高
         */
        int width = 0;
        int height = 0;

        int cCount = getChildCount();

        int cWidth = 0;
        int cHeight = 0;
        MarginLayoutParams cParams = null;

        // 用于计算左边两个childView的高度
        int lHeight = 0;
        // 用于计算右边两个childView的高度，最终高度取二者之间大值
        int rHeight = 0;

        // 用于计算上边两个childView的宽度
        int tWidth = 0;
        // 用于计算下面两个childiew的宽度，最终宽度取二者之间大值
        int bWidth = 0;

        /**
         * 根据childView计算的出的宽和高，以及设置的margin计算容器的宽和高，主要用于容器是warp_content时
         */
        for (int i = 0; i < cCount; i++){
            View childView = getChildAt(i);
            cWidth = childView.getMeasuredWidth();
            cHeight = childView.getMeasuredHeight();
            width += cWidth;
        }


        /**
         * 如果是wrap_content设置为我们计算的值
         * 否则：直接设置为父容器计算的值
         */
//        setMeasuredDimension(width, (heightMode == MeasureSpec.EXACTLY) ? sizeHeight
//                : height);
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        int left = 0;
        for (int i = 0; i < childCount; i++){
            getChildAt(i).layout(0 + left,0,getMeasuredWidth(),getMeasuredHeight());
            left += getChildAt(i).getMeasuredWidth();
        }
    }

    private  int lastX = 0;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int scrollX = getScrollX();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:

                int delX = x - lastX;
                int newScrollX = scrollX - delX;
                if (newScrollX < 0){
                    newScrollX = 0;
                }else if (newScrollX > mMaxWidth){
                    newScrollX = mMaxWidth;
                }
                scrollTo(newScrollX,0);
                break;
            case MotionEvent.ACTION_UP:
                mScoller.startScroll(scrollX,0,0-scrollX,0);
                invalidate();
                break;
            default:
                break;
        }
        lastX = x;
        return true;
    }

    @Override
    public void computeScroll() {
        if (mScoller.computeScrollOffset()){
            scrollTo(mScoller.getCurrX(),mScoller.getCurrY());
            postInvalidate();
        }
    }


}
