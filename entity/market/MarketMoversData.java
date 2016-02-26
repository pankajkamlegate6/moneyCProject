package com.moneycontrol.handheld.entity.market;

import java.io.Serializable;
import java.util.ArrayList;

import com.moneycontrol.handheld.entity.home.FieldData;

public class MarketMoversData implements Serializable {
	private String lastupdated, error;
	private MarketMoversBSE_NSE_Data bseData, nseData;
	private ArrayList<FieldData> datefiltterurl;

	private ArrayList<FieldData> exchangeUrl;

	public ArrayList<FieldData> getExchangeUrl() {
		return exchangeUrl;
	}

	public void setExchangeUrl(ArrayList<FieldData> exchangeUrl) {
		this.exchangeUrl = exchangeUrl;
	}

	public ArrayList<FieldData> getDatefiltterurl() {
		return datefiltterurl;
	}

	public void setDatefiltterurl(ArrayList<FieldData> datefiltterurl) {
		this.datefiltterurl = datefiltterurl;
	}

	public String getLastupdated() {
		return lastupdated;
	}

	public void setLastupdated(String lastupdated) {
		this.lastupdated = lastupdated;
	}

	public MarketMoversBSE_NSE_Data getBseData() {
		return bseData;
	}

	public void setBseData(MarketMoversBSE_NSE_Data bseData) {
		this.bseData = bseData;
	}

	public MarketMoversBSE_NSE_Data getNseData() {
		return nseData;
	}

	public void setNseData(MarketMoversBSE_NSE_Data nseData) {
		this.nseData = nseData;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
}
