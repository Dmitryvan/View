package com.dataart.selenium.tests;

import com.dataart.selenium.framework.BaseTest;
import com.dataart.selenium.models.AppBuilder;
import com.dataart.selenium.models.Application;
import com.dataart.selenium.models.User;
import com.dataart.selenium.pages.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.ArrayList;
import static com.dataart.selenium.framework.BasePage.initPage;
import static com.dataart.selenium.models.UserBuilder.admin;
import static org.fest.assertions.Assertions.assertThat;

public class AppTest extends BaseTest {

    private LoginPage loginPage;
    private BasicPage basicPage;
    private HomePage homePage;
    private AppPage appPage;
    private MyAppPage myAppPage;
    private User user;

    @BeforeMethod(alwaysRun = true)
    public void openLoginPage() {
        basicPage = initPage(BasicPage.class);
        loginPage = basicPage.forceLogout();
        homePage = initPage(HomePage.class);
        appPage = initPage(AppPage.class);
        myAppPage = initPage(MyAppPage.class);
        user = admin();
    }

    @Test (priority = 1)
    // Open a page of any application. Note down the information about this application given on the page.
    // Then click the download button. Firstly make sure that the information about this application is shown
    // on the opened page in text format (this is a JSON response). Then you must compare the parameters
    // displayed on the application page and the parameters displayed on the JSON page.
    // They must be identical. For example, if the number of downloads on the application page is 4, the "numberOfDownloads"
    // parameter must display 4 (please note that after clicking the download button the value will be +1).
    // You don’t have to verify stuff that is not shown on the application page, e.g. “id” or "imageData".
    public void compareParam() {
        loginPage.loginAs(user);
        homePage.clickAppIcon();
        ArrayList myAttribute = appPage.getAppInfo();
        AppPage.clickDownload();
        assertThat(appPage.GetInfoFromJsonResponse()).isEqualTo(myAttribute);
    }
    @Test (priority = 2)
    // Create a new application without images. Verify that it is displayed correctly and can be downloaded.
    public void newAppWithoutImages() {
        loginPage.loginAs(user);
        homePage.clickMyAppsBtn();
        myAppPage.clickAddNewAppBtn();
        Application application = AppBuilder.withoutImages();
        myAppPage.createNewApp(application);
        homePage.openNewApp();  // Verify that it is displayed correctly and can be downloaded.
        ArrayList myAttribute = appPage.getAppInfo();
        AppPage.clickDownload();
        assertThat(appPage.GetInfoFromJsonResponse()).isEqualTo(myAttribute);
        basicPage.forceLogout();
    }
    @Test (priority = 3)
    // Edit an application without images, and verify that the changes were applied.
    public void changeApp() {
        loginPage.loginAs(user);
        homePage.openNewApp();
        myAppPage.editApp();
        homePage.clickHomeBtn();
        homePage.openNewApp();
        assertThat(appPage.description.getText()).isEqualTo("Description: " + myAppPage.message3);
        basicPage.forceLogout();
    }
    @Test (priority = 4)
//    Create a new application with an image and icon.
    public void newAppImage() {
        loginPage.loginAs(user);
        homePage.clickMyAppsBtn();
        myAppPage.clickAddNewAppBtn();
        Application application = AppBuilder.withImage();
        myAppPage.createNewApp(application);
        homePage.clickHomeBtn();
        homePage.openNewAppImage(); // Verify app creation
        basicPage.forceLogout();
    }
    @Test (priority = 5)
//    Create an application, and download it many times (5 or 10, for example).
//    Verify that it has appeared in the most popular apps section, and if you click it, you will be taken
//    to the details page of this application.
    public void downloadApp() {
        loginPage.loginAs(user);
        homePage.clickMyAppsBtn();
        myAppPage.clickAddNewAppBtn();
        Application application = AppBuilder.withoutImages();
        myAppPage.createNewApp(application);
        homePage.openNewApp();
        myAppPage.downloadToAppPopularState();
        homePage.clickHomeBtn();
        homePage.clickPopApp();
        appPage.assertDownloadApp(); // Verify that it has appeared in the most popular apps section...
        basicPage.forceLogout();
    }
    @Test (priority = 6)
//    Delete an application and verify that it has been removed.
    public void deleteApp() {
        loginPage.loginAs(user);
        homePage.clickMyAppsBtn();
        myAppPage.clickAddNewAppBtn();
        Application application = AppBuilder.withoutImages();
        myAppPage.createNewApp(application);
        homePage.openNewApp();
        myAppPage.deleteNewAppWithoutImages();
        assertThat(basicPage.getFlashMessage()).isEqualTo("Deleted");
    }
}