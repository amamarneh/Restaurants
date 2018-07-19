package com.am.restauarnts.data.response;

import com.google.gson.annotations.SerializedName;

public class OrderCreatedResponse  extends ServerResponse{
    @SerializedName("order_id")
    private int order_id;

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }
}
