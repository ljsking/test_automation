package com.nhncorp.naver.qa4team.areaname;

import com.thoughtworks.selenium.Selenium;

public class AreaNameTestCaseRunner {
	public void run(AreaTestCase tc, Selenium selenium){
		selenium.click("xpath=" + tc.getXpath());
		
	}
}
