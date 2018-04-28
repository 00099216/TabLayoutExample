package com.velasco00036514.navigationproject;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class RestaurantListFragment extends Fragment {
    RecyclerView recyclerView;
    RestaurantsAdapter restaurantsAdapter;
    List<Restaurant> restaurantList;
    boolean isFavorite = true;

    onRestaurantSelected mCallBack;

    public RestaurantListFragment() {
    }

    public interface onRestaurantSelected{
        public List<Restaurant> getFragmentList(boolean isFav);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mCallBack = (onRestaurantSelected) context;
        } catch (ClassCastException e){
            throw new ClassCastException(context.toString() + "must implement onRestaurantSelectedListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.restaurants_list, container, false);

        //setting up restaurant adapter
        restaurantsAdapter = new RestaurantsAdapter(getContext(), restaurantList);

        //setting up recyclerview
        recyclerView = v.findViewById(R.id.recyclerView);
        recyclerView.setAdapter(restaurantsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));

        return v;
    }

    public void setFav(boolean fav){
        this.isFavorite = fav;
    }

    public void setList(List<Restaurant> l){
        this.restaurantList = l;
    }
}
