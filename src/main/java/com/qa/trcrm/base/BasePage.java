package com.qa.trcrm.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {

	public WebDriver driver;
	public Properties prop;
	OptionsManager option;

	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}

	public WebDriver init_driver(Properties prop, String browserName) {
		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		} else {
			System.out.println(browserName + " not found");
		}
		driver.get(prop.getProperty("url"));
		driver.manage().window().maximize();
		return driver;
	}

	/**
	 * This method is used to intialized the web driver on the basis of browser name
	 * 
	 * @param prop
	 * @return web driver
	 */
	public WebDriver init_driver(Properties prop) {
		String browserName = prop.getProperty("browser");

		option = new OptionsManager(prop);

		switch (browserName) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			tlDriver.set(new ChromeDriver(option.getChromeOptions()));
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			tlDriver.set(new FirefoxDriver(option.getFirefoxOptions()));
			break;
		case "edge":
			WebDriverManager.edgedriver().setup();
			tlDriver.set(new EdgeDriver());
			break;
		case "safari":
			WebDriverManager.getInstance(SafariDriver.class).setup();
			tlDriver.set(new SafariDriver());
			break;

		default:
			System.out.println(browserName + " not found");
			try {
				throw new Exception("NoSuchBrowserFound");
			} catch (Exception e) {
				e.printStackTrace();
				e.getMessage();
			}

		}
		getDriver().get(prop.getProperty("url"));
		getDriver().manage().window().maximize();

		return getDriver();
	}

	/**
	 * This method is use to get the property file
	 * 
	 * @return
	 */
	public Properties init_prop() {

		String env = System.getProperty("env");
		if(env==null) {
			env="qa";
		}
		String path;
		prop = new Properties();
		try {
			if (env.equalsIgnoreCase("qa")) {
				path = "./src/main/java/com/qa/trcrm/config/config_qa.properties";
			} else if (env.equalsIgnoreCase("prod")) {
				path = "./src/main/java/com/qa/trcrm/config/config_prod.properties";
			} else {
				path = "./src/main/java/com/qa/trcrm/config/config.properties";
			}
			FileInputStream fis = new FileInputStream(path);
			prop.load(fis);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop;
	}

	/**
	 * This method is used to take screenshot and return path
	 * 
	 * @return path
	 */
	public String getScreenshot() {

		File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png";

		File destPath = new File(path);

		try {
			FileUtils.copyFile(src, destPath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return path;
	}
}
