package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.turorialsninja.qa.pages.AccountPage;
import com.turorialsninja.qa.pages.HomePage;
import com.turorialsninja.qa.pages.LoginPage;
import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.utils.Utilities;

public class LoginTest extends Base {
	
	LoginPage loginPage;
	
	public LoginTest(){
	super();	
		
	}
	
	public WebDriver driver;		// Declared in Global level
	Utilities util;

	
	@BeforeMethod
	public void commonSetup() {
		
		
		driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
		util = new Utilities(driver);
		
		HomePage homePage = new HomePage(driver);
		loginPage = homePage.navigateToLoginPage();
		
	}
	
	
	@AfterMethod
	public void tearDown() {
		
		driver.quit();
		
	}

	@Test (priority=1,dataProvider="validCredentialsSupplier")
	public void verifyLoginWithValidCredentials(String Email, String Password) {
	
		AccountPage accountPage = loginPage.login(Email, Password);
		Assert.assertTrue(accountPage.getDisplayStatusOfEditYourAccountInformation(),"Edit Your Account Informaiton Is Not Displayed");

	}
	
	@DataProvider (name="validCredentialsSupplier")
	public Object[][] supplyTestData() {
		
		Object[][] data = Utilities.getTestDataFromExcel("Login");
		return data;
	}


	@Test (priority=2)
	public void verifyLoginWithInvalidCredentials() {
		
		loginPage.login(Utilities.generateEmailWithTimeStamp(),dataProp.getProperty("invalidPassword"));
		Assert.assertTrue(loginPage.retrieveEmailPasswordNotMatchingWarningMessageText().contains(dataProp.getProperty("emailPasswordNoMatchWarning")),"Expeted Warning Message Is Not Displayed");
		
		
	}
	
	
	@Test (priority=3)
	public void verifyLoginWithInvalidEmailAndValidPassword() {
		
		loginPage.login(Utilities.generateEmailWithTimeStamp(), prop.getProperty("validPassword"));
		Assert.assertTrue(loginPage.retrieveEmailPasswordNotMatchingWarningMessageText().contains(dataProp.getProperty("emailPasswordNoMatchWarning")),"Expeted Warning Message Is Not Displayed");

	}
	
	
	@Test (priority=4)
	public void verifyLoginWithValidEmailAndInvalidPassword() {
		
		loginPage.login(prop.getProperty("validEmail"),dataProp.getProperty("invalidPassword"));
		Assert.assertTrue(loginPage.retrieveEmailPasswordNotMatchingWarningMessageText().contains(dataProp.getProperty("emailPasswordNoMatchWarning")),"Expeted Warning Message Is Not Displayed");
		
	}
	
	
	@Test (priority=5)
	public void verifyLoginWithoutProvidingCredentials() {
		
		WebElement loginButton = util.waitForVisibilityOfElemant(driver.findElement(By.xpath("//input[@value='Login']")));
		loginButton.click();
		
		 
		loginPage.clickOnLoginButton();
		Assert.assertTrue(loginPage.retrieveEmailPasswordNotMatchingWarningMessageText().contains(dataProp.getProperty("emailPasswordNoMatchWarning")),"Expeted Warning Message Is Not Displayed");
	
		
	}
	
	
	}

