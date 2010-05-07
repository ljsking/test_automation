package com.nhncorp.naver.qa4team.regression_test;

import java.util.Arrays;
import java.util.List;

public class TestCase{
	private List<String> keywords;
	int tcNumber;
	private String section;
	private String subCategory;
	private String className;
	private String headTitle;

	public TestCase(int tcNumber, String section, String subCategory, String keywords, String className, String headTitle){
		this.tcNumber = tcNumber;
		this.keywords = Arrays.asList(keywords.split("\\s*,\\s*"));
		this.section = section;
		this.subCategory = subCategory;
		this.className = className;
		this.headTitle = headTitle;
	}
	
	public String toString(){
		return String.format("%d[%s-%s %s %s{%s}]", tcNumber,section, subCategory, keywords, className, headTitle);
	}

	public List<String> getKeywords() {
		return keywords;
	}
	
	public int getTcNumber() {
		return tcNumber;
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
	
	public String getHeadTitle() {
		return headTitle;
	}
}
