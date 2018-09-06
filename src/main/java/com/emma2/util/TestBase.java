package com.emma2.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import ru.yandex.qatools.allure.annotations.Attachment;


public class TestBase {

	/*
	 * WebDriver Properties - OR, Config Logs - Application, selenium Excel
	 * Reading DB Conn mail
	 * 
	 */
	
	public ExtentReports rep = ExtentManager.getInstance();
	public static ExtentTest test;

	public static WebDriver driver;
	public static Properties OR = new Properties();
	public static Properties Config = new Properties();
	public static FileInputStream fis;
	public static Logger log = Logger.getLogger("devpinoyLogger");
//	public static MonitoringMail mail = new MonitoringMail();
	public static ExcelReader excel = new ExcelReader(
			System.getProperty("user.dir") + "\\src\\test\\resources\\excel\\testdata.xlsx");
	public static WebDriverWait wait;

	@BeforeSuite
	public void setUp() {

		if (driver == null) {

			try {
				fis = new FileInputStream(
						System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Config.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				Config.load(fis);
				log.debug("Config properties file loaded");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				fis = new FileInputStream(
						System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\OR.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				OR.load(fis);
				log.debug("OR properties file loaded");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (Config.getProperty("browser").equals("firefox")) {

				driver = new FirefoxDriver();
				test.log(LogStatus.INFO, "Launching Chrome Browser");
				log.debug("Firefox launched");
			} else if (Config.getProperty("browser").equals("chrome")) {

				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\chromedriver.exe");
				driver = new ChromeDriver();
				log.debug("Chrome launched");
			} else if (Config.getProperty("browser").equals("ie")) {

				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\chromedriver.exe");
				driver = new InternetExplorerDriver();
				log.debug("IE launched");
			}

			driver.get(Config.getProperty("testsiteurl"));
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			wait = new WebDriverWait(driver, 5);
//			try {
////				DbManager.setMysqlDbConnection();
//			} catch (ClassNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}

		}

	}

	public static void click(String locator) {

		driver.findElement(By.cssSelector(OR.getProperty(locator))).click();
		test.log(LogStatus.INFO, "Clicking on "+locator);
		
	}

	public static void type(String locator,String value) {

		driver.findElement(By.cssSelector(OR.getProperty(locator))).sendKeys(value);
		test.log(LogStatus.INFO, "Typing in "+locator+" entered value as: "+value);
		
	}

	public static boolean isElementPresent(String css) {

		try {

			driver.findElement(By.cssSelector(css));
			return true;

		} catch (Throwable t) {

			return false;
		}

	}

	
	/*@AfterMethod
	public void quitReport(){
		
		
			rep.endTest(test);
			rep.flush();
		
		
	}*/
	
	@AfterSuite
	public void tearDown() {

		/*if(rep!=null){
			
			rep.flush();
		}
		*/
		if(driver!=null)
			driver.quit();
		log.debug("Test suite completed !!!");
	}

}
