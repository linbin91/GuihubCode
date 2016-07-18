package com.felink.guihubcode.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.felink.guihubcode.R;

/**
 * Created by Administrator on 2016/7/18.
 */
public class DeleteViewHolder extends RecyclerView.ViewHolder {
    public ImageView icon;
    public TextView title;
    public TextView msg;
    public TextView time;
    public ViewGroup deleteHolder;

    public DeleteViewHolder(View itemView) {
        super(itemView);
        icon = (ImageView) itemView.findViewById(R.id.icon);
        title = (TextView) itemView.findViewById(R.id.title);
        msg = (TextView) itemView.findViewById(R.id.msg);
        time = (TextView) itemView.findViewById(R.id.time);
        deleteHolder = (ViewGroup)itemView.findViewById(R.id.holder);
    }
}
