package com.dataart.selenium.models;

public class Application {
    private String title;
    private String description;
    private String category;
    private String imageIcon;

    public Application(String imageIcon, String title) {
        this.imageIcon = imageIcon;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }
    public String getImageIcon() {
        return imageIcon;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setImageIcon (String imageIcon) {
        this.imageIcon = imageIcon;
    }


}