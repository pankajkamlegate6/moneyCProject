package com.moneycontrol.handheld.entity.market;

public class DividendsData {
	private String announcement_date, effective_date, dividend_type, dividend_per, remarks;

	public String getAnnouncement_date() {
		return announcement_date;
	}

	public void setAnnouncement_date(String announcement_date) {
		this.announcement_date = announcement_date;
	}

	public String getEffective_date() {
		return effective_date;
	}

	public void setEffective_date(String effective_date) {
		this.effective_date = effective_date;
	}

	public String getDividend_type() {
		return dividend_type;
	}

	public void setDividend_type(String dividend_type) {
		this.dividend_type = dividend_type;
	}

	public String getDividend_per() {
		return dividend_per;
	}

	public void setDividend_per(String dividend_per) {
		this.dividend_per = dividend_per;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}
