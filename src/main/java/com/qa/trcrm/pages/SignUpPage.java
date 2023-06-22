package com.qa.trcrm.pages;

import org.openqa.selenium.WebDriver;

public class SignUpPage {

	WebDriver driver;

	public SignUpPage(WebDriver driver) {
		this.driver = driver;
	}

	public void m2() {
		System.out.println("m2--remote");
	}

	public void m1() {
		System.out.println("m1--local");

	}
}
