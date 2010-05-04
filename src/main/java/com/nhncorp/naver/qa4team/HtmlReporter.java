package com.nhncorp.naver.qa4team;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.*;

public class HtmlReporter {
	public HtmlReporter(String path){
		Document doc = Jsoup.parse("");
		Element div = doc.createElement("div");
		div.attr("class", "pass");
		doc.appendChild(div);
		
		div.appendChild(doc.createElement("h2").text("Title 한글"));
		div.appendChild(doc.createElement("h3").text("Sub Title 한글"));
		div.appendChild(doc.createElement("img").attr("src", "./").attr("alt", "screenshot"));
		
		try {
			FileUtils.writeStringToFile(new File(path), doc.html());
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}
}
