package com.moneycontrol.handheld.entity.home;

import java.io.Serializable;

public class FandOActionBean implements Serializable {

	private String id, shortname, expdateValue, lastvalue, percentchange,
			direction, openintValue, openintperctValue, option_type,
			strike_price, exchange, symbolName, intrument_val, change_val,
			volume, msg_type, ind_id, expdateValue_d;
	private String ex_date;

	public String getEx_date() {
		return ex_date;
	}

	public String getExpdateValue_d() {
		return expdateValue_d;
	}

	public void setExpdateValue_d(String expdateValue_d) {
		this.expdateValue_d = expdateValue_d;
	}

	public String getMsg_type() {
		return msg_type;
	}

	public void setMsg_type(String msg_type) {
		this.msg_type = msg_type;
	}

	public String getInd_id() {
		return ind_id;
	}

	public void setInd_id(String ind_id) {
		this.ind_id = ind_id;
	}

	public void setEx_date(String ex_date) {
		this.ex_date = ex_date;
	}

	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	public String getChange_val() {
		return change_val;
	}

	public void setChange_val(String change_val) {
		this.change_val = change_val;
	}

	public String getIntrument_val() {
		return intrument_val;
	}

	public void setIntrument_val(String intrument_val) {
		this.intrument_val = intrument_val;
	}

	private boolean isSysmboll = false;

	public boolean isSysmboll() {
		return isSysmboll;
	}

	public void setSysmboll(boolean isSysmboll) {
		this.isSysmboll = isSysmboll;
	}

	public String getSymbolName() {
		return symbolName;
	}

	public void setSymbolName(String symbolName) {
		this.symbolName = symbolName;
	}

	public void setExchange(String exchange) {
		this.exchange = exchange;
	}

	public String getExchange() {
		return exchange;
	}

	private double oival, poival;

	public String getOption_type() {
		return option_type;
	}

	public void setOption_type(String option_type) {
		this.option_type = option_type;
	}

	public String getStrike_price() {
		return strike_price;
	}

	public void setStrike_price(String strike_price) {
		this.strike_price = strike_price;
	}

	public double getPoival() {
		return poival;
	}

	public void setPoival(double poival) {
		this.poival = poival;
	}

	public double getOival() {
		return oival;
	}

	public void setOival(double oival) {
		this.oival = oival;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getShortname() {
		return shortname;
	}

	public void setShortname(String shortname) {
		this.shortname = shortname;
	}

	public String getExpdateValue() {
		return expdateValue;
	}

	public void setExpdateValue(String expdateValue) {
		this.expdateValue = expdateValue;
	}

	public String getLastvalue() {
		return lastvalue;
	}

	public void setLastvalue(String lastvalue) {
		this.lastvalue = lastvalue;
	}

	public String getPercentchange() {
		return percentchange;
	}

	public void setPercentchange(String percentchange) {
		this.percentchange = percentchange;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getOpenintValue() {
		return openintValue;
	}

	public void setOpenintValue(String openintValue) {
		this.openintValue = openintValue;
	}

	public String getOpenintperctValue() {
		return openintperctValue;
	}

	public void setOpenintperctValue(String openintperctValue) {
		this.openintperctValue = openintperctValue;
	}

	public FandOActionBean() {

	}

}
