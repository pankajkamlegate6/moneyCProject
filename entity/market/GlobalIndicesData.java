package com.moneycontrol.handheld.entity.market;

import java.io.Serializable;

public class GlobalIndicesData implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id, stkexchg, lastPrice, change, percentChange, direction, lastUpdated, flag;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStkexchg() {
		return stkexchg;
	}

	public void setStkexchg(String stkexchg) {
		this.stkexchg = stkexchg;
	}

	public String getLastprice() {
		return lastPrice;
	}

	public void setLastprice(String lastprice) {
		this.lastPrice = lastprice;
	}

	public String getChange() {
		return change;
	}

	public void setChange(String change) {
		this.change = change;
	}

	public String getPercentchange() {
		return percentChange;
	}

	public void setPercentchange(String percentchange) {
		this.percentChange = percentchange;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getLastupdated() {
		return lastUpdated;
	}

	public void setLastupdated(String lastupdated) {
		this.lastUpdated = lastupdated;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
}
