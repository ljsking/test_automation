package com.nhncorp.naver.qa4team;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.testng.annotations.Test;

import com.nhncorp.naver.qa4team.regression_test.RegressionTest;

public class ConvertPathForJSTest {
	@Test
	public void convertTest(){
		String source = "c:\\a\\b\\c";
		source = source.replace("\\", "\\\\");
		org.testng.Assert.assertEquals("c:\\\\a\\\\b\\\\c", source);
	}
	@Test
	public void mkdirTest(){
		File path = new File(RegressionTest.getTargetFolder());
		org.testng.Assert.assertTrue(path.isDirectory());
		FileUtils.deleteQuietly(path);
	}
}
