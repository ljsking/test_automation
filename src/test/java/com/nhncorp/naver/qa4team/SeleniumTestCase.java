package com.nhncorp.naver.qa4team;

import org.openqa.selenium.server.SeleniumServer;
import org.testng.annotations.BeforeClass;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;

public class SeleniumTestCase {
	protected Selenium selenium;
	private SeleniumServer server;
	@BeforeClass
	final public void beforeClassSel() throws Exception{
		server = new SeleniumServer();
		server.start();
		selenium = new DefaultSelenium("localhost", 4444, "*iexplore", "http://www.naver.com");
		selenium.start();
	}
}
