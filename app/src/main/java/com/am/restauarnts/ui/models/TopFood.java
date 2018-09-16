package com.am.restauarnts.ui.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.am.restauarnts.data.model.FoodEntity;
import com.am.restauarnts.data.model.TopFoodEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ALa on 3/13/2018.
 */

public class TopFood implements Parcelable{
    private int id;
    private String name;
    private String image;
    private String description;
    private float price;
    private int categoryId;
    private Restaurant restaurant;
    private String restaurantName;
    private List<Food> mandatory;
    private List<Option> options;

    protected TopFood(Parcel in) {
        id = in.readInt();
        name = in.readString();
        image = in.readString();
        description = in.readString();
        price = in.readFloat();
        categoryId = in.readInt();
        restaurant = in.readParcelable(Restaurant.class.getClassLoader());
        restaurantName = in.readString();
        mandatory = in.createTypedArrayList(Food.CREATOR);
        options = in.createTypedArrayList(Option.CREATOR);
    }

    public static final Creator<TopFood> CREATOR = new Creator<TopFood>() {
        @Override
        public TopFood createFromParcel(Parcel in) {
            return new TopFood(in);
        }

        @Override
        public TopFood[] newArray(int size) {
            return new TopFood[size];
        }
    };

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public TopFood() {
    }
    public TopFood(TopFoodEntity foodEntity) {
        this.id = foodEntity.getId();
        this.name = foodEntity.getName();
        this.description = foodEntity.getDescription();
        this.price = foodEntity.getPrice();
        this.categoryId = foodEntity.getCategory_id();
        this.restaurantName = foodEntity.getRestaurant().getName();
        this.restaurant = new Restaurant(foodEntity.getRestaurant());
        this.image = foodEntity.getImage();
        if (foodEntity.getMandatory() != null){
            this.mandatory =new ArrayList<>(foodEntity.getMandatory().size());
            for (FoodEntity f :
                    foodEntity.getMandatory()) {
                this.mandatory.add(new Food(f));
            }
        }

    }
    public static List<TopFood> getAsList(List<TopFoodEntity> foodEntities){
        List<TopFood> list = new ArrayList<>();
        for (TopFoodEntity entity :
                foodEntities) {
            list.add(new TopFood(entity));
        }
        return list;
    }
    public Food getAsFood(){
        Food food = new Food();
        food.setId(this.id);
        food.setCategoryId(this.categoryId);
        food.setDescription(this.description);
        food.setImage(this.image);
        food.setMandatory(this.mandatory);
        food.setName(this.name);
        food.setOptions(this.options);
        food.setPrice(this.price);
        food.setRestaurantId(this.restaurant.getId());
        return food;
    }

    public boolean hasMandatory(){
        return this.mandatory != null && !this.mandatory.isEmpty();
    }





    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public List<Food> getMandatory() {
        return mandatory;
    }

    public void setMandatory(List<Food> mandatory) {
        this.mandatory = mandatory;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(image);
        parcel.writeString(description);
        parcel.writeFloat(price);
        parcel.writeInt(categoryId);
        parcel.writeParcelable(restaurant, i);
        parcel.writeString(restaurantName);
        parcel.writeTypedList(mandatory);
        parcel.writeTypedList(options);
    }
}
