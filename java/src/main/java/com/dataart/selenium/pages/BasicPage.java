package com.dataart.selenium.pages;

import com.dataart.selenium.framework.BasePage;
import com.dataart.selenium.framework.BaseTest;
import org.openqa.selenium.By;
import static com.dataart.selenium.framework.Utils.isElementPresent;

public class BasicPage extends BasePage {

    public final static By logoutBy = By.xpath("//a[contains(text(), 'Logout')]");
    public final static By flash = By.xpath("//p[@class='flash']");

    public LoginPage forceLogout() {
        driver.get(getUrlStart());
        if (isElementPresent(logoutBy)) {
            driver.findElement(logoutBy).click();
        }
        return initPage(LoginPage.class);
    }

    public String getFlashMessage() {
        if (isElementPresent(flash)) {
            return driver.findElement(flash).getText();
        }
        return null;
    }

    public String getUrlStart(){
        return settings.getUrl(BaseTest.selectUrl);
    }
}