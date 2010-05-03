package com.nhncorp.naver.qa4team;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.List;

import org.apache.commons.io.IOUtils;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;

public class Main {
	enum Browser{IE, FF}
	enum Font{System, Nanum}
	static private Browser browser = Browser.IE;
	private Font font = Font.System;
	private File excel = null;
	private List<TestCase> testcases;
	static private String hideDivJS;
	static private String captureForIE;
	private static String getStringFromFile(String file){
		InputStream in = PngGenerator.class.getClassLoader().getResourceAsStream(file);
		StringWriter writer = new StringWriter();
		try {
			if(in == null){
				in = new FileInputStream(new File("src/main/resources/"+file));
			}
			IOUtils.copy(in, writer);
			in.close();
			writer.close();
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
		return writer.toString();
	}
	public static String getHideDivJS(){
		if(hideDivJS == null){
			hideDivJS = getStringFromFile("hideDiv.js");
		}
		System.out.println(hideDivJS);
		return hideDivJS;
	}
	public static String getCaptureForIE(){
		if(captureForIE == null){
			captureForIE = getStringFromFile("captureForIE.js");
		}
		return captureForIE;
	}
	public Main(String[] args) throws IOException{
		if(args.length>0){
			excel = new File(args[0]);
		}else{
			printUsage("xxxx.jar");
		}
		if(args.length>1){
			String browserArg = args[1];
			try{
				browser = Browser.valueOf(browserArg);
			} catch(IllegalArgumentException e) {
				printUsage("xxxx.jar");
			}
		}
		if(args.length>2){
			String fontArg = args[2];
			try{
				font = Font.valueOf(fontArg);
			} catch(IllegalArgumentException e) {
				printUsage("xxxx.jar");
			}
		}
		testcases = null;
		try {
			testcases = new TestCasesFactory().getTestCases(new FileInputStream(excel));
		} catch (FileNotFoundException e) {
			throw new IllegalStateException(e);
		}
	}
	
	public void run() {
		String browserStr = browser.equals(Browser.IE)?"*iexplore":"*firefox";
		Selenium selenium = new DefaultSelenium("localhost", 4444, browserStr, "http://www.naver.com");
		if(font.equals(Font.System))
			NanumSwitch.offNanum(selenium);
		else NanumSwitch.onNanum(selenium);
		for(TestCase tc:testcases){
			new TestCaseRunner().run(tc, selenium);
		}
	}
	
	private void printUsage(String jarFileName){
		System.out.println(String.format("java -jar %s tc.xslx [browsertype [font]]", jarFileName));
		System.out.println(String.format("\t default: IE System"));
		System.out.println(String.format("\t browsertype: IE, FF"));
		System.out.println(String.format("\t fonttype: System, Nanum"));
		System.exit(-1);
	}
	
	public static void main(String[] args) throws IOException {
		new Main(args).run();
	}
	public static Browser getBrowser() {
		return browser;
	}
	
}
