package com.sky.rewards.exception;

public class TechnicalFailureException extends RewardsException {

	private static final long serialVersionUID = 652868047634903780L;

	public TechnicalFailureException(String arg0) {
		super(arg0);
	}
	
	public TechnicalFailureException(String arg0 , Throwable arg1) {
		super(arg0 , arg1);
	}
}
