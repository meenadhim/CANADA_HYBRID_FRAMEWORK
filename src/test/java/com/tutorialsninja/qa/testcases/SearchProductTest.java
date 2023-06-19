package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.tutorialsninja.qa.RetryFailedTest.MyRetry;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.SearchProductPage;
import com.tutorialsninja.qa.testBase.TestBase;

public class SearchProductTest extends TestBase {

	public WebDriver driver;
	public SoftAssert softassert = new SoftAssert();
	public SearchProductPage searchproductpage;
	public HomePage homepage;

	@BeforeMethod
	public void setUp() {
		
		driver  = initializeBrowserAndOpenApplication("chrome");
	
	}
	
	@Test(priority = 1   ,retryAnalyzer = MyRetry.class)
	public void verifySearchWithValidProduct()   {
		homepage = new HomePage(driver);
	    homepage.enterProductNameInSearchbox(testdataprop.getProperty("validProduct"));
		searchproductpage = homepage.clickOnSearchButton();
	    softassert.assertTrue(searchproductpage.validateDisplayOfValidProduct());
		softassert.assertAll();
	}
	
	@Test(priority = 2 ,retryAnalyzer = MyRetry.class)
	public void verifySearchWithInValidProduct()   {
		homepage = new HomePage(driver);
		homepage.enterProductNameInSearchbox(testdataprop.getProperty("invalidProduct"));
		searchproductpage = homepage.clickOnSearchButton();
		softassert.assertFalse(searchproductpage.validateDisplayOfinValidOrNoProduct());
		softassert.assertAll();
	}
	@Test(priority = 3 , retryAnalyzer = MyRetry.class,dependsOnMethods = "verifySearchWithInValidProduct")
	public void verifySearchWithNoProduct()   {
		homepage = new HomePage(driver);
		homepage.clickOnSearchButton();
		searchproductpage = homepage.clickOnSearchButton();
		softassert.assertTrue(searchproductpage.validateDisplayOfinValidOrNoProduct());
	    softassert.assertAll();
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();

	}
}