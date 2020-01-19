package com.dataart.selenium.tests;

import com.dataart.selenium.framework.BaseTest;
import com.dataart.selenium.models.User;
import com.dataart.selenium.pages.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static com.dataart.selenium.framework.BasePage.initPage;
import static com.dataart.selenium.models.UserBuilder.admin;
import static org.fest.assertions.Assertions.assertThat;

public class AjaxTest extends BaseTest {

    private AjaxPage ajaxPage;
    private LoginPage loginPage;
    private BasicPage basicPage;
    private HeaderPage headerPage;
    private HomePage homePage;
    private AppPage appPage;
    private MyAppPage myAppPage;
    private User user;

    @BeforeMethod(alwaysRun = true)
    public void openLoginPage() {
        ajaxPage = initPage(AjaxPage.class);
        basicPage = initPage(BasicPage.class);
        loginPage = basicPage.forceLogout();
        headerPage = initPage(HeaderPage.class);
        homePage = initPage(HomePage.class);
        appPage = initPage(AppPage.class);
        myAppPage = initPage(MyAppPage.class);
        user = admin();
    }

    @Test
    // Enter two valid numbers, click ‘Sum’, wait for the result and check if the result is correct.
    public void calcSumCorrect() {
        loginPage.loginAs(user);
        homePage.clickAjaxTestBtn();
        ajaxPage.calcSum("1", "2");
        assertThat(ajaxPage.result.getText()).isEqualTo("Result is: 3.0");
    }

    @Test
    // Enter one valid number and one string (not a number), click ‘Sum’, wait for the result, and verify that the message ‘Incorrect data’ appears.
    public void calcSumIncorrect(){
        loginPage.loginAs(user);
        homePage.clickAjaxTestBtn();
        ajaxPage.calcSum("1","qwerty");
        assertThat(ajaxPage.result.getText()).isEqualTo("Result is: Incorrect data");
    }
}
