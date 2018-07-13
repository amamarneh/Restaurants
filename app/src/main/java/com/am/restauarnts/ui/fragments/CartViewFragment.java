package com.am.restauarnts.ui.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.am.restauarnts.R;
import com.am.restauarnts.ui.adapters.CartItemsAdapter;
import com.am.restauarnts.ui.models.Cart;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CartViewFragment extends Fragment implements CartItemsAdapter.CartAdapterListener{

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    private RecyclerView recyclerView;
    private Button btnAddItems, btnSendOrder;
    private CartItemsAdapter mAdapter;
    private TextView tvTotal,tvRestaurantName;
    private View cardRestaurantClosed, cardRestaurantOpen;
    public CartViewFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_cart_view, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.recyclerView);
        btnAddItems = view.findViewById(R.id.btnAddItems);
        btnSendOrder = view.findViewById(R.id.btnSendOrder);
        tvTotal = view.findViewById(R.id.tvTotal);
        tvRestaurantName = view.findViewById(R.id.tvRestaurantName);
        cardRestaurantClosed = view.findViewById(R.id.cardRestaurantClosed);
        cardRestaurantOpen = view.findViewById(R.id.cardRestaurantOpen);

        LinearLayoutManager linearLayout = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayout);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                linearLayout.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        mAdapter = new CartItemsAdapter(Cart.getCart(getContext()).getCartItems(),this);
        recyclerView.setAdapter(mAdapter);


        getRestaurantStatus();

        //update ui
        onCartItemsChanged();

        btnSendOrder.setOnClickListener(view1 -> {

        });
        btnAddItems.setOnClickListener(view1 -> {

        });
    }

    private void getRestaurantStatus() {
        if(Cart.getCart(getContext()).isEmpty()){
            progressBar.setVisibility(View.GONE);
            cardRestaurantOpen.setVisibility(View.GONE);
            cardRestaurantClosed.setVisibility(View.GONE);
            tvRestaurantName.setVisibility(View.GONE);
        }else {
            tvRestaurantName.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.VISIBLE);
            cardRestaurantOpen.setVisibility(View.GONE);
            cardRestaurantClosed.setVisibility(View.GONE);
        }
    }

    @Override
    public void onCartItemsChanged() {
        Cart cart = Cart.getCart(getContext());
        if(cart.isEmpty())
            return;
        tvTotal.setText(cart.getPrice()+" NIS");
        tvRestaurantName.setText(cart.getRestaurant().getName());
    }
}
