package com.nhncorp.naver.qa4team.regression_test;

import java.io.File;

import com.nhncorp.naver.qa4team.HtmlReporter;
import com.nhncorp.naver.qa4team.Main;
import com.thoughtworks.selenium.Selenium;

/*1. 특정 키워드 검색 결과 화면과 SnapsIE를 임베드 해서 저장*/
/*2. 원하는 컬렉션 빼고 다 지움*/
/*3. 캡쳐*/
public class TestCaseRunner{
	public void run(TestCase tc, Selenium selenium){
		for(String keyword : tc.getKeywords()){
			try{
				String path = ScreenCapturer.generate(selenium, keyword, tc.getClassName(), Main.getTargetFolder()+tc.getClassName()+"_"+keyword+".png");
				if(!new File(path).isFile()) throw new IllegalStateException("Did not generate PNG file");
				HtmlReporter.getInstance().pass(keyword, tc.getSection(), path);
			}catch(Throwable t){
				HtmlReporter.getInstance().fail(keyword, tc.getSection(), t.toString());
			}
		}
	}
}