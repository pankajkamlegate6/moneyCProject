package com.moneycontrol.handheld.entity.market;

import java.io.Serializable;

public class MarketCorporateDividendsData implements Serializable {

	private String announcementDate;
	private String effectiveDate;
	private String dividendType;
	private String dividendPer;
	private String remarks;
	private String message;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getAnnouncementDate() {
		return announcementDate;
	}
	public void setAnnouncementDate(String announcementDate) {
		this.announcementDate = announcementDate;
	}
	public String getEffectiveDate() {
		return effectiveDate;
	}
	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	public String getDividendType() {
		return dividendType;
	}
	public void setDividendType(String dividendType) {
		this.dividendType = dividendType;
	}
	public String getDividendPer() {
		return dividendPer;
	}
	public void setDividendPer(String dividendPer) {
		this.dividendPer = dividendPer;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}
