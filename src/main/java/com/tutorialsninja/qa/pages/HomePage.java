package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	WebDriver driver;

	@FindBy(xpath ="//a[@title='My Account']" )
	private WebElement myAccountMenu;
	
	@FindBy(linkText = "Login")
	private WebElement loginMenu;
	
	@FindBy(linkText = "Register")
	private WebElement registerMenu;
	
	@FindBy(xpath  = "//input[@name='search']")
	private WebElement searchField;
	
	@FindBy(xpath  = "//button[@class='btn btn-default btn-lg']")
	private WebElement clickOnSearchButton;
	

	public HomePage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//Actions
	
	public void clickOnMyAccount()
	{
		myAccountMenu.click();
	}
	
	public LoginPage clickOnLoginOption()
	{
		loginMenu.click();
		return new LoginPage(driver);
	}
	
	
	public RegisterPage clickOnRegisterOption()
	{
		registerMenu.click();
		return new RegisterPage(driver);
	}
	
	public void enterProductToSearch(String searchText)
	{
		searchField.sendKeys(searchText);
		
	}
	
	public Searchpage clickOnSearchButton()
	{
		clickOnSearchButton.click();
		return new Searchpage(driver);
	}
	
	public LoginPage navigateToLoginPage()
	{
		myAccountMenu.click();
		loginMenu.click();
		return new LoginPage(driver);
	}
	
	
	public RegisterPage navigateToRegisterPage()
	{
		myAccountMenu.click();
		registerMenu.click();
		return new RegisterPage(driver);
	}
	
	public Searchpage search(String searchText )
	{
		searchField.sendKeys(searchText);
		clickOnSearchButton.click();
		return new Searchpage(driver);
		
	}
	
}
