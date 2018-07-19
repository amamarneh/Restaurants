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
import com.am.restauarnts.ui.base.BaseViewHolder;
import com.am.restauarnts.ui.buychat.ChatActivity;
import com.am.restauarnts.ui.models.OrderBriefModel;
import com.am.restauarnts.ui.models.OrderDetailsItem;
import com.am.restauarnts.utils.DateUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyOrdersAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private List<OrderBriefModel> items;

    public MyOrdersAdapter(List<OrderBriefModel> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_my_order, parent, false);
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
        @BindView(R.id.tvName)
        TextView tvName;
        @BindView(R.id.tvCart)
        TextView tvCart;
        @BindView(R.id.tvDate)
        TextView tvDate;
        @BindView(R.id.tvStatus)
        TextView tvStatus;
        @BindView(R.id.imageView)
        ImageView imageView;
        public ItemHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        @Override
        public void onBind(int position) {
            tvName.setText(items.get(position).getRestaurant().getName());
            tvDate.setText(android.text.format.DateUtils.getRelativeDateTimeString(itemView.getContext()
                    , items.get(position).getCreated().getTime()
                    , android.text.format.DateUtils.MINUTE_IN_MILLIS
            , android.text.format.DateUtils.WEEK_IN_MILLIS,0));
            tvStatus.setText("");
            tvCart.setText(items.get(position).getItemsCount()+" items | "+items.get(position).getTotalPrice()+" NIS");
            Glide.with(itemView.getContext()).load(items.get(position).getRestaurant().getImage())
                    .apply(RequestOptions.placeholderOf(R.drawable.food1))
                    .into(imageView);

            itemView.setOnClickListener(view -> {
                Intent intent= ChatActivity.getChatIntent(itemView.getContext(),items.get(getAdapterPosition()).getId());
                itemView.getContext().startActivity(intent);
            });
        }
    }
}
