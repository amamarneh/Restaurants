package com.am.restauarnts.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.am.restauarnts.ui.models.Cart;
import com.google.gson.Gson;

public class CartUtil{
    public static Cart getCart(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("cart",Context.MODE_PRIVATE);
        String cartJson  = sharedPreferences.getString("cartJson",null);
        if(cartJson == null)
            return null;
        Cart cart = new Gson().fromJson(cartJson,Cart.class);
        return cart;
    }
    public static void saveCart(Context context, Cart cart){
        SharedPreferences sp = context.getSharedPreferences("cart",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("cartJson",new Gson().toJson(cart));
        editor.apply();
    }
}