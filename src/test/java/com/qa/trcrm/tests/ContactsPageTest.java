package com.qa.trcrm.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.trcrm.base.BasePage;
import com.qa.trcrm.pages.ContactsPage;
import com.qa.trcrm.pages.LoginPage;
import com.qa.trcrm.pojo.Contacts;
import com.qa.trcrm.utils.AppConstants;
import com.qa.trcrm.utils.ExcelUtil;

public class ContactsPageTest {

	WebDriver driver;
	BasePage basePage;
	Properties prop;
	ContactsPage contactPage;
	Contacts contacts;

	@BeforeMethod
	public void setUp() {
		basePage = new BasePage();
		prop = basePage.init_prop();
		driver = basePage.init_driver(prop);
		contactPage = new LoginPage(driver).doLogin(prop.getProperty("username"), prop.getProperty("password"))
				.goToContactsPage();
	}

	@Test(priority = 1)
	public void verifyContactsPageTitleTest() {
		Assert.assertEquals(contactPage.getContactsPageTitle(),AppConstants.CONTACT_PAGE_TITLE);
	}

	@Test(priority = 2)
	public void verifyContactsPageHeaderTest() {
		String contactPageHeader = contactPage.getContactsPageHeader();
		Assert.assertEquals(contactPageHeader, AppConstants.CONTACT_PAGE_HEADER);
	}

	@Test(priority =3, dataProvider = "getTestData")
	public void verifyAddPersonTest(String name, String email) {
		 contacts=new Contacts(name,email);
		String addPersonMsg = contactPage.addPerson(contacts);
		Assert.assertEquals(addPersonMsg, AppConstants.PERSON_ADDED_MSG);
	}

	@DataProvider
	public Object[][] getTestData() {
		Object data[][] = ExcelUtil.getTestData(AppConstants.CONTACTS_SHEET_NAME);

		return data;
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
