package com.nhncorp.naver.qa4team;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import com.thoughtworks.selenium.Selenium;

public class PngGenerator{
	final String orginalPath = "C:\\snapsie_test.png";
	public String pngGenerate(WebDriver driver, String url, String collectionName, String target){
		driver.get(url);
		/*String js;
		try {
			js = FileUtils.readFileToString(new File("src/main/resources/screencapture.js"));
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
		executeJS(driver, js);
		js ="hideSectionExclude('"+collectionName+"')";
		executeJS(driver, js);
		executeJS(driver, "save();");
		File f = new File(orginalPath);
		f.renameTo(new File(target));*/
		return target;
	}
	private void executeJS(WebDriver driver, String js){
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript(js);
	}
}
