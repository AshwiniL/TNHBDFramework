package com.tutorialsninja.qa.testcases;


import java.sql.DriverManager;
import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.BaseClass;
import com.tutorialsninja.qa.pages.AccountPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.LoginPage;
import com.tutorialsninja.qa.utils.CommonUtils;

public class LoginTest extends BaseClass{
	LoginPage loginPage;
	LoginTest() {
		super();
		// TODO Auto-generated constructor stub
	}


	public WebDriver driver;
	
	@BeforeMethod
	public void setUp() {
		
		
		
		    driver = initialize(prop.getProperty("browserName"));
		    HomePage homePage=new HomePage(driver);
		    loginPage=  homePage.navigateToLoginPage();
		   
		    
	}
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
	
	
	@Test(priority=1, dataProvider = "LoginTestData")
	public void verifyLoginWithValidCredentials(String email, String password)
	{
		
		AccountPage accountPage = loginPage.login(email, password);
		Assert.assertTrue(accountPage.getDisplyStatusOfYourAccountInfoOption(email));
		
		
	}
	
	@DataProvider(name="LoginTestData")
	public Object[][] getData()
	{
		 Object[][] data = CommonUtils.getDataFromExcelFile("Login");
		 return data;
	}
	
	@Test(priority=2)
	public void verifyLoginWithValidInvalidCredentials()
	{
		loginPage.login(CommonUtils.generateEmailWithTimeStamp(), dataProp.getProperty("invalidPassword"));	
		Assert.assertEquals(loginPage.retriveEmilPasswordNotMatchingWarning(), dataProp.getProperty("noMatchForEmailAndPasswordWarningMsg"));
		
	
		
	}

	

	
	@Test(priority=3)
	public void verifyLoginWithInvalidEmailAndValidPassword()
	{
		loginPage.login(CommonUtils.generateEmailWithTimeStamp(), prop.getProperty("validPassword"));
		Assert.assertEquals(loginPage.retriveEmilPasswordNotMatchingWarning(), dataProp.getProperty("noMatchForEmailAndPasswordWarningMsg"));
		
	
		
	}
	
	@Test(priority=4)
	public void verifyLoginWithValidEmailAndInvalidPassword()
	{
		loginPage.login(prop.getProperty("validEmailID"), dataProp.getProperty("invalidPassword"));
        String actualErrMsg = loginPage.retriveEmilPasswordNotMatchingWarning();
		Assert.assertEquals(actualErrMsg, dataProp.getProperty("noMatchForEmailAndPasswordWarningMsg"));
		
		
		
	}
	
	
	@Test(priority=5)
	public void verifyLoginWithoutCredentials()
	{
	
		loginPage.clickOnLoginButton();
     	String actualErrMsg = loginPage.retriveEmilPasswordNotMatchingWarning();;
		Assert.assertEquals(actualErrMsg,  dataProp.getProperty("noMatchForEmailAndPasswordWarningMsg"));
		
		
		
	}
}
