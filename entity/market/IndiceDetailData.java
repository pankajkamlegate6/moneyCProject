package com.moneycontrol.handheld.entity.market;

import java.io.Serializable;
import java.util.ArrayList;

import com.moneycontrol.handheld.entity.home.FieldData;

public class IndiceDetailData implements Serializable {

	private IndiceOverviewData overviewData = null;
	private ArrayList<FieldData> tabsList = null;
	private String graphUrl;
	private int refreshRate = -1;
	private boolean autorefreshFlag = false;

	public String getGraphUrl() {
		return graphUrl;
	}

	public void setGraphUrl(String graphUrl) {
		this.graphUrl = graphUrl;
	}

	public IndiceOverviewData getOverviewData() {
		return overviewData;
	}

	public void setOverviewData(IndiceOverviewData overviewData) {
		this.overviewData = overviewData;
	}

	public ArrayList<FieldData> getTabsList() {
		return tabsList;
	}

	public void setTabsList(ArrayList<FieldData> tabsList) {
		this.tabsList = tabsList;
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
}
