package com.am.restauarnts.repo;

import com.am.restauarnts.task.LiveTask;
import com.am.restauarnts.task.SingleLiveTask;
import com.am.restauarnts.ui.buychat.model.BuyMessage;
import com.am.restauarnts.ui.models.Cart;
import com.am.restauarnts.ui.models.MenuItemModel;
import com.am.restauarnts.ui.models.Order;
import com.am.restauarnts.ui.models.OrderBriefModel;
import com.am.restauarnts.ui.models.OrderDetailsModel;
import com.am.restauarnts.ui.models.Restaurant;

import java.util.List;

public interface ActionRepo {
    LiveTask<List<Restaurant>> getRestaurants();
    LiveTask<List<MenuItemModel>> getMenu(Restaurant restaurant);
    LiveTask<Integer> sendOrder(Cart cart, String address);
    SingleLiveTask<Void> sendMessage(String chatId, BuyMessage message);
    LiveTask<OrderDetailsModel> getOrderDetails(int orderId);
    LiveTask<List<OrderBriefModel>> getMyOrders();
}
