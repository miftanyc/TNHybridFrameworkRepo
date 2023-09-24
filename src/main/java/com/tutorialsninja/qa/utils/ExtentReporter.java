package com.tutorialsninja.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter {
	
	

	public static ExtentReports generateExtentReport() {
		
		ExtentReports extentReport = new ExtentReports();
		File extentReportFile = new File(System.getProperty("user.dir") + "/test-output/ExtentReports/extentReport.html");

		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extentReportFile);

		//set configuration of extentReprt looks and theme using sparkReporter
		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setReportName("TutorialsNinja Test Automation Results Report");
		sparkReporter.config().setDocumentTitle("TN Automation Report");
		//sparkReporter.config().setTimeStampFormat("MM/dd/YYYY hh:mm:ss");
		
		//To bind all sparkReporter request above use following command
		extentReport.attachReporter(sparkReporter);
		
		
		Properties prop = new Properties();
		File propfile = new File(System.getProperty("user.dir") + "/src/main/java/com/tutorialsninja/qa/config/config.properties");

		try{
		FileInputStream fis = new FileInputStream(propfile);
		prop.load(fis);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		extentReport.setSystemInfo("Application URL", prop.getProperty("url"));
		extentReport.setSystemInfo("Browser Name", prop.getProperty("browserName"));
		extentReport.setSystemInfo("Email", prop.getProperty("validEmail") );
		extentReport.setSystemInfo("Password", prop.getProperty("validPassword"));
		extentReport.setSystemInfo("Operating System", System.getProperty("os.name"));
		extentReport.setSystemInfo("User Name", System.getProperty("user.name"));
		extentReport.setSystemInfo("Java Version", System.getProperty("java.version"));
		
		
		//Use Following Line to get OS, UserName and JavaVersion Information which implemented in above code
		//System.getProperties().list(System.out);
		
		return extentReport;
	
	}
}
