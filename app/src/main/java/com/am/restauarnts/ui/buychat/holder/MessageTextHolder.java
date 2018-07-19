package com.am.restauarnts.ui.buychat.holder;

import android.view.View;
import android.widget.TextView;

import com.am.restauarnts.R;
import com.am.restauarnts.ui.base.BaseViewHolderFirebase;
import com.am.restauarnts.ui.buychat.model.MessageText;

public class MessageTextHolder extends BaseViewHolderFirebase<MessageText> {
    private TextView tvMessage;
    public MessageTextHolder(View itemView) {
        super(itemView);
        tvMessage=itemView.findViewById(R.id.tvMessage);
    }

    @Override
    public void onBind(MessageText item, int position) {
        tvMessage.setText(item.getText());
    }
}
