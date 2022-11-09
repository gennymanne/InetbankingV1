package com.inetbanking.testcases;

import com.inetbanking.pageObjects.BaseClass;
import com.inetbanking.pageObjects.LoginPage;
import com.inetbanking.pageObjects.Reporting;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTest extends BaseClass {

    //public WebDriver driver;

    @Test
     public void login() throws InterruptedException, IOException {
        driver.get(baseUrl);
        logger.info("Opened Url");
        driver.manage().window().maximize();
        LoginPage lp= new LoginPage(driver);
        System.out.println(System.getProperty("user.dir"));
        lp.clickOnSignin();
       logger.info("Click on Sigin");
        try {
            lp.setUserEmail(uname);
            logger.info("entered email");
            lp.clickonContinue();
            logger.info("click on continue");
            lp.setPassword(pwd);
            logger.info("entered password");
            lp.clickSubmit();
            logger.info("Clicked on login");
        }
        catch(Exception e){
            //rept.logger.log(Status.FAIL,"Error");
            //Assert.assertFalse(true);
            Reporting.extentreport.flush();
        }

        if (lp.getHomeTitle().equals("Hello, Sravani")){
            Assert.assertTrue(true);
        }
        else{
            captureScreen(driver, "login");
            Assert.assertTrue(false);
        }
    }



}
