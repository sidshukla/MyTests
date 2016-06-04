package com.bmc.analyser.springer;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import com.bmc.analyser.springer.model.GridLocation;

public class SpringerAnalyserTest {

	SpringerAnalyser springerAnalyser = new SpringerAnalyserImpl();

	@Test
	public void analyseGridValidTest1() {
		GridLocation expectedResult = new GridLocation(3, 3, 26);
		List<GridLocation> actualAnalysedScores = springerAnalyser
				.analyseGrid(new int[] { 5, 3, 1, 2, 0, 4, 1, 1, 3, 2, 2, 3, 2,
						4, 3, 0, 2, 3, 3, 2, 1, 0, 2, 4, 3 }, 5, 1);

		Assert.assertEquals(1, actualAnalysedScores.size());
		Assert.assertEquals(expectedResult, actualAnalysedScores.get(0));
	}

	@Test
	public void analyseGridValidTest2() {
		GridLocation expectedResult_1 = new GridLocation(1, 2, 27);
		GridLocation expectedResult_2 = new GridLocation(1, 1, 25);
		GridLocation expectedResult_3 = new GridLocation(2, 2, 23);

		List<GridLocation> actualAnalysedScores = springerAnalyser
				.analyseGrid(new int[] { 2, 3, 2, 1, 4, 4, 2, 0, 3, 4, 1, 1, 2,
						3, 4, 4 }, 4, 3);

		Assert.assertEquals(3, actualAnalysedScores.size());
		Assert.assertEquals(expectedResult_1, actualAnalysedScores.get(0));
		Assert.assertEquals(expectedResult_2, actualAnalysedScores.get(1));
		Assert.assertEquals(expectedResult_3, actualAnalysedScores.get(2));
	}

}
