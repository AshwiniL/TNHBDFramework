package com.tutorialsninja.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter {
	
	
	public static ExtentReports generateExtentReport()
	{
		FileInputStream propFile = null;
	ExtentReports extentReport= new ExtentReports();
	
	File extentReportFile = new File(System.getProperty("user.dir")+"\\test-output\\ExtentReport\\ExtentReport.html");
	ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extentReportFile);
	
	sparkReporter.config().setTheme(Theme.DARK);
	sparkReporter.config().setReportName("Tutorials Ninja Test Automation");
	sparkReporter.config().setDocumentTitle("TN Automation Report");
	sparkReporter.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");
	
	extentReport.attachReporter(sparkReporter);
	
	Properties reportProp= new Properties();
	try {
		 propFile= new FileInputStream(new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsninja\\qa\\config\\config.properties"));
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	}
	
	try {
		reportProp.load(propFile);
	} catch (IOException e) {
		e.printStackTrace();
	}
	
	extentReport.setSystemInfo("Application URL", reportProp.getProperty("url"));
	extentReport.setSystemInfo("Browser", reportProp.getProperty("browserName"));
	extentReport.setSystemInfo("Email", reportProp.getProperty("validEmailID"));
	extentReport.setSystemInfo("Operating System", System.getProperty("os.name"));
	extentReport.setSystemInfo("Java Version", "java.version");
	extentReport.setSystemInfo("User", "user.name");
	
	return extentReport;
	
	}
	

}
