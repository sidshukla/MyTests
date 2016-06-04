package com.sky.pd;

public enum Product {

    PLUS_BOX(30.00),
    PLUS_BOX_WITH_HD(50.00),
    PLUS_BOX_WITH_HD_2_TB(100.00),
    PLUS_MULTIROOM(75.00),
    HUB(20.00);

    private double price;

    private Product(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
    
    /*
     * Method to check if the input is valid
     */
    public static boolean hasValue(String product){
    	for(Product tempProduct : Product.values()){
    		if(tempProduct.toString().equals(product)){
    			return true;
    		}
    	}
    	return false;
    }

}
