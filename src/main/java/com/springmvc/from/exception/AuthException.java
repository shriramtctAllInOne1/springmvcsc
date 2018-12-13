package com.springmvc.from.exception;

import java.util.Date;

public class AuthException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private Date date;
	private String message;

	public AuthException(Date date, String message) {
		super();
		this.date = date;
		this.message = message;
	}

	public Date getDate() {
		return date;
	}

	public String getMessage() {
		return message;
	}

	@Override
	public String toString() {
		return "AuthException [date=" + date + ", message=" + message + "]";
	}
}