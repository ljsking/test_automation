package com.nhncorp.naver.qa4team;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.openqa.selenium.server.SeleniumServer;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;
import static org.testng.Assert.*;

public class TakeScreencapture {
	protected Selenium selenium;
	protected SeleniumServer sserver;
	final private String target = "C:\\snapsie_test.png";
	final private String target4JS = "C:\\\\snapsie_test.png";
	@BeforeClass
	public void before() throws Exception{
		sserver = new SeleniumServer();
		sserver.start();
		selenium = new DefaultSelenium("localhost", 4444, "*iexplore", "http://www.naver.com");
		selenium.start();
		FileUtils.deleteQuietly(new File(target));
	}
	
	private String getJS(){
		InputStream in = PngGenerator.class.getClassLoader().getResourceAsStream("screencapture.js");
		StringWriter writer = new StringWriter();
		try {
			IOUtils.copy(in, writer);
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
		return writer.toString();
	}
	@Test
	public void runScriptTest() throws InterruptedException{
		selenium.open("/");
		selenium.type("query", "안녕");
		selenium.click("//input[@alt='검색']");
		selenium.runScript(getJS());
		selenium.runScript("hideSectionExclude('nimage section');");
		selenium.runScript("save('"+target4JS+"');");
		assertTrue(new File(target).isFile());
	}
	
	@AfterClass
	public void after(){
		FileUtils.deleteQuietly(new File(target));
	}
}
