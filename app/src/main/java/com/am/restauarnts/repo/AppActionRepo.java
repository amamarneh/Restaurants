package com.am.restauarnts.repo;

import com.am.restauarnts.data.DataFactory;
import com.am.restauarnts.data.model.CategoryEntity;
import com.am.restauarnts.data.model.RestaurantEntity;
import com.am.restauarnts.data.response.MenuResponse;
import com.am.restauarnts.data.response.RestaurantsResponse;
import com.am.restauarnts.task.LiveTask;
import com.am.restauarnts.ui.models.Food;
import com.am.restauarnts.ui.models.MenuItemModel;
import com.am.restauarnts.ui.models.Restaurant;
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
                        if (response.getCategories() != null){
                            for (CategoryEntity c :
                                    response.getCategories()) {
                                list.add(new MenuItemModel(c));
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
}
