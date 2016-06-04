package com.sky.rewards.exception;

public class RewardsException extends Exception {

	private static final long serialVersionUID = 6006770615344179844L;
	
	public RewardsException(String arg0) {
		super(arg0);
	}
	
	public RewardsException(String arg0 , Throwable arg1) {
		super(arg0, arg1);
	}
}
