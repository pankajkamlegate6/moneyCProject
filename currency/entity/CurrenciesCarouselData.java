package com.moneycontrol.handheld.currency.entity;

import com.moneycontrol.handheld.api.AppBeanParacable;

public class CurrenciesCarouselData implements AppBeanParacable {

	private String name;

	String lastprice;

	String change;

	String perchange;

	String direction;

	String lastupdate;

	String flag;

	String large_flag;

	public String getLarge_flag() {
		return large_flag;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getPerchange() {
		return perchange;
	}

	public void setPerchange(String perchange) {
		this.perchange = perchange;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getLastupdate() {
		return lastupdate;
	}

	public void setLastupdate(String lastupdate) {
		this.lastupdate = lastupdate;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
}
