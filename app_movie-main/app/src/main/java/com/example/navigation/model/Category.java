package com.example.navigation.model;

import com.example.navigation.model.Movie;
import com.example.navigation.model.MovieData;

import java.util.List;

public class Category {
    int id;
    String categoryName;
    String categoryField;
    List<MovieData> movies;

    public String getCategoryField() {
        return this.categoryField;
    }

    public void setCategoryField(final String categoryField) {
        this.categoryField = categoryField;
    }

    public Category(final String categoryName) {
        this.categoryName = categoryName;
    }

    public Category(final String categoryName, final String categoryField) {
        this.categoryName = categoryName;
        this.categoryField = categoryField;
    }

    public List<MovieData> getMovies() {
        return movies;
    }

    public int getId() {
        return id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMovies(List<MovieData> movies) {
        this.movies = movies;
    }
}
