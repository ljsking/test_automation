package com.nhncorp.naver.qa4team;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.BindException;
import java.net.ServerSocket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.openqa.selenium.server.RemoteControlConfiguration;
import org.openqa.selenium.server.SeleniumServer;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;

public class Main {
	enum Browser{IE, FF}
	enum Font{System, Nanum}
	static private Browser browser = Browser.IE;
	static private Font font = Font.System;
	private File excel = null;
	private List<TestCase> testcases;
	static private String hideDivJS;
	static private String captureForIE;
	static private String targetFolder;
	static private String testURL = "http://search.naver.com/search.naver?where=nexearch&query=";
	static private int port = -1;
	
	private int getSeleniumPort(){
		if(port == -1){
			port = (int) (2000+Math.random()*3000);
			while(isOpenedPort(port)){
				port = (int) (2000+Math.random()*3000);
			}
		}
		return port;
	}
	
	public static String getBrowserString(){
		return browser.equals(Browser.IE)?"*iexplore":"*firefox";
	}
	
	private boolean isOpenedPort(int port){
		try {
			ServerSocket socket;
			socket = new ServerSocket(port);
			socket.close();
			return false;
		} catch (BindException e) {
			return true;
		}catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}
	
	public static String getTargetFolder(){
		if(targetFolder == null){
			SimpleDateFormat format =
		            new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
			targetFolder = String.format("./%s_%s_%s/", browser, font, format.format(new Date()));
			File fold = new File(targetFolder);
			if(!fold.mkdir())
				throw new IllegalStateException("Directory is not created.");
			targetFolder = fold.getAbsolutePath()+"\\";
		}
		return targetFolder;
	}
	
	public static String getTestURL(){
		return testURL;
	}
	
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
		HtmlReporter.setPath(getTargetFolder()+"report.html");
		SeleniumServer sserver;
		try {
			RemoteControlConfiguration conf = new RemoteControlConfiguration();
			conf.setPort(getSeleniumPort());
			sserver = new SeleniumServer(conf);
			sserver.start();
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
		String browserStr = getBrowserString();
		Selenium selenium = new DefaultSelenium("localhost", getSeleniumPort(), browserStr, "http://www.naver.com");
		selenium.start();
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
	
	public static void main(String[] args) throws Exception {
		new Main(args).run();
	}
	public static Browser getBrowser() {
		return browser;
	}

	public static void setBrowser(Browser br) {
		Main.browser = br;
	}
	
}
