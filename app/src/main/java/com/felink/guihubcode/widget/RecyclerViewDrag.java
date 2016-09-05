package com.felink.guihubcode.widget;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.felink.guihubcode.R;
import com.felink.guihubcode.RecyclerView.MessageItemTouchCallback;
import com.felink.guihubcode.RecyclerView.MyAdapter;
import com.felink.guihubcode.RecyclerView.OnStartDragListener;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created Administrator
 * on 2016/9/5
 * deprecated:
 */
public class RecyclerViewDrag extends AppCompatActivity implements OnStartDragListener{

    @InjectView(R.id.recyclerview_drag)
    RecyclerView recyclerviewDrag;
    private MyAdapter mdapter;
    private ArrayList<String> mlist;
    private ItemTouchHelper itemtouchHelper;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_drag);
        ButterKnife.inject(this);

        initData();

        //重点
        MessageItemTouchCallback callback = new MessageItemTouchCallback(mdapter);
        itemtouchHelper= new ItemTouchHelper(callback);
        itemtouchHelper.attachToRecyclerView(recyclerviewDrag);



    }

    private void initData() {
        mlist = new ArrayList<>();

        for (int i = 0; i < 10; i++){
            mlist.add("item" + i);
        }
        mdapter = new MyAdapter(mlist,this);
        recyclerviewDrag.setLayoutManager(new LinearLayoutManager(this));
        recyclerviewDrag.setAdapter(mdapter);
    }


    public void  onStartDrag(MyAdapter.ViewHolder viewholder){
        //主动触发滑动效果
        itemtouchHelper.startDrag(viewholder);
    }
}
