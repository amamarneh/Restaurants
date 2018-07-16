package com.am.restauarnts.data.network;

import com.am.restauarnts.data.model.RestaurantEntity;
import com.am.restauarnts.data.response.MenuResponse;
import com.am.restauarnts.data.response.RestaurantsResponse;
import com.am.restauarnts.task.Task;
import com.androidnetworking.interfaces.ParsedRequestListener;

import java.util.List;

public interface WebHelper {
    void getRestaurants(ParsedRequestListener<RestaurantsResponse> listener);
    void getMenu(int restaurantId, ParsedRequestListener<MenuResponse> listener);
}
