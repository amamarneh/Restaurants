package com.am.restauarnts.ui.models;

import com.am.restauarnts.data.model.OrderDetailsItemEntity;

import java.util.ArrayList;
import java.util.List;

public class OrderDetailsItem {
    private Food food;
    private int quantity;
    private double price;
    private List<Option> options;

    public OrderDetailsItem() {
    }
    public OrderDetailsItem(OrderDetailsItemEntity entity) {
        this.food = new Food(entity.getStock());
        this.quantity = entity.getQuantity();
        this.options = Option.getAsList(entity.getOptions());
        this.price = entity.getPrice();

    }
    public static List<OrderDetailsItem> getAsList(List<OrderDetailsItemEntity> entities){
        List<OrderDetailsItem> list = new ArrayList<>();
        if (entities != null)
        for (OrderDetailsItemEntity e :
                entities) {
            list.add(new OrderDetailsItem(e));
        }
        return list;
    }
    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }
}
