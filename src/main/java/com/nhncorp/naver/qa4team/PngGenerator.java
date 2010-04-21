package com.nhncorp.naver.qa4team;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.server.SeleniumServer;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;

public class PngGenerator{
	final String orginalPath = "C:\\snapsie_test.png";
	public String pngGenerate(String htmlName, String collectionName, String target){
		WebServer webserver = new WebServer();
		webserver.start();
		Selenium selenium;
		SeleniumServer sserver;
		try {
			sserver = new SeleniumServer();
			sserver.start();
		} catch (Exception e) {
			webserver.stop();
			throw new IllegalStateException(e);
		}
		selenium = new DefaultSelenium("localhost", 4444, "*iexplore", "http://test.search.naver.com:"+webserver.getPort());
		selenium.start();
		selenium.open("/"+htmlName);
		String js;
		try {
			js = FileUtils.readFileToString(new File("src/main/resources/screencapture.js"));
		} catch (IOException e) {
			sserver.stop();
			webserver.stop();
			throw new IllegalStateException(e);
		}
		executeJS(selenium, js);
		js ="hideSectionExclude('"+collectionName+"')";
		executeJS(selenium, js);
		executeJS(selenium, "save();");
		sserver.stop();
		webserver.stop();
		File f = new File(orginalPath);
		f.renameTo(new File(target));
		return target;
	}
	private String executeJS(Selenium selenium, String js){
		return selenium.getEval("with (selenium.browserbot.getCurrentWindow()) {"+js+"}");
	}
}
