package com.tutorialsninja.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.tutorialsninja.qa.utils.CommonUtils;

public class BaseClass {
	
	WebDriver driver;
	public Properties prop,dataProp;
	FileInputStream fis,dataFile;
	
	public BaseClass()
	{
		prop=new Properties();
		try {
			 fis = new FileInputStream(new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsninja\\qa\\config\\config.properties"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			prop.load(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		dataProp = new Properties();
		try {
			dataFile = new FileInputStream(new File(System.getProperty("user.dir")+"\\testdata\\testdata.properties"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			dataProp.load(dataFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	public WebDriver initialize(String browserName)
	{
		if(browserName.equalsIgnoreCase("chrome"))
		{
			 driver = new ChromeDriver();
		}
		else if(browserName.equals("firefox"))
		{
		 driver= new FirefoxDriver();	
		}
		else if(browserName.equals("edge"))
		{
			 driver= new EdgeDriver();	
			}
		
		   
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(CommonUtils.IMPLICIT_WAIT_TIME));
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(CommonUtils.PAGE_LOAD_TIME));
			
			driver.get(prop.getProperty("url"));
			
			return driver;
	}

}
