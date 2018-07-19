package com.am.restauarnts.data.model;

import java.util.List;

public class OrderItem {
    private int stock_id;
    private int quantity;
    private String message;
    private List<Integer> option;

    public int getStock_id() {
        return stock_id;
    }

    public void setStock_id(int stock_id) {
        this.stock_id = stock_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Integer> getOption() {
        return option;
    }

    public void setOption(List<Integer> option) {
        this.option = option;
    }
}
