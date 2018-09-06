package com.emma2.pages;

import com.emma2.util.DriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

    @FindBy(id = "UserName")
    private WebElement userName;

    @FindBy(id = "Password")
    private WebElement password;

    @FindBy(id = "RememberMe")
    private WebElement rememberMe;

    @FindBy(css = "input[type='submit']")
    private WebElement logIn;


    @Override
    protected ExpectedCondition getPageLoadCondition() {
        return ExpectedConditions.visibilityOf(userName);
    }

    public LoginPage open(String url) {

        DriverManager.getDriver().navigate().to(url);
        return (LoginPage) openPage(LoginPage.class);
    }

    public void doLogin(String usrName, String pwd) {
        userName.sendKeys(usrName);
        password.sendKeys(pwd);
        logIn.click();
//        return (HomePage) openPage(HomePage.class);
    }

}
