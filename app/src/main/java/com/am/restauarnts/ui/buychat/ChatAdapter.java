package com.am.restauarnts.ui.buychat;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.am.restauarnts.R;
import com.am.restauarnts.repo.RepoFactory;
import com.am.restauarnts.ui.base.BaseViewHolderFirebase;
import com.am.restauarnts.ui.buychat.holder.MessageCartHolder;
import com.am.restauarnts.ui.buychat.holder.MessageItemAddedHolder;
import com.am.restauarnts.ui.buychat.holder.MessageTextHolder;
import com.am.restauarnts.ui.buychat.model.BuyMessage;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;

public class ChatAdapter extends FirestoreRecyclerAdapter<BuyMessage,BaseViewHolderFirebase<BuyMessage>> {
    static final int TYPE_TEXT = 1;
    static final int TYPE_TEXT_SELF = 2;
    static final int TYPE_CART = 3;
    static final int TYPE_ITEM_ADDED = 4;

    public ChatAdapter(@NonNull FirestoreRecyclerOptions<BuyMessage> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull BaseViewHolderFirebase<BuyMessage> holder, int position, @NonNull BuyMessage model) {
        holder.onBind(model,position);
    }

    @NonNull
    @Override
    public BaseViewHolderFirebase onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        switch (viewType){
            case TYPE_TEXT:{
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_message_text, parent, false);
                return new MessageTextHolder(view);
            }
            case TYPE_TEXT_SELF:{
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_message_text_self, parent, false);
                return new MessageTextHolder(view);
            }
            case TYPE_CART:{
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_message_cart, parent, false);
                return new MessageCartHolder(view);
            }
            case TYPE_ITEM_ADDED:{
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_message_item_added, parent, false);
                return new MessageItemAddedHolder(view);
            }
        }
        return new MessageTextHolder(view);
    }
    @Override
    public int getItemViewType(int position) {
        switch (getItem(position).getType()){
            case BuyMessage.TYPE_TEXT:{
                if (getItem(position).getSender_id().equals(RepoFactory.getUserRepo().getCurrentUser().getId()))
                    return TYPE_TEXT_SELF;
                return TYPE_TEXT;
            }
            case BuyMessage.TYPE_CART:
                return TYPE_CART;
            case BuyMessage.TYPE_REPORT:
                return TYPE_CART;
            case BuyMessage.TYPE_ITEM_ADDED:
                return TYPE_ITEM_ADDED;
        }
        return TYPE_TEXT;
    }
}
