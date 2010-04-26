package com.nhncorp.naver.qa4team;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.server.SeleniumServer;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

import com.thoughtworks.selenium.DefaultSelenium;


public class JavaScriptExecutorTest {
	@Test
	public void excuteTest() throws Exception{
		SeleniumServer sserver = new SeleniumServer();
		sserver.start();
		DefaultSelenium selenium = new DefaultSelenium("localhost", 4444, "*iexplore", "http://search.naver.com/");
		selenium.start();
		selenium.open("search.naver?where=nexearch&query=%BE%C8%B3%E7");
		selenium.getEval("");
		String title = "안녕 :: 네이버 통합검색";
		String childSpanTextSnippet =
	         " {" +
	            "selenium.browserbot.getCurrentWindow().document.title " +
	            "}";
		assertEquals(title, selenium.getEval(childSpanTextSnippet));
		String js = FileUtils.readFileToString(new File("src/main/resources/screencapture.js"));
		selenium.getEval(js);
		js ="selenium.browserbot.getCurrentWindow().hideSectionExclude('music section')";
		selenium.getEval(js);
		Thread.sleep(1000);
	}
}
