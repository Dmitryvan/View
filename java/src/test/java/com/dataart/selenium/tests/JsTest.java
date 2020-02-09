package com.dataart.selenium.tests;

import com.dataart.selenium.framework.BaseTest;
import com.dataart.selenium.models.User;
import com.dataart.selenium.pages.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static com.dataart.selenium.framework.BasePage.initPage;
import static com.dataart.selenium.models.UserBuilder.admin;

public class JsTest extends BaseTest {

    private JsPage jsPage;
    private LoginPage loginPage;
    private BasicPage basicPage;
    private HomePage homePage;
    private User user;

    @BeforeMethod(alwaysRun = true)
    public void openLoginPage() {
        jsPage = initPage(JsPage.class);
        basicPage = initPage(BasicPage.class);
        loginPage = basicPage.forceLogout();
        homePage = initPage(HomePage.class);
        user = admin();
    }

    @Test
    // Get the coordinates of the jumping div (with a red border and the text ‘Find me !’ inside).
    // Enter the coordinates into the input fields and press ‘Process’.
    // Handle the alert and verify that the message ‘Whoo Hoooo! Correct!’ is displayed.
    public void handleAlert() throws InterruptedException {
        loginPage.loginAs(user);
        homePage.clickJsTestBtn();
        jsPage.handleCoordinates();
        jsPage.assertPopupMessage();
    }
}