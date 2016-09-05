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
 * Created by Administrator on 2016/7/20.
 */
public class AnimatorActivity extends AppCompatActivity implements View.OnClickListener {
    @InjectView(R.id.tv_animation_base)
    TextView tvAnimationBase;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        ButterKnife.inject(this);
        tvAnimationBase.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_animation_base:
                toAnimationBase();
                break;
            default:
                break;
        }
    }

    private void toAnimationBase() {
        Intent intent = new Intent(this, BaseAnimation.class);
        startActivity(intent);
    }
}
