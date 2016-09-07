package com.linbin.recyclerviewcenter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import jameson.io.library.util.ScreenUtil;

/**
 * Created Administrator
 * on 2016/9/7
 * deprecated:
 */
public class CardScaleHelper {
    private RecyclerView mRecyclerView;
    private Context context;

    private float mScale = 0.9f;
    private int mPagePadding = 15; // 卡片的padding, 卡片间的距离等于2倍的mPagePadding
    private int mShowLeftCardWidth = 15;//左边卡片显示大小

    private int mCardWidth;//卡片宽度
    private int mOnePageWidth; // 滑动一页的距离
    private int mCardGralleryWidth;

    private int mCurrentItemsPos;
    private int mCurrentItemOffset;

    public void attachToRecyclerView(RecyclerView recyclerView) {
        this.mRecyclerView = recyclerView;
        context = mRecyclerView.getContext();

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                mCurrentItemOffset += dx;
                computeCurrentItemPos();
                onScrolledChangedCallback();
            }
        });

        initWidth();
    }

    private void initWidth() {
        mRecyclerView.post(new Runnable() {
            @Override
            public void run() {
                mCardGralleryWidth = mRecyclerView.getWidth();
                mCardWidth = mCardGralleryWidth - ScreenUtil.dip2px(context,
                        2 * (mPagePadding + mShowLeftCardWidth));
                mOnePageWidth = mCardWidth;
                mRecyclerView.smoothScrollToPosition(mCurrentItemsPos);
                onScrolledChangedCallback();
            }
        });
    }

    /**
     * 根据滑动的距离，缩放
     */
    private void onScrolledChangedCallback() {
        int offset = mCurrentItemOffset - mCurrentItemsPos * mOnePageWidth;
        float percent = (float) Math.max(Math.abs(offset) * 1.0f / mOnePageWidth, 0.0001);

        View leftView = null;
        View currentView = null;
        View rightView = null;

        if (mCurrentItemsPos > 0) {
            leftView = mRecyclerView.getLayoutManager().findViewByPosition(mCurrentItemsPos - 1);
        }
        currentView = mRecyclerView.getLayoutManager().findViewByPosition(mCurrentItemsPos);
        if (mCurrentItemsPos < (mRecyclerView.getAdapter().getItemCount() - 1)) {
            rightView = mRecyclerView.getLayoutManager().findViewByPosition(mCurrentItemsPos + 1);
        }

        if (leftView != null) {
            leftView.setScaleY((1 - mScale) * percent + mScale);
        }

        if (currentView != null) {
            // y = (mScale - 1)x + 1
            currentView.setScaleY((mScale - 1) * percent + 1);
        }
        if (rightView != null) {
            // y = (1 - mScale)x + mScale
            rightView.setScaleY((1 - mScale) * percent + mScale);
        }
    }

    /**
     * 计算当前的pos
     */
    private void computeCurrentItemPos() {
        if (mOnePageWidth <= 0) {
            return;
        }
        boolean pageChanged = false;
        if (Math.abs(mCurrentItemOffset - mCurrentItemsPos * mOnePageWidth) >= mOnePageWidth) {
            pageChanged = true;
        }
        if (pageChanged) {
            int tempPos = mCurrentItemsPos;
            mCurrentItemsPos = mCurrentItemOffset / mOnePageWidth;
        }


    }


    public void setCurrentItemPos(int currentItemPos) {
        this.mCurrentItemsPos = currentItemPos;
    }

    public int getCurrentItemPos() {
        return mCurrentItemsPos;
    }

    private int getDestItemOffset(int destPos) {
        return mOnePageWidth * destPos;
    }

}
