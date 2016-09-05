package com.felink.guihubcode.widget;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.felink.guihubcode.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2016/7/19.
 */
public class ScollerActivity extends AppCompatActivity implements View.OnClickListener {

    @InjectView(R.id.tv_delet)
    TextView tvDelet;
    @InjectView(R.id.tv_drawer)
    TextView tvDrawer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoller);
        ButterKnife.inject(this);
        initListener();
    }

    private void initListener() {
        tvDelet.setOnClickListener(this);
        tvDrawer.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_delet:
                toDeleteActivity();
                break;
            case R.id.tv_drawer:
                toDrawerActivity();
                break;
            default:
                break;
        }
    }

    private void toDrawerActivity() {
    }

    private void toDeleteActivity() {
        Intent intent = new Intent(this,ScollerDeleteActivity.class);
        startActivity(intent);
    }
}
