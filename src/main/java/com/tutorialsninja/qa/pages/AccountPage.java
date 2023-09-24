package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountPage {

	WebDriver driver;
	
	
	//Objects
	@FindBy(linkText="Edit your account information")
	private WebElement editYourAccounInformationText;
	
	
	@FindBy(xpath="//div[@id='content']/h1")
	private WebElement accountCreatedSuccessfullyMessage;
	
	
	//Constructor
	public AccountPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	//Actions
	public boolean isLoginSuccessfull() {
		boolean LoginSuccessfull = editYourAccounInformationText.isDisplayed();
		return LoginSuccessfull;
	}
	
	public String accountCreatedSuccessfullyMessage() {
		String accountSuccessMessage = accountCreatedSuccessfullyMessage.getText();
		return accountSuccessMessage;
	}
}
