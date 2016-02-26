package com.moneycontrol.handheld.chart.data;

import java.io.Serializable;

public class GraphDayEntity implements Serializable {
	String name, Url;

	public void setName(String name) {
		this.name = name;
	}

	public void setUrl(String url) {
		Url = url;
	}

	public String getName() {
		return name;
	}

	public String getUrl() {
		return Url;
	}

}
