package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.SearchPage;

public class Search extends Base {

	public Search() {
		super();
	}
	
	public WebDriver driver;
	SearchPage searchpage;
	
	
	@BeforeMethod
	public void setup() {
		
		driver = initializedBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
		searchpage =new SearchPage(driver);
		
	}
	
	
	@AfterMethod
	public void browserQuit() {
		driver.quit();
	}
	
	
	@Test(priority=1)
	public void searchWithExistingProduct() {
		
		searchpage.typeProductInSearchBox(dataProp.getProperty("existingProductSearch"));
		searchpage.clickSearchButton();
	
		Assert.assertTrue(searchpage.isProductDisplayed());
	
	}
	
	@Test(priority=2)
	public void searchWithNonexistingProduct() {
		
		searchpage.typeProductInSearchBox(dataProp.getProperty("nonExistingProductSearch"));
		searchpage.clickSearchButton();
	
		Assert.assertEquals(searchpage.noMatchProductMessage(), dataProp.getProperty("noMatchProductMessage"));
		
	}
	
	@Test(priority=3, dependsOnMethods= {"searchWithNonexistingProduct"})
	public void searchWithNoTextInSearchBox() {

		searchpage.clickSearchButton();
	
		Assert.assertEquals(searchpage.noMatchProductMessage(), dataProp.getProperty("noMatchProductMessage"));
		
	}
	
	
	
	
	
	
}
