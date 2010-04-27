package com.nhncorp.naver.qa4team;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.TimedOutException;

public class PngGenerator{
	final String orginalPath = "C:\\snapsie_test.png";
	public String pngGenerate(WebDriver driver, String keyword, String collectionName, String target){
		driver.get("http://naver.com");
		driver.findElement(By.id("query")).sendKeys(keyword);
		try{
			driver.findElement(By.xpath("//input[@alt='검색']")).click();
		}catch(TimedOutException e){
			//pass
		}
		String js;
		try {
			js = FileUtils.readFileToString(new File("src/main/resources/screencapture.js"));
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
		((JavascriptExecutor)driver).executeScript(js);
		js = "hideSectionExclude('"+collectionName+"');save();";
		((JavascriptExecutor)driver).executeScript(js);
		File f = new File(orginalPath);
		f.renameTo(new File(target));
		return target;
	}
}
