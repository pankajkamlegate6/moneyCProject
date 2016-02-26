package com.moneycontrol.handheld.entity.home;

import java.io.Serializable;

public class InfoAlertData implements Serializable {

	private int status = 0, pageviews = 0;
	private String title = "", message = "";
	private String count = "", autonumber = "",language="";

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getAutonumber() {
		return autonumber;
	}

	public void setAutonumber(String autonumber) {
		this.autonumber = autonumber;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getStatus() {
		return status;
	}

	public void setPageviews(int pageviews) {
		this.pageviews = pageviews;
	}

	public int getPageviews() {
		return pageviews;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
