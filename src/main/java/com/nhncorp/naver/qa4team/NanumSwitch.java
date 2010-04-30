package com.nhncorp.naver.qa4team;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.TimedOutException;

public class NanumSwitch {
	static private void init(WebDriver driver){
		try{
			driver.get("http://search.naver.com/search.naver?where=nexearch&query=%BE%C8%B3%E7");
		}catch(TimedOutException e){
			//pass
		}
	}
	static private void execute(WebDriver driver, String js){
		try{
			((JavascriptExecutor)driver).executeScript(js);
		}catch(TimedOutException e){
			//pass
		}
	}
	static public void onNanum(WebDriver driver){
		init(driver);
		String js = "nanum.Core.on(nanum.Service.showNanumGuide);";
		execute(driver, js);
	}
	static public void offNanum(WebDriver driver){
		init(driver);
		String js = "nanum.Core.off();";
		execute(driver, js);
	}
}
