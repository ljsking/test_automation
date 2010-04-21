package com.nhncorp.naver.qa4team;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TestCasesFactory {
	private String preSection;
	private String preKeyword;
	private final String SHEET_NAME = "2. 통검검색결과노출영역";
	private final int TCNUM_COL_NUM = 0;
	private final int SECTION_COL_NUM = 1;
	private final int SUBCATEGORY_COL_NUM = 2;
	private final int KEYWORDS_COL_NUM = 3;
	private final int CLASSNAME_COL_NUM = 4;
	private final int HEAD_TITLE_COL_NUM = 5;
	private final int START_ROW_NUMBER = 4;
	
	
	private TestCase getTestCase(XSSFRow row){
		int tcNumber = (int) row.getCell(TCNUM_COL_NUM).getNumericCellValue();
		String section = row.getCell(SECTION_COL_NUM).getStringCellValue();
		if(section.equals("")){
			section = preSection;
		}else{
			preSection = section;
		}
		String subCategory = row.getCell(SUBCATEGORY_COL_NUM).getStringCellValue();
		String keyword = row.getCell(KEYWORDS_COL_NUM).getStringCellValue();
		if(keyword.equals("")){
			keyword = preKeyword;
		}else{
			preKeyword = keyword;
		}
		String className = row.getCell(CLASSNAME_COL_NUM).getStringCellValue();
		String headTitle = row.getCell(HEAD_TITLE_COL_NUM).getStringCellValue();
		return new TestCase(tcNumber, section, subCategory, keyword, className, headTitle);
	}
	public List<TestCase> getTestCases(InputStream is){
		List<TestCase> testCases = new ArrayList<TestCase>();
		preSection = "";
		preKeyword = "";
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
				testCases.add(getTestCase(row));
			}else{
				finished = true;
			}
		}
		return testCases;
	}
}
