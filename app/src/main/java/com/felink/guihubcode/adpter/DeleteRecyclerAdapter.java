package com.felink.guihubcode.adpter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.felink.guihubcode.R;
import com.felink.guihubcode.bean.MessageItem;
import com.felink.guihubcode.holder.DeleteViewHolder;
import com.felink.guihubcode.view.SlideView;

import java.util.List;

/**
 * Created by Administrator on 2016/7/18.
 */
public class DeleteRecyclerAdapter extends RecyclerView.Adapter{

    private Context context;
    private LayoutInflater inflater;
    private List<MessageItem> lists;
    private SlideView.OnSlideListener mSlideListener;


    public DeleteRecyclerAdapter(Context context, List<MessageItem> lists,SlideView.OnSlideListener onSlideListener) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.lists = lists;
        mSlideListener = onSlideListener;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.list_item, null);
        SlideView slideView = new SlideView(context);
        slideView.setContentView(itemView);
        if (mSlideListener != null){
            slideView.setOnSlideListener(mSlideListener);
        }
        slideView.shrink();
        DeleteViewHolder holder = new DeleteViewHolder(slideView);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        MessageItem item = lists.get(position);

        DeleteViewHolder viewHolder = (DeleteViewHolder)holder;
        viewHolder.icon.setImageResource(item.iconRes);
        viewHolder.title.setText(item.title);
        viewHolder.msg.setText(item.msg);
        viewHolder.time.setText(item.time);
        viewHolder.deleteHolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lists.remove(position);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }


}
