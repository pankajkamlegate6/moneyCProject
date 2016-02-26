package com.moneycontrol.handheld.entity.market;

import java.io.Serializable;

public class MarketCorporateSplitsData implements Serializable {
	private String announceDate, oldFv, newFv, exSplitDate, message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getAnnounceDate() {
		return announceDate;
	}

	public void setAnnounceDate(String announceDate) {
		this.announceDate = announceDate;
	}

	public String getOldFv() {
		return oldFv;
	}

	public void setOldFv(String oldFv) {
		this.oldFv = oldFv;
	}

	public String getNewFv() {
		return newFv;
	}

	public void setNewFv(String newFv) {
		this.newFv = newFv;
	}

	public String getExSplitDate() {
		return exSplitDate;
	}

	public void setExSplitDate(String exSplitDate) {
		this.exSplitDate = exSplitDate;
	}
}
