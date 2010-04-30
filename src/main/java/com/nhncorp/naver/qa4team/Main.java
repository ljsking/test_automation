package com.nhncorp.naver.qa4team;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class Main {
	enum Browser{IE, FF}
	enum Font{System, Nanum}
	static private Browser browser = Browser.IE;
	private Font font = Font.System;
	private File excel = null;
	private List<TestCase> testcases;
	static private String hideDivJS;
	static private String captureForIE;
	public static String getHideDivJS(){
		return hideDivJS;
	}
	public static String getCaptureForIE(){
		return captureForIE;
	}
	private void readJSFiles() throws IOException{
		InputStream in = PngGenerator.class.getClassLoader().getResourceAsStream("hideDiv.js");
		StringWriter writer = new StringWriter();
		IOUtils.copy(in, writer);
		hideDivJS = writer.toString();
		in.close();
		writer.close();
		in = PngGenerator.class.getClassLoader().getResourceAsStream("captureForIE.js");
		writer = new StringWriter();
		captureForIE = writer.toString();
		in.close();
		writer.close();
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
		readJSFiles();
		testcases = null;
		try {
			testcases = new TestCasesFactory().getTestCases(new FileInputStream(excel));
		} catch (FileNotFoundException e) {
			throw new IllegalStateException(e);
		}
	}
	
	public void run() {
		WebDriver driver = browser.equals(Browser.IE)?new InternetExplorerDriver():new FirefoxDriver();
		if(font.equals(Font.System))
			NanumSwitch.offNanum(driver);
		else NanumSwitch.onNanum(driver);
		for(TestCase tc:testcases){
			new TestCaseRunner().run(tc, driver);
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
