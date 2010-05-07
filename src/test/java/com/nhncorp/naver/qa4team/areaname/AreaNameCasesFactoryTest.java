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
		//logger.debug(testCases);
		assertEquals(testCases.size(), 4);
		int tcNum = 1;
		for(AreaTestCase tc:testCases){
			assertEquals(tcNum, tc.getTcNumber());;
			assertFalse(tc.getXpath().equals(""));
			assertFalse(tc.getExpectedValue().equals(""));
			++tcNum;
		}
	/*	assertEquals(Arrays.asList(new String[]{"꽃배달"}), testCases.get(20).getKeywords());
		assertEquals(Arrays.asList(new String[]{"꽃배달"}), testCases.get(21).getKeywords());
		assertEquals(Arrays.asList(new String[]{"꽃배달"}), testCases.get(22).getKeywords());
		assertEquals(Arrays.asList(new String[]{"꽃배달"}), testCases.get(23).getKeywords());
		assertEquals("광고", testCases.get(20).getSection());
		assertEquals("광고", testCases.get(21).getSection());
		assertEquals("광고", testCases.get(22).getSection());
		assertEquals("광고", testCases.get(23).getSection());
		assertEquals(Arrays.asList(new String[]{"티뷰론","SM5"}), testCases.get(28).getKeywords());*/
	}
}
