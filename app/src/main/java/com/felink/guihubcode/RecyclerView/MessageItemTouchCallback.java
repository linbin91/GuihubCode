package com.felink.guihubcode.RecyclerView;

import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * Created Administrator
 * on 2016/9/5
 * deprecated:
 */
public class MessageItemTouchCallback extends ItemTouchHelper.Callback {

    private ItemTouchHelperAdapterCallback adapterCallback;
    public MessageItemTouchCallback(ItemTouchHelperAdapterCallback adapterCallback){
       this.adapterCallback = adapterCallback;
    }
    /**
     * 首先回调该方法滑动的方向，返回的int 就代表是否监听该方向
     * @param recyclerView
     * @param viewHolder
     * @return
     */
    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        //int dragFlags 拖拽
        //int swipeFlags 侧滑
        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        int swipeFlags = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        return makeMovementFlags(dragFlags,swipeFlags);
    }

    /**
     * 上下滑动的回调
     * @param recyclerView
     * @param viewHolder
     * @param target
     * @return
     */
    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        //让adpter交换数据并且刷新
        adapterCallback.onItemMove(viewHolder.getAdapterPosition(),target.getAdapterPosition());
        return false;
    }

    /**
     * 左右滑动的回调
     * @param viewHolder
     * @param direction
     */
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
//        adapterCallback.onItemSwiped(viewHolder.getAdapterPosition());
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return true;
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        //侧滑的时候条目需要重绘，加个动画效果
        //zzz
        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE){
            //dx : (左滑)-width ~ 0 ~ width（右滑）
//            float alpha = 1 -Math.abs(dX)/viewHolder.itemView.getWidth();
//            viewHolder.itemView.setScaleX(alpha);
//            viewHolder.itemView.setScaleY(alpha);
//            if (alpha == 0){
//                //已经滑出去le
//                viewHolder.itemView.setAlpha(1);
//                viewHolder.itemView.setScaleX(1);
//                viewHolder.itemView.setScaleY(1);
//            }

            //侧滑菜单提示
            if (dX <= -0.5 * viewHolder.itemView.getWidth()){
                viewHolder.itemView.setTranslationX(-0.5f * viewHolder.itemView.getWidth());
            }
        }else{
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);

        }

    }
}
