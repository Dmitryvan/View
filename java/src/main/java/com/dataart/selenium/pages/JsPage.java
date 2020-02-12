package com.dataart.selenium.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import static org.fest.assertions.Assertions.assertThat;

public class JsPage extends BasicPage {
    @FindBy(css = "#x")
    WebElement topField;
    @FindBy (css = "#y")
    WebElement leftField;
    @FindBy (id = "process")
    WebElement processBtn;

    public void handleCoordinates() {
        ((JavascriptExecutor)driver).executeScript("return $(\"#top\").val(Math.round($(\".flash\").offset().top))");
        ((JavascriptExecutor)driver).executeScript("return $(\"#left\").val(Math.round($(\".flash\").offset().left))");
        processBtn.click();
        }

        public void assertPopupMessage(){
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            assertThat(alertText).isEqualTo("Whoo Hoooo! Correct!");
        }
}