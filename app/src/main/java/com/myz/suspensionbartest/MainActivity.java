package com.myz.suspensionbartest;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Rv悬浮窗方案demo{仿github:https://github.com/wuapnjie/SuspensionBar}
 * @author myz
 * @date 2019/3/7
 */
public class MainActivity extends AppCompatActivity {

    private RecyclerView mRvList;
    private RelativeLayout mRlSb;
    private TextView mTvSbTitle;
    private SbAdapter adapter;
    private int mSbHeight;
    private int mCurrentPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){
        mRvList = findViewById(R.id.rv_list);
        mRlSb = findViewById(R.id.rl_sb);
        mTvSbTitle = findViewById(R.id.tv_sb_text);
        List<SbAdapter.Model> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(new SbAdapter.Model());
        }
        adapter = new SbAdapter(list);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        mRvList.setLayoutManager(layoutManager);
        mRvList.setAdapter(adapter);
        mRvList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                mSbHeight = mRlSb.getHeight();
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                View view = layoutManager.findViewByPosition(mCurrentPosition + 1);
                if (view != null) {
                    if (view.getTop() <= mSbHeight) {
                        mRlSb.setY(-(mSbHeight - view.getTop()));
                    } else {
                        mRlSb.setY(0);
                    }
                }

                if (mCurrentPosition != layoutManager.findFirstVisibleItemPosition()) {
                    mCurrentPosition = layoutManager.findFirstVisibleItemPosition();

                    updateSb();
                    mRlSb.setY(0);
                }
            }
        });
        updateSb();
        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRvList.scrollToPosition(0);
            }
        });
    }

    private void updateSb(){
        mTvSbTitle.setText("item" + mCurrentPosition);
    }




}
