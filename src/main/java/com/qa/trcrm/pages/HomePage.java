package com.qa.trcrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.trcrm.utils.AppConstants;
import com.qa.trcrm.utils.ElementUtil;
import com.qa.trcrm.utils.JavaScriptUtil;

public class HomePage {

	WebDriver driver;
	ElementUtil util;
	JavaScriptUtil jsUtil;

	By homePageHeader = By.xpath("//span[text()='Homepage']");
	By loggedInUser=By.xpath("//span[text()=' sachin sharma']");
	
	By contactsPageLink = By.xpath("//li[@id='contactMenuLi']/a");

	public HomePage(WebDriver driver) {
		this.driver = driver;
		util = new ElementUtil(driver);
		jsUtil=new JavaScriptUtil(driver);

	}

	public String getHomePageHeader() {
		util.waitForPresenceOfElementLocated(homePageHeader);
		return util.doGetText(homePageHeader);
	}

	public String getHomePageTitle() {
		util.waitForPresenceOfTitle(AppConstants.HOME_PAGE_TITLE);
		return util.doGetTitle();
	}
	public String loggedInUser() {
		return util.doGetText(loggedInUser);
	}
	public ContactsPage goToContactsPage() {
		util.waitForPresenceOfElementLocated(contactsPageLink);
		jsUtil.clickElementByJS(util.getElement(contactsPageLink));
		
		return new ContactsPage(driver);
	}
}
