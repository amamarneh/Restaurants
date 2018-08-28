package com.am.restauarnts.ui.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.am.restauarnts.R;
import com.am.restauarnts.ui.fragments.CartViewFragment;
import com.am.restauarnts.ui.fragments.HomeFragment;
import com.am.restauarnts.ui.fragments.ProfileFragment;
import com.am.restauarnts.ui.fragments.RestaurantsFragment;
import com.am.restauarnts.ui.models.Cart;
import com.jaeger.library.StatusBarUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity implements CartViewFragment.OnFragmentInteractionListener {

    @BindView(R.id.tvTitle)
    TextView tvTitle;
    private Fragment mFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setSupportActionBar(findViewById(R.id.toolbar));
        ButterKnife.bind(this);
//        StatusBarUtil.setTranslucentForImageView(this,0,findViewById(R.id.view_need_offset));

        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.bottom_navigation);


        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_explore:
                                replaceFragment(new RestaurantsFragment(),false);
                                setTitle("Discover Restaurants");
                                break;
                            case R.id.action_cart:
                                replaceFragment(new CartViewFragment(),false);
                                setTitle("Cart");
                                break;
                            case R.id.action_profile:
                                replaceFragment(new ProfileFragment(),false);
                                setTitle("Profile");
                                break;
                            case R.id.action_home:
                                replaceFragment(new HomeFragment(),false);
                                setTitle("Home");
                                break;
                        }
                        return true;
                    }
                });

        replaceFragment(new HomeFragment(),false);// main fragment
        setTitle("Home");
    }

    @Override
    public void setTitle(CharSequence title) {
        tvTitle.setText(title);
    }

    protected void replaceFragment(Fragment fragment, boolean addToStack){
        mFragment = fragment;
        FragmentManager fm = getSupportFragmentManager();
        if(addToStack)
            fm.beginTransaction().replace(R.id.content,fragment).addToBackStack(null).commit();
        else
            fm.beginTransaction().replace(R.id.content,fragment).commit();
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
    }
}
