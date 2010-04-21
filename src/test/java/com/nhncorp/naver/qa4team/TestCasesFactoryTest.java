package com.nhncorp.naver.qa4team;

import static org.testng.Assert.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import org.testng.annotations.Test;

public class TestCasesFactoryTest {
	@Test
	public void testGetTestCases() throws IOException{
		InputStream myxls = new FileInputStream("src/main/resources/TestCase.xlsx");
		TestCasesFactory factory = new TestCasesFactory();
		List<TestCase> testCases = factory.getTestCases(myxls);
		//logger.debug(testCases);
		assertEquals(88, testCases.size());
		int tcNum = 1;
		for(TestCase tc:testCases){
			assertEquals(tcNum, tc.getTcNumber());
			assertTrue(tc.getKeywords().size()>0);
			assertFalse(tc.getSection().equals(""));
			assertFalse(tc.getClassName().equals(""));
			assertFalse(tc.getHeadTitle().equals(""));
			++tcNum;
		}
		assertEquals(Arrays.asList(new String[]{"꽃배달"}), testCases.get(20).getKeywords());
		assertEquals(Arrays.asList(new String[]{"꽃배달"}), testCases.get(21).getKeywords());
		assertEquals(Arrays.asList(new String[]{"꽃배달"}), testCases.get(22).getKeywords());
		assertEquals(Arrays.asList(new String[]{"꽃배달"}), testCases.get(23).getKeywords());
		assertEquals("광고", testCases.get(20).getSection());
		assertEquals("광고", testCases.get(21).getSection());
		assertEquals("광고", testCases.get(22).getSection());
		assertEquals("광고", testCases.get(23).getSection());
		assertEquals(Arrays.asList(new String[]{"티뷰론","SM5"}), testCases.get(28).getKeywords());
	}
}
