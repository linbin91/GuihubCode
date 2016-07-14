package com.felink.guihubcode.adpter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.felink.guihubcode.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/7/14.
 */
public class DataAdapter extends RecyclerView.Adapter {

    private LayoutInflater mLayoutInflater;
    private ArrayList<String> mDataList = new ArrayList<>();

    public DataAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
    }

    public void setData(ArrayList<String> list) {
        this.mDataList = list;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mLayoutInflater.inflate(R.layout.sample_item_text, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        String item = mDataList.get(position);

        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.textView.setText(item);
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    private class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.info_text);
        }
    }
}
