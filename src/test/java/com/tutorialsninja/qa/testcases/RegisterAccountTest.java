package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.turorialsninja.qa.pages.AccountSuccessPage;
import com.turorialsninja.qa.pages.HomePage;
import com.turorialsninja.qa.pages.RegisterPage;
import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.utils.Utilities;

public class RegisterAccountTest extends Base{
	
	
	RegisterPage registerPage;
	AccountSuccessPage accountSuccessPage;
	
	public RegisterAccountTest(){
		super();
		
	}
	
	public WebDriver driver;
	
	@BeforeMethod
	public void setup () {
		
		driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
		HomePage homePage = new HomePage(driver);
		registerPage = homePage.navigateToRegisterPage();
		
	}
	
	@AfterMethod
	public void tearDown() {
		
		driver.quit();
	}
	
	@Test (priority=1)
	public void verifyRegisteringAnAccountWithMandatoryFields() {
		
		 
//		registerPage.enterFirstName(dataProp.getProperty("firstName"));
//		registerPage.enterLastName(dataProp.getProperty("lastName"));
//		registerPage.enterEmailAddress(Utilities.generateEmailWithTimeStamp());
//		registerPage.enterTelephoneNumber(dataProp.getProperty("phoneNumber"));
//		registerPage.enterPassword(prop.getProperty("validPassword"));
//		registerPage.enterConfirmPassword(prop.getProperty("validPassword"));
//		registerPage.selectPrivacyPolicy();
//		accountSuccessPage = registerPage.clickOnContinueButton(); 
		
		accountSuccessPage = registerPage.registerWithMandatoryFields(dataProp.getProperty("firstName"),dataProp.getProperty("lastName"),Utilities.generateEmailWithTimeStamp(),dataProp.getProperty("phoneNumber"),prop.getProperty("validPassword"));
		Assert.assertEquals(accountSuccessPage.retrieveAccountSuccessPageHeading(), dataProp.getProperty("accountSuccessfullyCreatedHeading"),"Account Success page is not displayed");
		
	}	
	
	
	@Test (priority=2)
	public void verifyRegisteringAccountByProvidingAllFields() {
		
		accountSuccessPage = registerPage.registerWithAllFields(dataProp.getProperty("firstName"),dataProp.getProperty("lastName"),Utilities.generateEmailWithTimeStamp(),dataProp.getProperty("phoneNumber"),prop.getProperty("validPassword"));
//		registerPage.enterFirstName(dataProp.getProperty("firstName"));
//		registerPage.enterLastName(dataProp.getProperty("lastName"));
//		registerPage.enterEmailAddress(Utilities.generateEmailWithTimeStamp());
//		registerPage.enterTelephoneNumber(dataProp.getProperty("phoneNumber")); 
//		registerPage.enterPassword(prop.getProperty("validPassword"));
//		registerPage.enterConfirmPassword(prop.getProperty("validPassword"));
//		registerPage.selectYesNewsletterOption();
//		registerPage.selectPrivacyPolicy();
//		accountSuccessPage = registerPage.clickOnContinueButton();
 	 
//		String actualSuccessHeading = accountSuccessPage.retrieveAccountSuccessPageHeading();
		Assert.assertEquals(accountSuccessPage.retrieveAccountSuccessPageHeading(), dataProp.getProperty("accountSuccessfullyCreatedHeading"),"Account Success page is not displayed");
			 
	}
	
	@Test (priority=3)
	public void verifyRegisteringAccountWithExistingEmailAddress() {
		
		accountSuccessPage = registerPage.registerWithAllFields(dataProp.getProperty("firstName"),dataProp.getProperty("lastName"),prop.getProperty("validEmail"),dataProp.getProperty("phoneNumber"),prop.getProperty("validPassword"));
		
//		registerPage.enterFirstName(dataProp.getProperty("firstName"));
//		registerPage.enterLastName(dataProp.getProperty("lastName"));
//		registerPage.enterEmailAddress(prop.getProperty("validEmail"));
//		registerPage.enterTelephoneNumber(dataProp.getProperty("phoneNumber")); 
//		registerPage.enterPassword(prop.getProperty("validPassword"));
//		registerPage.enterConfirmPassword(prop.getProperty("validPassword"));
//		registerPage.selectYesNewsletterOption();
//		registerPage.selectPrivacyPolicy();
//		registerPage.clickOnContinueButton();
		
		 
//		String actualWarningMessage = registerPage.retrieveDuplicateEmailAddressWarning();
		Assert.assertTrue(registerPage.retrieveDuplicateEmailAddressWarning().contains(dataProp.getProperty("duplicateEmailWarning")),"Warning Message regarding duplicate email address is not displayed");
		

	}
	
	
	@Test (priority=4)
	public void verifyRegisteringAccountWithoutFillingOutAnyInformation() {
		
		 
		registerPage.clickOnContinueButton();
		 
//		String actualPrivacyPolicyWarning = registerPage.retrievePrivacyPolicyWarning();
		Assert.assertTrue(registerPage.retrievePrivacyPolicyWarning().contains(dataProp.getProperty("privacyPolicyWarning")),"Privacy Policy Warning Message is not displayed");
		
//		String actualFirstNameWarning = registerPage.retrieveFirstNameWarning();
		Assert.assertEquals(registerPage.retrieveFirstNameWarning(), dataProp.getProperty("firstNameWarning"), "First Name Warning Message is not displayed");
		
//		String actualLastNameWarning = registerPage.retrieveLastNameWarning();
		Assert.assertEquals(registerPage.retrieveLastNameWarning(), dataProp.getProperty("lastNameWarning"), "Last Name Warning Message is not displayed");
		
//		String actualEmailWarning = registerPage.retrieveEmailWarning();
		Assert.assertEquals(registerPage.retrieveEmailWarning(), dataProp.getProperty("emailWarning"), "E-Mail Address Warning Message is not displayed");
		
//		String actualTelephoneWarning = registerPage.retrievePhoneWarning();
		Assert.assertEquals(registerPage.retrievePhoneWarning(), dataProp.getProperty("telePhoneWarning"), "Telephone Warning Message is not displayed");
		
//		String actualPasswordWarning = registerPage.retrievePasswordWarning();
		Assert.assertEquals(registerPage.retrievePasswordWarning(), dataProp.getProperty("passwordWarning"), "Password Warning Message is not displayed");
		
		 
	}
	
}
