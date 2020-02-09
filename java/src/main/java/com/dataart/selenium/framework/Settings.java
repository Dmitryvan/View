package com.dataart.selenium.framework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import java.io.*;
import java.util.Properties;

public class Settings {

    private static final String SELENIUM_BASEURL = "selenium.baseUrl";
    private static final String SELENIUM_AUTH = "selenium.authUrl";
    private static final String SELENIUM_BROWSER = "selenium.browser";
    private static final String SELENIUM_PROPERTIES = "selenium.properties";

    private String baseUrl;
    private String authUrl;
    private BrowserType browser;
    private Properties properties = new Properties();

    public Settings() {
        loadSettings();
    }

    private void loadSettings() {
        properties = loadPropertiesFile();
        baseUrl = getPropertyOrThrowException(SELENIUM_BASEURL);
        authUrl = getPropertyOrThrowException(SELENIUM_AUTH);
        browser = BrowserType.convertToBrowserType(getPropertyOrThrowException(SELENIUM_BROWSER));
    }

    private Properties loadPropertiesFile() {
        try {
            // get specified property file
            String filename = getPropertyOrNull(SELENIUM_PROPERTIES);
            // it is not defined, use default
            if (filename == null) {
                filename = SELENIUM_PROPERTIES;
            }
            // try to load from classpath
            InputStream stream = getClass().getClassLoader().getResourceAsStream(filename);
            // no file in classpath, look on disk
            if (stream == null) {
                stream = new FileInputStream(new File(filename));
            }
            Properties result = new Properties();
            result.load(stream);
            return result;
        } catch (IOException e) {
            throw new UnknownPropertyException("Property file is not found");
        }
    }

    public String getPropertyOrNull(String name) {
        return getProperty(name, false);
    }

    public String getPropertyOrThrowException(String name) {
        return getProperty(name, true);
    }

    public String getUrl(){
        String baseAuthUrl = System.getProperty("selenium.baseAuthUrl");
        boolean isAuth = Boolean.parseBoolean(baseAuthUrl);
        if (isAuth){
            return getAuthUrl();
        }
        return getBaseUrl();
    }

    private String getProperty(String name, boolean forceExceptionIfNotDefined) {
        String result;
        if ((result = System.getProperty(name, null)) != null && result.length() > 0) {
            return result;
        } else if ((result = getPropertyFromPropertiesFile(name)) != null && result.length() > 0) {
            return result;
        } else if (forceExceptionIfNotDefined) {
            throw new UnknownPropertyException("Unknown property: [" + name + "]");
        }
        return result;
    }

    private String getPropertyFromPropertiesFile(String name) {
        Object result = properties.get(name);
        if (result == null) {
            return null;
        } else {
            return result.toString();
        }
    }

    public WebDriver getDriver() {
        return getDriver(browser);
    }

    WebDriver getDriver(BrowserType browserType) {
        switch (browserType) {
            case GC:
                System.setProperty("webdriver.chrome.driver", ".//target//test-classes//chromedriver.exe");
                return new ChromeDriver();
            case FIREFOX:
                System.setProperty("webdriver.gecko.driver", ".//target//test-classes//geckodriver.exe");
                return new FirefoxDriver();
            case IE:
                System.setProperty("webdriver.ie.driver", ".//target//test-classes//IEDriverServer.exe");
                return new InternetExplorerDriver();
            default:
                throw new UnknownBrowserException("Cannot create driver for unknown browser type");
        }
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getAuthUrl() {
        return authUrl;
    }

    public BrowserType getBrowser() {
        return browser;
    }
}