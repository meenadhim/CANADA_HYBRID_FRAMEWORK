package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {

	public WebDriver driver;

	@FindBy(id = "input-firstname")
	private WebElement firstnameTextBox;

	@FindBy(id = "input-lastname")
	private WebElement lastnameTextBox;

	@FindBy(id = "input-email")
	private WebElement emailTextBox;

	@FindBy(id = "input-telephone")
	private WebElement telephoneTextBox;

	@FindBy(id = "input-password")
	private WebElement passwordTextBox;

	@FindBy(id = "input-confirm")
	private WebElement confirmpasswordTextBox;

	@FindBy(name = "agree")
	private WebElement privacyPolicyCheckBox;

	@FindBy(css = "input.btn.btn-primary")
	private WebElement ContinueButton;

	@FindBy(xpath = "//input[@name = 'newsletter'][@value = '1']")
	private WebElement newsLetterRadioButton;

	@FindBy(xpath = "//div[contains(@class, 'alert-dismissible')]")
	private WebElement duplicateEmailWarning;

	@FindBy(xpath = "//input[@id = 'first-name']/following-sibling::div")
	private WebElement firstnameWarning;
	
	@FindBy(xpath = "//input[@id = 'last-name']/following-sibling::div")
	private WebElement lastnameWarning;
	
	@FindBy(xpath = "//input[@id = 'e-mail']/following-sibling::div")
	private WebElement emailWarning;
	
	@FindBy(xpath = "//input[@id = 'telephone']/following-sibling::div")
	private WebElement telephoneWarning;
	
	@FindBy(xpath = "//input[@id = 'password']/following-sibling::div")
	private WebElement passwordWarning;
	
	@FindBy(xpath = "//input[@class = 'alert-dismissible']")
	private WebElement privacyPolicyWarning;

	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void enterFirstName(String firstNameText) {
		firstnameTextBox.sendKeys(firstNameText);
	}

	public void enterLastName(String lastNameText) {
		lastnameTextBox.sendKeys(lastNameText);
	}

	public void enterEmail(String emailText) {
		emailTextBox.sendKeys(emailText);
	}

	public void enterTelephone(String telephoneText) {
		telephoneTextBox.sendKeys(telephoneText);
	}

	public void enterPassword(String passwordText) {
		passwordTextBox.sendKeys(passwordText);
	}

	public void enterConfirmPassword(String confirmpasswordText) {
		confirmpasswordTextBox.sendKeys(confirmpasswordText);
	}

	public void clickonPrivacyPolicyCheckBox() {
		privacyPolicyCheckBox.click();
	}

	public AccountSuccessPage clickonContinueButton() {
		ContinueButton.click();
		return new AccountSuccessPage(driver);
	}

	public void selectYesNewsLetterRadioButton() {
		newsLetterRadioButton.click();

	}

	public String retrieveDuplicateEmailWarningMessage() {
		String duplicateEmail = duplicateEmailWarning.getText();
		return duplicateEmail;
	}

	public String retrieveFirstnameWarningMessage() {
		String fnWarning = firstnameWarning.getText();
		return fnWarning;
	}
	public String retrieveLastnameWarningMessage() {
		String lnWarning = lastnameWarning.getText();
		return lnWarning;
	}
	public String retrieveEmailWarningMessage() {
		String emWarning = emailWarning.getText();
		return emWarning;
	}
	public String retrieveTelephoneWarningMessage() {
		String tWarning = telephoneWarning.getText();
		return tWarning;
	}
	public String retrievePasswordWarningMessage() {
		String pWarning = passwordWarning.getText();
		return pWarning;
	}
	public String retrievePrivacyPolicyWarningMessage() {
		String ppWarning = privacyPolicyWarning.getText();
		return ppWarning;
	

}
}