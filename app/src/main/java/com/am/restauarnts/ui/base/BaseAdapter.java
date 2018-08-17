package com.am.restauarnts.ui.base;

import android.support.v7.widget.RecyclerView;

import java.util.List;

public abstract class BaseAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {
    protected List<T> items;
    public void setList(List<T> items){
        if (this.items != null){
            this.items.clear();
            this.items.addAll(items);
        }else{
            this.items = items;
        }
        notifyDataSetChanged();
    }
}
