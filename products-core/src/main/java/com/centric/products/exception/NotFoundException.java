package com.centric.products.exception;

public class NotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1058107605569325465L;
	
	
	public NotFoundException(final String message) {
	       super(message);
	   }

}
