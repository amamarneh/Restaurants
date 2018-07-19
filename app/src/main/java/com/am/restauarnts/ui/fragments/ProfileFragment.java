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
import com.am.restauarnts.ui.adapters.MyOrdersAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileFragment extends Fragment {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    public ProfileFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this,view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        RepoFactory.getRepoInstance()
                .getMyOrders()
                .observe(this,response->{
                    if (response == null) return;
                    if (response.isSuccessful()){
                        MyOrdersAdapter adapter = new MyOrdersAdapter(response.data);
                        recyclerView.setAdapter(adapter);
                    }else{
                        //
                    }
                });
        return view;
    }

}
