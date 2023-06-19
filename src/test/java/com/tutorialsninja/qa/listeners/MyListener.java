package com.tutorialsninja.qa.listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tutorialsninja.qa.ExtentReports.ExtentReporter;
import com.tutorialsninja.qa.utilities.Utils;

public class MyListener implements ITestListener {

	public ExtentReports extentReport;
	public ExtentTest extentTest;
	public WebDriver driver;
	

	@Override
	public void onStart(ITestContext context) {
		System.out.println("Project Execution started");
		try {
			extentReport = ExtentReporter.generateExtentReports();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void onTestStart(ITestResult result) {
		extentTest = extentReport.createTest(result.getName());
		extentTest.log(Status.INFO,  result.getName() + "started executing");

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		extentTest.log(Status.PASS, result.getName() + "passed");

	}

	@Override
	public void onTestFailure(ITestResult result) {

		try {
			try {
				driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver")
						.get(result.getInstanceName());
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		} catch (NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
			String destinationPath = Utils.captureScreenShotCode(driver, result.getName());
			extentTest.addScreenCaptureFromPath(destinationPath);
			extentTest.log(Status.FAIL, result.getName() + "failed");
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {

		extentTest.log(Status.SKIP, result.getName() + "skipped");

	}

	@Override
	public void onFinish(ITestContext context) {
		System.out.println("Project Execution finished");
		extentReport.flush();
		String pathOfExtentReport = System.getProperty("user.dir") + "\\test-output\\ExtentReports\\extentreport.html";
		File extentReportpath = new File(pathOfExtentReport);
		try {
			Desktop.getDesktop().browse(extentReportpath.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
