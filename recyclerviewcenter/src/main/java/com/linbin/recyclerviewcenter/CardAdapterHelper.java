package com.linbin.recyclerviewcenter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import jameson.io.library.util.ScreenUtil;

/**
 * Created Administrator
 * on 2016/9/7
 * deprecated:
 */
public class CardAdapterHelper {

    private int mPagePadding = 15;
    private int mShowLeftCardWidth = 15;

    public void onCreateViewHolder(ViewGroup parent, View itemView){
        RecyclerView.LayoutParams lp = (RecyclerView.LayoutParams) itemView.getLayoutParams();
        lp.width = parent.getWidth() - ScreenUtil.dip2px(parent.getContext(), 2 * (mPagePadding + mShowLeftCardWidth));
        itemView.setLayoutParams(lp);
    }

    public void onBindViewHolder(View itemView, int position, int itemCount){
        int padding = ScreenUtil.dip2px(itemView.getContext(),mPagePadding);
        itemView.setPadding(padding,0,padding,0);
        int leftMargin = position == 0 ? padding + ScreenUtil.dip2px(itemView.getContext(),mShowLeftCardWidth) : 0;
        int rightMargin = position == itemCount - 1 ? padding + ScreenUtil.dip2px(itemView.getContext(), mShowLeftCardWidth) : 0;
        setViewMargin(itemView, leftMargin,0,rightMargin,0);
    }

    private void setViewMargin(View itemView, int left, int top, int right, int bottom) {
        ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) itemView.getLayoutParams();
        if (lp.leftMargin != left || lp.topMargin != top || lp.rightMargin != right ||
                lp.bottomMargin != bottom){
            lp.setMargins(left, top, right, bottom);
            itemView.setLayoutParams(lp);
        }
    }

}
