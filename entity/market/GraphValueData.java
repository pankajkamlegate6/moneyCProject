package com.moneycontrol.handheld.entity.market;

import java.io.Serializable;

public class GraphValueData implements Serializable {
	private String time, value, open, high, low, volume, dayValue;

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getOpen() {
		return open;
	}

	public void setOpen(String open) {
		this.open = open;
	}

	public String getHigh() {
		return high;
	}

	public void setHigh(String high) {
		this.high = high;
	}

	public String getLow() {
		return low;
	}

	public void setLow(String low) {
		this.low = low;
	}

	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	public void setDayValue(String dayValue) {
		this.dayValue = dayValue;
	}

	public String getDayValue() {
		return dayValue;
	}
}
