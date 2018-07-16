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

    public Restaurant() {
    }
    public Restaurant(RestaurantEntity restaurantEntity) {
        this.id = restaurantEntity.getId();
        this.name = restaurantEntity.getName();
        this.image = restaurantEntity.getImage();
        this.info = restaurantEntity.getInfo();
        this.addressName = restaurantEntity.getAddress_name();
    }


    protected Restaurant(Parcel in) {
        id = in.readInt();
        name = in.readString();
        image = in.readString();
        info = in.readString();
        addressName = in.readString();
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
    }
}
