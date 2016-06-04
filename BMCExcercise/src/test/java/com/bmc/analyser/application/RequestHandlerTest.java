package com.bmc.analyser.application;

import static org.mockito.Mockito.when;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.bmc.analyser.springer.SpringerAnalyser;
import com.bmc.analyser.springer.model.GridLocation;

@RunWith(MockitoJUnitRunner.class)
public class RequestHandlerTest {

	@InjectMocks
	RequestHandler analyserHanlder = new RequestHandler();

	@Mock
	SpringerAnalyser springerAnalyser;

	@Mock
	PrintStream printStream;

	@Test
	public void processRequestValidInputTest() {
		List<GridLocation> expected = new ArrayList<GridLocation>();
		expected.add(new GridLocation(3, 3, 26));

		when(springerAnalyser.analyseGrid(
						eq(new int[] { 5, 3, 1, 2, 0, 4, 1, 1, 3, 2, 2, 3, 2,
								4, 3, 0, 2, 3, 3, 2, 1, 0, 2, 4, 3 }), eq(5),
						eq(1))).thenReturn(expected);
		
		String[] input = new String[] { "1", "5", "5", "3", "1", "2", "0", "4",
				"1", "1", "3", "2", "2", "3", "2", "4", "3", "0", "2", "3",
				"3", "2", "1", "0", "2", "4", "3" };
		
		List<GridLocation> actualGridLocations = analyserHanlder.processRequest(input);

		verify(springerAnalyser).analyseGrid(
				new int[] { 5, 3, 1, 2, 0, 4, 1, 1, 3, 2, 2, 3, 2, 4, 3, 0, 2,
						3, 3, 2, 1, 0, 2, 4, 3 }, 5, 1);
		verifyNoMoreInteractions(springerAnalyser);
		
		Assert.assertEquals(1, actualGridLocations.size());
		Assert.assertEquals(expected.get(0), actualGridLocations.get(0));
	}
	
	@Test
	public void processRequestNullInputTest(){
		try{
			analyserHanlder.processRequest(null);
		}catch (IllegalArgumentException e) {
			Assert.assertEquals("Input cannot be null", e.getMessage());
		}
	}
	
	@Test
	public void processRequestEmptyInputTest(){
		try{
			analyserHanlder.processRequest(new String[0]);
		}catch (IllegalArgumentException e) {
			Assert.assertEquals("Input cannot be null", e.getMessage());
		}
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void analyseGridInvalidResultSize() throws Exception {
		String[] input = new String[] { "30", "5", "5", "3", "1", "2", "0", "4",
				"1", "1", "3", "2", "2", "3", "2", "4", "3", "0", "2", "3",
				"3", "2", "1", "0", "2", "4", "3" };
		try {
			analyserHanlder.processRequest(input);
		} catch (IllegalArgumentException e) {
			Assert.assertEquals(
					"Result size cannot be greater than the input grid size",
					e.getMessage());
			throw e;
		}
	}
	
	@Test (expected = IllegalArgumentException.class) 
	public void analyseGridInvalidGridSize() throws Exception{
		String[] input = new String[] { "1", "10", "5", "3", "1", "2", "0", "4",
				"1", "1", "3", "2", "2", "3", "2", "4", "3", "0", "2", "3",
				"3", "2", "1", "0", "2", "4", "3" };
		try {
			analyserHanlder.processRequest(input);
		} catch (IllegalArgumentException e) {
			Assert.assertEquals(
					"Input grid array does not match the grid size",
					e.getMessage());
			throw e;
		}
	}
}
