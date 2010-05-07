package com.nhncorp.naver.qa4team.areaname;

import org.testng.annotations.Test;


public class AreaNameTestCaseRunnerTest{
	@Test(groups = {"longTest"})
	public void integrationTest(){
		new AreaNameTestCaseRunner().run(new AreaTestCase(1, "//form[@id='blog_option_sort_form']//button[span='정확도']", "opt.sortsim", false), selenium);
	}

}
