package com.moneycontrol.handheld.entity.market;

import java.io.Serializable;
import java.util.ArrayList;

import com.moneycontrol.handheld.entity.home.FieldData;

public class MarketFutureData implements Serializable {

	private String shortName, fullName;
	private ArrayList<MarketFutureItemData> marketFutureItemData;
	private ArrayList<FieldData> urlList;
	private String topic_id;
	private int refreshRate = -1;
	private boolean autorefreshFlag = false;

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

	public String getTopic_id() {
		return topic_id;
	}

	public void setTopic_id(String topic_id) {
		this.topic_id = topic_id;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public ArrayList<MarketFutureItemData> getMarketFutureItemData() {
		return marketFutureItemData;
	}

	public void setMarketFutureItemData(
			ArrayList<MarketFutureItemData> marketFutureItemData) {
		this.marketFutureItemData = marketFutureItemData;
	}

	public ArrayList<FieldData> getUrlList() {
		return urlList;
	}

	public void setUrlList(ArrayList<FieldData> urlList) {
		this.urlList = urlList;
	}

}
