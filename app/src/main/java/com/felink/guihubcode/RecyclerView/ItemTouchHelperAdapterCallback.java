package com.felink.guihubcode.RecyclerView;

/**
 * Created Administrator
 * on 2016/9/5
 * deprecated:
 */
public interface ItemTouchHelperAdapterCallback {
    /**
     * 拖拽回调
     * @param fromPosition
     * @param toPosition
     * @return
     */
    boolean onItemMove(int fromPosition,int toPosition);

    /**
     * 侧滑删除
     * @param position
     */
    void onItemSwiped(int position);

}
