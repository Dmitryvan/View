package com.dataart.selenium.framework;

import org.testng.ITestResult;
import org.testng.annotations.*;
import java.util.concurrent.TimeUnit;
import static com.dataart.selenium.framework.BasePage.driver;

public class BaseTest {
    private Settings settings = new Settings();

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() {
        String browser = settings.getPropertyOrNull("selenium.browser");
        BrowserType browserType = BrowserType.convertToBrowserType(browser);
        BasePage.driver = settings.getDriver(browserType);
        BasePage.settings = settings;
        BasePage.driver.get(settings.getUrl());
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
    public void afterClass() {
        BasePage.driver.quit();
    }
}