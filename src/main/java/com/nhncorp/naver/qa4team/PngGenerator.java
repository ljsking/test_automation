package com.nhncorp.naver.qa4team;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import com.thoughtworks.selenium.Selenium;

public class PngGenerator{
	final String orginalPath = "C:\\snapsie_test.png";
	public String pngGenerate(Selenium selenium, String htmlName, String collectionName, String target){
		selenium.open(htmlName);
		String js;
		try {
			js = FileUtils.readFileToString(new File("src/main/resources/screencapture.js"));
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
		executeJS(selenium, js);
		js ="hideSectionExclude('"+collectionName+"')";
		executeJS(selenium, js);
		executeJS(selenium, "save();");
		File f = new File(orginalPath);
		f.renameTo(new File(target));
		return target;
	}
	private String executeJS(Selenium selenium, String js){
		return selenium.getEval("with (selenium.browserbot.getCurrentWindow()) {"+js+"}");
	}
}
