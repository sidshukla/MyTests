package com.bmc.analyser.application;

import java.util.List;

import com.bmc.analyser.springer.SpringerAnalyser;
import com.bmc.analyser.springer.SpringerAnalyserImpl;
import com.bmc.analyser.springer.model.GridLocation;

/**
 * Handler class which processes the input and pass the request to the analyser class
 * and prints the response as required
 * @author siddharth
 * 
 */
public class RequestHandler {

	/*
	 * Inject the spring analyser class
	 */
	SpringerAnalyser springerAnalyser;

	public RequestHandler() {
		springerAnalyser = new SpringerAnalyserImpl();
	}

	/**
	 * 
	 * @param gridRequest
	 */
	public List<GridLocation> processRequest(String[] gridRequest) {

		if(gridRequest == null || gridRequest.length == 0){
			throw new IllegalArgumentException("Input cannot be null");
		}
		
		int gridSize = Integer.parseInt(gridRequest[1]);
		int resultSize = Integer.parseInt(gridRequest[0]);
		
		if(gridRequest.length - 2 < resultSize){
			throw new IllegalArgumentException("Result size cannot be greater than the input grid size");
		}else if(gridRequest.length -2 != (gridSize*gridSize)){
			throw new IllegalArgumentException("Input grid array does not match the grid size");
		}

		int[] inputGrid = new int[gridRequest.length - 2];

		for (int i = 0; i < inputGrid.length; i++) {
			inputGrid[i] = Integer.parseInt(gridRequest[i + 2]);
		}
		return springerAnalyser.analyseGrid(inputGrid, gridSize, resultSize);
	}

	/**
	 * 
	 * @param analysedScores
	 */
	public void printData(List<GridLocation> gridLocations) {
		for (GridLocation gridLocation : gridLocations) {
			System.out.println("(" + gridLocation.getxLoc() + ","
					+ gridLocation.getyLoc() + ") Score : "
					+ gridLocation.getHeat());
		}
	}
}
