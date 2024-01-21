package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {

	
	WebDriver driver;

	@FindBy(xpath ="//input[@id='input-firstname']" )
	private WebElement firstNameField;
	
	@FindBy(xpath ="//input[@id='input-lastname']" )
	private WebElement lastNameField;
	
	@FindBy(xpath ="//input[@id='input-email']" )
	private WebElement emailField;
	
	@FindBy(xpath ="//input[@id='input-telephone']" )
	private WebElement telephoneField;
	
	@FindBy(xpath ="//input[@id='input-password']" )
	private WebElement passwordField;
	
	@FindBy(xpath ="//input[@id='input-confirm']" )
	private WebElement confirmField;
	
	@FindBy(xpath ="//input[@name='agree']" )
	private WebElement agreeCheckBox;
	
	@FindBy(xpath ="//input[@value='Continue']" )
	private WebElement continueButton;
	
	
	@FindBy(xpath ="//input[@value='1'])[2]" )
	private WebElement newsLetterOptionCheckbox;
	
	
	public RegisterPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void enterFirstName(String firstNameText)
	{
		firstNameField.sendKeys(firstNameText);
	}
	
	public void enterLastName(String lastNameText)
	{
		lastNameField.sendKeys(lastNameText);
	}
	
	public void enterEmailID(String emailText)
	{
		emailField.sendKeys(emailText);
	}
	
	public void enterPhoneNumber(String phoneNumberText)
	{
		telephoneField.sendKeys(phoneNumberText);
	}
	
	public void enterPassword(String passwordText)
	{
		passwordField.sendKeys(passwordText);
	}
	
	public void enterConfirmPassword(String confirmPasswordText)
	{
		confirmField.sendKeys(confirmPasswordText);
	}
	
	public void selectPolicyCheckBox()
	{
		agreeCheckBox.click();
	}
	
	public void selectNewsLetterPreferenceOption()
	{
		newsLetterOptionCheckbox.click();
	}
	
	
	public AccountSuccessPage clickOnContinueButton()
	{
		continueButton.click();
		return new AccountSuccessPage(driver);
	}
	
	public AccountSuccessPage registerWithMandatoryFields(String firstNameText, String lastNameText, String emailText, String phoneNumberText,String passwordText )
	{
		firstNameField.sendKeys(firstNameText);
		lastNameField.sendKeys(lastNameText);
		emailField.sendKeys(emailText);
		telephoneField.sendKeys(phoneNumberText);
		passwordField.sendKeys(passwordText);
		confirmField.sendKeys(passwordText);
		agreeCheckBox.click();
		continueButton.click();
		return new AccountSuccessPage(driver);
		
		
	}
	
	public AccountSuccessPage registerAllFields(String firstNameText, String lastNameText, String emailText, String phoneNumberText,String passwordText )
	{
		firstNameField.sendKeys(firstNameText);
		lastNameField.sendKeys(lastNameText);
		emailField.sendKeys(emailText);
		telephoneField.sendKeys(phoneNumberText);
		passwordField.sendKeys(passwordText);
		confirmField.sendKeys(passwordText);
		agreeCheckBox.click();
		newsLetterOptionCheckbox.click();
		continueButton.click();
		return new AccountSuccessPage(driver);
		
		
	} 
}
