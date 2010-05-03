package com.nhncorp.naver.qa4team;

import java.io.File;

import com.nhncorp.naver.qa4team.Main.Browser;
import com.thoughtworks.selenium.Selenium;

public class PngGenerator{
	private static final String orginalPath = "C:\\snapsie_test.png";
	static public String generate(Selenium selenium, String keyword, String collectionName, String target){
		selenium.open("http://naver.com");
		selenium.type("query", keyword);
		selenium.click("//input[@alt='검색']");
		selenium.runScript(Main.getHideDivJS());
		selenium.runScript("hideSectionExclude('"+collectionName+"');");
		if(Main.getBrowser().equals(Browser.IE)){
			selenium.runScript(Main.getCaptureForIE());
			selenium.runScript("capture();");
			File f = new File(orginalPath);
			f.renameTo(new File(target));
		}else{
			selenium.captureEntirePageScreenshot(target, null);
		}
		return target;
	}
}
