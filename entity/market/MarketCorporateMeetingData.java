package com.moneycontrol.handheld.entity.market;

import java.io.Serializable;

public class MarketCorporateMeetingData implements Serializable {

	private String date, remark;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
