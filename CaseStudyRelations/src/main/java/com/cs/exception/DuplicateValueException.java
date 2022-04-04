package com.cs.exception;

import org.springframework.dao.DataIntegrityViolationException;

public class DuplicateValueException extends DataIntegrityViolationException {

	private static final long serialVersionUID = 1L;

	public DuplicateValueException(String msg) {
		super(msg);
	}

}
