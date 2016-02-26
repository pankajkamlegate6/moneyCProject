package com.moneycontrol.handheld.entity.market;

import java.io.Serializable;

public class MarketFutureItemInnerData implements Serializable {
	private String bidPrice;
	private String bidQty;
	private String offerPrice;
	private String offerQty;
	private String openInterest;
	private String openInterestChange;
	private String openInterestPercentChange;
	private String avgPrice;
	private String openPrice;
	private String highPrice;
	private String lowPrice;
	private String prevClose;
	private String marketLot;
	private String turnover;
	private String contracts;
	private String rolloverPercent;
	private String rolloverCost;
	private String openInterestPCR;
	private String prevOpenInterestPCR;
	private String topicId;

	public String getTopicId() {
		return topicId;
	}

	public void setTopicId(String topicId) {
		this.topicId = topicId;
	}

	public String getRolloverPercent() {
		return rolloverPercent;
	}

	public void setRolloverPercent(String rolloverPercent) {
		this.rolloverPercent = rolloverPercent;
	}

	public String getRolloverCost() {
		return rolloverCost;
	}

	public void setRolloverCost(String rolloverCost) {
		this.rolloverCost = rolloverCost;
	}

	public String getBidPrice() {
		return bidPrice;
	}

	public void setBidPrice(String bidPrice) {
		this.bidPrice = bidPrice;
	}

	public String getBidQty() {
		return bidQty;
	}

	public void setBidQty(String bidQty) {
		this.bidQty = bidQty;
	}

	public String getOfferPrice() {
		return offerPrice;
	}

	public void setOfferPrice(String offerPrice) {
		this.offerPrice = offerPrice;
	}

	public String getOfferQty() {
		return offerQty;
	}

	public void setOfferQty(String offerQty) {
		this.offerQty = offerQty;
	}

	public String getOpenInterest() {
		return openInterest;
	}

	public void setOpenInterest(String openInterest) {
		this.openInterest = openInterest;
	}

	public String getOpenInterestChange() {
		return openInterestChange;
	}

	public void setOpenInterestChange(String openInterestChange) {
		this.openInterestChange = openInterestChange;
	}

	public String getOpenInterestPercentChange() {
		return openInterestPercentChange;
	}

	public void setOpenInterestPercentChange(String openInterestPercentChange) {
		this.openInterestPercentChange = openInterestPercentChange;
	}

	public String getAvgPrice() {
		return avgPrice;
	}

	public void setAvgPrice(String avgPrice) {
		this.avgPrice = avgPrice;
	}

	public String getOpenPrice() {
		return openPrice;
	}

	public void setOpenPrice(String openPrice) {
		this.openPrice = openPrice;
	}

	public String getHighPrice() {
		return highPrice;
	}

	public void setHighPrice(String highPrice) {
		this.highPrice = highPrice;
	}

	public String getLowPrice() {
		return lowPrice;
	}

	public void setLowPrice(String lowPrice) {
		this.lowPrice = lowPrice;
	}

	public String getPrevClose() {
		return prevClose;
	}

	public void setPrevClose(String prevClose) {
		this.prevClose = prevClose;
	}

	public String getMarketLot() {
		return marketLot;
	}

	public void setMarketLot(String marketLot) {
		this.marketLot = marketLot;
	}

	public String getTurnover() {
		return turnover;
	}

	public void setTurnover(String turnover) {
		this.turnover = turnover;
	}

	public String getContracts() {
		return contracts;
	}

	public void setContracts(String contracts) {
		this.contracts = contracts;
	}

	public String getOpenInterestPCR() {
		return openInterestPCR;
	}

	public void setOpenInterestPCR(String openInterestPCR) {
		this.openInterestPCR = openInterestPCR;
	}

	public String getPrevOpenInterestPCR() {
		return prevOpenInterestPCR;
	}

	public void setPrevOpenInterestPCR(String prevOpenInterestPCR) {
		this.prevOpenInterestPCR = prevOpenInterestPCR;
	}
}
