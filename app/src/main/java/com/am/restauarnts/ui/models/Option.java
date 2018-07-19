package com.am.restauarnts.ui.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.am.restauarnts.data.model.OptionEntity;

import java.util.ArrayList;
import java.util.List;

public class Option implements Parcelable{
    private int id;
    private double price;
    private String name;
    private int stockId;

    public Option() {
    }
    public Option(OptionEntity entity) {
        this.id = entity.getId();
        this.price = entity.getPrice();
        this.name = entity.getName();
        this.stockId = entity.getStock_id();
    }
    public static List<Option> getAsList(List<OptionEntity> entities){
        List<Option> options = new ArrayList<>();
        if (entities != null)
        for (OptionEntity e :
                entities) {
            options.add(new Option(e));
        }
        return options;
    }
    protected Option(Parcel in) {
        id = in.readInt();
        price = in.readDouble();
        name = in.readString();
        stockId = in.readInt();
    }

    public static final Creator<Option> CREATOR = new Creator<Option>() {
        @Override
        public Option createFromParcel(Parcel in) {
            return new Option(in);
        }

        @Override
        public Option[] newArray(int size) {
            return new Option[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStockId() {
        return stockId;
    }

    public void setStockId(int stockId) {
        this.stockId = stockId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeDouble(price);
        parcel.writeString(name);
        parcel.writeInt(stockId);
    }
}
