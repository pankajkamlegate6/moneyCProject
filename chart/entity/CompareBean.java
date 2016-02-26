package com.moneycontrol.handheld.chart.entity;

import java.util.ArrayList;

import com.moneycontrol.handheld.api.AppBeanParacable;

public class CompareBean implements AppBeanParacable {

	ArrayList<CompareEntity> list = new ArrayList<CompareEntity>();

	public void setList(ArrayList<CompareEntity> list) {
		this.list = list;
	}

	public ArrayList<CompareEntity> getList() {
		return list;
	}
}
