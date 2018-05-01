package com.velasco00036514.navigationproject.adapters;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.velasco00036514.navigationproject.R;
import com.velasco00036514.navigationproject.models.Restaurant;

import java.util.List;

public class RestaurantsAdapter extends RecyclerView.Adapter<RestaurantsAdapter.RestaurantViewHolder> implements Parcelable{
    Context mCtx;
    List<Restaurant> restaurantList;

    public RestaurantsAdapter(Context mCtx, List<Restaurant> restaurantList) {
        this.mCtx = mCtx;
        this.restaurantList = restaurantList;
    }

    protected RestaurantsAdapter(Parcel in) {
        restaurantList = in.createTypedArrayList(Restaurant.CREATOR);
    }

    public static final Creator<RestaurantsAdapter> CREATOR = new Creator<RestaurantsAdapter>() {
        @Override
        public RestaurantsAdapter createFromParcel(Parcel in) {
            return new RestaurantsAdapter(in);
        }

        @Override
        public RestaurantsAdapter[] newArray(int size) {
            return new RestaurantsAdapter[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(restaurantList);
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
