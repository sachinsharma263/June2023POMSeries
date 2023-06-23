package com.qa.trcrm.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.trcrm.base.BasePage;
import com.qa.trcrm.pages.ContactsPage;
import com.qa.trcrm.pages.LoginPage;
import com.qa.trcrm.utils.AppConstants;

public class ContactsPageTest {

	WebDriver driver;
	BasePage basePage;
	Properties prop;
	ContactsPage contactPage;
	
	@BeforeMethod
	public void setUp() {
		basePage=new BasePage();
		prop=basePage.init_prop();
		driver=basePage.init_driver(prop);
		contactPage=new LoginPage(driver).doLogin(prop.getProperty("username"), prop.getProperty("password")).goToContactsPage();
	}
	@Test
	public void verifyContactsPageTitle() {
		Assert.assertEquals(contactPage.getContactsPageTitle(), "TRCRM");
	}
	@Test
	public void verifyContactsPageHeaderTest() {
		String contactPageHeader=contactPage.getContactsPageHeader();
		Assert.assertEquals(contactPageHeader, AppConstants.CONTACT_PAGE_HEADER);
	}
	@Test
	public void verifyAddPersonTest() {
		String addPersonMsg=contactPage.addPerson("navven", "naveen@gmail.com");
		Assert.assertEquals(addPersonMsg, AppConstants.PERSON_ADDED_MSG);
	}

}
