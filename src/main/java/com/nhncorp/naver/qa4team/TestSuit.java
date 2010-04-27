package com.nhncorp.naver.qa4team;

import com.nhncorp.naver.qa4team.Main.Browser;
import com.nhncorp.naver.qa4team.Main.Font;

public class TestSuit {
	private Browser browser;
	private Font font;
	public TestSuit(Browser browser, Font font){
		this.browser = browser;
		this.font = font;
	}
	public void run() {
		System.out.println(String.format("%s, %s is running", browser, font));
	}

}
