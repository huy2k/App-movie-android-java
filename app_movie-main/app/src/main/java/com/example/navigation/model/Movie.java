package com.example.navigation.model;

public class Movie {
    private int id_mv;
    private  String title, description;
    private int image;
    float rate;

    public Movie(int id_mv, String title, int image, String description, float rate) {
        this.id_mv = id_mv;
        this.title = title;
        this.image = image;
        this.description = description;
        this.rate = rate;
    }

    public int getId_mv() {
        return id_mv;
    }

    public void setId_mv(int id_mv) {
        this.id_mv = id_mv;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int  image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }
}
