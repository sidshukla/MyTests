package com.sky.pd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Process {

	public List<ProductOutput> processInput(String[] input) {
		if (input == null || input.length == 0) {
			throw new IllegalArgumentException("No input argument");
		}
		
		Map<Product, ProductOutput> simpleInput = new HashMap<Product, ProductOutput>();

		for (String arg : input) {
			if (Product.hasValue(arg)) {
				Product local = Product.valueOf(arg);
				if (simpleInput.containsKey(local)) {
					ProductOutput productOutputLocal = simpleInput.get(local);
					productOutputLocal.setProductQuantity(productOutputLocal.getProductQuantity()+ 1);
				} else {
					ProductOutput productOutput = new ProductOutput(); 
					productOutput.setProductName(local.toString());
					productOutput.setProductPrice(local.getPrice());
					productOutput.setProductQuantity(1);
					simpleInput.put(local, productOutput);
				}
			} else {
				System.out.println(arg + " is not a valid input");
				//bad thing to do , but will work for now
				System.exit(0);
			}
		}

		return new ArrayList<ProductOutput>(simpleInput.values());
	}
}
