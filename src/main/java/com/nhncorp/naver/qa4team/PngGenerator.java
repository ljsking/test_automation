package com.nhncorp.naver.qa4team;

import java.io.File;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.TimedOutException;

import com.nhncorp.naver.qa4team.Main.Browser;

public class PngGenerator{
	private static final String orginalPath = "C:\\snapsie_test.png";
	static public String generate(WebDriver driver, String keyword, String collectionName, String target){
		driver.get("http://naver.com");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		driver.findElement(By.cssSelector("span.green_window>input#query")).sendKeys(keyword);
		try{
			driver.findElement(By.xpath("//input[@alt='검색']")).click();
		}catch(TimedOutException e){
			//pass
		}
		
		((JavascriptExecutor)driver).executeScript(Main.getHideDivJS());
		((JavascriptExecutor)driver).executeScript("hideSectionExclude('"+collectionName+"');");
		
		if(Main.getBrowser().equals(Browser.IE)){
			((JavascriptExecutor)driver).executeScript(Main.getCaptureForIE());
			((JavascriptExecutor)driver).executeScript("capture();");
			File f = new File(orginalPath);
			f.renameTo(new File(target));
		}else{
			File screenshotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			screenshotFile.renameTo(new File(target));
		}
		return target;
	}
}
