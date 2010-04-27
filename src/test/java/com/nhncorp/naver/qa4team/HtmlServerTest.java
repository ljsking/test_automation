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
	
	final String nanum_target = "C:\\snapsie_test_nanum.png";
	final String system_target = "C:\\snapsie_test_system.png";
	final String keyword = "프라하의 연인";
	WebDriver driver = new InternetExplorerDriver();
	
	@BeforeClass
	public void beforeClass() {
		FileUtils.deleteQuietly(new File(nanum_target));
		FileUtils.deleteQuietly(new File(system_target));
	}

	@AfterClass
	public void afterClass() {
		//FileUtils.deleteQuietly(new File(target));
	}
	@Test(groups = {"longTest"})
	public void screencaptureSystemFontTest() throws Exception{
		NanumSwitch.offNanum(driver);
		assertFalse((new File(system_target)).isFile());
		new PngGenerator().pngGenerate(driver, keyword, "content_search section", system_target);
		assertTrue((new File(system_target)).isFile());
	}
	@Test(groups = {"longTest"})
	public void screencaptureNanumFontTest() throws Exception{
		NanumSwitch.onNanum(driver);
		assertFalse((new File(nanum_target)).isFile());
		new PngGenerator().pngGenerate(driver, keyword, "content_search section", nanum_target);
		assertTrue((new File(nanum_target)).isFile());
	}
}
