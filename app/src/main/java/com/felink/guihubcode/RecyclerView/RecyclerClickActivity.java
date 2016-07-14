package com.felink.guihubcode.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.felink.guihubcode.R;
import com.felink.guihubcode.adpter.DataAdapter;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2016/7/14.
 */
public class RecyclerClickActivity extends Activity {

    @InjectView(R.id.list)
    RecyclerView list;

    private DataAdapter mDataAdapter = null;
    private  ArrayList<String> dataList = null;
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
        list.setAdapter(mDataAdapter);

        list.setLayoutManager(new LinearLayoutManager(this));
        list.addOnItemTouchListener(new RecyclerItemClickListener(list) {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(RecyclerClickActivity.this,dataList.get(position),Toast.LENGTH_SHORT).show();
            }
        });
    }
}
