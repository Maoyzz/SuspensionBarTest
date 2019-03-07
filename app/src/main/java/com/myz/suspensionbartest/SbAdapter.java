package com.myz.suspensionbartest;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * @author myz
 * @date 2019/3/7
 * desc:
 */
public class SbAdapter extends BaseQuickAdapter<SbAdapter.Model,BaseViewHolder> {


    public SbAdapter( @Nullable List<Model> data) {
        super(R.layout.item_sb,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Model item) {
        helper.setText(R.id.tv_title,"item" + helper.getLayoutPosition());
    }

    public static class Model{

    }
}
