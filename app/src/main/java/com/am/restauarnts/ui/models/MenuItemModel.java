package com.am.restauarnts.ui.models;

import com.am.restauarnts.data.model.CategoryEntity;
import com.am.restauarnts.data.model.FoodEntity;

import java.util.ArrayList;
import java.util.List;

public class MenuItemModel {
    private boolean expanded;
    private String name;
    private List<Food> foods;
    private Restaurant restaurant;
    public MenuItemModel() {
    }
    public MenuItemModel(CategoryEntity categoryEntity, Restaurant restaurant) {
        this.name = categoryEntity.getName();
        this.restaurant =restaurant;
        if (categoryEntity.getFood() != null){
            this.foods = new ArrayList<>(categoryEntity.getFood().size());
            for (FoodEntity f :
                    categoryEntity.getFood()) {
                this.foods.add(new Food(f));
            }
        }

    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public List<Food> getFoods() {
        return foods;
    }

    public void setFoods(List<Food> foods) {
        this.foods = foods;
    }

    public String getName() {
        return name;

    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    public void setName(String name) {
        this.name = name;
    }
}
