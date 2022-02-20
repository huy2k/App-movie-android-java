package com.example.navigation.model;

public class CommentData {
    private String userID;
    private String content;

    public CommentData(final String userID, final String content) {
        this.userID = userID;
        this.content = content;
    }

    public CommentData(){

    }
    public String getContent() { return content;}

    public void setContent(String content) { this.content = content;}

    public String getUserID() {  return userID;}

    public void setUserID(String userID) { this.userID = userID;}
}
