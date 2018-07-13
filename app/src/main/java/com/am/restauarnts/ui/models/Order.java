package com.am.restauarnts.ui.models;

import com.am.onlinerestaurant.ui.buychat.model.MessageCartItem;

import java.util.List;

public class Order {
    public final static int STATUS_PENDING = 0;
    public final static int STATUS_ACCEPTED = 1;
    public final static int STATUS_REJECTED = 2;

    private List<MessageCartItem> items;
    private int status;
    private String created;

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

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
