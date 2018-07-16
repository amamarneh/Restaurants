package com.am.restauarnts.data.response;

import com.am.restauarnts.data.model.RestaurantEntity;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RestaurantsResponse {

    @SerializedName("records")
    private List<RestaurantEntity> restaurantEntities;

    public List<RestaurantEntity> getRestaurantEntities() {
        return restaurantEntities;
    }

    public void setRestaurantEntities(List<RestaurantEntity> restaurantEntities) {
        this.restaurantEntities = restaurantEntities;
    }
}
