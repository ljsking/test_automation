package com.nhncorp.naver.qa4team;

import static org.testng.Assert.*;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class HtmlServerTest{
	
	final String target = "C:\\snapsie_test_.png";
	final String url = "http://search.naver.com/search.naver?sm=tab_hty&where=nexearch&query=%C7%C1%B6%F3%C7%CF%C0%C7+%BF%AC%C0%CE&x=0&y=0";
	WebDriver dirver = new InternetExplorerDriver();
	
	@BeforeClass
	public void beforeClass() {
		FileUtils.deleteQuietly(new File(target));
	}

	@AfterClass
	public void afterClass() {
		FileUtils.deleteQuietly(new File(target));
	}
	@Test
	public void servingTest() throws Exception{
		assertFalse((new File(target)).isFile());
		new PngGenerator().pngGenerate(dirver, url, "content_search section", target);
		assertTrue((new File(target)).isFile());
	}
}
