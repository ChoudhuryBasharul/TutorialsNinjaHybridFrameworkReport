package com.tutorialsninja.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utilities {
	
	WebDriver driver;
	public Utilities (WebDriver driver) {
		
		this.driver=driver;
	}
	
	public static final int IMPLICIT_WAIT_TIME=10;
	public static final int PAGE_LOAD_TIME=5;
	public static final int EXPLICT_WAIT=30;
	
	
	
	public static String generateEmailWithTimeStamp() {
		
		Date date = new Date();
		String timestamp = date.toString().replace(" ", "_").replace(":", "_");
		return "choudhurybasharul"+timestamp+"@gmail.com";
		
}
	
	public static Object [][] getTestDataFromExcel(String sheetName) {
		File excelFile = new File(System.getProperty("user.dir")+"/src/main/java/com/tutorialsninja/qa/testdata/TutorialsNinja0929.xlsx");
		XSSFWorkbook workbook = null;
		
		try {
		FileInputStream fisExcel = new FileInputStream(excelFile);
		workbook = new XSSFWorkbook(fisExcel);
		}catch(Throwable e) {
			e.printStackTrace();
		}
		
		XSSFSheet sheet = workbook.getSheet(sheetName);
		
		int rows= sheet.getLastRowNum();
		int cols = sheet.getRow(0).getLastCellNum();
		
		Object [][] data = new Object [rows][cols];
		
		for (int i=0;i<rows;i++) {
			
			XSSFRow row = sheet.getRow(i+1);
			
			for (int j=0; j<cols;j++) {
				
				XSSFCell cell = row.getCell(j);
				CellType cellType = cell.getCellType();
				
				switch(cellType) {
				
				case STRING:
					
					data [i][j] = cell.getStringCellValue();
					break;
					
				case NUMERIC:
					data[i][j]= Integer.toString((int)cell.getNumericCellValue());
					break;
					
				case BOOLEAN:
					data [i][j] = cell.getBooleanCellValue();
					break;
				}
			}
		}
		
		return data;
	}
	
	public WebElement waitForVisibilityOfElemant(WebElement element) {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(EXPLICT_WAIT));
		
		WebElement webElement = wait.until(ExpectedConditions.visibilityOf(element));
		return webElement;
		
	}
	
	public static String captureScreenshot(WebDriver driver, String testName) {
		
		File srcScreenshot =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String destinationScreenshotPath = System.getProperty("user.dir")+"\\Screenshots\\"+testName+".png";
		
		try {
			FileHandler.copy(srcScreenshot, new File(destinationScreenshotPath));
		} catch (IOException e) {
	
			e.printStackTrace();
		}
	
	return destinationScreenshotPath;
}
}

