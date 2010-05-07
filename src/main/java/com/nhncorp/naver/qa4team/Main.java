package com.nhncorp.naver.qa4team;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;

import com.nhncorp.naver.qa4team.regression_test.RegressionTest;

public class Main {
	private static AutomationTestable parsingArguments(String[] args) throws Exception{
		Options options = new Options();
		Option areaname = OptionBuilder.create("areaname");
		options.addOption(areaname);
		CommandLineParser parser = new GnuParser();
		CommandLine line = parser.parse( options, args );
        if(line.hasOption("h")){
        	return null;
        } else {
        	return new RegressionTest(args);
        }
	}

	public static void main(String[] args) throws Exception {
		parsingArguments(args).run();
	}

}
