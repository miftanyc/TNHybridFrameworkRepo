package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.AccountPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.RegisterPage;
import com.tutorialsninja.qa.utils.Utilities;

public class Register extends Base {

	public Register() {
		super();
	}
	
	public WebDriver driver;
	HomePage homepage;
	AccountPage accountpage;
	RegisterPage regpage;
	
	
	@BeforeMethod
	public void setup() {
		
		driver = initializedBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
		homepage = new HomePage(driver);
		accountpage = new AccountPage(driver);
		regpage = new RegisterPage(driver);
		
		
		homepage.clickOnMyAccount();
		homepage.selectRegisterOption();
	}
	
	
	@AfterMethod
	public void browserQuit() {
		driver.quit();
	}
	
	
	@Test (priority =1)
	public void VerifyRegisteringAnAccountByProvidingOnlyMandatoryFields() {
		
		regpage.firstNameTextBox(dataProp.getProperty("firstName"));
		regpage.lastNameTextBox(dataProp.getProperty("lastName"));
		regpage.emailTextBox(Utilities.generateEmailWithTimeStamp());
		regpage.telephoneTextBox(dataProp.getProperty("phoneNumber"));
		regpage.passwrodTextBox(prop.getProperty("validPassword"));
		regpage.passwordConfirmTextBox(prop.getProperty("validPassword"));
		regpage.policyCheckBox();
		regpage.registrationOption();
	
		String ActualResult = accountpage.accountCreatedSuccessfullyMessage();
		String ExpectedResult = dataProp.getProperty("accountCreatedSuccessfullyMessage");;
		Assert.assertEquals(ActualResult, ExpectedResult, "Account is Not Successfully Created");
		
	}
	
	
	@Test (priority=2)
	public void verifyRegisteringAnAccountByProvidingAllTheFields() {
		
		regpage.firstNameTextBox(dataProp.getProperty("firstName"));
		regpage.lastNameTextBox(dataProp.getProperty("lastName"));
		regpage.emailTextBox(Utilities.generateEmailWithTimeStamp());
		regpage.telephoneTextBox(dataProp.getProperty("phoneNumber"));
		regpage.passwrodTextBox(prop.getProperty("validPassword"));
		regpage.passwordConfirmTextBox(prop.getProperty("validPassword"));
		regpage.policyCheckBox();	
		regpage.selectNewsLatterRadioButton();
		regpage.registrationOption();
		
		String ActualResult = accountpage.accountCreatedSuccessfullyMessage();
		String ExpectedResult = dataProp.getProperty("accountCreatedSuccessfullyMessage");
		Assert.assertEquals(ActualResult, ExpectedResult, "Test Failed: Account is Not Successfully Created");
	}
	
	
	@Test (priority=3)
	public void varifyRegisteringAnAccountWithAllMissingField() {
		
		regpage.registrationOption();
		
		//Check Privacy Policy Warning Message
		String warningActualResult = regpage.privacyPolicyWarningMessage();
		String warningExpectedResult = dataProp.getProperty("privacyPolicyWarning");
		Assert.assertEquals(warningActualResult, warningExpectedResult, "Test Failed: Account should not Registered");
		
		//Assert FirstName warning Message		
		String FNAR= regpage.firstNameWarningMessage();
		String FNER = dataProp.getProperty("firstNameWarning");
		Assert.assertEquals(FNAR, FNER,"Firt Name failure Message Didn't Display");
		
		//Assert LastName warning Message
		String LNAR= regpage.lastNameWarningMessage();
		String LNER = dataProp.getProperty("lastNameWarning");
		Assert.assertEquals(LNAR, LNER,"Last Name failure Message Didn't Display");
		
		//Assert Email warning Message
		String EmailAR= regpage.emailWarningMessage();
		String EmailER = dataProp.getProperty("emailWarning");
		Assert.assertEquals(EmailAR, EmailER,"Email failure Message Didn't Display");	
		
		//Assert PHonenumber warning Message
		String TelephoneAR= regpage.phoneWarningMessage();
		String TelephoneER = dataProp.getProperty("phoneWarning");
		Assert.assertEquals(TelephoneAR, TelephoneER,"Telephone failure Message Didn't Display");

		//Assert password warning Message
		String passwordAR= regpage.passwordWarningMessage();
		String passwrodER = dataProp.getProperty("passwordWarning");
		Assert.assertEquals(passwordAR, passwrodER,"Password failure Message Didn't Display");
		
	}
	
	
	@Test (priority=4)
	public void varifyRegisteringWithExistingEmail() {

		regpage.firstNameTextBox(dataProp.getProperty("firstName"));
		regpage.lastNameTextBox(dataProp.getProperty("lastName"));
		regpage.emailTextBox(prop.getProperty("validEmail"));
		regpage.telephoneTextBox(dataProp.getProperty("phoneNumber"));
		regpage.passwrodTextBox(prop.getProperty("validPassword"));
		regpage.passwordConfirmTextBox(prop.getProperty("validPassword"));
		regpage.policyCheckBox();
		regpage.registrationOption();
		
		
		String ActualResult = regpage.alreadyRegisteredEmailWarningMessage();
		String ExpectedResult = dataProp.getProperty("alreadyRegisteredEmailWarning");
		Assert.assertEquals(ActualResult, ExpectedResult, "Account is Not Successfully Created");
		
	}
	
}
