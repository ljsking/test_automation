package com.nhncorp.naver.qa4team;

import org.testng.annotations.Test;

public class PRSceencapture {
	@Test
	public void integrationTest(){
		/*1. 특정 키워드 검색 결과 화면과 SnapsIE를 임베드 해서 저장*/
		/*2. 원하는 컬렉션 빼고 다 지움*/
		/*3. 캡쳐*/
		String query = TestHTMLPageGenerator.convertURL("아사히신문");
		String url = "http://search.naver.com/search.naver?where=nexearch&query=";
		String htmlDir = "web-inf/";
		String html = "target.html";
		String collectionName = "goto_direct";
		String pngDir = "results/";
		new TestHTMLPageGenerator().generateTestHTMLPage(url+query, htmlDir+html);
		new PngGenerator().pngGenerate(html, collectionName, pngDir+collectionName+".png");
	}
}
