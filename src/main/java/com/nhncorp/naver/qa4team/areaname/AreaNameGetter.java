package com.nhncorp.naver.qa4team.areaname;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.nhncorp.naver.qa4team.Main;
import com.thoughtworks.selenium.Selenium;

public class AreaNameGetter{
	private Selenium selenium;
	public AreaNameGetter(Selenium selenium){
		this.selenium = selenium;
	}
	private String getURL(){
		return "http://search.naver.com/search.naver?where=nexearch&sm=tab_jum&ie=utf8&query=%EC%95%88%EB%85%95";
	}
	private String getRootURL(){
		return "http://search.naver.com";
	}
	private String getAreaNamesFromCookie(){
		if(!selenium.getLocation().matches("^"+getRootURL()+".+")){
			selenium.open(getURL());
		}
		long end = System.currentTimeMillis() + 5000;
        while (System.currentTimeMillis() < end) {
        	String result = selenium.getEval("selenium.browserbot.getCurrentWindow().$Cookie().get('area_name')");
        	Pattern pattern = Pattern.compile("%\\d+E");
        	Matcher matcher = pattern.matcher(result);
            return matcher.replaceAll(">");
        }
        throw new RuntimeException("Cannot getAreaNamesFromCookie for 5 seconds");
	}
	public String getAreaName(Selenium selenium, String target){
		selenium.open(getURL());
		selenium.runScript(Main.getStringFromFile("areaName.js"));
		selenium.runScript("initializeCookie();");
		selenium.runScript("overrideGoCR();overrideTCR();");
		selenium.click(target);
		return getAreaNamesFromCookie();
	}
}
