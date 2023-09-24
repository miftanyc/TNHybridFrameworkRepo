package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.AccountPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.LoginPage;
import com.tutorialsninja.qa.utils.Utilities;

public class Login extends Base{

	public Login() {
		super();
	}
	
	
	public WebDriver driver;
	HomePage homepage;
	LoginPage loginpage;
	AccountPage accountPage;
	
	
	@BeforeMethod
	public void setup() {
		
		driver = initializedBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
		homepage = new HomePage(driver);
		loginpage = new LoginPage(driver);
		accountPage = new AccountPage(driver);
	
		homepage.clickOnMyAccount();
		homepage.selectLoginOption();
		
		
	}
	
	
	@AfterMethod
	public void browserQuit() {
		driver.quit();
	}	
	
	
	@Test (priority=1, dataProvider="validCredentialSupplier")
	public void varifyLoginWithValidCredential(String Email, String Password) {
		
		loginpage.provideEmail(Email);
		loginpage.providePassword(Password);
		loginpage.clickLoginButton();
		
		Assert.assertTrue(accountPage.isLoginSuccessfull(), "Failed to Login");
		
	}
	
	
	@DataProvider(name="validCredentialSupplier")
	public Object[][] supplyTestdata(){
		Object[][] data = Utilities.getTestDataFromExcel("Login");
		return data;
	}

	
	@Test (priority=2)
	public void varifyLoginWithInvalidCredential() {
			
		loginpage.provideEmail(Utilities.generateEmailWithTimeStamp());
		loginpage.providePassword(dataProp.getProperty("invalidPassword"));
		loginpage.clickLoginButton();
			
		String actualMessage = loginpage.noMatchForEmailAddressOrPassword();
		String expectedMessage = dataProp.getProperty("noMatchEmailWarning");		
		Assert.assertTrue(actualMessage.contains(expectedMessage),"Message Not Matched");
		
	}
	
	
	@Test (priority=3)
	public void varifyLoginWithInvalidEmailAndValidPassword() {
	
		loginpage.provideEmail(Utilities.generateEmailWithTimeStamp());
		loginpage.providePassword(prop.getProperty("validPassword"));
		loginpage.clickLoginButton();
		
		
		String actualMessage = loginpage.noMatchForEmailAddressOrPassword();
		String expectedMessage = dataProp.getProperty("noMatchEmailWarning");		
		Assert.assertTrue(actualMessage.contains(expectedMessage),"Message Not Matched");
		
	}
	
	
	@Test (priority=4)
	public void varifyLoginWithValidEmailAndInvalidPassword() {
			
		loginpage.provideEmail(prop.getProperty("validEmail"));
		loginpage.providePassword(dataProp.getProperty("invalidPassword"));
		loginpage.clickLoginButton();
		
		String actualMessage = loginpage.noMatchForEmailAddressOrPassword();
		String expectedMessage = dataProp.getProperty("noMatchEmailWarning");		
		Assert.assertTrue(actualMessage.contains(expectedMessage),"Message Not Matched");
		
	}
	
	
	@Test (priority=5)
	public void varifyLoginWithoutProvidingCredentials() {
			
		loginpage.clickLoginButton();
		
		String actualMessage = loginpage.noMatchForEmailAddressOrPassword();
		String expectedMessage = dataProp.getProperty("noMatchEmailWarning");		
		Assert.assertTrue(actualMessage.contains(expectedMessage),"Message Not Matched");		
	
	}
		
}
