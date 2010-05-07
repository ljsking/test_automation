package com.nhncorp.naver.qa4team.areaname;

import static org.testng.Assert.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.testng.annotations.Test;

public class AreaNameCasesFactoryTest {
	@Test
	public void testGetTestCases() throws IOException{
		InputStream myxls = new FileInputStream("src/main/resources/areaNameTestCase.xlsx");
		AreaTestCasesFactory factory = new AreaTestCasesFactory();
		List<AreaTestCase> testCases = factory.getTestCases(myxls);
		assertEquals(testCases.size(), 4);
		int tcNum = 1;
		for(AreaTestCase tc:testCases){
			assertEquals(tcNum, tc.getTcNumber());;
			assertFalse(tc.getXpath().equals(""));
			assertFalse(tc.getExpectedValue().equals(""));
			++tcNum;
		}
		assertEquals(testCases.get(0).getXpath(), "//form[@id='blog_option_sort_form']//button[span='정확도']");
		assertEquals(testCases.get(1).getExpectedValue(), "opt.sortdate");
	}
}
