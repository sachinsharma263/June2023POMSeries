package com.qa.trcrm.utils;

/**
 * This post will explain the integration of any java logger (let’s say Log4j) with ITestListener to customise result output and print the same to log file.
 * https://qavalidation.com/2018/08/combine-testnglistener-with-log4j-for-selenium-result-reporting.html/ 
 */
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log {

	private static Logger logger = LogManager.getLogger(Log.class.getName());

	public static void info(String msg) {

		logger.info(msg);
	}

	public static void debug(String msg) {
		logger.debug(msg);
	}

	public static void warn(String msg) {
		logger.warn(msg);
	}

	public static void error(String msg) {
		logger.error(msg);
	}

	public static void fatal(String msg) {
		logger.fatal(msg);
	}

}
