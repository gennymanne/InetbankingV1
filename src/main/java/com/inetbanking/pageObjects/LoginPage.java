package com.inetbanking.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    WebDriver driver;

    public LoginPage(WebDriver rdriver){
        driver= rdriver;
        PageFactory.initElements(rdriver,this);
    }
    @FindBy(xpath="//span[text()='Account & Lists']")
    WebElement Signinlink;

    @FindBy(name="email")
    WebElement userEmail;

    @FindBy(id="continue")
    WebElement continueButton;

    @FindBy(name="password")
    WebElement passwordButton;

    @FindBy(id="signInSubmit")
    WebElement submit;

    @FindBy(xpath ="//span[@id='nav-link-accountList-nav-line-1']")
   public  WebElement homeTitle;
    @FindBy(xpath = "//span[text()='Sign Out']")
    public WebElement logoutbtn;

    public void clickOnSignin()
    {
        Signinlink.click();
    }

    public void setUserEmail(String uname){
        userEmail.sendKeys(uname);
    }
    public void clickonContinue(){
        continueButton.click();
    }
    public void setPassword(String pwd)
    {
        passwordButton.sendKeys(pwd);
    }
    public void clickSubmit()
    {
        submit.click();
    }

    public void clickLogout(){
        logoutbtn.click();
    }

    public String getHomeTitle(){
        String text = homeTitle.getText();
         return text;
    }
}
