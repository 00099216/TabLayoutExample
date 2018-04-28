package com.velasco00036514.navigationproject.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.velasco00036514.navigationproject.R;
import com.velasco00036514.navigationproject.Restaurant;

import java.util.List;

public class RestaurantsAdapter extends RecyclerView.Adapter<RestaurantsAdapter.RestaurantViewHolder>{
    Context mCtx;
    List<Restaurant> restaurantList;

    public RestaurantsAdapter(Context mCtx, List<Restaurant> restaurantList) {
        this.mCtx = mCtx;
        this.restaurantList = restaurantList;
    }

    @NonNull
    @Override
    public RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mCtx).inflate(R.layout.restaurant_cardview, parent, false);
        RestaurantViewHolder vh = new RestaurantViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantViewHolder holder, int position) {
        holder.restaurantTitle.setText(restaurantList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        if (restaurantList != null)
            return restaurantList.size();
        else
            return 0;
    }

    protected class RestaurantViewHolder extends RecyclerView.ViewHolder {
        ImageView restaurantImage;
        TextView restaurantTitle;

        public RestaurantViewHolder(View itemView) {
            super(itemView);

            restaurantImage = itemView.findViewById(R.id.restaurant_image);
            restaurantTitle = itemView.findViewById(R.id.restaurantTitle);
        }
    }
}
