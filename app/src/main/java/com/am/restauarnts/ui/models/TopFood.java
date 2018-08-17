package com.am.restauarnts.ui.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.am.restauarnts.data.model.FoodEntity;

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
    private int restaurantId;
    private String restaurantName;
    private List<TopFood> mandatory;
    private List<Option> options;

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public TopFood() {
    }
    public TopFood(FoodEntity foodEntity) {
        this.id = foodEntity.getId();
        this.name = foodEntity.getName();
        this.description = foodEntity.getDescription();
        this.price = foodEntity.getPrice();
        this.categoryId = foodEntity.getCategory_id();
        this.restaurantId = foodEntity.getRestaurant_id();
        this.image = foodEntity.getImage();
        if (foodEntity.getMandatory() != null){
            this.mandatory =new ArrayList<>(foodEntity.getMandatory().size());
            for (FoodEntity f :
                    foodEntity.getMandatory()) {
                this.mandatory.add(new TopFood(f));
            }
        }

    }
    public boolean hasMandatory(){
        return this.mandatory != null && !this.mandatory.isEmpty();
    }

    protected TopFood(Parcel in) {
        id = in.readInt();
        name = in.readString();
        image = in.readString();
        description = in.readString();
        price = in.readFloat();
        categoryId = in.readInt();
        restaurantId = in.readInt();
        mandatory = in.createTypedArrayList(TopFood.CREATOR);
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

    public List<TopFood> getMandatory() {
        return mandatory;
    }

    public void setMandatory(List<TopFood> mandatory) {
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
