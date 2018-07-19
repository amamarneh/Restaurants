package com.am.restauarnts.ui.buychat;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.am.restauarnts.R;
import com.am.restauarnts.ui.base.BaseViewHolder;
import com.am.restauarnts.ui.buychat.model.FoodBrief;
import com.am.restauarnts.ui.models.Food;
import com.am.restauarnts.ui.models.OrderDetailsItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrdersBriefAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private List<OrderDetailsItem> items;

    public OrdersBriefAdapter(List<OrderDetailsItem> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_order_brief, parent, false);
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
        @BindView(R.id.tvQuantity)
        TextView tvQuantity;
        public ItemHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        @Override
        public void onBind(int position) {
            tvName.setText(items.get(position).getFood().getName());
            tvQuantity.setText(items.get(position).getQuantity() +"");
        }
    }
}
