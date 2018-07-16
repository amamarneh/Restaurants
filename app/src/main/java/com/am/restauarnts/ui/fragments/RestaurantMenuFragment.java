package com.am.restauarnts.ui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.am.restauarnts.R;
import com.am.restauarnts.repo.RepoFactory;
import com.am.restauarnts.ui.adapters.MenuAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RestaurantMenuFragment extends Fragment {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    public RestaurantMenuFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_restaurant_menu, container, false);
        ButterKnife.bind(this,rootView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        RepoFactory.getRepoInstance()
                .getMenu(null)
                .observe(this,response->{
                    if (response == null) return;
                    if (response.isSuccessful()){
                        MenuAdapter adapter = new MenuAdapter(response.data);
                        recyclerView.setAdapter(adapter);
                    }
                });
        return rootView;
    }

}
