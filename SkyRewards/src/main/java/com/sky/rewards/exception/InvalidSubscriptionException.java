package com.sky.rewards.exception;

public class InvalidSubscriptionException extends RewardsException {

	private static final long serialVersionUID = -7494137832358026258L;

	public InvalidSubscriptionException(String arg0) {
		super(arg0);
	}
	
	public InvalidSubscriptionException(String arg0 , Throwable arg1) {
		super(arg0 , arg1);
	}
}
