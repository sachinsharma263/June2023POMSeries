package com.qa.trcrm.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.qa.trcrm.base.BasePage3;
import com.qa.trcrm.pages.HomePage;
import com.qa.trcrm.pages.LoginPage;
import com.qa.trcrm.utils.AppConstants;

public class HomePageTest3 {

	WebDriver driver;
	Properties prop;
	BasePage3 basePage;
	LoginPage loginPage;
	HomePage homePage;

	@Parameters("browser")
	@BeforeTest
	public void setUp(String browserName) {
		basePage = new BasePage3();
		prop = basePage.init_prop();
		driver = basePage.init_driver(prop,browserName);
		loginPage = new LoginPage(driver);
		homePage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));

	}

	@Test(priority = 1)
	public void verifyHomePageTitleTest() {
		String homePageTitle = homePage.getHomePageTitle();
		Assert.assertEquals(homePageTitle, AppConstants.HOME_PAGE_TITLE);
	}
	@Test(priority = 2)
	public void verifyHomePageHeaderTest() {
		Assert.assertEquals(homePage.getHomePageHeader(),AppConstants.HOME_PAGE_HEADER);
	}

	@Test(priority = 3)
	public void verifyLoggedInUserTest() {
		Assert.assertEquals(homePage.loggedInUser(),prop.getProperty("accountname"));
	}
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
