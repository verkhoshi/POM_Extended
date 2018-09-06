package com.emma2.util;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class DriverManager {
	
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
    private static final Logger LOGGER = Logger.getLogger(DriverManager.class);
    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void setWebDriver(WebDriver webdriver) {
        driver.set(webdriver);
        
    }
    


    public static void bringBrowserToForeground(){
        String currentWindowHandle = getDriver().getWindowHandle();
        ((JavascriptExecutor)getDriver()).executeScript("alert('Test')");
        getDriver().switchTo().alert().accept();
        getDriver().switchTo().window(currentWindowHandle);
    }
}
