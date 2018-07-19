package com.am.restauarnts.data.network;

import com.am.restauarnts.data.model.RestaurantEntity;
import com.am.restauarnts.data.request.OrderCreateRequest;
import com.am.restauarnts.data.response.MenuResponse;
import com.am.restauarnts.data.response.MyOrdersResponse;
import com.am.restauarnts.data.response.OrderCreatedResponse;
import com.am.restauarnts.data.response.OrderDetailsResponse;
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

    @Override
    public void sendOrder(OrderCreateRequest request , ParsedRequestListener<OrderCreatedResponse> listener) {
        AndroidNetworking.post(ServerEndPoints.ORDER_CREATE)
                .addApplicationJsonBody(request)
                .build()
                .getAsObject(OrderCreatedResponse.class,listener);
    }

    @Override
    public void getOrderDetails(int orderId, ParsedRequestListener<OrderDetailsResponse> listener) {
        AndroidNetworking.get(ServerEndPoints.ORDER_GET)
                .addQueryParameter("oid",orderId+"")
                .build()
                .getAsObject(OrderDetailsResponse.class,listener);
    }

    @Override
    public void getMyOrders(String userId, ParsedRequestListener<MyOrdersResponse> listener) {
        AndroidNetworking.get(ServerEndPoints.ORDER_GET_ALL)
                .addQueryParameter("uid",userId)
                .build()
                .getAsObject(MyOrdersResponse.class,listener);
    }
}
