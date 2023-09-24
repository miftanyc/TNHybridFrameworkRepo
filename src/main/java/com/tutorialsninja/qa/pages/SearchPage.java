package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {

	
	WebDriver driver;
	
	//Object
	@FindBy(xpath="//div[@id='search']/input")
	private WebElement searchBox;
	
	@FindBy(xpath="//button[@class='btn btn-default btn-lg']")
	private WebElement searchButton;
	
	@FindBy(xpath="//div[@class='product-layout product-grid col-lg-3 col-md-3 col-sm-6 col-xs-12']")
	private WebElement productAvailableElement;
	
	@FindBy(xpath="//div[@id='content']/p[2]")
	private WebElement noMatchProductMessageElement;
	
	
	//Constructor
	public SearchPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	
	//Actions
	public void typeProductInSearchBox(String productname) {
		searchBox.sendKeys(productname);
	}
	
	
	public void clickSearchButton() {
		searchButton.click();
	}
	
	public boolean isProductDisplayed() {
		boolean isDisplayedproduct = productAvailableElement.isDisplayed();
		return isDisplayedproduct;
	}
	
	public String noMatchProductMessage() {
		String noMatchProductMessageText = noMatchProductMessageElement.getText();
		return noMatchProductMessageText;
	}
	
	
	
	
}
