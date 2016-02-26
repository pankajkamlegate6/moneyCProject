package com.moneycontrol.handheld.entity.market;

import java.io.Serializable;

public class MarketCompanyBasicItemData implements Serializable {
	private String id, security_code, fullname, shortname, lastvalue, change,
			percentchange, sector, lastupdate, dayhigh, daylow, yearlyhigh,
			yearlylow, todaysopen, volume, yesterdaysclose, mktcap, pe = "",
			pc, industryPE, bidprice, bidqty, offerprice, offerqty, topicid,
			direction, bookValue, priceBook, deliverables, dividend, divYield,
			marketLot, faceValue, eps, trade_msg;

	public String getPc() {
		return pc;
	}

	public void setPc(String pc) {
		this.pc = pc;
	}

	public String getIndustryPE() {
		return industryPE;
	}

	public void setIndustryPE(String industryPE) {
		this.industryPE = industryPE;
	}

	public String getTrade_msg() {
		return trade_msg;
	}

	public void setTrade_msg(String trade_msg) {
		this.trade_msg = trade_msg;
	}

	public String getEps() {
		return eps;
	}

	public void setEps(String eps) {
		this.eps = eps;
	}

	public String getBookValue() {
		return bookValue;
	}

	public void setBookValue(String bookValue) {
		this.bookValue = bookValue;
	}

	public String getPriceBook() {
		return priceBook;
	}

	public void setPriceBook(String priceBook) {
		this.priceBook = priceBook;
	}

	public String getDeliverables() {
		return deliverables;
	}

	public void setDeliverables(String deliverables) {
		this.deliverables = deliverables;
	}

	public String getDividend() {
		return dividend;
	}

	public void setDividend(String dividend) {
		this.dividend = dividend;
	}

	public String getDivYield() {
		return divYield;
	}

	public void setDivYield(String divYield) {
		this.divYield = divYield;
	}

	public String getMarketLot() {
		return marketLot;
	}

	public void setMarketLot(String marketLot) {
		this.marketLot = marketLot;
	}

	public String getFaceValue() {
		return faceValue;
	}

	public void setFaceValue(String faceValue) {
		this.faceValue = faceValue;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSecurity_code() {
		return security_code;
	}

	public void setSecurity_code(String security_code) {
		this.security_code = security_code;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getShortname() {
		return shortname;
	}

	public void setShortname(String shortname) {
		this.shortname = shortname;
	}

	public String getLastvalue() {
		return lastvalue;
	}

	public void setLastvalue(String lastvalue) {
		this.lastvalue = lastvalue;
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

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public String getLastupdate() {
		return lastupdate;
	}

	public void setLastupdate(String lastupdate) {
		this.lastupdate = lastupdate;
	}

	public String getDayhigh() {
		return dayhigh;
	}

	public void setDayhigh(String dayhigh) {
		this.dayhigh = dayhigh;
	}

	public String getDaylow() {
		return daylow;
	}

	public void setDaylow(String daylow) {
		this.daylow = daylow;
	}

	public String getYearlyhigh() {
		return yearlyhigh;
	}

	public void setYearlyhigh(String yearlyhigh) {
		this.yearlyhigh = yearlyhigh;
	}

	public String getYearlylow() {
		return yearlylow;
	}

	public void setYearlylow(String yearlylow) {
		this.yearlylow = yearlylow;
	}

	public String getTodaysopen() {
		return todaysopen;
	}

	public void setTodaysopen(String todaysopen) {
		this.todaysopen = todaysopen;
	}

	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	public String getYesterdaysclose() {
		return yesterdaysclose;
	}

	public void setYesterdaysclose(String yesterdaysclose) {
		this.yesterdaysclose = yesterdaysclose;
	}

	public String getMktcap() {
		return mktcap;
	}

	public void setMktcap(String mktcap) {
		this.mktcap = mktcap;
	}

	public String getPe() {
		return pe;
	}

	public void setPe(String pe) {
		this.pe = pe;
	}

	public String getBidprice() {
		return bidprice;
	}

	public void setBidprice(String bidprice) {
		this.bidprice = bidprice;
	}

	public String getBidqty() {
		return bidqty;
	}

	public void setBidqty(String bidqty) {
		this.bidqty = bidqty;
	}

	public String getOfferprice() {
		return offerprice;
	}

	public void setOfferprice(String offerprice) {
		this.offerprice = offerprice;
	}

	public String getOfferqty() {
		return offerqty;
	}

	public void setOfferqty(String offerqty) {
		this.offerqty = offerqty;
	}

	public String getTopicid() {
		return topicid;
	}

	public void setTopicid(String topicid) {
		this.topicid = topicid;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}
}
