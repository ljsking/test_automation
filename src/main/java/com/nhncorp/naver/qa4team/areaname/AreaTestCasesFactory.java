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
	private final String SHEET_NAME = "블로그";
	private final int TCNUM_COL_NUM = 0;
	private final int XPATH_COL_NUM = 1;
	private final int EXPECTEDVALUE_COL_NUM = 2;
	private final int START_ROW_NUMBER = 1;
	
	
	private AreaTestCase getAreaTestCase(XSSFRow row){
		int tcNumber = (int) row.getCell(TCNUM_COL_NUM).getNumericCellValue();
		String xpath = row.getCell(XPATH_COL_NUM).getStringCellValue();
		String expectedValue = row.getCell(EXPECTEDVALUE_COL_NUM).getStringCellValue();
		boolean result = false;
		return new AreaTestCase(tcNumber, xpath, expectedValue, result);
	}
	
	public List<AreaTestCase> getTestCases(InputStream is){
		List<AreaTestCase> areaTestCases = new ArrayList<AreaTestCase>();
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
