package com.nhncorp.naver.qa4team.areaname;

import org.openqa.selenium.server.SeleniumServer;
import org.testng.annotations.BeforeClass;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;

import com.nhncorp.naver.qa4team.HtmlReporter;
import com.nhncorp.naver.qa4team.Main;

public class SeleniumTestCase {
	protected Selenium selenium;
	private SeleniumServer server;
	@BeforeClass
	public void beforeClassSel() throws Exception{
		HtmlReporter.setPath("report.html");
		server = new SeleniumServer();
		server.start();
		selenium = new DefaultSelenium("localhost", 4444, Main.getBrowserString(), "http://www.naver.com");
		selenium.start();
	}
}
