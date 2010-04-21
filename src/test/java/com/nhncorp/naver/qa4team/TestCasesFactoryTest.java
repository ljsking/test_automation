package com.nhncorp.naver.qa4team;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class TestCasesFactoryTest {
	@Test
	public void testGetTestCases() throws IOException{
		InputStream myxls = new FileInputStream("src/main/resources/TestCase.xlsx");
		TestCasesFactory factory = new TestCasesFactory();
		List<TestCase> testCases = factory.getTestCases(myxls);
		//logger.debug(testCases);
		assertEquals(87, testCases.size());
		for(TestCase tc:testCases){
			assertTrue(tc.getKeywords().size()>0);
			assertFalse(tc.getSection().equals(""));
			//assertFalse(tc.getSubCategory().equals(""));
			//assertFalse(tc.getClassName().equals(""));
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
