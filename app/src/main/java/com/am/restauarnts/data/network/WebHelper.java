package com.am.restauarnts.data.network;

import com.am.restauarnts.data.model.RestaurantEntity;
import com.am.restauarnts.data.request.OrderCreateRequest;
import com.am.restauarnts.data.response.MenuResponse;
import com.am.restauarnts.data.response.MyOrdersResponse;
import com.am.restauarnts.data.response.OrderCreatedResponse;
import com.am.restauarnts.data.response.OrderDetailsResponse;
import com.am.restauarnts.data.response.RestaurantsResponse;
import com.am.restauarnts.data.response.TopFoodResponse;
import com.am.restauarnts.task.Task;
import com.androidnetworking.interfaces.ParsedRequestListener;

import java.util.List;

public interface WebHelper {
    void getRestaurants(ParsedRequestListener<RestaurantsResponse> listener);
    void getMenu(int restaurantId, ParsedRequestListener<MenuResponse> listener);
    void sendOrder(OrderCreateRequest orderCreateRequest, ParsedRequestListener<OrderCreatedResponse> listener);
    void getOrderDetails(int orderId, ParsedRequestListener<OrderDetailsResponse> listener);
    void getMyOrders(String userId, ParsedRequestListener<MyOrdersResponse> listener);
    void getTopFood(int limit, ParsedRequestListener<TopFoodResponse> listener);
}
