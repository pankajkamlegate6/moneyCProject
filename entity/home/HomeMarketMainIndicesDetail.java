package com.moneycontrol.handheld.entity.home;

import java.io.Serializable;

public class HomeMarketMainIndicesDetail implements Serializable{
	private String stkexchg, ind_id, lastprice, change, percentchange, direction, open, high, low, prevclose, yearlyhigh, yearlylow, flag, lastupdated;

	public String getStkexchg() {
		return stkexchg;
	}

	public void setStkexchg(String stkexchg) {
		this.stkexchg = stkexchg;
	}

	public String getInd_id() {
		return ind_id;
	}

	public void setInd_id(String ind_id) {
		this.ind_id = ind_id;
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

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getOpen() {
		return open;
	}

	public void setOpen(String open) {
		this.open = open;
	}

	public String getHigh() {
		return high;
	}

	public void setHigh(String high) {
		this.high = high;
	}

	public String getLow() {
		return low;
	}

	public void setLow(String low) {
		this.low = low;
	}

	public String getPrevclose() {
		return prevclose;
	}

	public void setPrevclose(String prevclose) {
		this.prevclose = prevclose;
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

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getLastupdated() {
		return lastupdated;
	}

	public void setLastupdated(String lastupdated) {
		this.lastupdated = lastupdated;
	}
}
