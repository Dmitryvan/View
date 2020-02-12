package com.dataart.selenium.pages;

import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import java.util.ArrayList;

public class AppPage extends BasicPage{

    @FindBy(css = ".download-button > a")
    public static WebElement downloadBtn;
    @FindBy(css = ".name")
    WebElement title;
    @FindBy(xpath = "//div[contains(text(), 'Description:')]")
    public WebElement description;
    @FindBy(xpath = "//div[contains(text(), 'Category:')]")
    WebElement category;
    @FindBy(xpath = "//div[contains(text(), 'Author:')]")
    WebElement author;
    @FindBy(xpath = "//div[contains(text(), 'of downloads:')]")
    WebElement numberOfDownloads;
    @FindBy(css = "pre")
    WebElement jsonString;

    public static void clickDownload(){
        downloadBtn.click();
    }

    public String getTitle(){
        return title.getText();
    }

    public String getDescription(){
        return description.getText();
    }

    public String getCategory(){
        return category.getText();
    }

    public String getAuthor(){
        return author.getText();
    }

    public String getNumberOfDownloads(){
        return numberOfDownloads.getText();
    }

    public ArrayList getAppInfo() {
        ArrayList info = new ArrayList();
        info.add(getTitle());
        info.add(getDescription());
        info.add(getCategory());
        info.add(getAuthor());
        info.add(getNumberOfDownloads());
        return info;
    }

    public Object GetInfoFromJsonResponse() throws JSONException {
        String jsonText = jsonString.getText();
        ArrayList jsonInfo = new ArrayList();
        JSONObject json = new JSONObject(jsonText);
        JSONObject category = json.getJSONObject("category");
        JSONObject author = json.getJSONObject("author");
        jsonInfo.add(json.get("title"));
        jsonInfo.add("Description: " + json.get("description"));
        jsonInfo.add("Category: " + category.get("title"));
        jsonInfo.add("Author: " + author.get("name"));
        int nOfDownloads = (Integer) json.get("numberOfDownloads");
        nOfDownloads -= 1;
        jsonInfo.add("# of downloads: " + nOfDownloads);
        return jsonInfo;
    }

    public AppPage assertDownloadApp() {
        Assert.assertTrue(title.isDisplayed());
        Assert.assertTrue(description.isDisplayed());
        Assert.assertTrue(category.isDisplayed());
        Assert.assertTrue(author.isDisplayed());
        Assert.assertTrue(numberOfDownloads.isDisplayed());
        return initPage(AppPage.class);
    }
}