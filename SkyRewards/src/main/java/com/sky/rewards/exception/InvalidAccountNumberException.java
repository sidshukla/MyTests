package com.sky.rewards.exception;

public class InvalidAccountNumberException extends RewardsException{

	private static final long serialVersionUID = -7906146448853684890L;
	
	public InvalidAccountNumberException(String arg0) {
		super(arg0);
	}
	
	public InvalidAccountNumberException(String arg0 , Throwable arg1) {
		super(arg0 , arg1);
	}
}
