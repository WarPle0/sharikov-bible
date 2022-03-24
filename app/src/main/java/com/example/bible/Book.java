package com.example.bible;

public class Book {
    String title;
    String link;

    public Book(String title, String link) {
        this.title = title;
        this.link = link;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLink() {
        return link;
    }
}
