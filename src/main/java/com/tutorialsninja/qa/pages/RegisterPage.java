package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {

	WebDriver driver;
	
	//Object
	@FindBy(id="input-firstname")
	private WebElement firstName;
	
	
	@FindBy(id="input-lastname")
	private WebElement lastName;
	
	
	@FindBy(id="input-email")
	private WebElement emailField;
	
	
	@FindBy(id="input-telephone")
	private WebElement telephoneField;
	
	
	@FindBy(id="input-password")
	private WebElement passwordField;
	
	
	@FindBy(id="input-confirm")
	private WebElement passwordField2;
	
	@FindBy(name="agree")
	private WebElement policyCheckBox;
	
	@FindBy(xpath="//input[@type='submit']")
	private WebElement registrationbutton;
	
	@FindBy(xpath="//label[@class='radio-inline']/input[@type='radio'][@value='1']")
	private WebElement radiobuttonNewslatter;
	
	@FindBy(xpath="//div[contains(@class, 'alert')]")
	private WebElement privacyPolicyWarning;
	
	@FindBy(xpath="//input[@id='input-firstname']/following-sibling::div")
	private WebElement firstNameWarning;
	
	@FindBy(xpath="//input[@id='input-lastname']/following-sibling::div")
	private WebElement lastNameWarning;
	
	@FindBy(xpath="//input[@id='input-email']/following-sibling::div")
	private WebElement emailWarning;
	
	@FindBy(xpath="//input[@id='input-telephone']/following-sibling::div")
	private WebElement phoneWarning;
	
	@FindBy(xpath="//input[@id='input-password']/following-sibling::div")
	private WebElement passwordWarning;
	
	@FindBy(xpath="//div[contains(@class, 'alert')]")
	private WebElement alreadyRegisteredEmailWarning;
	
	
	
	//Constructor
	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	
	//Actions
	public void firstNameTextBox(String firstname) {
		firstName.sendKeys(firstname);
		
	}
	
	public void lastNameTextBox(String lastname) {
		lastName.sendKeys(lastname);
	}
	
	public void emailTextBox(String emailtext) {
		emailField.sendKeys(emailtext);
	}
	
	public void telephoneTextBox(String phonenumber) {
		telephoneField.sendKeys(phonenumber);
	}
	
	public void passwrodTextBox(String passwordText) {
		passwordField.sendKeys(passwordText);
	}
	
	public void passwordConfirmTextBox(String passwordText2) {
		passwordField2.sendKeys(passwordText2);
	}
	
	public void policyCheckBox() {
		policyCheckBox.click();
	}
	
	public void selectNewsLatterRadioButton() {
		radiobuttonNewslatter.click();
	}
	
	public void registrationOption() {
		registrationbutton.click();
	}
	
	
	public String privacyPolicyWarningMessage() {
		String passwordWarningText = privacyPolicyWarning.getText();
		return passwordWarningText;
		
	}
	
	public String firstNameWarningMessage() {
		String firstNameWarningText = firstNameWarning.getText();
		return firstNameWarningText;
	}
	
	public String lastNameWarningMessage() {
		String lastNameWarningText = lastNameWarning.getText();
		return lastNameWarningText;
	}
	
	
	public String emailWarningMessage() {
		String emailWarningText = emailWarning.getText();
		return emailWarningText;
	}
	
	public String phoneWarningMessage() {
		String phoneWarningText = phoneWarning.getText();
		return phoneWarningText;
	}
	
	public String passwordWarningMessage(){
		String passwordWarningText = passwordWarning.getText();
		return passwordWarningText;
	}
	
	public String alreadyRegisteredEmailWarningMessage() {
		String alreadyRegisteredEmailWarningMessageText = alreadyRegisteredEmailWarning.getText();
		return alreadyRegisteredEmailWarningMessageText;
	}
	
}
