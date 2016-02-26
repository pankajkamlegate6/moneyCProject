package com.moneycontrol.handheld.entity.home;

import java.util.ArrayList;

public class SplashData {
	private String id;
	private ArrayList<SplashItemData> item;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ArrayList<SplashItemData> getItem() {
		return item;
	}

	public void setItem(ArrayList<SplashItemData> item) {
		this.item = item;
	}

}
