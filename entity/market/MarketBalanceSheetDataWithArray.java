package com.moneycontrol.handheld.entity.market;

import java.util.ArrayList;

public class MarketBalanceSheetDataWithArray {

	private ArrayList<MarketBalanceSheetData> balancesheet = null;
	private ArrayList<String> trackerString = null;

	public ArrayList<MarketBalanceSheetData> getBalancesheet() {
		return balancesheet;
	}

	public void setBalancesheet(ArrayList<MarketBalanceSheetData> balancesheet) {
		this.balancesheet = balancesheet;
	}

	public ArrayList<String> getTrackerString() {
		return trackerString;
	}

	public void setTrackerString(ArrayList<String> trackerString) {
		this.trackerString = trackerString;
	}

}
