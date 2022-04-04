package com.cs.exception;

public class NotAdminException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public NotAdminException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NotAdminException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public NotAdminException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
