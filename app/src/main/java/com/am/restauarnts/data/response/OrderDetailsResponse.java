package com.am.restauarnts.data.response;

import com.am.restauarnts.data.model.OrderDetailsEntity;

public class OrderDetailsResponse extends  ServerResponse{
    private OrderDetailsEntity records;

    public OrderDetailsEntity getRecords() {
        return records;
    }

    public void setRecords(OrderDetailsEntity records) {
        this.records = records;
    }
}
