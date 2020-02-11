package com.dataart.selenium.tests;

import com.dataart.selenium.framework.BaseTest;
import com.dataart.selenium.models.User;
import com.dataart.selenium.pages.BasicPage;
import com.dataart.selenium.pages.HeaderPage;
import com.dataart.selenium.pages.LoginPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static com.dataart.selenium.framework.Utils.isElementPresent;
import static com.dataart.selenium.models.UserBuilder.admin;
import static org.fest.assertions.Assertions.assertThat;
import static com.dataart.selenium.framework.BasePage.initPage;

public class LoginTest extends BaseTest {

    private LoginPage loginPage;
    private BasicPage basicPage;
    private HeaderPage headerPage;
    private User user;

    @BeforeMethod(alwaysRun = true)
    public void openLoginPage() {
        basicPage = initPage(BasicPage.class);
        loginPage = basicPage.forceLogout();
        headerPage = initPage(HeaderPage.class);
        user = admin();
    }

    @Test
    public void correctLoginTest(){
        System.out.println("correctLoginTest STARTED");
        loginPage.loginAs(user);
        headerPage.assertHeader(user);
        System.out.println("correctLoginTest is DONE");
    }

    @Test
    public void incorrectLoginTest() {
        System.out.println("incorrectLoginTest STARTED");
        user.setPassword(user.getPassword() + user.getPassword());
        loginPage.loginAs(user);
        assertThat(isElementPresent(basicPage.flash)).isTrue();
        assertThat(basicPage.getFlashMessage()).isEqualTo("You have entered an invalid username or password!");
        System.out.println("incorrectLoginTest is DONE");
    }

    @Test
    public void incorrectThenCorrectTest() {
        System.out.println("incorrectThenCorrectTest STARTED");
        user.setPassword(user.getPassword() + user.getPassword());
        loginPage.loginAs(user);
        user.setPassword(admin().getPassword());
        loginPage.loginAs(user);
        headerPage.assertHeader(user);
        System.out.println("incorrectThenCorrectTest is DONE");
    }
}