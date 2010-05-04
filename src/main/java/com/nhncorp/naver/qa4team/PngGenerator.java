package com.nhncorp.naver.qa4team;

import com.nhncorp.naver.qa4team.Main.Browser;
import com.thoughtworks.selenium.Selenium;

public class PngGenerator{
	static public String generate(Selenium selenium, String keyword, String collectionName, String target){
		selenium.open(Main.getTestURL());
		selenium.type("query", keyword);
		selenium.click("//input[@alt='검색']");
		selenium.runScript(Main.getHideDivJS());
		selenium.runScript("hideSectionExclude('"+collectionName+"');");
		if(Main.getBrowser().equals(Browser.IE)){
			selenium.runScript(Main.getCaptureForIE());
			selenium.runScript("save('"+target.replace("\\","\\\\")+"');");
		}else{
			selenium.captureEntirePageScreenshot(target, null);
		}
		return target;
	}
}
