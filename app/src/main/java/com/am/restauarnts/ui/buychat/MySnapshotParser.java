package com.am.restauarnts.ui.buychat;

import android.support.annotation.NonNull;

import com.am.restauarnts.ui.buychat.model.BuyMessage;
import com.am.restauarnts.ui.buychat.model.MessageCart;
import com.am.restauarnts.ui.buychat.model.MessageItemAdded;
import com.am.restauarnts.ui.buychat.model.MessageText;
import com.firebase.ui.firestore.SnapshotParser;
import com.google.firebase.firestore.DocumentSnapshot;

public class MySnapshotParser implements SnapshotParser<BuyMessage> {
    @NonNull
    @Override
    public BuyMessage parseSnapshot(@NonNull DocumentSnapshot snapshot) {
        if(!snapshot.contains("type")){
            return snapshot.toObject(MessageText.class);
        }
        BuyMessage msg;

        switch (snapshot.get("type").toString()) {
            case BuyMessage.TYPE_TEXT +"":{
                msg = snapshot.toObject(MessageText.class);
                msg.setId(snapshot.getId());
                msg.setReference(snapshot.getReference());
                return msg;
            }
            case BuyMessage.TYPE_CART + "":{
                msg = snapshot.toObject(MessageCart.class);
                msg.setId(snapshot.getId());
                msg.setReference(snapshot.getReference());
                return msg;
            }
            case BuyMessage.TYPE_ITEM_ADDED + "":{
                msg = snapshot.toObject(MessageItemAdded.class);
                msg.setId(snapshot.getId());
                msg.setReference(snapshot.getReference());
                return msg;
            }

        }
        msg = new MessageText();
        msg.setType(BuyMessage.TYPE_NON);
        return msg;
    }
}
