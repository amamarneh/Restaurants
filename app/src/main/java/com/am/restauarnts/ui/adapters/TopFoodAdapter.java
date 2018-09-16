package com.am.restauarnts.ui.adapters;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.am.restauarnts.R;
import com.am.restauarnts.ui.activities.FoodDetailsActivity;
import com.am.restauarnts.ui.activities.RestaurantActivity;
import com.am.restauarnts.ui.base.BaseAdapter;
import com.am.restauarnts.ui.base.BaseViewHolder;
import com.am.restauarnts.ui.models.Restaurant;
import com.am.restauarnts.ui.models.TopFood;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TopFoodAdapter extends BaseAdapter<TopFood> {


    public TopFoodAdapter(List<TopFood> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.item_food_top, viewGroup, false);
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder baseViewHolder, int i) {
        baseViewHolder.onBind(i);
    }

    @Override
    public int getItemCount() {
        return items!=null?items.size():0;
    }

    class ItemHolder  extends BaseViewHolder implements View.OnClickListener{
        @BindView(R.id.tvRestaurantName)
        TextView tvRestaurantName;
        @BindView(R.id.tvName)
        TextView tvName;
        @BindView(R.id.imageView)
        ImageView imageView;
        @BindView(R.id.tvPrice)
        TextView tvPrice;

        public ItemHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onBind(int position) {
            tvRestaurantName.setText(itemView.getContext().getString(R.string.available_at,
                    items.get(position).getRestaurantName()));
            tvName.setText(items.get(position).getName());
            tvPrice.setText(itemView.getContext().getString(R.string.price_format,
                    String.valueOf(items.get(position).getPrice())));
            Glide.with(itemView.getContext()).load(items.get(position).getImage())
                    .apply(RequestOptions.placeholderOf(R.drawable.food1))
                    .into(imageView);

        }

        @Override
        public void onClick(View view) {
            int pos = getAdapterPosition();
            Intent intent = FoodDetailsActivity.getForFood(itemView.getContext(),items.get(pos).getAsFood(),items.get(pos).getRestaurant());
            itemView.getContext().startActivity(intent);
        }
    }
}
