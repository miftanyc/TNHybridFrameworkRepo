package com.tutorialsninja.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class Utilities {

	public static final int IMPLICIT_WAIT_TIME = 15;
	public static final int PAGE_LOAD_TIME = 15;
	
	
	
	//Random Email address generator based on timestamp
	public static String generateEmailWithTimeStamp() {
		Date date = new Date();
		String part = date.toString().replace(" ", "_").replace(":", "_");
		String email = "qatechie"+part+"@gmail.com";
		return email;
	}
	
	
	
	//Excel Data retrieve method
	public static Object[][] getTestDataFromExcel(String sheetName) {
		
		//*** Provide Excel Link Here***
		File excelFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsninja\\qa\\testdata\\Tutorialsninja.xlsx");
		
		XSSFWorkbook workbook = null;
		try{
			FileInputStream fisExcel = new FileInputStream(excelFile);
			workbook = new XSSFWorkbook(fisExcel);
			//XSSF stand's for “XML SpreadSheet Format”
		}catch(Throwable e) {
			e.printStackTrace();
		}
		
		
		XSSFSheet sheet = workbook.getSheet(sheetName);
		
		//get the row number of sheet
		int rows = sheet.getLastRowNum();
		//get the column number of sheet
		int cols = sheet.getRow(0).getLastCellNum();
		
		//create the object for 2 dimensional array to store row and column data
		Object[][] data = new Object[rows][cols];
		
		
		//Store the data from row and column of an Excel File
		for(int i=0; i<rows; i++) {
			
			XSSFRow row = sheet.getRow(i+1); //i+1 to exclude the Heading Row
			
			for(int j=0; j<cols; j++) {
				
				XSSFCell cell = row.getCell(j); 
				//row. means get the cell data for that row
				
				//column will have different data type. so store data base on its type use folloing command
				CellType cellType = cell.getCellType();
			
				switch(cellType) {
				
				case STRING:
					data[i][j] = cell.getStringCellValue();
					break;
					
				case NUMERIC:
					data[i][j] = Integer.toString((int)cell.getNumericCellValue());
					break;
					
				case BOOLEAN:
					data[i][j] = cell.getBooleanCellValue();
					break;
				
				}
			}		
		}
		
		return data;	
	}
	
	public static String captureScreenshot(WebDriver driver, String testName) {
		
		File srcScreenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String destinationScreenshotPath = System.getProperty("user.dir")+"\\Screenshots\\"+testName+".png";
		try {
			FileHandler.copy(srcScreenshot, new File (destinationScreenshotPath));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return destinationScreenshotPath;
		
	}
	
}
