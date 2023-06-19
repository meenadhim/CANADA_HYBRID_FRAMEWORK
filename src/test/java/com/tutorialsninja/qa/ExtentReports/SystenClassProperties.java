package com.tutorialsninja.qa.ExtentReports;

public class SystenClassProperties {

	public static void main(String[] args) {

     
		 System.getProperties().list(System.out);
		 System.out.println("The SDET who is doing this is "    +  System.getProperty("user.name"));

	}

}
