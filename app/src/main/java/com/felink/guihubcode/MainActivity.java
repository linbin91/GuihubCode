package com.felink.guihubcode;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.felink.guihubcode.RecyclerView.RecyclerActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvMarquee;
    private TextView tvRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
    }

    private void initListener() {
        tvMarquee.setOnClickListener(this);
        tvRecyclerView.setOnClickListener(this);
    }

    private void initView() {
        tvMarquee = (TextView) findViewById(R.id.tv_marquee);
        tvRecyclerView = (TextView) findViewById(R.id.tv_recycler);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_marquee:
                toMarqueeActivity();
                break;
            case R.id.tv_recycler:
                toRecyclerActivity();
                break;
            default:
                break;
        }
    }

    /**
     * 进入Recycler界面
     */
    private void toRecyclerActivity() {
        Intent intent = new Intent(MainActivity.this,RecyclerActivity.class);
        startActivity(intent);
    }

    /**
     * 跑马灯界面
     */
    private void toMarqueeActivity() {
        Intent intent = new Intent(MainActivity.this,MarqueeActivity.class);
        startActivity(intent);
    }
}
