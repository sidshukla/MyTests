package com.bmc.analyser.application;

import java.util.List;

import com.bmc.analyser.springer.model.GridLocation;

public class AnalyserApp {

	/**
	 * Main class which takes the input as command line parameter
	 * It calls the Request handler to process request and print the output
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		RequestHandler analyserHanlder = new RequestHandler();

		List<GridLocation> analysedScores = analyserHanlder.processRequest(args);
		analyserHanlder.printData(analysedScores);
	}
}
