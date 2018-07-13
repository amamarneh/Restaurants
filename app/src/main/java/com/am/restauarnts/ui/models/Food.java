package com.am.restauarnts.ui.models;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ALa on 3/13/2018.
 */

public class Food implements Parcelable{
    private String name,description,imgUrl;
    private List<Food> foodCats;
    private String id;
    private Restaurant restaurant;

    protected Food(Parcel in) {
        name = in.readString();
        description = in.readString();
        imgUrl = in.readString();
        foodCats = in.createTypedArrayList(Food.CREATOR);
        id = in.readString();
        restaurant = in.readParcelable(Restaurant.class.getClassLoader());
        prevPrice = in.readFloat();
        newPrice = in.readFloat();
    }

    public static final Creator<Food> CREATOR = new Creator<Food>() {
        @Override
        public Food createFromParcel(Parcel in) {
            return new Food(in);
        }

        @Override
        public Food[] newArray(int size) {
            return new Food[size];
        }
    };

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Food(){}


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }




    public List<Food> getFoodCats() {
        return foodCats;
    }

    public void setFoodCats(List<Food> foodCats) {
        this.foodCats = foodCats;
    }


    private float prevPrice,newPrice;// previous price can be null, we look at the new price

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public float getPrevPrice() {
        return prevPrice;
    }

    public void setPrevPrice(float prevPrice) {
        this.prevPrice = prevPrice;
    }

    public float getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(float newPrice) {
        this.newPrice = newPrice;
    }




    public void addCat(Food food){
        if(foodCats == null)
            foodCats = new ArrayList<>();
        foodCats.add(food);
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(description);
        parcel.writeString(imgUrl);
        parcel.writeTypedList(foodCats);
        parcel.writeString(id);
        parcel.writeParcelable(restaurant, i);
        parcel.writeFloat(prevPrice);
        parcel.writeFloat(newPrice);
    }
}
