package com.am.restauarnts.ui.models;

import com.am.onlinerestaurant.data.model.FirebaseCategory;

import java.util.List;

public class Category {
    private String id;
    private String name;
    private List<Food> foods;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Food> getFoods() {
        return foods;
    }

    public void setFoods(List<Food> foods) {
        this.foods = foods;
    }

    public static Category fromFirebase(FirebaseCategory category){
        Category c = new Category();
        c.setName(category.getName());
        c.setId(category.getId());
        return c;
    }
}
