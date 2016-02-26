package com.moneycontrol.handheld.entity.market;

import java.io.Serializable;
import java.util.ArrayList;

public class MarketCorporateBasicData implements Serializable {
	private String recentFlag = "";
	private ArrayList<MarketCorporateRecentData> recentList;
	private ArrayList<MarketCorporateHeaderData> headerList;
	
	public String getRecentFlag() {
		return recentFlag;
	}
	public void setRecentFlag(String recentFlag) {
		this.recentFlag = recentFlag;
	}
	public ArrayList<MarketCorporateRecentData> getRecentList() {
		return recentList;
	}
	public void setRecentList(ArrayList<MarketCorporateRecentData> recentList) {
		this.recentList = recentList;
	}
	public ArrayList<MarketCorporateHeaderData> getHeaderList() {
		return headerList;
	}
	public void setHeaderList(ArrayList<MarketCorporateHeaderData> headerList) {
		this.headerList = headerList;
	}
}
