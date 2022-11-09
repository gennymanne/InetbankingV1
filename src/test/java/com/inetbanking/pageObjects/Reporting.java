package com.inetbanking.pageObjects;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Reporting extends TestListenerAdapter {

           public ExtentSparkReporter extentspark;
           public static ExtentReports extentreport;
           public ExtentTest logger;


           public void onStart(ITestContext testContext)
           {
             String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
             String reportname = "Test-report "+timestamp+".html";
             String filepath=System.getProperty("user.dir")+"/test-output/"+reportname;
             extentspark = new ExtentSparkReporter(filepath);
              try {
                   extentspark.loadXMLConfig(System.getProperty("user.dir")+"/extent-config.xml");
               } catch (Exception e) {
                   System.out.println("Exception is: "+e.getMessage());
               }
               extentreport = new ExtentReports();
               extentreport.attachReporter(extentspark);
               extentreport.setSystemInfo("host name", "Narendra-Lenovo");
               //extentreport.setSystemInfo("username", "Narendra-Lenovo");
               extentspark.config().setDocumentTitle("BankingReprot");
               extentspark.config().setReportName("Functional test Automation report");
               extentspark.config().setTheme(Theme.DARK);
           }


           public void onTestSuccess(ITestResult tr){

               logger= extentreport.createTest(tr.getName());
               logger.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN));
           }
           public void onTestFailure(ITestResult tr){
               logger = extentreport.createTest(tr.getName());
               logger.log(Status.FAIL,MarkupHelper.createLabel(tr.getName(),ExtentColor.RED));

               String screenshotpath =System.getProperty("user.dir")+"/Screenshots/"+tr.getName()+".png";

               File f = new File(screenshotpath);
               if(f.exists()){

                   logger.fail("Screenshot is below"+logger.addScreenCaptureFromPath(screenshotpath));
               }

           }

           public void onTestSkipped(ITestResult tr)
           {
               logger= extentreport.createTest(tr.getName());
               logger.log(Status.SKIP,MarkupHelper.createLabel(tr.getName(),ExtentColor.AMBER));

           }

           public void onTestFinish(ITestContext testContext)
           {
                  extentreport.flush();
           }
}
