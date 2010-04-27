package com.nhncorp.naver.qa4team;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class IESearchRegressionTest {
	private WebDriver driver;
	@BeforeClass
	public void beforeClass(){
		driver = new InternetExplorerDriver();
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
	
	@Test(dataProvider = "fromExcel")
	public void mainTest(TestCase tc) throws FileNotFoundException {
		new TestCaseRunner().run(tc, driver);
	}
}
