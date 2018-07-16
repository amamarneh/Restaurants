package com.am.restauarnts.ui.adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.am.restauarnts.ui.fragments.RestaurantDetailsFragment;
import com.am.restauarnts.ui.fragments.RestaurantMenuFragment;
import com.am.restauarnts.ui.fragments.RestaurantsFragment;
import com.am.restauarnts.ui.models.Restaurant;

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private Restaurant restaurant;

    public SectionsPagerAdapter(FragmentManager fm, Restaurant restaurant) {
        super(fm);
        this.restaurant = restaurant;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return RestaurantMenuFragment.getInstance(restaurant);
            case 1:
                default:
                return new RestaurantDetailsFragment();
        }
    }
    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Menu";
            case 1:
                return "Details";
        }
        return null;
    }
}