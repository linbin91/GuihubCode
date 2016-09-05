package com.felink.guihubcode.widget;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.felink.guihubcode.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2016/7/20.
 */
public class BaseAnimation extends AppCompatActivity implements View.OnClickListener {


    @InjectView(R.id.tv_content)
    TextView tvContent;
    @InjectView(R.id.move)
    Button move;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_animation);
        ButterKnife.inject(this);

        move.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.move:
                doMoveAction();
                break;
            default:
                break;
        }
    }


    private void doMoveAction() {

        ObjectAnimator animation = null;

        tvContent .setOnClickListener(this);

        animation =  ObjectAnimator.ofFloat(tvContent, "translationY",50);
        animation.setDuration(200);
        animation.start();

    }
}
