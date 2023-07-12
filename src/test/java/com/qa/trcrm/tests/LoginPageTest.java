package com.qa.trcrm.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.trcrm.base.BasePage;

import com.qa.trcrm.pages.HomePage;

import com.qa.trcrm.pages.LoginPage;
import com.qa.trcrm.pojo.Credentials;
import com.qa.trcrm.utils.AppConstants;
import com.qa.trcrm.utils.JiraPolicy;
import com.qa.trcrm.utils.Log;

public class LoginPageTest {

	WebDriver driver;
	BasePage basePage;
	Properties prop;
	LoginPage loginPage;
	HomePage homePage;
	Credentials credentials;

	@BeforeTest
	public void setUp() {
		basePage = new BasePage();
		Log.info("base page laucnhed");
		prop = basePage.init_prop();
		Log.info("prop initialized");
		driver = basePage.init_driver(prop);
		Log.info("driver launched");
		loginPage = new LoginPage(driver);
		Log.info("LoginPage intialized");

		if (System.getProperty("email")==null && System.getProperty("pass")==null) {
			credentials = new Credentials(prop.getProperty("username"), prop.getProperty("password"));
		}
		else if (System.getProperty("email").isEmpty() && System.getProperty("pass").isEmpty()) {
			credentials = new Credentials(prop.getProperty("username"), prop.getProperty("password"));
		} else {
			credentials = new Credentials(System.getProperty("email"), System.getProperty("pass"));

		}

	}

	@Test(priority = 1)
	public void verifyLoginPageTitleTest() {
		Log.info("getting Login page title");
		String title = loginPage.getLoginPageTitle();
		Log.info("Login page title is: " + title);
		Assert.assertEquals(title, AppConstants.LOGIN_PAGE_TITLE);
	}

	@JiraPolicy(logTicketReady = true)
	@Test(priority = 2)
	public void verifySignNowLinkTest() {
		Log.info("verify sign up link");
		Assert.assertTrue(loginPage.verifySignUpLink());
	}

	@Test(priority = 3)
	public void loginTest() {
		Log.info("login attempt");
		homePage = loginPage.doLogin(credentials);
		Assert.assertEquals(homePage.getHomePageHeader(), AppConstants.HOME_PAGE_HEADER);

	}

	@Test(dataProvider = "getLoginInvalidData", enabled = false)
	public void login_InvalidTestCases(String email, String pass) {
		loginPage.doLogin(email, pass);
		Assert.assertTrue(loginPage.errorMsg());
	}

	@DataProvider
	public Object[][] getLoginInvalidData() {
		Object data[][] = { { "test@gmail.com", "test@123" }, { "test2@gmail.com", "test@123" } };
		return data;

	}

	@AfterTest
	public void tearDown() {
		Log.info("quiting the browser");
		driver.quit();
	}
}
