package com.am.restauarnts.ui.activities;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.am.restauarnts.R;
import com.am.restauarnts.ui.fragments.CartViewFragment;
import com.am.restauarnts.ui.fragments.RestaurantsFragment;
import com.jaeger.library.StatusBarUtil;

public class HomeActivity extends AppCompatActivity {

    private Fragment mFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        StatusBarUtil.setTranslucentForImageView(this,0,findViewById(R.id.view_need_offset));

        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.bottom_navigation);


        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_explore:
                                replaceFragment(new RestaurantsFragment(),false);
                                setTitle("Discover\nRestaurants");
                                break;
                            case R.id.action_cart:
                                replaceFragment(new CartViewFragment(),false);
                                setTitle("Cart");
                                break;
                            case R.id.action_profile:
//                                replaceFragment(new ProfileFragment(),false);
                                setTitle("Profile");
                                break;
                        }
                        return true;
                    }
                });

        replaceFragment(new RestaurantsFragment(),false);// main fragment
        setTitle("Top");
    }
    protected void replaceFragment(Fragment fragment, boolean addToStack){
        mFragment = fragment;
        FragmentManager fm = getSupportFragmentManager();
        if(addToStack)
            fm.beginTransaction().replace(R.id.content,fragment).addToBackStack(null).commit();
        else
            fm.beginTransaction().replace(R.id.content,fragment).commit();
    }
}
