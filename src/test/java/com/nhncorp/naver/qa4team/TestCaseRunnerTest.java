package com.nhncorp.naver.qa4team;

import org.testng.annotations.Test;

import com.nhncorp.naver.qa4team.regression_test.TestCase;
import com.nhncorp.naver.qa4team.regression_test.TestCaseRunner;

public class TestCaseRunnerTest extends SeleniumTestCase {
	@Test(groups = {"longTest"})
	public void integrationTest(){
		new TestCaseRunner().run(new TestCase(1, "바로가기", "", "아사히신문", "goto_direct", "바로가기"), selenium);
	}
}
