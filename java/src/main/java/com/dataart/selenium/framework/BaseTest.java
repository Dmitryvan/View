package com.dataart.selenium.framework;

import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class BaseTest {
    private static Settings settings = new Settings();

    @BeforeSuite(alwaysRun = true)
    public static void beforeSuite() {
        BasePage.driver = settings.getDriver();
        BasePage.settings = settings;
        BasePage.driver.get(settings.getBaseUrl());
        BasePage.driver.manage().window().maximize();
        BasePage.driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    }

    @AfterSuite(alwaysRun = true)
    public static void afterClass() {
        BasePage.driver.quit();
    }
}
