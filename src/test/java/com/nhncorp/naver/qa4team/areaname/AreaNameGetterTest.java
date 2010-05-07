package com.nhncorp.naver.qa4team.areaname;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

import com.nhncorp.naver.qa4team.SeleniumTestCase;
import com.nhncorp.naver.qa4team.regression_test.RegressionTest;
import com.nhncorp.naver.qa4team.regression_test.RegressionTest.Browser;

public class AreaNameGetterTest extends SeleniumTestCase {
	String url = "http://search.naver.com/search.naver?where=nexearch&query=%C0%FC%BF%EC%B6%F7&sm=top_lve";
	@BeforeClass
	public void beforeClassSel() throws Exception{
		RegressionTest.setBrowser(Browser.FF);
		super.beforeClassSel();
	}
	@Test
	public void getAreaNameTest(){
		AreaNameGetter getter = new AreaNameGetter(selenium);
		assertEquals("GNB.naverhome", getter.getAreaName(selenium, "//div[@id='gnb']//a[.='네이버']"));
	}
}
