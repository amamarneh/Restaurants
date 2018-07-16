package com.am.restauarnts.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.am.restauarnts.R;
import com.am.restauarnts.ui.adapters.SectionsPagerAdapter;
import com.am.restauarnts.ui.base.BaseActivity;
import com.am.restauarnts.ui.models.Restaurant;
import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RestaurantActivity extends BaseActivity {

    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvShortInfo)
    TextView tvShortInfo;
    @BindView(R.id.tvInfo)
    TextView tvInfo;
    @BindView(R.id.ratingBar)
    RatingBar ratingBar;

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private Restaurant mRestaurant;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);
        setupBackArrow();
        ButterKnife.bind(this);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
//        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
        tabLayout.setupWithViewPager(mViewPager);
        mRestaurant = getIntent().getParcelableExtra("model");
        setTitle(mRestaurant.getName());
        tvName.setText(mRestaurant.getName());
        tvInfo.setText(mRestaurant.getInfo() );
        tvShortInfo.setText(mRestaurant.getInfo());
        Glide.with(this).load(mRestaurant.getImage()).into(imageView);

    }
    public static Intent getForRestaurant(Context context, Restaurant restaurant){
        Intent intent = new Intent(context,RestaurantActivity.class);
        intent.putExtra("model",restaurant);
        return intent;
    }
}
