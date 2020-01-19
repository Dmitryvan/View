package com.dataart.selenium.pages;

import com.dataart.selenium.framework.Utils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

public class MyAppPage extends BasicPage {

    public String message1 = "Boroda App";
    public String message2 = "blablabla";
    public String message3 = "Opps, has been changed";
    public String message4 = "Cat App";
    public String image = "image";
    public String noimage = "noimage";

    WebDriverWait wait = new WebDriverWait(driver, 10);

    @FindBy (css = "a[href='/new']")
    WebElement addToNewApp;
    @FindBy(css = "[name='title']")
    WebElement titleTextField;
    @FindBy (css = "[name='description']")
    public WebElement descriptionTextField;
    @FindBy (css = "[name='category']")
    WebElement category;
    @FindBy (xpath = "//option[.='Information']")
    WebElement informationCategory;
    @FindBy (css = "input[type='submit']")
    WebElement createBtn;

    @FindBy (xpath = "//a[.='Edit']")
    WebElement editBtn;

    @FindBy (css = "[name='image']")
    WebElement chooseFileImage;
    @FindBy (css = "[name='icon']")
    WebElement chooseFileIcon;
    @FindBy (xpath = "//a[.='Delete']")
    WebElement deleteBtn;

    public MyAppPage clickAddNewAppBtn(){
        Utils.waitForElementPresent(addToNewApp);
        wait.until(ExpectedConditions.elementToBeClickable(addToNewApp));
        addToNewApp.click();
        return initPage(MyAppPage.class);
    }

    private void checkCategory(){
        category.click();
        Utils.waitForElementPresent(informationCategory);
        wait.until(ExpectedConditions.elementToBeClickable(informationCategory));
        informationCategory.click();
    }

    public MyAppPage addImageApp(String a) {
        if (a.equals(image)) {
//            chooseFileImage.sendKeys("C:\\Users\\dvanin\\Downloads\\Git\\dvanin-aut06\\java\\src\\test\\resources\\Pic\\appimage.jpeg");
            File appimage = new File("appimage.jpeg");
            chooseFileImage.sendKeys(appimage.getAbsolutePath());
//            chooseFileIcon.sendKeys("C:\\Users\\dvanin\\Downloads\\Git\\dvanin-aut06\\java\\src\\test\\resources\\Pic\\iconapp.jpeg");
            File iconapp = new File("iconapp.jpeg");
            chooseFileImage.sendKeys(iconapp.getAbsolutePath());
        }
        else {
            a.equals(noimage);
        }
        return initPage(MyAppPage.class);
    }

    public MyAppPage textOfTitle(String b){
        if (b.equals(message1)){
            titleTextField.sendKeys("Boroda App");
        }
        if (b.equals(message4)) {
            titleTextField.sendKeys("Cat App");
        }
        return initPage(MyAppPage.class);
    }

    public MyAppPage createNewApp(String a, String b){
        Utils.waitForElementPresent(titleTextField);
        textOfTitle(b);
        descriptionTextField.sendKeys("blablabla");
        checkCategory();
        addImageApp(a);
        clickCreateBtn();
        return initPage(MyAppPage.class);
    }

    private void clickCreateBtn(){
        createBtn.click();
    }
    public MyAppPage editApp() {
        Utils.waitForElementPresent(editBtn);
        editBtn.click();
        descriptionTextField.clear();
        descriptionTextField.sendKeys(message3);
        clickCreateBtn();
        return initPage(MyAppPage.class);
    }

    public MyAppPage openFiveTimesAppPopular(){
        int i;
        for (i=0; i<5; i++) {
            AppPage.clickDownload();
            driver.navigate().back();
        }
        driver.navigate().refresh();
        return initPage(MyAppPage.class);
    }

    public MyAppPage deleteNewAppWithoutImages() {
        deleteBtn.click();
        driver.switchTo().alert().accept();
        return initPage(MyAppPage.class);
    }
}