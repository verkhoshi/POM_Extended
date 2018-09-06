package com.emma2.util;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;



public class DriverFactory {
    private static final Logger LOGGER = Logger.getLogger(DriverFactory.class);
    private static String downloadFilepath;
    private static String gridURL;
    private static String chromeDriverExePath;
    
    public static void setBrowserFileDownloadLocation(String path){
        downloadFilepath = path;
    }
    public static void setGridURL(String url){
        gridURL = url;
    }
    public static void setchromeDriverExePath(String path){
        chromeDriverExePath = path;
    }
    public static WebDriver createInstance(String browserName) {
        WebDriver driver = null;
        try{
            if (browserName.toLowerCase().contains("firefox")) {
                driver = new FirefoxDriver();
                DriverManager.setWebDriver(driver);
                return driver;
            }else if (browserName.toLowerCase().contains("phantomjs")) {
                try{
                    DesiredCapabilities cap = DesiredCapabilities.phantomjs();
                    driver = new RemoteWebDriver(new URL("http://localhost:4444"),cap);
                    driver.manage().window().maximize();
                    DriverManager.setWebDriver(driver);
                }catch(Exception e){
                    e.printStackTrace();
                }
                return driver;
            }else if (browserName.toLowerCase().contains("IE")) {
                driver = new InternetExplorerDriver();
                DriverManager.setWebDriver(driver);
                return driver;
            }else if (browserName.toLowerCase().contains("chrome")) {
//String downloadFilepath = PluginAPI.getInstance().getWeb_properties().get("FILE_DOWNLOAD_LOCATION").toString();
                if(downloadFilepath.equals("")){
                    downloadFilepath = "/";
                }
                String chromedriver_path = chromeDriverExePath;
// String chromedriver_path = System.getProperty("user.dir") + "/drivers/chromedriver.exe";
                System.setProperty("webdriver.chrome.driver",chromedriver_path);
                HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
// chromePrefs.put("profile.default_content_settings.popups", 0);
                chromePrefs.put("download.default_directory", downloadFilepath);

                ChromeOptions options = new ChromeOptions();
                options.addArguments("test-type");
                options.addArguments(Arrays.asList("--start-maximized"));
                options.addArguments(Arrays.asList("--ssl-protocol=any"));
                options.addArguments(Arrays.asList("--ignore-ssl-errors=true"));
                options.addArguments(Arrays.asList("--disable-extensions"));
                options.addArguments(Arrays.asList("--ignore-certificate-errors"));
                options.setExperimentalOption("prefs", chromePrefs);

                DesiredCapabilities crcapabilities = DesiredCapabilities.chrome();
                crcapabilities.setCapability("chrome.binary",chromedriver_path);
                crcapabilities.setCapability(ChromeOptions.CAPABILITY, options);
                crcapabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
                LOGGER.info("Starting the Local Chrome: ");
                driver = new ChromeDriver(crcapabilities);
                DriverManager.setWebDriver(driver);
                return driver;
            }else if(browserName.toLowerCase().contains("remote")){
// String downloadFilepath = PluginAPI.getInstance().getWeb_properties().get("FILE_DOWNLOAD_LOCATION").toString();
                if(downloadFilepath.equals("")){
                    downloadFilepath = "/";
                }
                String chromedriver_path = chromeDriverExePath;
// String chromedriver_path = System.getProperty("user.dir") + "/drivers/chromedriver.exe";
                System.setProperty("webdriver.chrome.driver",chromedriver_path);
                HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
// chromePrefs.put("profile.default_content_settings.popups", 0);
                chromePrefs.put("download.default_directory", downloadFilepath);

                ChromeOptions options = new ChromeOptions();
                options.addArguments("test-type");
                options.addArguments(Arrays.asList("--start-maximized"));
                options.addArguments(Arrays.asList("--ssl-protocol=any"));
                options.addArguments(Arrays.asList("--ignore-ssl-errors=true"));
                options.addArguments(Arrays.asList("--disable-extensions"));
                options.addArguments(Arrays.asList("--ignore-certificate-errors"));
                options.setExperimentalOption("prefs", chromePrefs);

                DesiredCapabilities crcapabilities = DesiredCapabilities.chrome();
                crcapabilities.setCapability("chrome.binary",chromedriver_path);
                crcapabilities.setCapability(ChromeOptions.CAPABILITY, options);
                crcapabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
//String grid_url = PluginAPI.getInstance().getWeb_properties().get("GRID_URL").toString();
                LOGGER.info("Hitting the RemoteWebdriver: " + gridURL);
//                SessionDetails.setSessionID(null);
                RemoteWebDriver rwd = new RemoteWebDriver(new URL(gridURL),crcapabilities);
                DriverManager.setWebDriver(rwd);
//                SessionDetails.setSessionID(rwd.getSessionId().toString());

// String videoURL = grid_url.replace("wd/hub","grid/admin/HubVideoDownloadServlet/?sessionId=")+rwd.getSessionId().toString();
// FrameworkGlue.addCaseMessage(String.format("TestCase Video:%s",videoURL));
                return driver;
            }
        }catch(MalformedURLException e1){
            LOGGER.error("Error",e1);
        }catch(Exception e1){
            LOGGER.error(ExceptionUtils.getStackTrace(e1));

        }
        return driver;
    }

    public static void destroyDriverInstance(WebDriver driver){
        try{
            if(driver != null){
                driver.close();
                driver.quit();
            }
        }catch(Exception e){
            LOGGER.error(e.getMessage());
        }
    }
}
