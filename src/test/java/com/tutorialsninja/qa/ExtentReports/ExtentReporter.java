package com.tutorialsninja.qa.ExtentReports;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter {
	public static ExtentReports extentReport;
	public static File extentReportFile;
	public static ExtentSparkReporter sparkReporter;
	public static Properties prop;
	public static FileInputStream ip;
	
	
	public static ExtentReports generateExtentReports() throws IOException    {
		
		//Step 1   We need to get the Maven dependency of Extent reports in pom.xml file
		//Step 2  Create the object of Extent report class
		
		extentReport = new ExtentReports();
		
		//Step 3 Create the object of the file class and pass the path of the .html file inside the constructor
		
		 extentReportFile = new File(System.getProperty("user.dir")   +  "\\test-outputExtentReports\\extentreport.html" );
		
		//Step 4  Create the Object of ExtentSparkReporter class and pass the File reference in the Constructor  
		
		 sparkReporter = new ExtentSparkReporter(extentReportFile);
		
		//Step 5  Using the sparkReporter, we can configure a lot of things
		
		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setReportName("TN Automation Results");
		sparkReporter.config().setDocumentTitle("TNReportTitle|Automation|Results");
		sparkReporter.config().setTimeStampFormat("MM/dd/yyyy hh:mm:ss");
		
		//Step 6  We need to attach the ExtentReport with the sparkReporter
		
		extentReport.attachReporter(sparkReporter);
		
		//Step 7 Add additional information and create a Properties file and read from it
		 prop  = new Properties();
		 ip = new FileInputStream(System.getProperty("user.dir")    +   "\\src\\test\\java\\com\\tutorilsninja\\qa\\config\\config.properties");
		prop.load(ip);
		
		//Step 8  Application URL, browser, username, password, operating system, Java version, name of the SDET
		
		extentReport.setSystemInfo("application url" , prop.getProperty("url"));
		extentReport.setSystemInfo("browser name" , prop.getProperty("browser"));
		extentReport.setSystemInfo("username" , prop.getProperty("validEmail"));
		extentReport.setSystemInfo("password" , prop.getProperty("validPassword"));
		extentReport.setSystemInfo("operating system",  System.getProperty("os.name"));
		extentReport.setSystemInfo("operating system version ",  System.getProperty("os.version"));
		extentReport.setSystemInfo("SDET name",  System.getProperty("user.name"));
		extentReport.setSystemInfo("Java Runtime version",  System.getProperty("java.runtime.version"));
		
		//Step 9 Return report
		
		return extentReport;
	}

}
