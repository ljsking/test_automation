package com.nhncorp.naver.qa4team;

import com.thoughtworks.selenium.Selenium;

public class NanumSwitch {
	static private final String url = "http://search.naver.com/search.naver?where=nexearch&query=%BE%C8%B3%E7";
	
	static public void onNanum(Selenium selenium){
		selenium.open(url);
		String js = "nanum.Core.on(nanum.Service.showNanumGuide);";
		selenium.runScript(js);
	}
	static public void offNanum(Selenium selenium){
		selenium.open(url);
		String js = "nanum.Core.off();";
		selenium.runScript(js);
	}
}
