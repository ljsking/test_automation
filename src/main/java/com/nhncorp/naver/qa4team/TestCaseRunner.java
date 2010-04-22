package com.nhncorp.naver.qa4team;

import java.io.File;
import com.thoughtworks.selenium.Selenium;

/*1. 특정 키워드 검색 결과 화면과 SnapsIE를 임베드 해서 저장*/
/*2. 원하는 컬렉션 빼고 다 지움*/
/*3. 캡쳐*/
public class TestCaseRunner{
	static public String convertURL(String source){
		byte[] bytes = source.getBytes();
		StringBuilder sb = new StringBuilder();
		for(byte b : bytes){
			sb.append(String.format("%%%X", b));
		}
		return sb.toString();
	}
	final String pngDir = "results/";
	final String htmlDir = "web-inf/";
	final String html = "target.html";
	final String url = "http://search.naver.com/search.naver?where=nexearch&query=";
	public void run(TestCase tc, Selenium selenium){
		for(String keyword : tc.getKeywords()){
			new TestHTMLPageGenerator().generateTestHTMLPage(url+convertURL(keyword), htmlDir+html);
			String path = new PngGenerator().pngGenerate(selenium, html, tc.getClassName(), pngDir+tc.getClassName()+".png");
			if(!new File(path).isFile()) throw new IllegalStateException("Did not generate PNG file");
		}
	}
}