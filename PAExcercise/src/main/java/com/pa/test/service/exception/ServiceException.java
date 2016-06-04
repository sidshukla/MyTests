package com.pa.test.service.exception;

/**
 * Custom exception class
 * @author Siddharth
 *
 */
public class ServiceException extends Exception{

	private static final long serialVersionUID = -5857159782795201524L;

	public ServiceException(String errorMessage) {
		super(errorMessage);
	}
}
