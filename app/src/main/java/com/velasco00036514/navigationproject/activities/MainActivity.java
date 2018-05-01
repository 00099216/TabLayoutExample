package com.velasco00036514.navigationproject.activities;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.velasco00036514.navigationproject.R;
import com.velasco00036514.navigationproject.adapters.RestaurantPagerAdapter;
import com.velasco00036514.navigationproject.fragments.RestaurantListFragment;
import com.velasco00036514.navigationproject.models.Restaurant;

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
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelableArrayList("restaurantList", (ArrayList<? extends Parcelable>) restaurantList);

        //saving fragments instance
        if (restaurantFragment.isAdded())
            getSupportFragmentManager().putFragment(outState, "restaurantFragment", restaurantFragment);

        if (favoriteFragment.isAdded())
            getSupportFragmentManager().putFragment(outState, "favoriteFragment", favoriteFragment);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null || !savedInstanceState.containsKey("instance")){
            //setting up initial list
            restaurantList = fillRestaurants();

            //creating fragments
            restaurantFragment = new RestaurantListFragment();
            favoriteFragment = new RestaurantListFragment();

            //configuring fragments
            restaurantFragment.setList(restaurantList);
            favoriteFragment.setList(favRestaurants(restaurantList));
        } else {
            restaurantList = savedInstanceState.getParcelableArrayList("instance");
            restaurantFragment = (RestaurantListFragment) getSupportFragmentManager().getFragment(savedInstanceState, "restaurantFragment");
            favoriteFragment = (RestaurantListFragment) getSupportFragmentManager().getFragment(savedInstanceState, "favoriteFragment");
        }

        //setting up the Toolbar
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);

        //setting up the PagerAdapter
        pagerAdapter = new RestaurantPagerAdapter(getSupportFragmentManager(), this);
        pagerAdapter.addItem("Restaurants", restaurantFragment);
        pagerAdapter.addItem("Favorites", favoriteFragment);

        //setting up the Viewpager
        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                List<Restaurant> newList = position == 0 ? restaurantList : favRestaurants(restaurantList);
                RestaurantListFragment listFragment = ((RestaurantListFragment)pagerAdapter.getItem(position));
                if (listFragment != null)
                    listFragment.updateList(newList);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //setting up the Tablayout
        tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setupWithViewPager(viewPager, true);
    }

    private ArrayList<Restaurant> fillRestaurants(){
        ArrayList<Restaurant> l = new ArrayList<>();
        String info = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.";
        l.add(new Restaurant(1, "Pizza Hut", info, 3, false));
        l.add(new Restaurant(1, "Domino's Pizza", info, 4, false));
        l.add(new Restaurant(1, "Papa Jhons", info, 3, false));
        l.add(new Restaurant(1, "Little Ceasars", info, 2, true));

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
