package com.nhncorp.naver.qa4team;

import static org.testng.Assert.*;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class HtmlServerTest {
	
	final String target = "C:\\snapsie_test_.png";
	
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
		new PngGenerator().pngGenerate("test.html", "music section", target);
		assertTrue((new File(target)).isFile());
	}
}
