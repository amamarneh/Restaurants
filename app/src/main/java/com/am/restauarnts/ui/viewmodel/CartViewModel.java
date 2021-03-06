package com.am.restauarnts.ui.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.am.restauarnts.ui.models.Cart;

public class CartViewModel extends AndroidViewModel {
    private MutableLiveData<Cart> cartMutableLiveData;

    public CartViewModel(@NonNull Application application) {
        super(application);
    }

    public void refresh(){
        fetchCart();
    }

    public MutableLiveData<Cart> getCartMutableLiveData() {
        if(cartMutableLiveData == null)
            cartMutableLiveData = new MutableLiveData<>();
        fetchCart();
        return cartMutableLiveData;
    }

    private void fetchCart() {
        cartMutableLiveData.setValue(Cart.getCart(getApplication()));
    }
}
