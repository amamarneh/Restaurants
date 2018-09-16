package com.am.restauarnts.repo;

import android.util.Log;

import com.am.restauarnts.data.DataFactory;
import com.am.restauarnts.data.model.CategoryEntity;
import com.am.restauarnts.data.model.OrderItem;
import com.am.restauarnts.data.model.RestaurantEntity;
import com.am.restauarnts.data.network.FirebaseEndPoint;
import com.am.restauarnts.data.request.OrderCreateRequest;
import com.am.restauarnts.data.response.MenuResponse;
import com.am.restauarnts.data.response.MyOrdersResponse;
import com.am.restauarnts.data.response.OrderCreatedResponse;
import com.am.restauarnts.data.response.OrderDetailsResponse;
import com.am.restauarnts.data.response.RestaurantsResponse;
import com.am.restauarnts.data.response.TopFoodResponse;
import com.am.restauarnts.task.LiveTask;
import com.am.restauarnts.task.SingleLiveTask;
import com.am.restauarnts.ui.buychat.model.BuyMessage;
import com.am.restauarnts.ui.models.Cart;
import com.am.restauarnts.ui.models.CartItem;
import com.am.restauarnts.ui.models.MenuItemModel;
import com.am.restauarnts.ui.models.Option;
import com.am.restauarnts.ui.models.OrderBriefModel;
import com.am.restauarnts.ui.models.OrderDetailsItem;
import com.am.restauarnts.ui.models.OrderDetailsModel;
import com.am.restauarnts.ui.models.Restaurant;
import com.am.restauarnts.ui.models.TopFood;
import com.am.restauarnts.utils.DateUtils;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;

import java.util.ArrayList;
import java.util.List;

public class AppActionRepo implements ActionRepo {
    @Override
    public LiveTask<List<Restaurant>> getRestaurants() {
        LiveTask<List<Restaurant>> task = new LiveTask<>();
        DataFactory.getWebHelper()
                .getRestaurants(new ParsedRequestListener<RestaurantsResponse>() {
                    @Override
                    public void onResponse(RestaurantsResponse response) {
                        List<Restaurant> restaurants = new ArrayList<>(response.getRestaurantEntities().size());
                        for (RestaurantEntity r :
                                response.getRestaurantEntities()) {
                            restaurants.add(new Restaurant(r));
                        }
                        task.success(restaurants);
                    }

                    @Override
                    public void onError(ANError anError) {
                        anError.printStackTrace();
                        task.error(anError.getMessage());
                    }
                });
        return task;
    }

    @Override
    public LiveTask<List<MenuItemModel>> getMenu(Restaurant restaurant) {
        LiveTask<List<MenuItemModel>> task = new LiveTask<>();
        DataFactory.getWebHelper()
                .getMenu(1, new ParsedRequestListener<MenuResponse>() {
                    @Override
                    public void onResponse(MenuResponse response) {
                        List<MenuItemModel> list = new ArrayList<>();
                        if (response.getCategories() != null) {
                            for (CategoryEntity c :
                                    response.getCategories()) {
                                list.add(new MenuItemModel(c, restaurant));
                            }
                        }
                        task.success(list);
                    }

                    @Override
                    public void onError(ANError anError) {
                        anError.printStackTrace();
                        task.error(anError.getMessage());
                    }
                });
        return task;
    }

    @Override
    public LiveTask<Integer> sendOrder(Cart cart, String address) {
        LiveTask<Integer> task = new LiveTask<>();
        OrderCreateRequest request = new OrderCreateRequest();
        request.setAddress(address);
        request.setRestaurant_id(cart.getRestaurant().getId());
        request.setUser_id(RepoFactory.getUserRepo().getCurrentUser().getId());
        List<OrderItem> items = new ArrayList<>();
        if (cart.getCartItems() != null)
            for (CartItem ci :
                    cart.getCartItems()) {
                OrderItem orderItem = new OrderItem();
                orderItem.setMessage(ci.getMessage());
                orderItem.setQuantity(ci.getQuantity());
                orderItem.setStock_id(ci.getFood().getId());
                List<Integer> options = new ArrayList<>();
                if (ci.getOptions() != null)
                    for (Option o :
                            ci.getOptions()) {
                        options.add(o.getId());
                    }
                orderItem.setOption(options);
                items.add(orderItem);
            }
        request.setItems(items);

        DataFactory.getWebHelper()
                .sendOrder(request, new ParsedRequestListener<OrderCreatedResponse>() {
                    @Override
                    public void onResponse(OrderCreatedResponse response) {
                        Log.d("tag", "response=" + response.getOrder_id());
                        task.success(response.getOrder_id());
                    }

                    @Override
                    public void onError(ANError anError) {
                        anError.printStackTrace();
                        task.error(anError.getMessage());
                    }
                });
        return task;
    }

    @Override
    public SingleLiveTask<Void> sendMessage(String chatId, BuyMessage message) {
        SingleLiveTask<Void> t = new SingleLiveTask<>();
        FirebaseEndPoint.getChatMessages(chatId)
                .add(message)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        t.success(null);
                    } else
                        t.error(null);
                });
        return t;
    }

    @Override
    public LiveTask<OrderDetailsModel> getOrderDetails(int orderId) {
        LiveTask<OrderDetailsModel> task = new LiveTask<>();
        DataFactory.getWebHelper()
                .getOrderDetails(orderId, new ParsedRequestListener<OrderDetailsResponse>() {
                    @Override
                    public void onResponse(OrderDetailsResponse response) {
                        OrderDetailsModel model = new OrderDetailsModel();
                        model.setCreated(DateUtils.parseDate(response.getRecords().getCreated()));
                        model.setId(response.getRecords().getId());
                        model.setRestaurant(new Restaurant(response.getRecords().getRestaurant()));
                        model.setTotalPrice(response.getRecords().getTotal_price());
                        model.setItems(OrderDetailsItem.getAsList(response.getRecords().getItems()));
                        model.setStatus(response.getRecords().getStatus());
                        task.success(model);
                    }

                    @Override
                    public void onError(ANError anError) {
                        anError.printStackTrace();
                        task.error(anError.getMessage());
                    }
                });
        return task;
    }

    @Override
    public LiveTask<List<OrderBriefModel>> getMyOrders() {
        LiveTask<List<OrderBriefModel>> task = new LiveTask<>();
        DataFactory.getWebHelper()
                .getMyOrders(RepoFactory.getUserRepo().getCurrentUser().getId(), new ParsedRequestListener<MyOrdersResponse>() {
                    @Override
                    public void onResponse(MyOrdersResponse response) {
                        task.success(OrderBriefModel.getAsList(response.getOrders()));
                    }

                    @Override
                    public void onError(ANError anError) {
                        task.error(anError.getMessage());
                    }
                });
        return task;
    }

    @Override
    public LiveTask<List<TopFood>> getTop() {
        LiveTask<List<TopFood>> task = new LiveTask<>();
        DataFactory.getWebHelper()
                .getTopFood(10, new ParsedRequestListener<TopFoodResponse>() {
                    @Override
                    public void onResponse(TopFoodResponse response) {
                        task.success(TopFood.getAsList(response.getData()));
                    }

                    @Override
                    public void onError(ANError anError) {
                        anError.printStackTrace();
                        task.error(anError.getMessage());
                    }
                });
        return task;
    }
}
