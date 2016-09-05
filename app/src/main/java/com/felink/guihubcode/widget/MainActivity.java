package com.felink.guihubcode.widget;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.felink.guihubcode.R;
import com.felink.guihubcode.RecyclerView.RecyclerActivity;
import com.linbin.changetheme.DayNightActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @InjectView(R.id.tv_scoller)
    TextView tvScoller;
    @InjectView(R.id.tv_animation)
    TextView tvAnimation;
    @InjectView(R.id.tv_recylerview_drag)
    TextView tvRecylerviewDrag;
    private TextView tvMarquee;
    private TextView tvRecyclerView;
    private TextView tvChangeTheme;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        initView();
        initListener();
    }

    private void initListener() {
        tvMarquee.setOnClickListener(this);
        tvRecyclerView.setOnClickListener(this);
        tvScoller.setOnClickListener(this);
        tvAnimation.setOnClickListener(this);
        tvChangeTheme.setOnClickListener(this);
        tvRecylerviewDrag.setOnClickListener(this);
    }

    private void initView() {
        tvMarquee = (TextView) findViewById(R.id.tv_marquee);
        tvRecyclerView = (TextView) findViewById(R.id.tv_recycler);
        tvChangeTheme = (TextView) findViewById(R.id.tv_change_theme);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_marquee:
                toMarqueeActivity();
                break;
            case R.id.tv_recycler:
                toRecyclerActivity();
                break;
            case R.id.tv_scoller:
                toScrollerActivity();
                break;
            case R.id.tv_animation:
                toAnimationActivity();
                break;
            case R.id.tv_change_theme:
                toChangeTheme();
                break;
            case R.id.tv_recylerview_drag:
                toRecyclerViewDrag();
                break;
            default:
                break;
        }
    }

    private void toRecyclerViewDrag() {
        Intent intent = new Intent(MainActivity.this, RecyclerViewDrag.class);
        startActivity(intent);
    }

    private void toChangeTheme() {
        Intent intent = new Intent(MainActivity.this, DayNightActivity.class);
        startActivity(intent);
    }

    /**
     * 进入动画集合界面
     */
    private void toAnimationActivity() {
        Intent intent = new Intent(MainActivity.this, AnimatorActivity.class);
        startActivity(intent);
    }

    /**
     * scoller 介绍
     */
    private void toScrollerActivity() {
        Intent intent = new Intent(MainActivity.this, ScollerActivity.class);
        startActivity(intent);
    }

    /**
     * 进入Recycler界面
     */
    private void toRecyclerActivity() {
        Intent intent = new Intent(MainActivity.this, RecyclerActivity.class);
        startActivity(intent);
    }

    /**
     * 跑马灯界面
     */
    private void toMarqueeActivity() {
        Intent intent = new Intent(MainActivity.this, MarqueeActivity.class);
        startActivity(intent);
    }
}
