package com.emma2.pages;

import com.emma2.util.DriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

public abstract class BasePage<T> {
    protected WebDriver driver;
    private static final Logger LOGGER = Logger
            .getLogger(BasePage.class);
    private long LOAD_TIMEOUT = 30;
    private long REFRESH_RATE = 2;
    private int AJAX_ELEMENT_TIMEOUT = 10;
    public BasePage(){
        this.driver = DriverManager.getDriver();
    }

    public BasePage(long loadTimeout, long pollingRate){
        this.driver = DriverManager.getDriver();

        this.LOAD_TIMEOUT = loadTimeout;
        this.REFRESH_RATE = pollingRate;
    }

    public T openPage(Class<T> clazz) {
        T page = null;
        try{
            driver = DriverManager.getDriver();
            AjaxElementLocatorFactory ajaxElemFactory = new AjaxElementLocatorFactory(driver, AJAX_ELEMENT_TIMEOUT);
            page = PageFactory.initElements(driver,clazz);
            PageFactory.initElements(ajaxElemFactory, page);
            ExpectedCondition pageLoadCondition = ((BasePage) page).getPageLoadCondition();
            waitForPageToLoad(pageLoadCondition);
        }catch(NoSuchElementException e){
            String error_screenshot = System.getProperty("user.dir") + "\\target\\screenshots\\" + clazz.getSimpleName() + "_error.png";
            this.takeScreenShot(error_screenshot);
            throw new IllegalStateException(String.format("This is not the %s page",clazz.getSimpleName()));
        }
        return page;
    }

    private void waitForPageToLoad(ExpectedCondition pageLoadCondition) {
        Wait wait = new FluentWait(DriverManager.getDriver())
                .withTimeout(LOAD_TIMEOUT, TimeUnit.SECONDS)
                .pollingEvery(REFRESH_RATE, TimeUnit.SECONDS);

        wait.until(pageLoadCondition);
    }
    protected abstract ExpectedCondition getPageLoadCondition();

    protected void waitForElementToLoad(ExpectedCondition<WebElement> elementLoadCondition, int timeoutInSeconds){
        Wait wait = new FluentWait(DriverManager.getDriver())
                .withTimeout(timeoutInSeconds, TimeUnit.SECONDS)
                .pollingEvery(1, TimeUnit.SECONDS);
        wait.until(elementLoadCondition);
    }

    public String takeScreenShot(){
        String imagePath = "";
        try {
            String start_time = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss.SSS").format(new java.util.Date());
            File imageFolder = new File(System.getProperty("user.dir") + "/target/screenshots");
            if(!imageFolder.exists()){
                imageFolder.mkdir();
            }
            imagePath = imageFolder.getAbsolutePath() + "/" + this.getClass().getSimpleName() + "_" + start_time + ".png";
            File imageFile = new File(imagePath);
            if(imageFile.exists()){
                imageFile.delete();
            }
            driver = new Augmenter().augment(DriverManager.getDriver());
            File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, imageFile);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
            throw new IllegalStateException("Screenshot path is not valid");
        }
        return imagePath;
    }

    public void takeScreenShot(String imageName){
        try {
            String start_time = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new java.util.Date());
            File imageFolder = new File(System.getProperty("user.dir") + "/target/screenshots");
            if(!imageFolder.exists()){
                imageFolder.mkdir();
            }
            String imagePath = imageFolder.getAbsolutePath() + "/" + imageName;
            driver = new Augmenter().augment(DriverManager.getDriver());
            File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File(imageName));

        } catch (IOException e) {
            LOGGER.error("Error",e);
            throw new IllegalStateException("Error taking screenshot");
        }
    }

    public String getPageTitle(){
        return DriverManager.getDriver().getTitle();
    }

}

