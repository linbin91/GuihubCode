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
            default:
                break;

        }
    }

    /**
     * 进入底部有更多的recyclerview
     *
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
