package com.am.restauarnts.ui.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.am.restauarnts.R;
import com.am.restauarnts.ui.base.BaseViewHolder;
import com.am.restauarnts.ui.models.Cart;
import com.am.restauarnts.ui.models.CartItem;
import com.bumptech.glide.Glide;

import java.util.List;

public class CartItemsAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    public static final int VIEW_TYPE_EMPTY = 0;
    public static final int VIEW_TYPE_NORMAL = 1;

    List<CartItem> items;
    private CartAdapterListener mListener;
    public CartItemsAdapter(List<CartItem> items, CartAdapterListener mListener) {
        this.items = items;
        this.mListener = mListener;
    }
    public interface CartAdapterListener{
        void onCartItemsChanged();
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        BaseViewHolder holder;
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        switch (viewType){
            case VIEW_TYPE_NORMAL:{
                View view = layoutInflater.inflate(R.layout.item_cart_item, parent, false);
                holder = new CartItemHolder(view);
                return holder;
            }
            case VIEW_TYPE_EMPTY:
                default:{
                    View view = layoutInflater.inflate(R.layout.item_empty, parent, false);
                    holder = new EmptyHolder(view);
                    return holder;
                }
        }
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        if (items != null && items.size() > 0) {
            return items.size();
        } else {
            return 1;
        }
    }
    @Override
    public int getItemViewType(int position) {
        if (items != null && !items.isEmpty()) {
            return VIEW_TYPE_NORMAL;
        } else {
            return VIEW_TYPE_EMPTY;
        }
    }
    class CartItemHolder extends BaseViewHolder{
        private TextView tvName,tvMessage,tvQuantity,tvTotal;
        private ImageView image;
        private Button btnInc,btnDec,btnRemove;
        CartItemHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvMessage = itemView.findViewById(R.id.tvMessage);
            tvQuantity = itemView.findViewById(R.id.tvQuantity);
            image = itemView.findViewById(R.id.image);
            btnInc = itemView.findViewById(R.id.btnInc);
            btnDec = itemView.findViewById(R.id.btnDec);
            btnRemove = itemView.findViewById(R.id.btnRemove);
            tvTotal = itemView.findViewById(R.id.tvTotal);
        }

        @Override
        public void onBind(int position) {
            tvName.setText(items.get(getAdapterPosition()).getFood().getName());

            tvQuantity.setText(items.get(getAdapterPosition()).getQuantity() + "");
            float total = items.get(getAdapterPosition()).getQuantity() * items.get(getAdapterPosition()).getFood().getNewPrice();
            tvTotal.setText(total+"");
            tvMessage.setText(items.get(getAdapterPosition()).getMessage());
            if(TextUtils.isEmpty(items.get(getAdapterPosition()).getMessage()))
                tvMessage.setVisibility(View.GONE);
            else
                tvMessage.setVisibility(View.VISIBLE);

            Glide.with(itemView.getContext()).load(items.get(getAdapterPosition()).getFood().getImgUrl())
                    .into(image);

            btnInc.setOnClickListener(v->{
                Cart cart = Cart.getCart(itemView.getContext());
                int c = cart.incrementItem(itemView.getContext(),items.get(getAdapterPosition()));
                items.get(getAdapterPosition()).setQuantity(c);
                tvQuantity.setText(c+"");
                if(mListener!=null)
                    mListener.onCartItemsChanged();
                notifyItemChanged(getAdapterPosition());
            });
            btnDec.setOnClickListener(v->{
                Cart cart = Cart.getCart(itemView.getContext());
                int c =cart.decrementItem(itemView.getContext(),items.get(getAdapterPosition()));
                items.get(getAdapterPosition()).setQuantity(c);
                tvQuantity.setText(c+"");
                if(c==0){
                    items.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                }
                if(mListener!=null)
                    mListener.onCartItemsChanged();
                notifyItemChanged(getAdapterPosition());
            });
            btnRemove.setOnClickListener(v->{
                Cart cart = Cart.getCart(itemView.getContext());
                cart.removeItem(itemView.getContext(),items.get(getAdapterPosition()));
                items.remove(getAdapterPosition());
                notifyItemRemoved(getAdapterPosition());
                if(mListener!=null)
                    mListener.onCartItemsChanged();
            });
        }
    }
    class EmptyHolder extends BaseViewHolder{

        public EmptyHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onBind(int position) {

        }
    }
}
