package com.moneycontrol.handheld.entity.home;

public class Intersitial {
	private String pv, section;
	private int showAfter = -1, status = 0;

	public String getPv() {
		return pv;
	}

	public void setPv(String pv) {
		this.pv = pv;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public int getShowAfter() {
		return showAfter;
	}

	public void setShowAfter(int showAfter) {
		this.showAfter = showAfter;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
