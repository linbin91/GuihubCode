package com.felink.guihubcode.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.felink.guihubcode.R;
import com.felink.guihubcode.adpter.DeleteRecyclerAdapter;
import com.felink.guihubcode.bean.MessageItem;
import com.felink.guihubcode.view.DeleteRecyclerViewOther;
import com.felink.guihubcode.view.SlideView;
import com.jcodecraeer.xrecyclerview.ProgressStyle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/17.
 * 集合下拉刷新与加载更多，以及删除item
 */
public class DeleteItemRecyclerActivity2 extends AppCompatActivity implements SlideView.OnSlideListener {

    private DeleteRecyclerViewOther mRecyclerView;
    private DeleteRecyclerAdapter mAdapter;
    private int refreshTime = 0;
    private int times = 0;


    private List<MessageItem> mMessageItems = new ArrayList<MessageItem>();

    private SlideView mLastSlideViewWithStatusOn;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_recycler_other);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mRecyclerView = (DeleteRecyclerViewOther) this.findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);

        mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        mRecyclerView.setArrowImageView(R.drawable.iconfont_downgrey);

//        View header = LayoutInflater.from(this).inflate(R.layout.recyclerview_header, (ViewGroup)findViewById(android.R.id.content),false);
//        mRecyclerView.addHeaderView(header);

        mRecyclerView.setLoadingListener(new DeleteRecyclerViewOther.LoadingListener() {
            @Override
            public void onRefresh() {
                refreshTime++;
                times = 0;
                new Handler().postDelayed(new Runnable() {
                    public void run() {

                        mMessageItems.clear();
                        for (int i = 0; i < 15; i++) {
                            MessageItem item = new MessageItem();
                            item.iconRes = R.mipmap.default_qq_avatar;
                            item.title = "腾讯新闻";
                            item.msg = "青岛爆炸满月：大量鱼虾死亡";
                            item.time = "晚上18:18";
                            mMessageItems.add(item);
                        }
                        mAdapter.notifyDataSetChanged();
                        mRecyclerView.refreshComplete();
                    }

                }, 1000);            //refresh data here
            }

            @Override
            public void onLoadMore() {
                if (times < 2) {
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            for (int i = 0; i < 15; i++) {
                                MessageItem item = new MessageItem();
                                item.iconRes = R.mipmap.default_qq_avatar;
                                item.title = "腾讯新闻";
                                item.msg = "青岛爆炸满月：大量鱼虾死亡";
                                item.time = "晚上18:18";
                                mMessageItems.add(item);
                            }
                            mRecyclerView.loadMoreComplete();
                            mAdapter.notifyDataSetChanged();
                        }
                    }, 1000);
                } else {
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            for (int i = 0; i < 9; i++) {
                                MessageItem item = new MessageItem();
                                item.iconRes = R.mipmap.default_qq_avatar;
                                item.title = "腾讯新闻";
                                item.msg = "青岛爆炸满月：大量鱼虾死亡";
                                item.time = "晚上18:18";
                                mMessageItems.add(item);
                            }
                            mRecyclerView.setNoMore(true);
                            mAdapter.notifyDataSetChanged();
                        }
                    }, 1000);
                }
                times++;
            }
        });

        initData();
        mAdapter = new DeleteRecyclerAdapter(this,mMessageItems,this);

        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setRefreshing(false);
        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(mRecyclerView) {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(DeleteItemRecyclerActivity2.this,"position=" + position,Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initData() {
        for (int i = 0; i < 20; i++) {
            MessageItem item = new MessageItem();
            if (i % 3 == 0) {
                item.iconRes = R.mipmap.default_qq_avatar;
                item.title = "腾讯新闻";
                item.msg = "青岛爆炸满月：大量鱼虾死亡";
                item.time = "晚上18:18";
            } else {
                item.iconRes = R.mipmap.wechat_icon;
                item.title = "微信团队";
                item.msg = "欢迎你使用微信";
                item.time = "12月18日";
            }
            mMessageItems.add(item);
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.

    }

    @Override
    public void onStop() {
        super.onStop();


    }

    @Override
    public void onSlide(View view, int status) {
        if (mLastSlideViewWithStatusOn != null && mLastSlideViewWithStatusOn != view) {
            mLastSlideViewWithStatusOn.shrink();
        }

        if (status == SLIDE_STATUS_ON) {
            mLastSlideViewWithStatusOn = (SlideView) view;
        }
    }
}
