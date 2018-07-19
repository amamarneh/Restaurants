package com.am.restauarnts.ui.buychat.model;

import java.util.List;

public class MessageCart extends BuyMessage {
    public final static int STATUS_PENDING = 0;
    public final static int STATUS_ACCEPTED = 1;
    public final static int STATUS_REJECTED = 2;

    private List<MessageCartItem> items;
    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<MessageCartItem> getItems() {
        return items;
    }

    public void setItems(List<MessageCartItem> items) {
        this.items = items;
    }

}
