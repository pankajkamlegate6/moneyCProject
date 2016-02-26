package com.moneycontrol.handheld.entity.market;

import java.io.Serializable;

public class MarketMoversActiveWeek52Data implements Serializable{
	private String lastupdated, error;
	private MarketMoversActiveWeek52NSE_BSE_Data bseData, nseData;

	public String getLastupdated() {
		return lastupdated;
	}

	public void setLastupdated(String lastupdated) {
		this.lastupdated = lastupdated;
	}

	public MarketMoversActiveWeek52NSE_BSE_Data getBseData() {
		return bseData;
	}

	public void setBseData(MarketMoversActiveWeek52NSE_BSE_Data bseData) {
		this.bseData = bseData;
	}

	public MarketMoversActiveWeek52NSE_BSE_Data getNseData() {
		return nseData;
	}

	public void setNseData(MarketMoversActiveWeek52NSE_BSE_Data nseData) {
		this.nseData = nseData;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
}
