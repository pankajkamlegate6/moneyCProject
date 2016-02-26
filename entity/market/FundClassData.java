package com.moneycontrol.handheld.entity.market;

import java.util.ArrayList;

public class FundClassData {
	private String fc_id, fc_desc;
	private ArrayList<FundClassItemData> item;

	public String getFc_id() {
		return fc_id;
	}

	public void setFc_id(String fc_id) {
		this.fc_id = fc_id;
	}

	public String getFc_desc() {
		return fc_desc;
	}

	public void setFc_desc(String fc_desc) {
		this.fc_desc = fc_desc;
	}

	public ArrayList<FundClassItemData> getItem() {
		return item;
	}

	public void setItem(ArrayList<FundClassItemData> item) {
		this.item = item;
	}

}
