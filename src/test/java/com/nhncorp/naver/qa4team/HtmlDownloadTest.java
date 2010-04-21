package com.nhncorp.naver.qa4team;

import java.io.File;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.io.FileUtils;
import org.htmlcleaner.DomSerializer;
import org.htmlcleaner.HtmlCleaner;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

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
		// Logger logger = Logger.getLogger(this.getClass());
		assertFalse((new File(target)).isFile());
		TestHTMLPageGenerator generator = new TestHTMLPageGenerator();
		generator
				.generateTestHTMLPage("http://search.naver.com/search.naver?where=nexearch&query=%BE%C8%B3%E7", target);
		assertTrue((new File(target)).isFile());
		
		HtmlCleaner cleaner = new HtmlCleaner();
		DomSerializer dom = new DomSerializer(cleaner.getProperties());
		Document doc = dom.createDOM(cleaner.clean(new File(target)));
		XPath xpath = XPathFactory.newInstance().newXPath();
		XPathExpression expr = xpath.compile("/html/head/script[@src='snapsie.js']");
		Object result = expr.evaluate(doc, XPathConstants.NODESET);
		NodeList nodes = (NodeList) result;
		assertEquals(1, nodes.getLength());
	}
}
