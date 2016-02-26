package com.moneycontrol.handheld.entity.market;

import java.io.Serializable;
import java.util.ArrayList;

import com.moneycontrol.handheld.entity.home.FieldData;

public class MarketFutureItemData implements Serializable {

	private String fnoSymbol;
	private String fnoInstrument;
	private String fnoExp;
	private String expDate;
	private String entDate;
	private String lastPrice;
	private String change;
	private String percentChange;
	private String volume;
	private MarketFutureItemInnerData marketFutureItemInnerData;
	private ArrayList<FieldData> urlList;
	private String expiry_date_d;

	public String getExpiry_date_d() {
		return expiry_date_d;
	}

	public void setExpiry_date_d(String expiry_date_d) {
		this.expiry_date_d = expiry_date_d;
	}

//	public String getExpiry_date() {
//		return expiry_date;
//	}
//
//	public void setExpiry_date(String expiry_date) {
//		this.expiry_date = expiry_date;
//	}

	public String getFnoSymbol() {
		return fnoSymbol;
	}

	public void setFnoSymbol(String fnoSymbol) {
		this.fnoSymbol = fnoSymbol;
	}

	public String getFnoInstrument() {
		return fnoInstrument;
	}

	public void setFnoInstrument(String fnoInstrument) {
		this.fnoInstrument = fnoInstrument;
	}

	public ArrayList<FieldData> getUrlList() {
		return urlList;
	}

	public void setUrlList(ArrayList<FieldData> urlList) {
		this.urlList = urlList;
	}

	public String getFnoExp() {
		return fnoExp;
	}

	public void setFnoExp(String fnoExp) {
		this.fnoExp = fnoExp;
	}

	public String getExpDate() {
		return expDate;
	}

	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}

	public String getEntDate() {
		return entDate;
	}

	public void setEntDate(String entDate) {
		this.entDate = entDate;
	}

	public String getLastPrice() {
		return lastPrice;
	}

	public void setLastPrice(String lastPrice) {
		this.lastPrice = lastPrice;
	}

	public String getChange() {
		return change;
	}

	public void setChange(String change) {
		this.change = change;
	}

	public String getPercentChange() {
		return percentChange;
	}

	public void setPercentChange(String percentChange) {
		this.percentChange = percentChange;
	}

	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	public MarketFutureItemInnerData getMarketFutureDetailData() {
		return marketFutureItemInnerData;
	}

	public void setMarketFutureDetailData(
			MarketFutureItemInnerData marketFutureDetailData) {
		this.marketFutureItemInnerData = marketFutureDetailData;
	}
}
