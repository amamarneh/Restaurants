package com.am.restauarnts.data.response;

import com.am.restauarnts.data.model.TopFoodEntity;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TopFoodResponse extends ServerResponse {
    @SerializedName("records")
    private List<TopFoodEntity> data;

    public List<TopFoodEntity> getData() {
        return data;
    }

    public void setData(List<TopFoodEntity> data) {
        this.data = data;
    }
}
