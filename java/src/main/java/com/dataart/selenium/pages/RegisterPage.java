package com.dataart.selenium.pages;

import com.dataart.selenium.framework.BasePage;
import com.dataart.selenium.framework.Utils;
import com.dataart.selenium.models.User;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegisterPage extends BasePage {

    @FindBy(xpath = NAME_FIELD)
    public WebElement userNameTextField;
    @FindBy(css = FIRST_NAME_FIELD)
    public WebElement userFNameTextField;
    @FindBy(css = LAST_NAME_FIELD)
    public WebElement userLNameTextField;
    @FindBy(css = PASSWORD_FIELD)
    public WebElement passwordTextField;
    @FindBy(css = CONFIRM_PASSWORD_FIELD)
    public WebElement confirmPasswordTextField;
    @FindBy(css = "input[type='submit']")
    WebElement registerBtn;

    @FindBy(css = "[name='role']")
    WebElement roleDropDownList;
    @FindBy(css = "option[value='USER']")
    WebElement userCheck;

    public HomePage registrationNewUser(User user) {
        userNameTextField.clear();
        userFNameTextField.clear();
        userLNameTextField.clear();
        passwordTextField.clear();
        confirmPasswordTextField.clear();
        userNameTextField.sendKeys(user.getUsername());
        userFNameTextField.sendKeys(user.getFname());
        userLNameTextField.sendKeys(user.getLname());
        passwordTextField.sendKeys(user.getPassword());
        confirmPasswordTextField.sendKeys(user.getPassword());
        registerBtn.click();
        return initPage(HomePage.class);
    }

    public RegisterPage registerViaDataProvider(String username, String fname, String lname, String password){
        userNameTextField.sendKeys(username);
        userFNameTextField.sendKeys(fname);
        userLNameTextField.sendKeys(lname);
        passwordTextField.sendKeys(password);
        confirmPasswordTextField.sendKeys(password);
        registerBtn.click();
        return initPage(RegisterPage.class);
    }

    public RegisterPage clickUserRole(){
        roleDropDownList.click();
        Utils.waitForElementPresent(userCheck);
        userCheck.click();
        return initPage(RegisterPage.class);
    }

    public RegisterPage checkRole (String userRole) {
        if (userRole.equals("checkUser")) {
            clickUserRole();
        }
        return initPage(RegisterPage.class);
    }

    public static final String NAME_FIELD = "//input[@name='name']";
    public static final String FIRST_NAME_FIELD = "[name='fname']";
    public static final String LAST_NAME_FIELD = "[name='lname']";
    public static final String PASSWORD_FIELD = "[name='password']";
    public static final String CONFIRM_PASSWORD_FIELD = "[name='passwordConfirm']";
}