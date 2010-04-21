package com.nhncorp.naver.qa4team;

import java.io.UnsupportedEncodingException;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class EncodingTest {
	
	@Test
	public void encodeTest() throws UnsupportedEncodingException{
		String source = "안녕";
		String dest = "%BE%C8%B3%E7";
		assertEquals(dest, TestHTMLPageGenerator.convertURL(source));
	}
}
