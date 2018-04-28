package com.velasco00036514.navigationproject.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.velasco00036514.navigationproject.R;
import com.velasco00036514.navigationproject.Restaurant;
import com.velasco00036514.navigationproject.adapters.RestaurantPagerAdapter;
import com.velasco00036514.navigationproject.fragments.RestaurantListFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RestaurantListFragment.onRestaurantSelected {
    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    RestaurantPagerAdapter pagerAdapter;
    List<Restaurant> restaurantList;
    RestaurantListFragment restaurantFragment, favoriteFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //setting up the Toolbar
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);

        //setting up initial list
        restaurantList = fillRestaurants();

        //creating fragments
        restaurantFragment = new RestaurantListFragment();
        favoriteFragment = new RestaurantListFragment();

        //configuring fragments
        restaurantFragment.setList(restaurantList);
        favoriteFragment.setList(favRestaurants(restaurantList));

        //setting up the PagerAdapter
        pagerAdapter = new RestaurantPagerAdapter(getSupportFragmentManager(), this);
        pagerAdapter.addItem("Restaurants", restaurantFragment);
        pagerAdapter.addItem("Favorites", favoriteFragment);

        //setting up the Viewpager
        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(pagerAdapter);

        //setting up the Tablayout
        tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setupWithViewPager(viewPager, true);
    }

    private ArrayList<Restaurant> fillRestaurants(){
        ArrayList<Restaurant> l = new ArrayList<>();
        l.add(new Restaurant(1, "Pizza Hut", 3, false));
        l.add(new Restaurant(1, "Domino's Pizza", 4, false));
        l.add(new Restaurant(1, "Papa Jhons", 3, false));
        l.add(new Restaurant(1, "Little Ceasars", 2, true));

        return l;
    }

    private ArrayList<Restaurant> favRestaurants(List<Restaurant> restaurants){
        ArrayList<Restaurant> favs = new ArrayList<>();

        for (Restaurant restaurant : restaurants){
            if (restaurant.isFavorite()) favs.add(restaurant);
        }

        return favs;
    }
}
