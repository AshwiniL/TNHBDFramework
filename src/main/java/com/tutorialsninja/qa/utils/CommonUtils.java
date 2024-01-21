package com.tutorialsninja.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

public class CommonUtils {

	public static final int IMPLICIT_WAIT_TIME=10;
	public static final int PAGE_LOAD_TIME=5;
	
	
	
	public static String generateEmailWithTimeStamp()
	{
		Date date = new Date();
		String timeStamp= date.toString().replace(" ","_").replace(":", "_");
		return "a11b"+timeStamp+"@mailinator.com";
	}
	
	public static Object[][] getDataFromExcelFile(String sheetName)
	{
		FileInputStream fisExcel = null;
		try {
			fisExcel = new FileInputStream(new File(System.getProperty("user.dir")+"\\testdata\\TutorialsNinjaTestData.xlsx"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		XSSFWorkbook workBook = null;
		try {
			workBook = new XSSFWorkbook(fisExcel);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 XSSFSheet sheet = workBook.getSheet(sheetName);
		 int rows= sheet.getFirstRowNum();
		 int cols = sheet.getRow(0).getLastCellNum();
		 
		 Object [][] data = new Object[rows][cols];
		 
		 for(int i=0;i<rows;i++)
		 {
			 XSSFRow row = sheet.getRow(i+1);
			 
			 for (int j=0;j<cols;j++)
			 {
				 XSSFCell cell = row.getCell(j);
				 CellType cellType = cell.getCellType();
				 
				 switch(cellType)
				 {
				 case STRING:
					 data[i][j]=cell.getStringCellValue();
					 break;
					 
				 case NUMERIC:
					 data[i][j]= Integer.toString((int)cell.getNumericCellValue());
					 break;
					 
				 }
			 }
		 }
		 return data;
	}
	
	public static String captureScreenShot(WebDriver driver, String testName)
	{
		File srcScreenShotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String destinationScreenshotFile = System.getProperty("user.dir")+"\\Screenshots\\"+testName+".png";
		try {
			org.openqa.selenium.io.FileHandler.copy(srcScreenShotFile, new File (destinationScreenshotFile));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return destinationScreenshotFile;
	}
	
	
}
