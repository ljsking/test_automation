package com.nhncorp.naver.qa4team.areaname;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AreaNameSampleTest{
	@DataProvider(name="fromExcel")
	public Iterator<Object[]> readFromExcel() throws FileNotFoundException{
		InputStream myxls = new FileInputStream("src/main/resources/areaNameTestCase.xlsx");
		AreaTestCasesFactory factory = new AreaTestCasesFactory();
		List<AreaTestCase> tcs = factory.getTestCases(myxls);
		List<Object[]> data = new ArrayList<Object[]>();
		for(AreaTestCase tc : tcs){
			data.add(new Object[]{tc});
		}
		return data.iterator();
	}
	
	@Test(dataProvider = "fromExcel")
	public void mainTest(AreaTestCase tc) throws FileNotFoundException {
		System.out.println(tc.toString());
	}
}
