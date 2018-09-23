package com.am.restauarnts.ui.models;

import com.am.restauarnts.data.model.OrderBriefEntity;
import com.am.restauarnts.utils.DateUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderBriefModel {
    private int id;
    private Date created;
    private float totalPrice;
    private Restaurant restaurant;
    private int itemsCount;
    private int status;

    public OrderBriefModel() {
    }



    public OrderBriefModel(OrderBriefEntity entity) {
        this.id = entity.getId();
        this.created = DateUtils.parseDate(entity.getCreated());
        this.totalPrice = entity.getTotal_price();
        this.restaurant = new Restaurant(entity.getRestaurant());
        this.itemsCount = entity.getItems_count();
        this.status = entity.getStatus();

    }
    public static List<OrderBriefModel> getAsList(List<OrderBriefEntity> entities){
        List<OrderBriefModel> list = new ArrayList<>();
        if (entities != null)
            for (OrderBriefEntity e :
                    entities) {
                list.add(new OrderBriefModel(e));
            }
        return list;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public int getItemsCount() {
        return itemsCount;
    }

    public void setItemsCount(int itemsCount) {
        this.itemsCount = itemsCount;
    }
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
