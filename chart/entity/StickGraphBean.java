package com.moneycontrol.handheld.chart.entity;

import java.util.ArrayList;

import com.moneycontrol.handheld.api.AppBeanParacable;
import com.moneycontrol.handheld.currency.entity.CurrenciesData.Refreshdata;
import com.moneycontrol.handheld.entity.messages.DropDownBean;

public class StickGraphBean implements AppBeanParacable {
	Refreshdata refreshdata;

	String message;

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	StickChartEntity graph;
	private ArrayList<DropDownBean> tabs = new ArrayList<DropDownBean>();

	private ArrayList<DropDownBean> dropdown = new ArrayList<DropDownBean>();

	public void setDropdown(ArrayList<DropDownBean> dropdown) {
		this.dropdown = dropdown;
	}

	public ArrayList<DropDownBean> getDropdown() {
		return dropdown;
	}

	public void setTabs(ArrayList<DropDownBean> tabs) {
		this.tabs = tabs;
	}

	public ArrayList<DropDownBean> getTabs() {
		return tabs;
	}

	public StickChartEntity getGraph() {
		return graph;
	}

	public void setGraph(StickChartEntity graph) {
		this.graph = graph;
	}

	public void setRefreshdata(Refreshdata refreshdata) {
		this.refreshdata = refreshdata;
	}

	public Refreshdata getRefreshdata() {
		return refreshdata;
	}

}
