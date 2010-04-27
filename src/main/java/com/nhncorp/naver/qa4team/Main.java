package com.nhncorp.naver.qa4team;

public class Main {
	enum Browser{IE, FF}
	enum Font{System, Nanum}
	public static void main(String[] args) {
		Browser browser = Browser.IE;
		Font font = Font.System;
		if(args.length>0){
			String browserArg = args[0];
			try{
				browser = Browser.valueOf(browserArg);
			} catch(IllegalArgumentException e) {
				printUsage("xxxx.jar");
			}
		}
		if(args.length>1){
			String fontArg = args[1];
			try{
				font = Font.valueOf(fontArg);
			} catch(IllegalArgumentException e) {
				printUsage("xxxx.jar");
			}
		}
		TestSuit suit = TestSuitFactory.newInstance(browser, font);
		suit.run();
	}
	private static void printUsage(String jarFileName){
		System.out.println(String.format("java -jar %s [browsertype [font]]", jarFileName));
		System.out.println(String.format("\t default: IE System"));
		System.out.println(String.format("\t browsertype: IE, FF"));
		System.out.println(String.format("\t fonttype: System, Nanum"));
	}
}
