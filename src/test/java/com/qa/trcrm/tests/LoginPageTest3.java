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

public class LoginPageTest3 {

	WebDriver driver;
	BasePage3 basePage;
	Properties prop;
	LoginPage loginPage;
	HomePage homePage;

	@Parameters("browser")
	@BeforeTest
	public void setUp(String browserName) {
		basePage = new BasePage3();
		prop = basePage.init_prop();
		driver = basePage.init_driver(prop,browserName);
		loginPage = new LoginPage(driver);

	}

	@Test(priority = 1)
	public void verifyLoginPageTitleTest() {
		String title = loginPage.getLoginPageTitle();
		Assert.assertEquals(title, AppConstants.LOGIN_PAGE_TITLE);
	}

	@Test(priority = 2)
	public void verifySignNowLinkTest() {
		Assert.assertTrue(loginPage.verifySignUpLink());
	}

	@Test(priority = 3)
	public void loginTest() {
		homePage=loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertEquals(homePage.getHomePageHeader(), AppConstants.HOME_PAGE_HEADER);
		
	}
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
