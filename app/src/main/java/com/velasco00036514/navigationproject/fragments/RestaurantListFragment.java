package com.velasco00036514.navigationproject.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.velasco00036514.navigationproject.R;
import com.velasco00036514.navigationproject.Restaurant;
import com.velasco00036514.navigationproject.adapters.RestaurantsAdapter;

import java.util.List;

public class RestaurantListFragment extends Fragment {
    RecyclerView recyclerView;
    RestaurantsAdapter restaurantsAdapter;
    List<Restaurant> restaurantList;
    onRestaurantSelected mCallBack;

    public RestaurantListFragment() {
    }

    public interface onRestaurantSelected{
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

    public void setList(List<Restaurant> l){
        this.restaurantList = l;
    }
}
