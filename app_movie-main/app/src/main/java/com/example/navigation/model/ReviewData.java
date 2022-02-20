package com.example.navigation.model;


import com.example.navigation.model.AuthorDetailData;

import java.util.List;

//This Class represents Review's Fields
public class ReviewData {
    private String author;
    AuthorDetailData authorDetailData;
    private String content;

    public ReviewData(final String author, final AuthorDetailData authorDetailData, final String content) {
        this.author = author;
        this.authorDetailData = authorDetailData;
        this.content = content;
    }

    public AuthorDetailData getAuthorDetailData() {
        return this.authorDetailData;
    }

    public void setAuthorDetailData(final AuthorDetailData authorDetailData) {
        this.authorDetailData = authorDetailData;
    }

    public ReviewData(String author, String content) {
        this.author = author;
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
