package com.felink.guihubcode.RecyclerView;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.felink.guihubcode.R;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by jianghejie on 15/11/26.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> implements ItemTouchHelperAdapterCallback{
    public ArrayList<String> datas = null;
    private OnStartDragListener mListener;
    public MyAdapter(ArrayList<String> datas, OnStartDragListener dragListener) {
        this.datas = datas;
        mListener = dragListener;
    }
    //创建新View，被LayoutManager所调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item,viewGroup,false);
        return new ViewHolder(view);
    }
    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int position) {
        viewHolder.mTextView.setText(datas.get(position));

        viewHolder.v.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN){
                    mListener.onStartDrag(viewHolder);
                }
                return false;
            }
        });
    }
    //获取数据的数量
    @Override
    public int getItemCount() {
        return datas.size();
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(datas,fromPosition,toPosition);
        //刷新所有的数据
       // notifyDataSetChanged();
        //刷新部分，同时伴随动画效果
        notifyItemMoved(fromPosition,toPosition);
        return false;
    }

    @Override
    public void onItemSwiped(int position) {
        //删除数据，然后刷新
        datas.remove(position);
        notifyItemRemoved(position);
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        private View v;
        public ViewHolder(View view){
            super(view);
            mTextView = (TextView) view.findViewById(R.id.text);
            v = view.findViewById(R.id.v);
        }
    }
}
