package com.am.restauarnts.ui.buychat.holder;

import android.view.View;
import android.widget.TextView;

import com.am.restauarnts.R;
import com.am.restauarnts.ui.base.BaseViewHolderFirebase;
import com.am.restauarnts.ui.buychat.model.MessageItemAdded;


public class MessageItemAddedHolder extends BaseViewHolderFirebase<MessageItemAdded> {
    private TextView tvName,tvQuantity;
    public MessageItemAddedHolder(View itemView) {
        super(itemView);
        tvName = itemView.findViewById(R.id.tvName);
        tvQuantity = itemView.findViewById(R.id.tvQuantity);
    }

    @Override
    public void onBind(MessageItemAdded item, int position) {
        tvName.setText(item.getName());
        tvQuantity.setText("Quantity: "+item.getQuantity());
    }
}
