package com.dataart.selenium.pages;

import com.dataart.selenium.framework.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import java.util.Set;

public class HomePage extends BasicPage {

    @FindBy(xpath = "//a[.='Home']")
    WebElement homeBtn;
    @FindBy(xpath = "//a[.='Ajax test page']")
    WebElement ajaxTestBtn;
    @FindBy(xpath = "//a[.='JS test page']")
    WebElement jsTestBtn;
    @FindBy(tagName = "img")
    WebElement appIcon;
    @FindBy (xpath = "//a[.='My applications']")
    WebElement myAppsBtn;

    public String newAppInList = "a[href*=\"/app?title=%s\"]";
    private String newAppSelectorFormat = String.format(newAppInList, MyAppPage.message1);

    @FindBy(css = "a[href*=\"/app?title=Cat App\"]")
    WebElement newAppImageInList;

    public static String popularApp = ".popular-app [alt='%s']";
    public static String popularAppSelector = String.format(popularApp, MyAppPage.message1);

    public static void openNewTabBrowser() {
        ((JavascriptExecutor)driver).executeScript("window.open('about:blank', '_blank');");
        Set<String> tab_handles = driver.getWindowHandles();
        int number_of_tabs = tab_handles.size();
        int second_tab_index = number_of_tabs-1;
        driver.switchTo().window(tab_handles.toArray()[second_tab_index].toString());
    }

    public static void switchToOldTabBrowser(){
        Set<String> tab_handles = driver.getWindowHandles();
        int number_of_tabs = tab_handles.size();
        int main_tab_index_ = number_of_tabs-2;
        driver.switchTo().window(tab_handles.toArray()[main_tab_index_].toString());
    }

    public LoginPage openLoginPageLink(){
        driver.get(settings.getBaseUrl());
        return initPage(LoginPage.class);
    }

    public HomePage clickHomeBtn(){
        homeBtn.click();
        return initPage(HomePage.class);
    }

    public HomePage clickAppIcon(){
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", appIcon);
        return initPage(HomePage.class);
    }

    public HomePage clickMyAppsBtn(){
        Utils.waitForElementPresent(myAppsBtn);
        Actions actions = new Actions(driver);
        actions.moveToElement(myAppsBtn).build().perform();
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", myAppsBtn);
        return initPage(HomePage.class);
    }

    public HomePage openNewApp() {
        WebElement newAppSelector = driver.findElement(By.cssSelector(newAppSelectorFormat));
        Utils.waitForElementPresent(newAppSelector);
        Actions actions = new Actions(driver);
        actions.moveToElement(newAppSelector).build().perform();
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", newAppSelector);
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", newAppSelector);
        return initPage(HomePage.class);
    }

    public HomePage openNewAppImage() {
        Utils.waitForElementPresent(newAppImageInList);
        Actions actions = new Actions(driver);
        actions.moveToElement(newAppImageInList).build().perform();
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", newAppImageInList);
        return initPage(HomePage.class);
    }

    public HomePage clickPopApp(){
        driver.findElement(By.cssSelector(popularAppSelector)).click();
        return initPage(HomePage.class);
    }

    public HomePage clickAjaxTestBtn(){
        Utils.waitForElementPresent(ajaxTestBtn);
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", ajaxTestBtn);
        return initPage(HomePage.class);
    }

    public HomePage clickJsTestBtn(){
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", jsTestBtn);
        return initPage(HomePage.class);
    }
}