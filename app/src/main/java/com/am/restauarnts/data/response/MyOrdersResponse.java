package com.am.restauarnts.data.response;

import com.am.restauarnts.data.model.OrderBriefEntity;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MyOrdersResponse extends ServerResponse {
    @SerializedName("records")
    private List<OrderBriefEntity> orders;

    public List<OrderBriefEntity> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderBriefEntity> orders) {
        this.orders = orders;
    }
}
