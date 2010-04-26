package com.nhncorp.naver.qa4team;

import static org.testng.Assert.*;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class HtmlServerTest extends IESeleniumTestCase {
	
	final String target = "C:\\snapsie_test_.png";
	final String url = "http://search.naver.com/search.naver?sm=tab_hty&where=nexearch&query=%C7%C1%B6%F3%C7%CF%C0%C7+%BF%AC%C0%CE";
	
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
		new PngGenerator().pngGenerate(selenium, url, "music section", target);
		assertTrue((new File(target)).isFile());
	}
}
