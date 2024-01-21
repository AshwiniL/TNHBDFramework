package com.tutorialsninja.qa.testcases;

import static org.testng.Assert.assertEquals;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.BaseClass;
import com.tutorialsninja.qa.pages.AccountSuccessPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.RegisterPage;
import com.tutorialsninja.qa.utils.CommonUtils;

public class RegisterTest extends BaseClass {
	public WebDriver driver;
	RegisterPage registerPage;
	AccountSuccessPage accountSuccessPage;
	
	RegisterTest() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@BeforeMethod
	public void setUp()
	{
		
		driver = initialize(prop.getProperty("browserName"));
		
		HomePage homePage=new HomePage(driver);
	   
	   	registerPage= homePage.navigateToRegisterPage();
		
	}
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();	
	}
	
	@Test(priority=1)
	public void registerWithMandatoryFields()
	{
		accountSuccessPage = registerPage.registerWithMandatoryFields(dataProp.getProperty("firstName"), dataProp.getProperty("lastName"), CommonUtils.generateEmailWithTimeStamp(), dataProp.getProperty("phoneNumber"), dataProp.getProperty("password"));
     	Assert.assertEquals(accountSuccessPage.retriveAccountCreationMessage(), dataProp.getProperty("accountCreationMessage"));
		
	
	}
	
	@Test(priority=2)
	public void registerWithAllFields()
	{
	
		accountSuccessPage= registerPage.registerAllFields(dataProp.getProperty("firstName"), dataProp.getProperty("lastName"), CommonUtils.generateEmailWithTimeStamp(), dataProp.getProperty("phoneNumber"), dataProp.getProperty("password"));
	    Assert.assertEquals(accountSuccessPage.retriveAccountCreationMessage(), dataProp.getProperty("accountCreationMessage"));
		
		
	}
	
	
	

}
