package com.am.restauarnts.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.am.restauarnts.R;
import com.am.restauarnts.ui.adapters.FoodMandatoryAdapter;
import com.am.restauarnts.ui.base.BaseActivity;
import com.am.restauarnts.ui.models.Cart;
import com.am.restauarnts.ui.models.CartItem;
import com.am.restauarnts.ui.models.Food;
import com.am.restauarnts.ui.models.Restaurant;
import com.bumptech.glide.Glide;
import com.jaeger.library.StatusBarUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FoodDetailsActivity extends BaseActivity {
    @BindView(R.id.recyclerViewCat)
    RecyclerView recyclerViewCat;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvInfo)
    TextView tvInfo;
    @BindView(R.id.tvPrice)
    TextView tvPrice;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.tvQuantity)
    TextView tvQuantity;
    @BindView(R.id.txtSpecialRequest)
    EditText txtSpecialRequest;
    @BindView(R.id.btnInc)
    TextView btnInc;
    @BindView(R.id.btnDec)
    TextView btnDec;
    @BindView(R.id.btnAddToCart)
    TextView btnAddToCart;

    private Food mFood;
    private Restaurant mRestaurant;
    private FoodMandatoryAdapter mandatoryAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_details);
        setSupportActionBar(findViewById(R.id.toolbar));
        ButterKnife.bind(this);
        setupBackArrow();
        StatusBarUtil.setTranslucentForImageView(this,0,findViewById(R.id.view_need_offset));
        setTitle("");

        mFood = getIntent().getParcelableExtra("model");
        mRestaurant = getIntent().getParcelableExtra("restaurant");

        tvName.setText(mFood.getName());
        tvInfo.setText(mFood.getDescription());
        tvPrice.setText(mFood.getPrice()+" NIS");
        Glide.with(this).load(mFood.getImage()).into(imageView);

        recyclerViewCat.setLayoutManager(new LinearLayoutManager(this));
        if (mFood.getMandatory() != null && !mFood.getMandatory().isEmpty()){
            mandatoryAdapter = new FoodMandatoryAdapter(mFood.getMandatory());
            recyclerViewCat.setAdapter(mandatoryAdapter);
            recyclerViewCat.setVisibility(View.VISIBLE);
        }else{
            recyclerViewCat.setVisibility(View.GONE);
        }

        btnInc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                increment();
            }
        });
        btnDec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decrement();
            }
        });
        btnAddToCart.setOnClickListener(view -> {
            if(getCartItem() != null) {
                Cart.getCart(this).addItem(this, getCartItem(), mRestaurant);
                Toast.makeText(this, "Added", Toast.LENGTH_SHORT).show();
                finish();
            }else{
                Toast.makeText(this, "Please choose food", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private CartItem getCartItem() {
        int q = Integer.parseInt(tvQuantity.getText().toString());
        CartItem ci;
        if (mFood.hasMandatory()){
            if (mandatoryAdapter.getItem() == null)
                return null;
            ci = new CartItem(mandatoryAdapter.getItem(),q);
        }else{
            ci = new CartItem(mFood,q);
        }
        if(!TextUtils.isEmpty(txtSpecialRequest.getText())){
            String msg = txtSpecialRequest.getText().toString().trim();
            ci.setMessage(msg);
        }
        return ci;
    }
    private void decrement() {
        int q = Integer.parseInt(tvQuantity.getText().toString());
        q--;
        if(q <= 0){
            q = 1;
        }
        tvQuantity.setText(q+"");

    }
    private void increment() {
        int q = Integer.parseInt(tvQuantity.getText().toString());
        q++;
        tvQuantity.setText(q+"");
    }
    public static Intent getForFood(Context context, Food food, Restaurant restaurant){
        Intent i = new Intent(context,FoodDetailsActivity.class);
        i.putExtra("model",food);
        i.putExtra("restaurant",restaurant);
        return i;
    }
}
