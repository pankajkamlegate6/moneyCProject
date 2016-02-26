package com.moneycontrol.handheld.entity.market;

import java.io.Serializable;

public class MarketCorporateRecentData implements Serializable {

	private String name, message;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
