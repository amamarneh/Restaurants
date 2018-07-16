package com.am.restauarnts.data.response;

import com.am.restauarnts.data.model.CategoryEntity;
import com.am.restauarnts.ui.models.Category;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MenuResponse {

    @SerializedName("records")
    private List<CategoryEntity> categories;

    public List<CategoryEntity> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryEntity> categories) {
        this.categories = categories;
    }
}
