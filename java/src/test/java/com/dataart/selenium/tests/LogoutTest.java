package com.dataart.selenium.tests;

import com.dataart.selenium.framework.BasePage;
import com.dataart.selenium.framework.BaseTest;
import com.dataart.selenium.models.User;
import com.dataart.selenium.pages.BasicPage;
import com.dataart.selenium.pages.HomePage;
import com.dataart.selenium.pages.LoginPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.dataart.selenium.framework.BasePage.initPage;
import static com.dataart.selenium.models.UserBuilder.admin;

public class LogoutTest extends BaseTest {

    private LoginPage loginPage;
    private BasicPage basicPage;
    private User user;

    @BeforeMethod(alwaysRun = true)
    public void openLoginPage(){
        basicPage = initPage(BasicPage.class);
        loginPage = basicPage.forceLogout();
        user = admin();
    }

    @Test
    public void logoutTest() {
        loginPage.loginAs(user);
        HomePage.openNewTabBrowser();
        BasePage.driver.get("http://192.168.197.61:8080");
        basicPage.forceLogout();
        HomePage.switchToOldTabBrowser();
        HomePage.clickHomeBtn();
    }
}
