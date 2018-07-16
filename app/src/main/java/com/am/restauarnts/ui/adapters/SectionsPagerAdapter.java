package com.am.restauarnts.ui.adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.am.restauarnts.ui.fragments.RestaurantDetailsFragment;
import com.am.restauarnts.ui.fragments.RestaurantMenuFragment;

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new RestaurantMenuFragment();
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