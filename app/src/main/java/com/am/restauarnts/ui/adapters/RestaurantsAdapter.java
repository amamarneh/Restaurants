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
import com.am.restauarnts.ui.activities.RestaurantActivity;
import com.am.restauarnts.ui.activities.ScrollingActivity;
import com.am.restauarnts.ui.base.BaseViewHolder;
import com.am.restauarnts.ui.models.Restaurant;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RestaurantsAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private List<Restaurant> items;

    public RestaurantsAdapter(List<Restaurant> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.item_restaurant, viewGroup, false);
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

    class ItemHolder  extends BaseViewHolder{
        @BindView(R.id.tvRestaurantName)
        TextView tvRestaurantName;
        @BindView(R.id.tvInfo)
        TextView tvInfo;
        @BindView(R.id.imageView)
        ImageView imageView;

        public ItemHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        @Override
        public void onBind(int position) {
            tvRestaurantName.setText(items.get(position).getName());
            tvInfo.setText(items.get(position).getInfo());
            Glide.with(itemView.getContext()).load(items.get(position).getImage())
                    .apply(RequestOptions.placeholderOf(R.drawable.food1))
                    .into(imageView);
            itemView.setOnClickListener(view -> {
                Intent intent = RestaurantActivity.getForRestaurant(itemView.getContext(),items.get(getAdapterPosition()));
                itemView.getContext().startActivity(intent);
            });
        }
    }
}
