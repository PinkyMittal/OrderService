package com.pubkart.catalog.exception;

import org.springframework.http.HttpStatus;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ApplicationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private HttpStatus status;

	public ApplicationException(String message, HttpStatus status) {
		super(message);
		this.status = status;
	}

}
