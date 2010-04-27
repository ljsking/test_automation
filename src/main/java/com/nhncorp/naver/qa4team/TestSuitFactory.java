package com.nhncorp.naver.qa4team;

import com.nhncorp.naver.qa4team.Main.Browser;
import com.nhncorp.naver.qa4team.Main.Font;

public class TestSuitFactory {

	public static TestSuit newInstance(Browser browser, Font font) {
		return new TestSuit(browser, font);
	}

}
