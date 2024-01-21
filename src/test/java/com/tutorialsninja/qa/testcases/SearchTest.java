package com.tutorialsninja.qa.testcases;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.BaseClass;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.Searchpage;

public class SearchTest extends BaseClass {

	public WebDriver driver;
	Searchpage searchpage;
	HomePage homePage;
	
	SearchTest() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@BeforeMethod
	public void setUp()
	{
		driver = initialize(prop.getProperty("browserName"));
	    homePage = new HomePage(driver);
		
	}
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
	
	
	@Test(priority = 1)
	public void searcWithValidProduct()
	{
		searchpage= homePage.search(dataProp.getProperty("validProductName"));
		Assert.assertTrue(searchpage.getSearchResultStatus());
	}
	
	
	@Test(priority = 2)
	public void searcWithInvalidProduct()
	{
		searchpage= homePage.search(dataProp.getProperty("invalidProductName"));
		assertEquals(searchpage.retriveNoProductFoundErrorMessage(), dataProp.getProperty("searchErrorMessage"));
	}
	
	
	@Test(priority = 3)
	public void searcWithoutProduct()
	{
		searchpage= homePage.clickOnSearchButton();
		assertEquals(searchpage.retriveNoProductFoundErrorMessage(), dataProp.getProperty("searchErrorMessage"));
		
	}
	
	
	
}
