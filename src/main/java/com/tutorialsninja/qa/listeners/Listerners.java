package com.tutorialsninja.qa.listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tutorialsninja.qa.utils.CommonUtils;
import com.tutorialsninja.qa.utils.ExtentReporter;

public class Listerners implements ITestListener {
	
	ExtentReports extentReport;
	ExtentTest extentTest;
	WebDriver driver;
	String testName;
	
	@Override
	public void onStart(ITestContext context) {
    extentReport=	ExtentReporter.generateExtentReport();
	}

	@Override
	public void onTestStart(ITestResult result) {
		
    testName= result.getName();
	extentTest = extentReport.createTest(testName);
	extentTest.log(Status.INFO, testName + "Test execution started");
	
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		extentTest.log(Status.INFO, testName + "Test executed successfully");
		extentTest.log(Status.PASS, testName);
		
	}

	@Override
	public void onTestFailure(ITestResult result) {

			try {
				driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
			} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
				e.printStackTrace();
			}
		
		String destinationScreenshotFile= CommonUtils.captureScreenShot(driver, result.getTestName());
		
		extentTest.addScreenCaptureFromPath(destinationScreenshotFile);
		
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.INFO, testName + "Test got failed");
		extentTest.log(Status.FAIL, testName);
	
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {

		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.SKIP, testName + "Test got skipped");
		
	}



	@Override
	public void onFinish(ITestContext context) {
		
		extentReport.flush();
		
		String pathOfExtentReport= System.getProperty("user.dir")+"\\test-output\\ExtentReport\\ExtentReport.html";
		File extentReportFile = new File(pathOfExtentReport);
		try {
			Desktop.getDesktop().browse(extentReportFile.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

}
