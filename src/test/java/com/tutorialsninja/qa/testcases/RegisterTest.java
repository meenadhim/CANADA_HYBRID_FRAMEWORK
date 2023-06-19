package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.tutorialsninja.qa.pages.AccountSuccessPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.RegisterPage;
import com.tutorialsninja.qa.testBase.TestBase;
import com.tutorialsninja.qa.testdata.ExcelData;
import com.tutorialsninja.qa.utilities.Utils;

public class RegisterTest extends TestBase {

	public WebDriver driver;
	public SoftAssert softassert = new SoftAssert();
	public RegisterPage registerpage;
	public HomePage homepage;
	public AccountSuccessPage accountsuccesspage;

	@BeforeMethod
	public void setUp() {
		driver  = initializeBrowserAndOpenApplication("chrome");
	    homepage = new HomePage(driver);
		homepage.clickonMyAccountLink();
		registerpage = homepage.selectRegisterOption();
		
	}

	@Test(priority = 1 , dataProvider = "TN Register", dataProviderClass = ExcelData.class)
	public void registerWithMandatoryFields(String firstname, String lastname, String email, String telephone, String password, String confirmPassword) {
		registerpage.enterFirstName(firstname);
		registerpage.enterLastName(lastname);
		registerpage.enterEmail(Utils.emailWithDateTimeStamp());
		registerpage.enterTelephone(telephone);
		registerpage.enterPassword(password);
		registerpage.enterConfirmPassword(confirmPassword);
		registerpage.clickonPrivacyPolicyCheckBox();
		accountsuccesspage = registerpage.clickonContinueButton();
		softassert.assertTrue(accountsuccesspage.validateDisplayAccountCreatedSuccessMessage());
		softassert.assertAll();

	}

	@Test(priority = 2)
	public void registerWithAllFields() {
		registerpage.enterFirstName(testdataprop.getProperty("firstname"));
		registerpage.enterLastName(testdataprop.getProperty("lastname"));
		registerpage.enterEmail(Utils.emailWithDateTimeStamp());
		registerpage.enterTelephone(testdataprop.getProperty("telephone"));
		registerpage.enterPassword(prop.getProperty("validPassword"));
		registerpage.enterConfirmPassword(prop.getProperty("validPassword"));
		registerpage.selectYesNewsLetterRadioButton();
		registerpage.clickonPrivacyPolicyCheckBox();
		accountsuccesspage = registerpage.clickonContinueButton();
		softassert.assertTrue(accountsuccesspage.validateDisplayAccountCreatedSuccessMessage());
	    softassert.assertAll();
	}

	@Test(priority = 3)
	public void registerWithExistingEmailID() {
		
		registerpage.enterFirstName(testdataprop.getProperty("firstname"));
		registerpage.enterLastName(testdataprop.getProperty("lastname"));
		registerpage.enterEmail(prop.getProperty("validEmail"));
		registerpage.enterTelephone(testdataprop.getProperty("telephone"));
		registerpage.enterPassword(prop.getProperty("validPassword"));
		registerpage.enterConfirmPassword(prop.getProperty("validPassword"));
		registerpage.selectYesNewsLetterRadioButton();
		registerpage.clickonPrivacyPolicyCheckBox();
		registerpage.clickonContinueButton();
		String actualWarningMessage = registerpage.retrieveDuplicateEmailWarningMessage();
		String expectedWarningMessage = testdataprop.getProperty("duplicateEmailWarning");
		softassert.assertEquals(actualWarningMessage, expectedWarningMessage);
	}

	@Test(priority = 4)
	public void registerWithNoFields() {
		
		registerpage.clickonContinueButton();
		
		String actualFirstnameWarningMessage  = registerpage.retrieveFirstnameWarningMessage();
		String expectedFirstnameWarningMessage  = testdataprop.getProperty("firstnameWarning");
		softassert.assertTrue(actualFirstnameWarningMessage.contains(expectedFirstnameWarningMessage));
		
		String actualLastnameWarningMessage  = registerpage.retrieveLastnameWarningMessage();
		String expectedLastnameWarningMessage  = testdataprop.getProperty("lastnameWarning");
		softassert.assertTrue(actualLastnameWarningMessage.contains(expectedLastnameWarningMessage));
		
		String actualEmailWarningMessage  = registerpage.retrieveEmailWarningMessage();
		String expectedEmailWarningMessage  = testdataprop.getProperty("emailWarning");
		softassert.assertTrue(actualEmailWarningMessage.contains(expectedEmailWarningMessage));
		
		String actualTelephoneWarningMessage  = registerpage.retrieveTelephoneWarningMessage();
		String expectedTelephoneWarningMessage  = testdataprop.getProperty("telephoneWarning");
		softassert.assertTrue(actualTelephoneWarningMessage.contains(expectedTelephoneWarningMessage));
		
		String actualPasswordWarningMessage  = registerpage.retrievePasswordWarningMessage();
		String expectedPasswordWarningMessage  = testdataprop.getProperty("passwordWarning");
		softassert.assertTrue(actualPasswordWarningMessage.contains(expectedPasswordWarningMessage));
		
		String actualPrivacyPolicyWarningMessage  = registerpage.retrievePrivacyPolicyWarningMessage();
		String expectedPrivacyPolicyWarningMessage  = testdataprop.getProperty("privacyPolicyWarning");
		softassert.assertTrue(actualPrivacyPolicyWarningMessage.contains(expectedPrivacyPolicyWarningMessage));
		
		softassert.assertAll();
       }

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
