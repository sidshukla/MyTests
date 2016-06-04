package com.sky.pd;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

public class ProcessTest {
	
	Process process = new Process();
	
	@Test (expected = IllegalArgumentException.class)
	public void testProcessInputWithNullArguments(){
		process.processInput(null);
	}
	
	@Test
	public void testProcessWithValidArgument(){
		String[] input = {"HUB" , "PLUS_BOX" , "PLUS_BOX"};
		
		ProductOutput output1 = new ProductOutput();
		output1.setProductName("HUB");
		output1.setProductPrice(20);
		output1.setProductQuantity(1);
		
		ProductOutput output2 = new ProductOutput();
		output2.setProductName("PLUS_BOX");
		output2.setProductPrice(30);
		output2.setProductQuantity(2);
		
		List<ProductOutput> actualOutput = process.processInput(input);
		
		Assert.assertEquals(actualOutput.size(), 2);
		Assert.assertEquals(output1, actualOutput.get(0));
		Assert.assertEquals(output2, actualOutput.get(1));
	}

}
