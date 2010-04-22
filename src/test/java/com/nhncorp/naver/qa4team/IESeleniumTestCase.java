package com.nhncorp.naver.qa4team;

import org.openqa.selenium.server.SeleniumServer;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;


public class IESeleniumTestCase {
	protected Selenium selenium;
	protected SeleniumServer sserver;
	protected WebServer webserver;
	@BeforeClass
	public void before() throws Exception{
		webserver = new WebServer();
		webserver.start();
		sserver = new SeleniumServer();
		sserver.start();
		selenium = new DefaultSelenium("localhost", 4444, "*iexplore", "http://test.search.naver.com:"+webserver.getPort());
		selenium.start();
	}
	
	@AfterClass
	public void after(){
	}
}
