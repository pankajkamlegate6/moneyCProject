package com.moneycontrol.handheld.chart.entity;

import com.moneycontrol.handheld.api.AppBeanParacable;

public class CompareEntity implements AppBeanParacable {

	String id;
	String shortname;
	String type;

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setShortname(String shortname) {
		this.shortname = shortname;
	}

	public String getShortname() {
		return shortname;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

}
