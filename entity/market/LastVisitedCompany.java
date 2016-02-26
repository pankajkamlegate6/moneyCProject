package com.moneycontrol.handheld.entity.market;

import java.io.Serializable;

@SuppressWarnings("serial")
public class LastVisitedCompany implements Serializable {
	private String companyID = null, companyName = null, companyTopicId = null;
	private String last_price;
	private String change = "10";
	private String percent_change = "10%";

	public String getLast_price() {
		return last_price;
	}

	public void setLast_price(String last_price) {
		this.last_price = last_price;
	}

	public String getChange() {
		return change;
	}

	public void setChange(String change) {
		this.change = change;
	}

	public String getPercent_change() {
		return percent_change;
	}

	public void setPercent_change(String percent_change) {
		this.percent_change = percent_change;
	}

	public void setCompanyID(String companyID) {
		this.companyID = companyID;
	}

	public String getCompanyID() {
		return companyID;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyTopicId(String companyTopicId) {
		this.companyTopicId = companyTopicId;
	}

	public String getCompanyTopicId() {
		return companyTopicId;
	}

}
