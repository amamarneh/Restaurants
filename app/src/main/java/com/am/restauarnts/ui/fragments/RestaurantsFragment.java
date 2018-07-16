package com.am.restauarnts.ui.fragments;


import android.arch.lifecycle.Observer;
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
import com.am.restauarnts.task.LiveResponse;
import com.am.restauarnts.ui.adapters.RestaurantsAdapter;
import com.am.restauarnts.ui.models.Restaurant;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RestaurantsFragment extends Fragment {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private RecyclerView.Adapter mAdapter;

    public RestaurantsFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_restaurants, container, false);
        ButterKnife.bind(this,view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        RepoFactory.getRepoInstance()
                .getRestaurants()
                .observe(this, response->{
                    if (response == null) return;
                    if (response.isSuccessful()){
                        mAdapter = new RestaurantsAdapter(response.data);
                        recyclerView.setAdapter(mAdapter);
                    }
                });
        return view;
    }

}
