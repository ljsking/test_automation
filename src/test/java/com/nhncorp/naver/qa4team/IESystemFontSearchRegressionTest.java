package com.nhncorp.naver.qa4team;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.nhncorp.naver.qa4team.regression_test.RegressionTest;
import com.nhncorp.naver.qa4team.regression_test.NanumSwitch;
import com.nhncorp.naver.qa4team.regression_test.TestCase;
import com.nhncorp.naver.qa4team.regression_test.TestCaseRunner;
import com.nhncorp.naver.qa4team.regression_test.TestCasesFactory;
import com.nhncorp.naver.qa4team.regression_test.RegressionTest.Browser;

public class IESystemFontSearchRegressionTest extends SeleniumTestCase {
	
	@BeforeClass
	public void beforeClassSel() throws Exception{
		RegressionTest.setBrowser(Browser.FF);
		super.beforeClassSel();
		NanumSwitch.offNanum(selenium);
	}
	@DataProvider(name="fromExcel")
	public Iterator<Object[]> readFromExcel() throws FileNotFoundException{
		InputStream myxls = new FileInputStream("src/main/resources/TestCase.xlsx");
		TestCasesFactory factory = new TestCasesFactory();
		List<TestCase> tcs = factory.getTestCases(myxls);
		List<Object[]> data = new ArrayList<Object[]>();
		for(TestCase tc : tcs){
			data.add(new Object[]{tc});
		}
		return data.iterator();
	}
	
	@Test(dataProvider = "fromExcel", groups = {"longTest"})
	public void mainTest(TestCase tc) throws FileNotFoundException {
		new TestCaseRunner().run(tc, selenium);
	}
}
