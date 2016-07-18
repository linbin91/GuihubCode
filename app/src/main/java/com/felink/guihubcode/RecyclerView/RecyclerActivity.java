package com.felink.guihubcode.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.felink.guihubcode.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2016/7/14.
 */
public class RecyclerActivity extends Activity implements View.OnClickListener {


    @InjectView(R.id.tv_recycler_header)
    TextView tvRecyclerHeader;
    @InjectView(R.id.tv_recycler_add_more)
    TextView tvRecyclerAddMore;
    @InjectView(R.id.tv_recycler_all)
    TextView tvRecyclerAll;
    @InjectView(R.id.tv_recycler_delete)
    TextView tvRecyclerDelete;
    @InjectView(R.id.tv_recycler_other_delete)
    TextView tvRecyclerOtherDelete;
    private TextView tvRecyclerClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_activity);
        ButterKnife.inject(this);
        tvRecyclerClick = (TextView) findViewById(R.id.tv_recycler_click);
        tvRecyclerClick.setOnClickListener(this);
        tvRecyclerHeader.setOnClickListener(this);
        tvRecyclerAddMore.setOnClickListener(this);
        tvRecyclerAll.setOnClickListener(this);
        tvRecyclerDelete.setOnClickListener(this);
        tvRecyclerOtherDelete.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_recycler_click:
                toRecyclerClickActivity();
                break;
            case R.id.tv_recycler_header:
                toRecyclerHeaderAndFooterActivity();
                break;
            case R.id.tv_recycler_add_more:
                toRecyclerMoreActivity();
                break;
            case R.id.tv_recycler_all:
                toRecyclerAll();
                break;
            case R.id.tv_recycler_delete:
                toRecyclerDelete();
                break;
            case R.id.tv_recycler_other_delete:
                toRecyclerOtherDelete();
                break;
            default:
                break;

        }
    }

    private void toRecyclerOtherDelete() {
        Intent intent = new Intent(RecyclerActivity.this, DeleteItemRecyclerActivity2.class);
        startActivity(intent);
    }

    /**
     * 进入删除item的界面
     */
    private void toRecyclerDelete() {
        Intent intent = new Intent(RecyclerActivity.this, DeleteItemRecyclerActivity.class);
        startActivity(intent);
    }

    /**
     * 网络集合了头部与加更多的
     */
    private void toRecyclerAll() {

        Intent intent = new Intent(RecyclerActivity.this, AllRecyclerActivity.class);
        startActivity(intent);
    }

    /**
     * 进入底部有更多的recyclerview
     */
    private void toRecyclerMoreActivity() {
        Intent intent = new Intent(RecyclerActivity.this, MoreRecyclerActivity.class);
        startActivity(intent);
    }

    /**
     * 进入到有头部尾部的activity
     */
    private void toRecyclerHeaderAndFooterActivity() {
        Intent intent = new Intent(RecyclerActivity.this, HeaderAndFooterActivity.class);
        startActivity(intent);
    }

    /**
     * Recycler 点击界面
     */
    private void toRecyclerClickActivity() {
        Intent intent = new Intent(RecyclerActivity.this, RecyclerClickActivity.class);
        startActivity(intent);
    }
}
