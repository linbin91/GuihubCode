package com.felink.guihubcode.RecyclerView;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.felink.guihubcode.widget.MainActivity;
import com.felink.guihubcode.R;
import com.felink.guihubcode.adpter.DataAdapter;
import com.felink.guihubcode.adpter.HeaderAndFooterWrapper;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2016/7/14.
 */
public class HeaderAndFooterActivity extends MainActivity{
    @InjectView(R.id.list)
    RecyclerView list;

    private DataAdapter mDataAdapter = null;
    private ArrayList<String> dataList = null;
    private HeaderAndFooterWrapper mWrapperAdatper = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_activity_click);
        ButterKnife.inject(this);

        dataList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            dataList.add("item" + i);
        }

        mDataAdapter = new DataAdapter(this);
        mDataAdapter.setData(dataList);
        mWrapperAdatper = new HeaderAndFooterWrapper(mDataAdapter);
        TextView header = new TextView(this);
        header.setText("header");

        TextView header2 = new TextView(this);
        header2.setText("header2");

        TextView footer = new TextView(this);
        footer.setText("footer");
        mWrapperAdatper.addHeaderView(header);
        mWrapperAdatper.addHeaderView(header2);
        mWrapperAdatper.addFootView(footer);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(mWrapperAdatper);


    }
}
