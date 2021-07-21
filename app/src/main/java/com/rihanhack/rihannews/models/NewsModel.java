package com.rihanhack.rihannews.models;

public class NewsModel {
    String title,description,source,image;

    public NewsModel(String title, String description, String source, String image) {
        this.title = title;
        this.description = description;
        this.source = source;
        this.image = image;
    }

    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getSource() { return source; }
    public String getImage() { return image; }
}
