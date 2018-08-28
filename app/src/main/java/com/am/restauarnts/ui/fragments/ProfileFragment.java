package com.am.restauarnts.ui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.am.restauarnts.R;
import com.am.restauarnts.repo.RepoFactory;
import com.am.restauarnts.ui.adapters.MyOrdersAdapter;
import com.am.restauarnts.ui.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileFragment extends BaseFragment {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.loadingBar)
    ProgressBar loadingBar;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvPhone)
    TextView tvPhone;
    public ProfileFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this,view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        tvName.setText(RepoFactory.getUserRepo()
                .getCurrentUser()
                .getName());
        tvPhone.setText(RepoFactory.getUserRepo()
                .getCurrentUser()
                .getPhone());

        loadingBar.setVisibility(View.VISIBLE);
        RepoFactory.getRepoInstance()
                .getMyOrders()
                .observe(this,response->{
                    if (response == null) return;

                    loadingBar.setVisibility(View.GONE);
                    if (response.isSuccessful()){
                        MyOrdersAdapter adapter = new MyOrdersAdapter(response.data);
                        recyclerView.setAdapter(adapter);
                        runLayoutAnimation(recyclerView);
                    }else{
                        //
                    }
                });
        return view;
    }

}
