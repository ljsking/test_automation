package com.nhncorp.naver.qa4team;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.*;

public class HtmlReporter {
	private Document doc = Jsoup.parse("");
	private static String path;
	private static final HtmlReporter INSTANCE = new HtmlReporter();
	private HtmlReporter(){
		doc.appendChild(doc.createElement("style").attr("type","text/css").text(".fail{background-color:#FFC0CB;}"));
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
		Element div = doc.createElement("div");
		div.attr("class", "pass");
		doc.appendChild(div);
		div.appendChild(doc.createElement("h2").text(String.format("%s(%s)", sectionName, keyword)));
		div.appendChild(doc.createElement("h3").text(new Date().toString()));
		div.appendChild(doc.createElement("img").attr("src", filename).attr("alt", "screenshot"));
		wirte();
	}
	
	public void fail(String keyword, String sectionName, String reason) {
		Element div = doc.createElement("div");
		div.attr("class", "fail");
		doc.appendChild(div);
		div.appendChild(doc.createElement("h2").text(String.format("%s(%s)", sectionName, keyword)));
		div.appendChild(doc.createElement("h3").text(new Date().toString()));
		div.appendChild(doc.createElement("p").text(reason));
		wirte();
	}

	public static HtmlReporter getInstance() {
		return INSTANCE;
	}
	
	public static void setPath(String path){
		HtmlReporter.path = path;
	}
}
