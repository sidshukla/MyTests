package com.bmc.analyser.springer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.bmc.analyser.springer.model.GridLocation;

/**
 * Analyser interface implementation
 * @author siddharth
 * 
 */
public class SpringerAnalyserImpl implements SpringerAnalyser {

	@Override
	public List<GridLocation> analyseGrid(int[] input, int gridSize,int resultSize) {
		
		List<GridLocation> analysedList = new ArrayList<GridLocation>();

		int[][] grid = new int[gridSize][gridSize];

		for (int i = 0, k = -1; i < gridSize; i++) {
			for (int j = 0; j < gridSize; j++) {
				grid[j][i] = input[++k];
			}
		}

		for (int y = 0; y < gridSize; y++) {
			for (int x = 0; x < gridSize; x++) {
				int heat = grid[x][y];

				if (x - 1 >= 0) {
					heat = heat + grid[x - 1][y];

					if (y - 1 >= 0) {
						heat = heat + grid[x - 1][y - 1];
					}

					if (y + 1 <= gridSize - 1) {
						heat = heat + grid[x - 1][y + 1];
					}
				}

				if (y - 1 >= 0) {
					heat = heat + grid[x][y - 1];
				}

				if (y + 1 <= gridSize - 1) {
					heat = heat + grid[x][y + 1];
				}

				if (x + 1 <= gridSize - 1) {
					heat = heat + grid[x + 1][y];
					if (y - 1 >= 0) {
						heat = heat + grid[x + 1][y - 1];
					}
					if (y + 1 <= gridSize - 1) {
						heat = heat + grid[x + 1][y + 1];
					}
				}
				GridLocation a = new GridLocation(x, y, heat);
				analysedList.add(a);
			}
		}

		Collections.sort(analysedList);

		List<GridLocation> resultList = new ArrayList<GridLocation>();

		for (int i = 0; i < resultSize; i++) {
			resultList.add(analysedList.get(i));
		}
		
		return resultList;
	}
}
