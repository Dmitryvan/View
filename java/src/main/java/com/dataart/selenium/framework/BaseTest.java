package com.dataart.selenium.framework;

import org.testng.ITestResult;
import org.testng.annotations.*;
import java.util.concurrent.TimeUnit;
import static com.dataart.selenium.framework.BasePage.driver;
import static com.dataart.selenium.framework.BrowserType.*;

public class BaseTest {
    private static Settings settings = new Settings();
    public static boolean selectUrl = false;  // chosen login page
    // true - open the url with base authentication,
    // false - open the regular url

    @BeforeSuite(alwaysRun = true)
    public static void beforeSuite() {
        BasePage.driver = settings.getDriver(GC);
        BasePage.settings = settings;
        BasePage.driver.get(settings.getUrl(selectUrl));
        BasePage.driver.manage().window().maximize();
        BasePage.driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (ITestResult.FAILURE==result.getStatus()) {
            Utils.captureScreenshot(driver, result.getName());
        }
    }

    @AfterSuite(alwaysRun = true)
    public static void afterClass() {
        BasePage.driver.quit();
    }

}