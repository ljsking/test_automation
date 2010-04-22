package com.nhncorp.naver.qa4team;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class HtmlDownloadTest {
	final String target = "web-inf/test_html.html";
	
	@BeforeClass
	public void beforeClass() {
		FileUtils.deleteQuietly(new File(target));
	}

	@AfterClass
	public void afterClass() {
		FileUtils.deleteQuietly(new File(target));
	}

	@Test
	public void downloadHTMLTest() throws Exception {
		assertFalse((new File(target)).isFile());
		TestHTMLPageGenerator generator = new TestHTMLPageGenerator();
		generator
				.generateTestHTMLPage("http://search.naver.com/search.naver?where=nexearch&query=%C7%C1%B6%F3%C7%CF%C0%C7+%BF%AC%C0%CE", target);
		File f = new File(target);
		assertTrue(f.isFile());
		Document doc = Jsoup.parse(FileUtils.readFileToString(f));
		Elements elms = doc.select("script[src=snapsie.js]");
		assertEquals(1, elms.size());
	}
}
