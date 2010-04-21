package com.nhncorp.naver.qa4team;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.htmlcleaner.DomSerializer;
import org.htmlcleaner.HtmlCleaner;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class TestHTMLPageGenerator {
	
	
	public void generateTestHTMLPage(String url, String target) {
		HttpClient client = new HttpClient();
		client.getParams().setParameter("http.protocol.content-charset",
				"UTF-8");
		// client.getParams().setParameter("http.protocol.content-charset",
		// "UTF-8");
		GetMethod get = new GetMethod(url);
		// get.addRequestHeader("Content-Type",
		// FORM_URL_ENCODED_CONTENT_TYPE+";charset=UTF-8");
		// get.addRequestHeader("Content-Type", "text/html; charset=UTF-8");
		get.setFollowRedirects(true);
		try {
			client.executeMethod(get);
			final InputStream in = get.getResponseBodyAsStream();
			File f = new File(target);
			OutputStream out = new FileOutputStream(f);
			injectJavascript(in, out);
			in.close();
			out.close();
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

	public void injectJavascript(final InputStream in, OutputStream out) throws ParserConfigurationException, IOException, TransformerFactoryConfigurationError, TransformerException {
		HtmlCleaner cleaner = new HtmlCleaner();
		DomSerializer dom = new DomSerializer(cleaner.getProperties());
		
		Document doc = dom.createDOM(cleaner.clean(in));
		Node head = doc.getElementsByTagName("head").item(0);
		Element script = doc.createElement("script");
		script.setAttribute("type", "text/javascript");
		script.setAttribute("src", "snapsie.js");
		head.appendChild(script);
		
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		transformer.setOutputProperty(OutputKeys.ENCODING, "euc-kr");
		DOMSource source = new DOMSource(doc);
		StreamResult sr = new StreamResult(out);
		transformer.transform(source, sr);
	}
}
