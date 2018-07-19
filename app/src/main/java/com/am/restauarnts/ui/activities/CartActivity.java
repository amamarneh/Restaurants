package com.am.restauarnts.ui.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.am.restauarnts.R;
import com.am.restauarnts.ui.base.BaseActivity;
import com.am.restauarnts.ui.fragments.CartFragment;
import com.am.restauarnts.ui.fragments.CartViewFragment;
import com.am.restauarnts.ui.models.Cart;

public class CartActivity extends BaseActivity implements CartViewFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        setupBackArrow();
        setTitle("Cart");

        if (savedInstanceState == null)
            getSupportFragmentManager().beginTransaction().replace(R.id.content, new CartViewFragment())
                .commit();
    }

    @Override
    public void onCartSendClicked() {
        Intent i = new Intent(this, AddressActivity.class);
        startActivity(i);
    }

    @Override
    public void onCartAddClicked() {
        Intent i = RestaurantActivity.getForRestaurant(this, Cart.getCart(this).getRestaurant());
        startActivity(i);
        finish();
    }
}
