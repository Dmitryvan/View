package com.dataart.selenium.pages;

import com.dataart.selenium.framework.BasePage;
import com.dataart.selenium.models.User;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(id = USER_NAME_TEXT_FIELD_ID)
    WebElement userNameTextField;
    @FindBy(id = USER_PASSWORD_TEXT_FIELD_ID)
    WebElement passwordTextField;
    @FindBy(xpath = LOGIN_BUTTON_XPATH)
    WebElement loginButton;

    public HomePage loginAs(User user) {
        userNameTextField.clear();
        passwordTextField.clear();
        userNameTextField.sendKeys(user.getUsername());
        passwordTextField.sendKeys(user.getPassword());
        loginButton.click();
        return initPage(HomePage.class);
    }

    public static final String USER_NAME_TEXT_FIELD_ID = "j_username";
    public static final String USER_PASSWORD_TEXT_FIELD_ID = "j_password";
    public static final String LOGIN_BUTTON_XPATH = "//input[@value='Login']";
}
