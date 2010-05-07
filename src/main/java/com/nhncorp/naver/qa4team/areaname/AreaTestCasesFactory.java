package com.nhncorp.naver.qa4team.areaname;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class AreaTestCasesFactory {
	private String preXpath;
	private String preExpectedValue;
	private final String SHEET_NAME = "블로그";
	private final int TCNUM_COL_NUM = 0;
	private final int XPATH_COL_NUM = 1;
	private final int AREANAME_COL_NUM = 2;
	private final int EXPECTEDVALUE_COL_NUM = 3;
	private final int START_ROW_NUMBER = 2;
	
	
	private AreaTestCase getAreaTestCase(XSSFRow row){
		int tcNumber = (int) row.getCell(TCNUM_COL_NUM).getNumericCellValue();
		String xpath = row.getCell(XPATH_COL_NUM).getStringCellValue();
		if(xpath.equals("")){
			xpath = preXpath;
		}else{
			preXpath = xpath;
		}
		String areaName = row.getCell(AREANAME_COL_NUM).getStringCellValue();
		String expectedValue = row.getCell(EXPECTEDVALUE_COL_NUM).getStringCellValue();
		if(expectedValue.equals("")){
			expectedValue = preExpectedValue;
		}else{
			preExpectedValue = expectedValue;
		}
		boolean result = false;
		return new AreaTestCase(tcNumber, xpath, areaName, expectedValue, result);
	}
	public List<AreaTestCase> getTestCases(InputStream is){
		List<AreaTestCase> areaTestCases = new ArrayList<AreaTestCase>();
		preXpath = "";
		preExpectedValue = "";
		XSSFWorkbook wb;
		try {
			wb = new XSSFWorkbook(is);
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
		XSSFSheet sheet = wb.getSheet(SHEET_NAME);
		boolean finished = false;
		int rowNumber = START_ROW_NUMBER;
		int idx = 1;
		while(!finished){
			XSSFRow row = sheet.getRow(rowNumber);
			XSSFCell idxCell = row.getCell(0);
			if(idxCell != null && idx == idxCell.getNumericCellValue()){
				++idx;
				++rowNumber;
				areaTestCases.add(getAreaTestCase(row));
			}else{
				finished = true;
			}
		}
		return areaTestCases;
	}
}
