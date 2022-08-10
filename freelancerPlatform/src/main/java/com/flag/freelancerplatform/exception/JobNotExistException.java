package com.flag.freelancerplatform.exception;

public class JobNotExistException extends RuntimeException {
	public JobNotExistException(String message) {
		super(message);
	}
}