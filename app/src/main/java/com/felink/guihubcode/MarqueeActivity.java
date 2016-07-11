package com.felink.guihubcode;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.felink.marqueelibrary.MarqueeLayout;
import com.felink.marqueelibrary.MarqueeLayoutAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/11.
 */
public class MarqueeActivity extends Activity{
    private MarqueeLayout mMarqueeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.marquee_activity);
        initView();
        initListener();
        initData();
    }

    private void initData() {
        final List<String> list = new ArrayList<>();
        list.add("我听见了你的声音 也藏着颗不敢见的心");
        list.add("我们的爱情到这刚刚好 剩不多也不少 还能忘掉");
        list.add("像海浪撞过了山丘以后还能撑多久 他可能只为你赞美一句后往回流");
        list.add("少了有点不甘 但多了太烦");
        MarqueeLayoutAdapter<String> adapter = new MarqueeLayoutAdapter<>();
        adapter.setCustomView(mMarqueeLayout, R.layout.item_simple_text, list, new MarqueeLayoutAdapter.InitViewCallBack<String>() {
            @Override
            public void init(View view, int position, String item) {
                ((TextView) view).setText(item);
            }
        });
        mMarqueeLayout.setAdapter(adapter);
        mMarqueeLayout.start();
    }

    private void initListener() {
    }

    private void initView() {
        mMarqueeLayout = (MarqueeLayout) findViewById(R.id.marquee_layout);
    }
}
