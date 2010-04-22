package com.nhncorp.naver.qa4team;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class TestHTMLPageGenerator {
	
	
	public void generateTestHTMLPage(String url, String target) {
		HttpClient client = new HttpClient();
		client.getParams().setParameter("http.protocol.content-charset",
				"UTF-8");
		GetMethod get = new GetMethod(url);
		get.setFollowRedirects(true);
		try {
			client.executeMethod(get);
			File f = new File(target);
			OutputStream out = new FileOutputStream(f);
			String result = injectJavascript(get.getResponseBodyAsString());
			out.write(result.getBytes());
			out.close();
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}
	

	public String injectJavascript(String data) throws ParserConfigurationException, IOException, TransformerFactoryConfigurationError, TransformerException {
		Document doc = Jsoup.parse(data);
		Element head = doc.getElementsByTag("head").get(0);
		Element script = doc.createElement("script");
		script.attr("type", "text/javascript");
		script.attr("src", "snapsie.js");
		head.appendChild(script);
		return doc.html();
	}
}
