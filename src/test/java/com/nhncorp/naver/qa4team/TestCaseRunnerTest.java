package com.nhncorp.naver.qa4team;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.Test;

public class TestCaseRunnerTest {
	@Test
	public void integrationTest(){
		WebDriver driver = new InternetExplorerDriver();
		new TestCaseRunner().run(new TestCase(1, "바로가기", "", "아사히신문", "goto_direct", "바로가기"), driver);
	}
}
