package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	

	WebDriver driver;

	@FindBy(xpath ="//input[@id='input-email']" )
	private WebElement emailIDField;
	
	@FindBy(xpath ="//input[@id='input-password']" )
	private WebElement passwordField;
	
	@FindBy(xpath ="//input[@value='Login']" )
	private WebElement loginBtn;

	@FindBy(xpath ="//div[@class='alert alert-danger alert-dismissible']" )
	private WebElement emailPasswordNotMatchingWarning;
	
	
	public LoginPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void enterEmailAddress(String emailText)
	{
		emailIDField.sendKeys(emailText);
	}
	
	public void enterPassword(String passwordText)
	{
		passwordField.sendKeys(passwordText);
	}
	
	public AccountPage clickOnLoginButton()
	{
		loginBtn.click();
		
		return new AccountPage(driver);
	}
	
	public String retriveEmilPasswordNotMatchingWarning()
	{
		String waringText= emailPasswordNotMatchingWarning.getText();
		return waringText;
		
	}
	
	public AccountPage login(String emailText, String passwordText)
	{
		emailIDField.sendKeys(emailText);
		passwordField.sendKeys(passwordText);
		loginBtn.click();
		return new AccountPage(driver);
		
	}

}
