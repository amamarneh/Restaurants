package com.am.restauarnts.data.network;

import com.am.restauarnts.data.model.RestaurantEntity;
import com.am.restauarnts.data.response.MenuResponse;
import com.am.restauarnts.data.response.RestaurantsResponse;
import com.am.restauarnts.task.Task;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.interfaces.ParsedRequestListener;


public class AppWebHelper implements WebHelper {
    @Override
    public void getRestaurants(ParsedRequestListener<RestaurantsResponse> listener) {
        AndroidNetworking.get(ServerEndPoints.RESTAURANTS)
                .build()
                .getAsObject(RestaurantsResponse.class,listener);
    }

    @Override
    public void getMenu(int restaurantId, ParsedRequestListener<MenuResponse> listener) {
        AndroidNetworking.get(ServerEndPoints.MENU)
                .addQueryParameter("rid",restaurantId+"")
                .build()
                .getAsObject(MenuResponse.class,listener);
    }
}
