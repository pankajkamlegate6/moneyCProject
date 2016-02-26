package com.moneycontrol.handheld.custom;

public class MyNetworkException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6037117040681956406L;
	private String message = "";

	public MyNetworkException(String message) {
		this.setMessage(message);
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
