package com.example.navigation.model;

public class AuthorDetailData {
    String name;
    String username;
    String avatar_path;
    int rateing;

    public AuthorDetailData() {
    }

    public AuthorDetailData(final String name, final String username, final String avatar_path, final int rateing) {

        this.name = name;
        this.username = username;
        this.avatar_path = avatar_path;
        this.rateing = rateing;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getAvatar_path() {
        return this.avatar_path;
    }

    public void setAvatar_path(final String avatar_path) {
        this.avatar_path = avatar_path;
    }

    public int getRateing() {
        return this.rateing;
    }

    public void setRateing(final int rateing) {
        this.rateing = rateing;
    }
}
