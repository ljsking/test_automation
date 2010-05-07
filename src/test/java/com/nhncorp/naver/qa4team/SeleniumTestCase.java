package com.nhncorp.naver.qa4team;

import org.openqa.selenium.server.SeleniumServer;
import org.testng.annotations.BeforeClass;

import com.nhncorp.naver.qa4team.regression_test.RegressionTest;
import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;

public class SeleniumTestCase {
	protected Selenium selenium;
	private SeleniumServer server;
	@BeforeClass
	public void beforeClassSel() throws Exception{
		HtmlReporter.setPath("report.html");
		server = new SeleniumServer();
		server.start();
		selenium = new DefaultSelenium("localhost", 4444, RegressionTest.getBrowserString(), "http://www.naver.com");
		selenium.start();
	}
}
