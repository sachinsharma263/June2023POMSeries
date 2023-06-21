package com.qa.trcrm.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementUtil {

	WebDriver driver;
	WebDriverWait wait;
	JavaScriptUtil jsUtil;
	Actions action;

	public ElementUtil(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, AppConstants.DEFAULT_TIME_OUT);
		jsUtil = new JavaScriptUtil(driver);
		action = new Actions(driver);
	}

	/**
	 * This method is used to create/identify/find the web element on the basis of
	 * By locator
	 * 
	 * @param locator
	 * @return web element
	 */
	public WebElement getElement(By locator) {
		WebElement element = null;
		try {
			element = driver.findElement(locator);
			//jsUtil.flash(element);
			return element;
		} catch (Exception e) {
			System.out.println("some exception occured while creating the web element: " + locator);
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return element;
	}

	/**
	 * This method is used to pass the value
	 * 
	 * @param locator
	 * @param value
	 */
	public void doSendKeys(By locator, String value) {
		getElement(locator).sendKeys(value);
	}

	/**
	 * This method is used to click one the web element
	 * 
	 * @param locator
	 */
	public void doClick(By locator) {
		getElement(locator).click();
	}

	/**
	 * This method is used to get the text
	 * 
	 * @param locator
	 * @return
	 */
	public String doGetText(By locator) {
		return getElement(locator).getText();
	}

	/**
	 * This method is used to check whether element is displayed or not
	 * 
	 * @param locator
	 * @return
	 */
	public boolean doIsDisplayed(By locator) {
		return getElement(locator).isDisplayed();
	}

	public void waitForPresenceOfElementLocated(By locator) {
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	public WebElement waitForvisibilityOfElement(By locator) {
		wait.until(ExpectedConditions.visibilityOf(getElement(locator)));

		return getElement(locator);
	}

	public String waitForPresenceOfTitle(String title) {
		wait.until(ExpectedConditions.titleIs(title));

		return doGetTitle();
	}

	public String doGetTitle() {
		return driver.getTitle();
	}

	public void doActionSendKeys(By locator, String value) {
		action.sendKeys(getElement(locator), value).build().perform();
	}

	public void doActionClick(By locator) {
		action.click(getElement(locator)).build().perform();
	}

	public void doMoveToElement(By locator) {
		action.moveToElement(getElement(locator)).build().perform();
	}

	public void doClear(By locator) {
		getElement(locator).clear();
	}
}
