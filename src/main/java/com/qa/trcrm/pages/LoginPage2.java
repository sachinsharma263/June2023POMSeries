package com.qa.trcrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.trcrm.base.BasePage3;

public class LoginPage2 extends BasePage3 {

	WebDriver driver;
	By email = By.id("_username");
	By password = By.id("_password");
	By loginBtn = By.xpath("//input[@type='submit']");

	By signUpNowLink = By.linkText("Sign Up Now");

	public LoginPage2(WebDriver driver) {
		this.driver = driver;
	}

	public String getLoginPageTitle() {
		return driver.getTitle();
	}

	public boolean verifySignUpLink() {
		return driver.findElement(signUpNowLink).isDisplayed();
	}

	public HomePage doLogin(String emailId, String pwd) {
		driver.findElement(email).sendKeys(emailId);
		driver.findElement(password).sendKeys(pwd);
		driver.findElement(loginBtn).click();
		
		return new HomePage(driver);
	}
}
