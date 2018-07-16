package com.am.restauarnts.repo;

import com.am.restauarnts.task.LiveTask;
import com.am.restauarnts.ui.models.MenuItemModel;
import com.am.restauarnts.ui.models.Restaurant;

import java.util.List;

public interface ActionRepo {
    LiveTask<List<Restaurant>> getRestaurants();
    LiveTask<List<MenuItemModel>> getMenu(Restaurant restaurant);
}
