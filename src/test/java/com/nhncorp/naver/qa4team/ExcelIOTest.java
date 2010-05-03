package com.nhncorp.naver.qa4team;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class ExcelIOTest {
	@Test
	public void readXlsxFileTest() throws IOException{
		InputStream myxls = new FileInputStream("src/main/resources/TestCase.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(myxls);
		assertEquals(5, wb.getNumberOfSheets());
		XSSFSheet sheet = wb.getSheet("2. 통검검색결과노출영역");
		
		XSSFRow row = sheet.getRow(1);
		XSSFCell c = row.getCell(1);
		assertEquals("<통검검색결과노출영역>", c.getStringCellValue());
		row = sheet.getRow(4);
		assertEquals("바로가기", row.getCell(1).getStringCellValue());
		assertEquals("", row.getCell(2).getStringCellValue());
		assertEquals("아사히신문", row.getCell(3).getStringCellValue());
		assertEquals("goto_direct section", row.getCell(4).getStringCellValue());
	}
}
