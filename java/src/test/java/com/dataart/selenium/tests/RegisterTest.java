package com.dataart.selenium.tests;

import com.dataart.selenium.dataproviders.MyDataProvider;
import com.dataart.selenium.framework.BaseTest;
import com.dataart.selenium.models.User;
import com.dataart.selenium.pages.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static com.dataart.selenium.framework.BasePage.driver;
import static com.dataart.selenium.framework.BasePage.initPage;
import static com.dataart.selenium.models.UserBuilder.admin;

public class RegisterTest extends BaseTest {

    private RegisterPage registerPage;
    private LoginPage loginPage;
    private BasicPage basicPage;
    private HeaderPage headerPage;
    private HomePage homePage;
    private AppPage appPage;
    private User user;

    @BeforeMethod(alwaysRun = true)
    public void openLoginPage() {
        basicPage = initPage(BasicPage.class);
        loginPage = basicPage.forceLogout();
        headerPage = initPage(HeaderPage.class);
        registerPage = initPage(RegisterPage.class);
        homePage = initPage(HomePage.class);
        appPage = initPage(AppPage.class);
        user = admin();
    }

    @Test
    // Register a new user, and verify that the user is logged in.
    public void registrationUserTest() {
        System.out.println("registrationUserTest STARTED");
        loginPage.clickRegNewUserBtn();
        registerPage.clickUserRole();
        registerPage.registrationNewUser(user);
        headerPage.assertHeader(user);
        System.out.println("registrationUserTest is DONE");
    }

    @Test
    // Register a new user, logout, and verify that the user can login.
    public void loginAfterRegistrationTest(){
        System.out.println("loginAfterRegistrationTest STARTED");
        loginPage.clickRegNewUserBtn();
        registerPage.clickUserRole();
        registerPage.registrationNewUser(user);
        basicPage.forceLogout();
        loginPage.loginAs(user);
        headerPage.assertHeader(user);
        System.out.println("loginAfterRegistrationTest is DONE");
    }

    @Test
    // Register as a developer, verify that the developer can open the page to upload an application.
    public void devUploadApp() {
        System.out.println("devUploadApp STARTED");
        loginPage.clickRegNewUserBtn();
        driver.navigate().refresh();
        registerPage.registrationNewUser(user);
        homePage.clickAppIcon();
        Assert.assertTrue(AppPage.downloadBtn.isDisplayed());
        System.out.println("devUploadApp is DONE");
    }

    @Test
    // Register as a regular user, verify that the regular user can see the applications but cannot upload them.
    public void userCantUploadApp(){
        System.out.println("userCantUploadApp STARTED");
        loginPage.clickRegNewUserBtn();
        registerPage.clickUserRole();
        registerPage.registrationNewUser(user);
        homePage.clickAppIcon();
        Assert.assertTrue(AppPage.downloadBtn.isDisplayed());
        System.out.println("userCantUploadApp is DONE");
    }

    @Test(dataProvider = "usersregister", dataProviderClass = MyDataProvider.class)
    // Register at least 5 users with different roles.
    // run one test as many times with as many users you put in the .CSV file.
    public void registrationFiveTimesTest(String username, String fname, String lname, String password, String userRole) {
        System.out.println("registrationFiveTimesTest STARTED");
        loginPage.clickRegNewUserBtn();
        registerPage.checkRole(userRole);
        registerPage.registerViaDataProvider(username, fname, lname, password);
        headerPage.assertHeaderViaDataProvider(fname, lname);
        System.out.println("registrationFiveTimesTest is DONE");
    }
}