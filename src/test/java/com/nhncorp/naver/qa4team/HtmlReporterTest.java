package com.nhncorp.naver.qa4team;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class HtmlReporterTest {
	String path = "test.html";
	@BeforeClass
	public void beforeClass() {
		FileUtils.deleteQuietly(new File(path));
	}

	@AfterClass
	public void afterClass() {
		FileUtils.deleteQuietly(new File(path));
	}
	@Test
	public void passTest(){
		HtmlReporter.setPath(path);
		HtmlReporter.getInstance().pass("안녕", "요기", "there.png");
		assertTrue(new File(path).exists());
	}
}
