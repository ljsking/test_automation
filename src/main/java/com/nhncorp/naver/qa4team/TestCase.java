package com.nhncorp.naver.qa4team;

import java.util.Arrays;
import java.util.List;

public class TestCase{
	private List<String> keywords;
	private String section;
	private String subCategory;
	private String className;

	public TestCase(String section, String subCategory, String keywords, String className){
		this.keywords = Arrays.asList(keywords.split("\\s*,\\s*"));
		this.section = section;
		this.subCategory = subCategory;
		this.className = className;
	}
	
	public String toString(){
		return String.format("[%s-%s %s %s]", section, subCategory, keywords, className);
	}

	public List<String> getKeywords() {
		return keywords;
	}

	public String getSection() {
		return section;
	}

	public String getSubCategory() {
		return subCategory;
	}

	public String getClassName() {
		return className;
	}
}
