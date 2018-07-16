package com.am.restauarnts.ui.adapters;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.am.restauarnts.R;
import com.am.restauarnts.ui.base.BaseViewHolder;
import com.am.restauarnts.ui.models.MenuItemModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MenuAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private List<MenuItemModel> items;

    public MenuAdapter(List<MenuItemModel> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_menu, parent, false);
        return new MenuHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return items!=null?items.size():0;
    }


    class MenuHolder extends BaseViewHolder{
        @BindView(R.id.tvName)
        TextView tvName;
        @BindView(R.id.recyclerView)
        RecyclerView recyclerView;
        @BindView(R.id.layout)
        View layout;
        public MenuHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        @Override
        public void onBind(int position) {
            tvName.setText(items.get(position).getName());
            itemView.setOnClickListener(view -> {
                items.get(getAdapterPosition()).setExpanded(!items.get(getAdapterPosition()).isExpanded());
                notifyItemChanged(getAdapterPosition());
            });
            Log.d("tag","items.get(position).isExpanded()="+items.get(position).isExpanded());

            if(items.get(position).isExpanded()) {
                recyclerView.setLayoutManager(new LinearLayoutManager(itemView.getContext()));
                MenuItemAdapter adapter = new MenuItemAdapter(items.get(position).getFoods(),items.get(position).getRestaurant());
                LinearLayoutManager layoutManager = new LinearLayoutManager(itemView.getContext());
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(adapter);
                DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                        layoutManager.getOrientation());
                recyclerView.addItemDecoration(dividerItemDecoration);
                recyclerView.setVisibility(View.VISIBLE);
                layout.setBackgroundColor(Color.LTGRAY);
            }else{
                recyclerView.setVisibility(View.GONE);
                layout.setBackgroundColor(Color.WHITE);
            }



        }
    }
}
