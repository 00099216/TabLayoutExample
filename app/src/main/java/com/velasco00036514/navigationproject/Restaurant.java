package com.velasco00036514.navigationproject;

public class Restaurant {
    private int id;
    private String name;
    private float rating;
    private boolean favorite;

    public Restaurant(int id, String name, float rating, boolean favorite) {
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.favorite = favorite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }
}
