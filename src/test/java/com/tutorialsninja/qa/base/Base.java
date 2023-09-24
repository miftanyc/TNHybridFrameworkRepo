package com.tutorialsninja.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.tutorialsninja.qa.utils.Utilities;

public class Base {
	
	WebDriver driver;
	public Properties prop;
	public Properties dataProp;
	
	
	public Base() {
		
		//Call Confiq.properties and Testdata.properties file
		prop = new Properties();
		File propFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsninja\\qa\\config\\config.properties");
		
		
		try {
			FileInputStream propfis = new FileInputStream(propFile);
			prop.load(propfis);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		dataProp = new Properties();
		File dataFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsninja\\qa\\testdata\\testdata.properties");
		FileInputStream datafis;
		
		
		try {
			datafis = new FileInputStream(dataFile);
			dataProp.load(datafis);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	//Browser Initializer
	public WebDriver initializedBrowserAndOpenApplicationURL(String browserName) {
		
		if(browserName.equalsIgnoreCase("Chrome")) {
			driver = new ChromeDriver();
		}else if(browserName.equalsIgnoreCase("Firefox")) {
			driver = new FirefoxDriver();
		}else if (browserName.equalsIgnoreCase("Edge")) {
			driver = new EdgeDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT_TIME));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.PAGE_LOAD_TIME));
		
		//Navigate to Page
		driver.get(prop.getProperty("url"));
		
		return driver;
		
	}

}
