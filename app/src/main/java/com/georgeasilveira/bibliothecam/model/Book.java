package com.georgeasilveira.bibliothecam.model;

import androidx.annotation.NonNull;

public class Book {
    private long id;
    private String title;
    private String author;
    private int page;
    private boolean favorite;
    private String location;

    public long getId() {
        return id;
    }

    public Book setId(long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Book setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public Book setAuthor(String author) {
        this.author = author;
        return this;
    }

    public int getPage() {
        return page;
    }

    public Book setPage(int page) {
        this.page = page;
        return this;
    }

    public boolean getFavorite() {
        return favorite;
    }

    public Book setFavorite(int favorite) {
        this.favorite = favorite != 0;
        return this;
    }

    public String getLocation() {
        return location;
    }

    public Book setLocation(String location) {
        this.location = location;
        return this;
    }

    @NonNull
    @Override
    public String toString() {
        return "Book{" +
                "Title - " + getTitle() + '\'' +
                ", Author - " + getAuthor() + '\'' +
                ", Page - " + getPage() + '\'' +
                ", Favorite - " + getFavorite() +
                '}';
    }
}
