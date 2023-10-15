package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.turorialsninja.qa.pages.HomePage;
import com.turorialsninja.qa.pages.SearchPage;
import com.tutorialsninja.qa.base.Base;

public class SearchTest extends Base {
	
	SearchPage searchPage;
	HomePage homePage;
	
	public SearchTest(){
		super();
		
	}
	
	public WebDriver driver;
	
	
	@BeforeMethod
	public void commonSetup() {
		
		driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
		homePage = new HomePage(driver);
		
	}
	
	@AfterMethod
	public void tearDown(){
		
		driver.quit();
		
	}
	
	
	@Test (priority=1)
	public void verifySearchWithValidProduct() {
		
		searchPage = homePage.searchForAProduct(dataProp.getProperty("validProduct"));
//		homePage.enterProductIntoSearchBoxField(dataProp.getProperty("validProduct"));
//		searchPage = homePage.clickOnSearchButton();
		Assert.assertTrue(searchPage.displayStatusOfHPValidProduct(),"Valid product HP is not displayed in the Search results");
		
	}
	
	@Test (priority=2)
	public void verifySearchWithInvalidProduct(){
		
		// Failing Test Intentionally- dataProp.getProperty("noProductTextinSearchBar")
		
		searchPage = homePage.searchForAProduct(dataProp.getProperty("invalidProduct"));
//		homePage.enterProductIntoSearchBoxField(dataProp.getProperty("invalidProduct"));
//		searchPage = homePage.clickOnSearchButton();		
//		String actualSearchMessage = searchPage.retrieveNoProductMessageText();
		Assert.assertEquals(searchPage.retrieveNoProductMessageText(), "abcd", "No product message in Search results is not displayed");
		
	}
	
	@Test (priority=3, dependsOnMethods={"verifySearchWithValidProduct","verifySearchWithInvalidProduct"})
	public void verifySearchWithoutAnyProduct() {
		
		
		searchPage = homePage.clickOnSearchButton();	
//		String actualSearchMessage = searchPage.retrieveNoProductMessageText();
		Assert.assertEquals(searchPage.retrieveNoProductMessageText(), dataProp.getProperty("noProductTextinSearchBar"), "No product message in Search results is not displayed");
	
		
	}
}
