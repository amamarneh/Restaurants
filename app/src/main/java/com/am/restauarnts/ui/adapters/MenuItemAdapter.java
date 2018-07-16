package com.am.restauarnts.ui.adapters;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.am.restauarnts.R;
import com.am.restauarnts.ui.activities.FoodDetailsActivity;
import com.am.restauarnts.ui.base.BaseViewHolder;
import com.am.restauarnts.ui.models.Food;
import com.am.restauarnts.ui.models.Restaurant;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MenuItemAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private List<Food> items;
    private Restaurant mRestaurant;
    public MenuItemAdapter(List<Food> items, Restaurant restaurant) {
        this.items = items;
        this.mRestaurant = restaurant;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_food_item, parent, false);
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return items != null?items.size():0;
    }

    class ItemHolder extends BaseViewHolder{
        @BindView(R.id.imageView)
        ImageView imageView;
        @BindView(R.id.tvName)
        TextView tvName;
        @BindView(R.id.tvInfo)
        TextView tvInfo;
        @BindView(R.id.tvPrice)
        TextView tvPrice;
        @BindView(R.id.layout)
        View layout;
        public ItemHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        @Override
        public void onBind(int position) {
            tvName.setText(items.get(position).getName());
            tvPrice.setText(items.get(position).getPrice() + " NIS");
            tvInfo.setText(items.get(position).getDescription());
            Glide.with(itemView.getContext()).load(items.get(position).getImage()).into(imageView);

            layout.setOnClickListener(view -> {
                Intent i = FoodDetailsActivity.getForFood(itemView.getContext(),items.get(getAdapterPosition()),mRestaurant);
                itemView.getContext().startActivity(i);
            });
        }
    }
}
