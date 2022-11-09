package com.inetbanking.pageObjects;

import com.intebanking.utilities.ReadConfig;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

public class BaseClass {

    ReadConfig rconfig = new ReadConfig();

    public String baseUrl = rconfig.getApplicatonUrl();
    public String uname = rconfig.getUserName();
    public String pwd= rconfig.getPassword();
    public WebDriver driver;
    public Logger logger;

    @Parameters("browser")
    @BeforeClass
    public void setup(String br)
    {

        if(br.equals("chrome")){

            System.setProperty("webdriver.chrome.driver",rconfig.getChromepath());
            driver = new ChromeDriver();
            driver.get(baseUrl);

        } else if (br.equals("firefox")) {
            System.setProperty("webdriver.gecko.driver",rconfig.getChromepath());
            driver = new FirefoxDriver();
        }
        else {
            System.out.println("no browser is picked");

    }

        logger = Logger.getLogger("ebanking");
        PropertyConfigurator.configure("Log4j.properties");

    }


    @AfterClass
    public void tearDown(){
        //driver.quit();
    }

    public void captureScreen(WebDriver driver,String tname) throws IOException {
        TakesScreenshot ts= (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File target= new File(System.getProperty("user.dir")+"/Screenshots/"+tname+".png");
        FileUtils.copyFile(source, target);
        System.out.println("Screnshot taken");
    }

    public String randomstring(){
        String generatedstring = RandomStringUtils.randomAlphabetic(8);
        return generatedstring;
    }

    public String randomnumeric(){
        String generatedstring2 = RandomStringUtils.randomNumeric(8);
        return generatedstring2;
    }
}
