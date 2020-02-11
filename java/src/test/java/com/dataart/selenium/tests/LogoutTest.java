package com.dataart.selenium.tests;

import com.dataart.selenium.framework.BaseTest;
import com.dataart.selenium.models.User;
import com.dataart.selenium.pages.BasicPage;
import com.dataart.selenium.pages.HomePage;
import com.dataart.selenium.pages.LoginPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static com.dataart.selenium.framework.BasePage.initPage;
import static com.dataart.selenium.framework.Utils.isElementPresent;
import static com.dataart.selenium.models.UserBuilder.admin;
import static org.fest.assertions.Assertions.assertThat;

public class LogoutTest extends BaseTest {
    private LoginPage loginPage;
    private BasicPage basicPage;
    private HomePage homePage;
    private User user;

    @BeforeMethod(alwaysRun = true)
    public void openLoginPage(){
        basicPage = initPage(BasicPage.class);
        loginPage = basicPage.forceLogout();
        homePage = initPage(HomePage.class);
        user = admin();
    }

    @Test
    //Login as a valid user. Open a new browser tab and logout. Switch to the first tab and click on any link.
    // User should be logged out automatically.
    public void logoutTest() {
        System.out.println("logoutTest STARTED");
        loginPage.loginAs(user);
        homePage.openNewTabBrowser();
        homePage.openLoginPageLink();
        basicPage.forceLogout();
        homePage.switchToOldTabBrowser();
        homePage.clickHomeBtn();
        assertThat(isElementPresent(loginPage.LOGIN_BUTTON_XPATH)).isTrue();
        System.out.println("logoutTest is DONE");
    }
}