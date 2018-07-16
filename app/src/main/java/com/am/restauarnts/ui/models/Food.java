package com.am.restauarnts.ui.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.am.restauarnts.data.model.FoodEntity;
import com.am.restauarnts.data.model.OptionEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ALa on 3/13/2018.
 */

public class Food implements Parcelable{
    private int id;
    private String name;
    private String image;
    private String description;
    private float price;
    private int categoryId;
    private int restaurantId;
    private List<Food> mandatory;
    private List<Option> options;

    public Food() {
    }
    public Food(FoodEntity foodEntity) {
        this.id = foodEntity.getId();
        this.name = foodEntity.getName();
        this.description = foodEntity.getDescription();
        this.price = foodEntity.getPrice();
        this.categoryId = foodEntity.getCategory_id();
        this.restaurantId = foodEntity.getRestaurant_id();
        this.restaurantId = foodEntity.getRestaurant_id();
        this.image = foodEntity.getImage();
        if (foodEntity.getMandatory() != null){
            this.mandatory =new ArrayList<>(foodEntity.getMandatory().size());
            for (FoodEntity f :
                    foodEntity.getMandatory()) {
                this.mandatory.add(new Food(f));
            }
        }

    }
    public boolean hasMandatory(){
        return this.mandatory != null && !this.mandatory.isEmpty();
    }

    protected Food(Parcel in) {
        id = in.readInt();
        name = in.readString();
        image = in.readString();
        description = in.readString();
        price = in.readFloat();
        categoryId = in.readInt();
        restaurantId = in.readInt();
        mandatory = in.createTypedArrayList(Food.CREATOR);
        options = in.createTypedArrayList(Option.CREATOR);
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

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
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
        parcel.writeInt(restaurantId);
        parcel.writeTypedList(mandatory);
        parcel.writeTypedList(options);
    }
}
