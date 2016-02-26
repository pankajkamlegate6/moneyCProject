package com.moneycontrol.handheld.entity.market;

import java.io.Serializable;

public class MarketCorporateAgmEgmData implements Serializable {

	private String announcementDate;
	private String purpose;
	private String agmDate;
	private String bookClosureSDate;
	private String bookClosureEDate;
	private String remark;
	
	public String getAnnouncementDate() {
		return announcementDate;
	}
	public void setAnnouncementDate(String announcementDate) {
		this.announcementDate = announcementDate;
	}
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	public String getAgmDate() {
		return agmDate;
	}
	public void setAgmDate(String agmDate) {
		this.agmDate = agmDate;
	}
	public String getBookClosureSDate() {
		return bookClosureSDate;
	}
	public void setBookClosureSDate(String bookClosureSDate) {
		this.bookClosureSDate = bookClosureSDate;
	}
	public String getBookClosureEDate() {
		return bookClosureEDate;
	}
	public void setBookClosureEDate(String bookClosureEDate) {
		this.bookClosureEDate = bookClosureEDate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
