package com.am.restauarnts.ui.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.am.restauarnts.data.model.RestaurantEntity;


/**
 * Created by ALa on 3/13/2018.
 */

public class Restaurant implements Parcelable{
    private int id;
    private String name;
    private String image;
    private String info;
    private String addressName;
    private boolean isDelivery;
    private boolean isOpen; //new
    private String openHours; //new
    private int stars;


    public Restaurant() {
    }
    public Restaurant(RestaurantEntity restaurantEntity) {
        this.id = restaurantEntity.getId();
        this.name = restaurantEntity.getName();
        this.image = restaurantEntity.getImage();
        this.info = restaurantEntity.getInfo();
        this.addressName = restaurantEntity.getAddress_name();
        this.isDelivery = restaurantEntity.getIs_delivery() == 1;
        this.isOpen = restaurantEntity.getStatus() == 1;
        this.openHours = restaurantEntity.getOpen_hours();
        this.stars = 5;
    }


    protected Restaurant(Parcel in) {
        id = in.readInt();
        name = in.readString();
        image = in.readString();
        info = in.readString();
        addressName = in.readString();
        isDelivery = in.readByte() == 1;
        isOpen = in.readByte() == 1;
        openHours = in.readString();
        stars = in.readInt();
    }

    public static final Creator<Restaurant> CREATOR = new Creator<Restaurant>() {
        @Override
        public Restaurant createFromParcel(Parcel in) {
            return new Restaurant(in);
        }

        @Override
        public Restaurant[] newArray(int size) {
            return new Restaurant[size];
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

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }
    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public boolean isDelivery() {
        return isDelivery;
    }

    public String getOpenHours() {
        return openHours;
    }

    public void setOpenHours(String openHours) {
        this.openHours = openHours;
    }

    public void setDelivery(boolean delivery) {
        isDelivery = delivery;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
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
        parcel.writeString(info);
        parcel.writeString(addressName);
        parcel.writeByte((byte) (isDelivery?1:0));
        parcel.writeByte((byte) (isOpen?1:0));
        parcel.writeString(openHours);
        parcel.writeInt(stars);
    }
}
