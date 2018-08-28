package com.am.restauarnts.ui.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.am.restauarnts.R;
import com.am.restauarnts.ui.models.Restaurant;

import butterknife.BindView;
import butterknife.ButterKnife;


public class RestaurantDetailsFragment extends Fragment {
    public static final String PARAM_RESTAURANT = "PARAM_RESTAURANT";
    @BindView(R.id.tvStatus)
    TextView tvStatus;
    @BindView(R.id.tvInfo)
    TextView tvInfo;
    @BindView(R.id.tvLocation)
    TextView tvLocation;
    @BindView(R.id.tvDelivery)
    TextView tvDelivery;
    @BindView(R.id.tvHours)
    TextView tvHours;

    private Restaurant mRestaurant;

    public RestaurantDetailsFragment() {
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
            mRestaurant = getArguments().getParcelable(PARAM_RESTAURANT);
    }
    public static RestaurantDetailsFragment getInstance(Restaurant restaurant){
        RestaurantDetailsFragment fragment = new RestaurantDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(PARAM_RESTAURANT,restaurant);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_restaurant_details, container, false);
        ButterKnife.bind(this,view);

        tvStatus.setText(mRestaurant.isOpen()?"OPEN":"CLOSED");
        tvInfo.setText(mRestaurant.getInfo());
        tvLocation.setText(mRestaurant.getAddressName());
        tvDelivery.setText(mRestaurant.isDelivery()?"YES":"NO");
        tvHours.setText(mRestaurant.getOpenHours());

        return view;
    }

}
