package com.nhncorp.naver.qa4team.areaname;

import org.testng.annotations.Test;

import com.nhncorp.naver.qa4team.SeleniumTestCase;

public class AreaNameGetterTest extends SeleniumTestCase {
	@Test
	public void getAreaNameTest(){
		AreaNameGetter.getAreaName(selenium, target);
	}
}
