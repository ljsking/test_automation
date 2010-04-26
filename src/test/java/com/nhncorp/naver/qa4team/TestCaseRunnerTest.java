package com.nhncorp.naver.qa4team;

import org.testng.annotations.Test;

public class TestCaseRunnerTest extends IESeleniumTestCase {
	@Test
	public void integrationTest(){
		new TestCaseRunner().run(new TestCase(1, "바로가기", "", "아사히신문", "goto_direct", "바로가기"), selenium);
	}
}
