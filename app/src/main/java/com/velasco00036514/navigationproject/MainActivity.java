package com.velasco00036514.navigationproject;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    RestaurantPagerAdapter pagerAdapter;
    List<Restaurant> restaurantList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //setting up the Toolbar
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);

        //setting up the PagerAdapter
        pagerAdapter = new RestaurantPagerAdapter(getSupportFragmentManager(), this);
        pagerAdapter.addItem("Restaurants", new RestaurantListFragment());
        pagerAdapter.addItem("Favorites", new RestaurantListFragment());

        //setting up the Viewpager
        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(pagerAdapter);

        //setting up the Tablayout
        tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setupWithViewPager(viewPager, true);


        //setting up
    }

    private ArrayList<Restaurant> fillRestaurants(){
        ArrayList<Restaurant> l = new ArrayList<>();
        l.add(new Restaurant(1, "Pizza Hut", 3, false));
        l.add(new Restaurant(1, "Domino's Pizza", 4, false));
        l.add(new Restaurant(1, "Papa Jhons", 3, false));
        l.add(new Restaurant(1, "Little Ceasars", 2, false));

        return l;
    }
}
