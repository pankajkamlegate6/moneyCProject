package com.moneycontrol.handheld.entity.market;

import java.io.Serializable;
import java.util.ArrayList;

import com.moneycontrol.handheld.entity.home.FieldData;
import com.moneycontrol.handheld.entity.messages.DropDownBean;

public class GraphData implements Serializable {
	private String name, date_time, current_close, prev_close, open, low, high,
			change, percentchange, direction, status;

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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	private ArrayList<GraphValueData> values;
	private ArrayList<FieldData> urlList;
	private ArrayList<FieldData> typelist;

	public void setTypelist(ArrayList<FieldData> typelist) {
		this.typelist = typelist;
	}

	public ArrayList<FieldData> getTypelist() {
		return typelist;
	}

	public ArrayList<FieldData> getUrlList() {
		return urlList;
	}

	public void setUrlList(ArrayList<FieldData> urlList) {
		this.urlList = urlList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDate_time() {
		return date_time;
	}

	public void setDate_time(String date_time) {
		this.date_time = date_time;
	}

	public String getCurrent_close() {
		return current_close;
	}

	public void setCurrent_close(String current_close) {
		this.current_close = current_close;
	}

	public String getPrev_close() {
		return prev_close;
	}

	public void setPrev_close(String prev_close) {
		this.prev_close = prev_close;
	}

	public ArrayList<GraphValueData> getValues() {
		return values;
	}

	public void setValues(ArrayList<GraphValueData> values) {
		this.values = values;
	}

	public void setOpen(String open) {
		this.open = open;
	}

	public String getOpen() {
		return open;
	}

	public void setLow(String low) {
		this.low = low;
	}

	public String getLow() {
		return low;
	}

	public void setHigh(String high) {
		this.high = high;
	}

	public String getHigh() {
		return high;
	}

	public void setChange(String change) {
		this.change = change;
	}

	public String getChange() {
		return change;
	}

	public void setPercentchange(String percentchange) {
		this.percentchange = percentchange;
	}

	public String getPercentchange() {
		return percentchange;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getDirection() {
		return direction;
	}
}
