package com.qa.trcrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.trcrm.pojo.Contacts;
import com.qa.trcrm.utils.ElementUtil;

public class ContactsPage {

	WebDriver driver;
	ElementUtil util;
	By contactsPageLink = By.xpath("//li[@id='contactMenuLi']/a");

	By name = By.name("name");
	By email = By.id("email0");

	By addPerson = By.xpath("//button[@class='hidden-xs hidden-sm btn btn-danger mr5 ng-scope ng-binding']");

	@FindBy(xpath = "//button[@class='hidden-xs hidden-sm btn btn-danger mr5 ng-scope ng-binding']")
	WebElement addPerson2;

	@FindBy(how = How.NAME, using = "name")
	WebElement name2;

	By id = By.id("email0");

	By saveBtn = By.xpath("//button[@class='btn btn-primary btn-large ng-binding']");

	By personAddedMsg = By.xpath("//span[text()='Person added.']");

	// By contactsPageHeader =
	// By.xpath("(//h2[@class='ng-binding'])");(//*[@id="personForm"]/div[1]/h2/text())[2]

	By contactsPageHeader = By.xpath("(//h2[@class='ng-binding'])[1]");

	public ContactsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		util = new ElementUtil(driver);
	}

	public String getContactsPageTitle() {
		return util.doGetTitle();
	}

	public String getContactsPageHeader() {
		return util.doGetText(contactsPageHeader);
	}

	public String addPerson(String Name, String Email) {

		util.waitForPresenceOfElementLocated(addPerson);
		util.doActionClick(addPerson);
		util.waitForPresenceOfElementLocated(name);
		util.doSendKeys(name, Name);
		util.doSendKeys(email, Email);

		util.waitForPresenceOfElementLocated(saveBtn);
		util.doClick(saveBtn);

		util.waitForPresenceOfElementLocated(personAddedMsg);

		return util.doGetText(personAddedMsg);

	}

	public String addPerson(Contacts contacts) {
		util.waitForPresenceOfElementLocated(addPerson);
		util.doClick(addPerson);
		util.waitForPresenceOfElementLocated(name);
		util.doSendKeys(name, contacts.getName());
		util.doSendKeys(email, contacts.getEmail());
		util.doClick(saveBtn);

		util.waitForPresenceOfElementLocated(personAddedMsg);

		return util.doGetText(personAddedMsg);
	}

}
