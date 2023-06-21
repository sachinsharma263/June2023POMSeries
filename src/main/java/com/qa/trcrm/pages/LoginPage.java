package com.qa.trcrm.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.qa.trcrm.base.BasePage3;
import com.qa.trcrm.utils.AppConstants;
import com.qa.trcrm.utils.ElementUtil;
import com.qa.trcrm.utils.JavaScriptUtil;

public class LoginPage extends BasePage3 {

	WebDriver driver;
	ElementUtil util;
	JavaScriptUtil jsUtil;

	By email = By.id("_username");
	By password = By.id("_password");
	By loginBtn = By.xpath("//input[@type='submit']");

	By signUpNowLink = By.linkText("Sign Up Now2");

	By error = By.id("error");

	/*
	 * Page factory- is a library class used to identify page objects, supported by
	 * selenium,support POM
	 */
	// 1 approach
	@FindBy(id = "_username")
	WebElement email2;
	@FindBy(id = "_password")
	WebElement password2;
	
  // 2 approach
	@FindBy(how = How.ID, using = "_username")
	WebElement email3;
	
	@FindBy(tagName="a") List<WebElement> list;
	@FindBy(how=How.TAG_NAME,using="a") List<WebElement> list2;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		util = new ElementUtil(driver);
		jsUtil = new JavaScriptUtil(driver);

		PageFactory.initElements(driver, this);
	}

	public String getLoginPageTitle() {
		return util.waitForPresenceOfTitle(AppConstants.LOGIN_PAGE_TITLE);
	}

	public boolean verifySignUpLink() {
		return util.doIsDisplayed(signUpNowLink);
	}

	public HomePage doLogin(String emailId, String pwd) {
		util.doClear(email);
		util.doSendKeys(email, emailId);
		util.doClear(password);
		util.doSendKeys(password, pwd);
		util.doClick(loginBtn);
		

		return new HomePage(driver);
	}

	public boolean errorMsg() {

		return util.doIsDisplayed(error);
	}
}
