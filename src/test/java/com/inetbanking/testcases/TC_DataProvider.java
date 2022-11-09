package com.inetbanking.testcases;

import com.inetbanking.pageObjects.BaseClass;
import com.inetbanking.pageObjects.LoginPage;
import com.intebanking.utilities.ExcelUtils;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class TC_DataProvider  extends BaseClass {

    @Test(dataProvider = "Logindata")
    public void loginDDT(String uname,String pwd) throws InterruptedException {
        LoginPage lp= new LoginPage(driver);
        Thread.sleep(3000);
        lp.clickOnSignin();
        logger.info("clicked on signin");
        lp.setUserEmail(uname);
        logger.info("entered user email");
        lp.clickonContinue();
        logger.info("Clicked on continue");
        lp.setPassword(pwd);
        logger.info("entered password");
        lp.clickSubmit();
        logger.info("clicked on submit");
        if(isAlertPresent()){
            driver.switchTo().alert().accept();
            driver.switchTo().defaultContent();
            Assert.assertTrue(false);
        }else{
            Assert.assertTrue(true);
            Actions act= new Actions(driver);
            act.moveToElement(lp.homeTitle);
            act.moveToElement(lp.logoutbtn).click().build().perform();

        }

    }

    public boolean isAlertPresent(){
        try{
             driver.switchTo().alert();
             return true;
        }
        catch(NoAlertPresentException e){
            return false;
        }


    }

    @DataProvider(name="Logindata")
    Object[][] getData() throws IOException {
         String path = System.getProperty("user.dir")+"\\src\\main\\java\\com\\inetbankng\\testdata\\Creds.xlsx";
         int rowcount= ExcelUtils.getRowCount(path,"Sheet1");
         int colcount =ExcelUtils.getCellCount(path,"Sheet1",rowcount);

         String logindata[][] = new String[rowcount][colcount];

         for (int i=1;i<rowcount;i++){
             for (int j=0; j<colcount;j++){

                 logindata[i-1][j]=ExcelUtils.getCellData(path,"Sheet1",i,j);
             }
         }
           return logindata;
    }
}
