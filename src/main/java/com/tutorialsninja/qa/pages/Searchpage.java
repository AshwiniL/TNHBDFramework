package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Searchpage {
	WebDriver driver;
	
	@FindBy(linkText  = "HP LP3065")
	private WebElement searchProductResult;
	
	@FindBy(xpath  = "//p[contains(text(),'There is no product that matches the search criteria.')]")
	private WebElement noProductFoundErrorMesaage;
	
	public Searchpage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean getSearchResultStatus()
	{
		boolean searchStatus= searchProductResult.isDisplayed();
		return searchStatus;
		
	}
	
	public String retriveNoProductFoundErrorMessage()
	{
		String errorMessage =noProductFoundErrorMesaage.getText();
		return errorMessage;
	}
	

}
