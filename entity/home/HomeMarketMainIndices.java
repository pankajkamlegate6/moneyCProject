package com.moneycontrol.handheld.entity.home;

import java.io.Serializable;

public class HomeMarketMainIndices implements Serializable{
	private String stkexchg, ind_id, lastprice, change, percentchange, direction, lastupdated, flag,link_flag;

	public String getLink_flag() {
		return link_flag;
	}

	public void setLink_flag(String link_flasg) {
		this.link_flag = link_flasg;
	}

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

	public String getLastupdated() {
		return lastupdated;
	}

	public void setLastupdated(String lastupdated) {
		this.lastupdated = lastupdated;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
}
