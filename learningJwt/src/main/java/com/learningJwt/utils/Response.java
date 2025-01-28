package com.learningJwt.utils;

public class Response {
	 private String message;
	 private int statusCode;
	 private boolean operation;
	 private String token;  // Add this field

	    // Getters and Setters for token
	    public String getToken() {
	        return token;
	    }

	    public void setToken(String token) {
	        this.token = token;
	    }
	public boolean getOperation() {
		return operation;
	}
	public void setOperation(boolean operation) {
		this.operation = operation;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
}