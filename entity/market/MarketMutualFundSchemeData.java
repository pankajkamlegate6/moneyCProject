package com.moneycontrol.handheld.entity.market;

import java.util.ArrayList;

public class MarketMutualFundSchemeData {
	private String schemename, lastprice, change, percentchange, schemedate, rating, direction;
	private MarketMutualFundSchemeInvestInfoData investinfo;
	private MarketMutualFundSchemeAbsreturnsData absreturns;
	private ArrayList<MarketMutualFundSchemeBestpicksData> bestpicks;

	public String getSchemename() {
		return schemename;
	}

	public void setSchemename(String schemename) {
		this.schemename = schemename;
	}

	public String getLastprice() {
		return lastprice;
	}

	public void setLastprice(String lastprice) {
		this.lastprice = lastprice;
	}

	public String getChange() {
		return change;
	}

	public void setChange(String change) {
		this.change = change;
	}

	public String getPercentchange() {
		return percentchange;
	}

	public void setPercentchange(String percentchange) {
		this.percentchange = percentchange;
	}

	public String getSchemedate() {
		return schemedate;
	}

	public void setSchemedate(String schemedate) {
		this.schemedate = schemedate;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public MarketMutualFundSchemeInvestInfoData getInvestinfo() {
		return investinfo;
	}

	public void setInvestinfo(MarketMutualFundSchemeInvestInfoData investinfo) {
		this.investinfo = investinfo;
	}

	public MarketMutualFundSchemeAbsreturnsData getAbsreturns() {
		return absreturns;
	}

	public void setAbsreturns(MarketMutualFundSchemeAbsreturnsData absreturns) {
		this.absreturns = absreturns;
	}

	public ArrayList<MarketMutualFundSchemeBestpicksData> getBestpicks() {
		return bestpicks;
	}

	public void setBestpicks(ArrayList<MarketMutualFundSchemeBestpicksData> bestpicks) {
		this.bestpicks = bestpicks;
	}

}
