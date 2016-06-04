package com.bmc.analyser.springer;

import java.util.List;

import com.bmc.analyser.springer.model.GridLocation;

/**
 * Analyser interface with operation to analyse the grid and provide 
 * a list of locations ordered by the heat value
 * @author siddharth
 *
 */

public interface SpringerAnalyser {

	/**
	 * 
	 * @param input
	 * @param gridSize
	 * @param resultSize
	 * @return
	 */
	List<GridLocation> analyseGrid(int[] input, int gridSize, int resultSize);
}
