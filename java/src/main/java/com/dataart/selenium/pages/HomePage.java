package com.dataart.selenium.pages;

import com.dataart.selenium.framework.BasePage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Set;

public class HomePage extends BasicPage {
    public HeaderPage getHeader() {
        return initPage(HeaderPage.class);
    }

    @FindBy(css = ".navigation > a:nth-child(3)")
    public static WebElement homeBtn;

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

    public static void clickHomeBtn(){
         homeBtn.click();
    }
}
