package com.dataart.selenium.pages;

import com.dataart.selenium.framework.Utils;
import com.dataart.selenium.models.Application;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.util.NoSuchElementException;
import java.util.Random;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;

public class MyAppPage extends BasicPage {

        static String generateRandomWord(int wordLength) {
        Random r = new Random();
        StringBuilder sb = new StringBuilder(wordLength);
        for(int i = 0; i < wordLength; i++) {
            int tmp = 'a' + r.nextInt('z' - 'a');
            sb.append(tmp);
        }
        return sb.toString();
    }

    public static String message1 = "-^-App-^-" + generateRandomWord(2);
    public static String message2 = "blablabla";
    public String message3 = "Opps, has been changed";
    public static String message4 = "Cat App";
    public static String image = "image";
    public static String noimage = "noimage";

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

    public void checkCategory(){
        category.click();
        Utils.waitForElementPresent(informationCategory);
        wait.until(ExpectedConditions.elementToBeClickable(informationCategory));
        informationCategory.click();
    }

    public MyAppPage addImageApp(Application a) {
        if (a.equals(image)) {
            File appimage = new File("appimage.jpeg");
            chooseFileImage.sendKeys(appimage.getAbsolutePath());
            File iconapp = new File("iconapp.jpeg");
            chooseFileIcon.sendKeys(iconapp.getAbsolutePath());
        }
        else {
            a.equals(noimage);
        }
        return initPage(MyAppPage.class);
    }

    public MyAppPage textOfTitle(String b){
        if (b.equals(message1)){
            titleTextField.sendKeys(message1);
        }
        if (b.equals(message4)) {
            titleTextField.sendKeys(message4);
        }
        return initPage(MyAppPage.class);
    }

    public void createNewApp(Application app) {
        Utils.waitForElementPresent(titleTextField);
        textOfTitle(app.getTitle());
        descriptionTextField.sendKeys("blablabla");
        checkCategory();
        addImageApp(app);
        clickCreateBtn();
    }

    public void clickCreateBtn(){
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

    public MyAppPage downloadToAppPopularState() {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(10, SECONDS)
                .pollingEvery(10, MILLISECONDS)
                .ignoring(NoSuchElementException.class);
        AppPage.clickDownload();
        driver.navigate().back();
        driver.navigate().refresh();
//        System.out.println(message1);
        wait.until(driver -> driver.findElement(By.cssSelector(HomePage.popularAppSelector)));
        return initPage(MyAppPage.class);
    }

    public MyAppPage deleteNewAppWithoutImages() {
        deleteBtn.click();
        driver.switchTo().alert().accept();
        return initPage(MyAppPage.class);
    }
}