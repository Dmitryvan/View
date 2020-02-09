package com.dataart.selenium.models;

import com.dataart.selenium.pages.MyAppPage;

public class AppBuilder {
    public static Application withoutImages() {
        Application application = new Application(MyAppPage.noimage, MyAppPage.message1);
        return application;
    }
    public static Application withImage() {
        Application application = new Application(MyAppPage.image, MyAppPage.message4);
        return application;
    }
}