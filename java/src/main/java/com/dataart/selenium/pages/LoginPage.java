package com.dataart.selenium.pages;

import com.dataart.selenium.framework.BasePage;
import com.dataart.selenium.models.User;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage {

    @FindBy(css = USER_NAME_TEXT_FIELD_CSS)
    WebElement userNameTextField;
    @FindBy(css = USER_PASSWORD_TEXT_FIELD_CSS)
    WebElement passwordTextField;
    @FindBy(xpath = LOGIN_BUTTON_XPATH)
    WebElement loginButton;
    @FindBy(css = "div > a")
    WebElement registerNewUser;

    WebDriverWait wait = new WebDriverWait(driver, 10);
    public HomePage loginAs(User user){
        wait.until(ExpectedConditions.elementToBeClickable(userNameTextField));
        userNameTextField.clear();
        passwordTextField.clear();
        userNameTextField.sendKeys(user.getUsername());
        passwordTextField.sendKeys(user.getPassword());
        loginButton.click();
        return initPage(HomePage.class);
    }

    public RegisterPage clickRegNewUserBtn(){
        registerNewUser.click();
        return initPage(RegisterPage.class);
    }

    public static final String USER_NAME_TEXT_FIELD_CSS = "#j_username";
    public static final String USER_PASSWORD_TEXT_FIELD_CSS = "#j_password";
    public static final String LOGIN_BUTTON_XPATH = "//input[@value='Login']";
}
