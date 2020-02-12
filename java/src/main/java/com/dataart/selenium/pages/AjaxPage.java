package com.dataart.selenium.pages;

import com.dataart.selenium.framework.Utils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AjaxPage extends BasicPage{

    @FindBy (css = "#x")
    WebElement xField;
    @FindBy (css = "#y")
    WebElement yField;
    @FindBy (css = "#calc")
    WebElement summBtn;
    @FindBy (id = "result")
    public WebElement result;

    public AjaxPage calcSum(String x, String y){
        Utils.waitForElementPresent(xField);
        xField.sendKeys(x);
        yField.sendKeys(y);
        summBtn.click();
        Utils.waitForElementPresent(result);
        System.out.println(result.getText());
        return initPage(AjaxPage.class);
    }
}
