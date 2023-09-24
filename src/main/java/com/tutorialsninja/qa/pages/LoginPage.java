package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	
	WebDriver driver;
	
	
	//Object
	@FindBy(xpath="//input[@id='input-email']")
	private WebElement emailInputFieldElement;
			
			
	@FindBy(xpath="//input[@id='input-password']")
	private WebElement passWordInputFieldElement;
	
	@FindBy(xpath="//input[@class='btn btn-primary']")
	private WebElement loginButtonElement;
	
	@FindBy(xpath="//div[contains(@class, 'alert-dismissible')]")
	private WebElement  noMatchForEmailAddressAndOrPassword;
	
	
	
	
	//Constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	
	//Action
	
	public void provideEmail(String emailText) {
		emailInputFieldElement.sendKeys(emailText);
	}
	
	
	public void providePassword(String passwordText) {
		passWordInputFieldElement.sendKeys(passwordText);
	}
	
	public void clickLoginButton() {
		loginButtonElement.click();
	}
	
	public String noMatchForEmailAddressOrPassword() {
		String noMatchForEmailAddressOrPassword = noMatchForEmailAddressAndOrPassword.getText();
		return noMatchForEmailAddressOrPassword;
	}
	
}
