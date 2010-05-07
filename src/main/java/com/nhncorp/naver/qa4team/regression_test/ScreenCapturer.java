package com.nhncorp.naver.qa4team.regression_test;

import com.nhncorp.naver.qa4team.regression_test.RegressionTest.Browser;
import com.thoughtworks.selenium.Selenium;

public class ScreenCapturer{
	static public String generate(Selenium selenium, String keyword, String collectionName, String target){
		selenium.open(RegressionTest.getTestURL());
		selenium.type("query", keyword);
		selenium.click("//input[@alt='검색']");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if(!selenium.isElementPresent("//div[@id='content']/div[@class='"+collectionName+"']"))
			throw new IllegalStateException("해당 영역이 존재하지 않습니다.");
		selenium.runScript(RegressionTest.getHideDivJS());
		selenium.runScript("hideSectionExclude('"+collectionName+"');");
		if(RegressionTest.getBrowser().equals(Browser.IE)){
			selenium.runScript(RegressionTest.getCaptureForIE());
			selenium.runScript("save('"+target.replace("\\","\\\\")+"');");
		}else{
			selenium.captureEntirePageScreenshot(target, "");
		}
		return target;
	}
}
