package com.am.restauarnts.ui.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.am.restauarnts.R;
import com.am.restauarnts.repo.RepoFactory;
import com.am.restauarnts.ui.adapters.MenuAdapter;
import com.am.restauarnts.ui.base.BaseFragment;
import com.am.restauarnts.ui.models.Restaurant;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RestaurantMenuFragment extends BaseFragment {
    public static final String PARAM_RESTAURANT = "PARAM_RESTAURANT";
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private Restaurant mRestaurant;
    public RestaurantMenuFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
            mRestaurant = getArguments().getParcelable(PARAM_RESTAURANT);
    }
    public static RestaurantMenuFragment getInstance(Restaurant restaurant){
        RestaurantMenuFragment fragment = new RestaurantMenuFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(PARAM_RESTAURANT,restaurant);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_restaurant_menu, container, false);
        ButterKnife.bind(this,rootView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        RepoFactory.getRepoInstance()
                .getMenu(mRestaurant)
                .observe(this,response->{
                    if (response == null) return;
                    if (response.isSuccessful()){
                        MenuAdapter adapter = new MenuAdapter(response.data);
                        recyclerView.setAdapter(adapter);
                        runLayoutAnimation(recyclerView);
                    }
                });
        return rootView;
    }

}
