package com.dataart.selenium.pages;

import com.dataart.selenium.models.User;
import org.openqa.selenium.By;

import static org.fest.assertions.Assertions.assertThat;

public class HeaderPage extends BasicPage {

    public String getWelcomeMessage() {
        return driver.findElement(By.cssSelector(".welcome")).getText();
    }

    public void assertHeader(User user){
        assertThat(getWelcomeMessage()).isEqualTo("Welcome " + user.getFname() + " " + user.getLname());
    }
}