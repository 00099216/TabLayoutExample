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

    public void updateList(List<Restaurant> l){
        restaurantList = l;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull final RestaurantViewHolder holder, final int position) {
        final Restaurant restaurant = restaurantList.get(position);

        holder.restaurantTitle.setText(restaurantList.get(position).getName());
        holder.favImage.setImageResource(restaurant.isFavorite() ? android.R.drawable.btn_star_big_on : android.R.drawable.btn_star_big_off);
        holder.favImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (restaurant.isFavorite()){
                    holder.favImage.setImageResource(android.R.drawable.btn_star_big_off);
                    restaurantList.get(position).setFavorite(false);
                }else{
                    holder.favImage.setImageResource(android.R.drawable.btn_star_big_on);
                    restaurantList.get(position).setFavorite(true);
                }

                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        if (restaurantList != null)
            return restaurantList.size();
        else
            return 0;
    }

    protected class RestaurantViewHolder extends RecyclerView.ViewHolder {
        ImageView restaurantImage, favImage;
        TextView restaurantTitle;

        public RestaurantViewHolder(View itemView) {
            super(itemView);

            restaurantImage = itemView.findViewById(R.id.restaurant_image);
            restaurantTitle = itemView.findViewById(R.id.restaurantTitle);
            favImage = itemView.findViewById(R.id.favIcon);
        }
    }
}
