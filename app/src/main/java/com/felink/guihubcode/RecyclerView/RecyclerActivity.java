package com.felink.guihubcode.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.felink.guihubcode.R;

/**
 * Created by Administrator on 2016/7/14.
 */
public class RecyclerActivity extends Activity implements View.OnClickListener {

    private TextView tvRecyclerClick;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_activity);
        tvRecyclerClick = (TextView) findViewById(R.id.tv_recycler_click);
        tvRecyclerClick.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_recycler_click:
                toRecyclerClickActivity();
                break;
            default:
                break;

        }
    }

    /**
     * Recycler 点击界面
     */
    private void toRecyclerClickActivity() {
        Intent intent = new Intent(RecyclerActivity.this,RecyclerClickActivity.class);
        startActivity(intent);
    }
}
