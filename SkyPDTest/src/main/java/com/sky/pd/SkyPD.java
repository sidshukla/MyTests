package com.sky.pd;

import java.util.List;

public class SkyPD {

	public static void main(String[] args) {
		Process p = new Process();
		List<ProductOutput> outputs = p.processInput(args);
		double grandTotal = 0.0;

		for (ProductOutput output : outputs) {
			
			double totalPrice = output.getProductQuantity() * output.getProductPrice();
			if (output.getProductQuantity() == 1) {
				StringBuffer stringBuffer = new StringBuffer();
				
				stringBuffer.append(output.getProductQuantity()).append(" ")
						    .append(output.getProductName()).append(" : $")
						    .append(totalPrice);
				System.out.println(stringBuffer.toString());
			} else {
				StringBuffer stringBuffer = new StringBuffer();
				stringBuffer.append(output.getProductQuantity()).append(" ")
							.append(output.getProductName()).append(" @ $")
							.append(output.getProductPrice()).append(" : $")
							.append(totalPrice);
				System.out.println(stringBuffer.toString());
			}
			grandTotal = grandTotal + totalPrice;
		}
		System.out.println("Grand total : $" + grandTotal);
	}
}
