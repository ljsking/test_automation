package com.nhncorp.naver.qa4team.fixture;

import java.io.IOException;

import com.nhncorp.naver.qa4team.regression_test.RegressionTest;
import com.nhncorp.naver.qa4team.regression_test.RegressionTest.Browser;
import com.nhncorp.naver.qa4team.regression_test.RegressionTest.Font;

public class SearchFixture {
	private Browser browser;
	private Font font;
	private String url;
	public SearchFixture(){
	}
	public boolean setBrowser(String br){
		try{
			browser = Browser.valueOf(br);
		} catch (IllegalArgumentException e){
			throw new RuntimeException("message:<<FF or IE>>");
		}
		return true;
	}
	public boolean setFont(String ft){
		try{
			font = Font.valueOf(ft);
		} catch (IllegalArgumentException e){
			throw new RuntimeException("message:<<System or Nanum>>");
		}
		return true;
	}
	public boolean setTestURL(String testURL){
		url = testURL;
		return true;
	}
	public String run(){
		try {
			return new RegressionTest(createArgs()).run();
		} catch (IOException e) {
			throw new RuntimeException("message:<<check Excel file>>");
		}
	}
	private String[] createArgs() {
		String[] args = new String[8];
		args[0] = "-br";
		args[1] = browser.toString();
		args[2] = "-ft";
		args[3] = font.toString();
		args[4] = "-url";
		args[5] = url;
		args[6] = "-xlsx";
		args[7] = ".\\FitNesseRoot\\files\\TestCase.xlsx";
		return args;
	}
}
