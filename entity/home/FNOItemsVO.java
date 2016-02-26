package com.moneycontrol.handheld.entity.home;

import java.io.Serializable;
import java.util.ArrayList;

public class FNOItemsVO implements Serializable {

	private ArrayList<FandOActionBean> list1 = new ArrayList<FandOActionBean>();;
	private ArrayList<FieldData> list;
	private ArrayList<FieldData> marketTpyelist;
	private ArrayList<FieldData> innertabs;
	private int refreshRate = -1;
	private boolean autorefreshFlag = false;
	private int listrefreshRate = -1;
	private boolean listAutorefreshFlag = false;
	private ArrayList<FieldData> sortTabs;

	public int getListrefreshRate() {
		return listrefreshRate;
	}

	public ArrayList<FieldData> getSortTabs() {
		return sortTabs;
	}

	public void setSortTabs(ArrayList<FieldData> sortTabs) {
		this.sortTabs = sortTabs;
	}

	public void setListrefreshRate(int listrefreshRate) {
		this.listrefreshRate = listrefreshRate;
	}

	public boolean isListAutorefreshFlag() {
		return listAutorefreshFlag;
	}

	public void setListAutorefreshFlag(boolean listAutorefreshFlag) {
		this.listAutorefreshFlag = listAutorefreshFlag;
	}

	public int getRefreshRate() {
		return refreshRate;
	}

	public void setRefreshRate(int refreshRate) {
		this.refreshRate = refreshRate;
	}

	public boolean isAutorefreshFlag() {
		return autorefreshFlag;
	}

	public void setAutorefreshFlag(boolean autorefreshFlag) {
		this.autorefreshFlag = autorefreshFlag;
	}

	public ArrayList<FieldData> getInnertabs() {
		return innertabs;
	}

	public void setInnertabs(ArrayList<FieldData> innertabs) {
		this.innertabs = innertabs;
	}

	public ArrayList<FieldData> getMarketTpyelist() {
		return marketTpyelist;
	}

	public void setMarketTpyelist(ArrayList<FieldData> marketTpyelist) {
		this.marketTpyelist = marketTpyelist;
	}

	public ArrayList<FandOActionBean> getList1() {
		return list1;
	}

	public void setList1(ArrayList<FandOActionBean> list1) {
		this.list1 = list1;
	}

	public ArrayList<FieldData> getList() {
		return list;
	}

	public void setList(ArrayList<FieldData> list) {
		this.list = list;
	}

	public void setSortTab(ArrayList<FieldData> innertabsforSort) {
		// TODO Auto-generated method stub

	}

}
