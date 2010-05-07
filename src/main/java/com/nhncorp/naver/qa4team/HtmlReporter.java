package com.nhncorp.naver.qa4team;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.*;

public class HtmlReporter {
	private Document doc = Jsoup.parse("");
	private Element body;
	private static String path;
	private static final HtmlReporter INSTANCE = new HtmlReporter();
	private int totalTCSize = 0;
	private int passed = 0;
	private int failed = 0;
	
	public void setTotalTCSize(int size){
		totalTCSize = size;		
	}
	
	private String getInfoText(){
		return String.format("Progress %d/%d Pass:%d Fail:%d", passed+failed, totalTCSize, passed, failed);
	}
	
	private HtmlReporter(){
		doc.appendChild(doc.createElement("style").attr("type","text/css").text(".fail{background-color:#FFC0CB;}"));
		body = doc.getElementsByTag("body").get(0);
		body.appendChild(doc.createElement("h1").attr("id","info").text(getInfoText()));
		
	}
	
	private void wirte(){
		try {
			FileUtils.deleteQuietly(new File(path));
			FileUtils.writeStringToFile(new File(path), doc.html());
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}

	public void pass(String keyword, String sectionName, String filename) {
		++passed;
		Element div = doc.createElement("div");
		div.attr("class", "pass");
		body.appendChild(div);
		div.appendChild(doc.createElement("h2").text(String.format("%s(%s)", sectionName, keyword)));
		div.appendChild(doc.createElement("h3").text(new Date().toString()));
		div.appendChild(doc.createElement("img").attr("src", filename).attr("alt", "screenshot"));
		doc.getElementById("info").text(getInfoText());
		wirte();
	}
	
	public void fail(String keyword, String sectionName, String reason) {
		++failed;
		Element div = doc.createElement("div");
		div.attr("class", "fail");
		body.appendChild(div);
		div.appendChild(doc.createElement("h2").text(String.format("%s(%s)", sectionName, keyword)));
		div.appendChild(doc.createElement("h3").text(new Date().toString()));
		div.appendChild(doc.createElement("p").text(reason));
		doc.getElementById("info").text(getInfoText());
		wirte();
	}

	public static HtmlReporter getInstance() {
		return INSTANCE;
	}
	
	public static void setPath(String path){
		HtmlReporter.path = path;
	}
}
