package com.nhncorp.naver.qa4team.areaname;

public class AreaTestCase{
	int tcNumber;
	private String xpath;
	private String expectedValue;
	private boolean result;

	public AreaTestCase(int tcNumber, String xpath, String expectedValue, boolean result){
		this.tcNumber = tcNumber;
		this.xpath = xpath;
		this.expectedValue = expectedValue;
		this.result = result;
	}
	
	public String toString(){
		return String.format("%d[%s-%s %s{%s}]", tcNumber,xpath, expectedValue, result);
	}

	public int getTcNumber() {
		return tcNumber;
	}

	public String getXpath() {
		return xpath;
	}

	public String getExpectedValue() {
		return expectedValue;
	}
	
	public boolean getResult() {
		return result;
	}
	
	protected void setResult(boolean result) {
		this.result = result;
	}
}
