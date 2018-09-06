package com.emma2.testcases;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.emma2.pages.LoginPage;
import com.emma2.util.DriverFactory;
import com.emma2.util.DriverManager;
import com.emma2.util.ExtentManager;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class loginTest {

    public ExtentTest test;

    @BeforeSuite
    public void initializeFramework() {
        DriverFactory.setBrowserFileDownloadLocation(System.getProperty("user.dir") + "\\target\\");
        DriverFactory.setchromeDriverExePath(System.getProperty("user.dir") + "\\drivers\\chromedriver.exe");
    }

    @AfterSuite
    public void destroyFramework() {
        DriverFactory.destroyDriverInstance(DriverManager.getDriver());
    }

    @BeforeTest
    public void setUpDriver() {

        WebDriver webDriver = DriverManager.getDriver();
        if (webDriver == null) {

            DriverFactory.createInstance("chrome");
        } else {
            DriverManager.getDriver().manage().deleteAllCookies();
            DriverManager.getDriver().navigate().to("https://geodev.cloudhsr.com/EMMA2DEV/Account/Login");
            DriverManager.getDriver().manage().deleteAllCookies();
        }
    }

    @BeforeMethod
    public void beforeMethod(Method method) {
        test = ExtentManager.getInstance().startTest((this.getClass().getName() + "::" + method.getName()), method.getName());

    }

    @Test
    public void dologinTest() {
        test.log(LogStatus.INFO, "Starting the Test");
        LoginPage loginPage = new LoginPage().open("https://geodev.cloudhsr.com/EMMA2DEV/Account/Login");
        loginPage.doLogin("Admin", "Welcome2EMMA2!");

    }

    @AfterMethod
    public void afterMethod() {

        ExtentManager.getInstance().endTest(test);
    }

    @Test
    public void loginTRUE() {

        Assert.assertTrue(true);

    }
}
