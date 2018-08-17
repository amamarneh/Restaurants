package com.am.restauarnts.ui.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.am.restauarnts.R;
import com.am.restauarnts.repo.RepoFactory;
import com.am.restauarnts.ui.adapters.TopFoodAdapter;
import com.am.restauarnts.ui.models.TopFood;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeFragment extends Fragment {
    @BindView(R.id.txtSearch)
    EditText txtSearch;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private List<TopFood> items;
    private TopFoodAdapter adapter;
    public HomeFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this,view);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        adapter = new TopFoodAdapter(items);
        recyclerView.setAdapter(adapter);
        RepoFactory.getRepoInstance()
                .getTop()
                .observe(this,response -> {
                   if (response == null)return;
                    adapter.setList(response.data);
                });

        return view;
    }

}
