package com.moneycontrol.handheld.entity.market;

import java.io.Serializable;

public class MarketMoversItemData implements Serializable {
	private String id, shortname, lastvalue, change, percentchange, direction, high, low, yearly_high, yearly_low, value, volume, topicid, error;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getShortname() {
		return shortname;
	}

	public void setShortname(String shortname) {
		this.shortname = shortname;
	}

	public String getLastvalue() {
		return lastvalue;
	}

	public void setLastvalue(String lastvalue) {
		this.lastvalue = lastvalue;
	}

	public String getChange() {
		return change;
	}

	public void setChange(String change) {
		this.change = change;
	}

	public String getPercentchange() {
		return percentchange;
	}

	public void setPercentchange(String percentchange) {
		this.percentchange = percentchange;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
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

	public String getYearly_high() {
		return yearly_high;
	}

	public void setYearly_high(String yearly_high) {
		this.yearly_high = yearly_high;
	}

	public String getYearly_low() {
		return yearly_low;
	}

	public void setYearly_low(String yearly_low) {
		this.yearly_low = yearly_low;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	public String getVolume() {
		return volume;
	}

	public String getTopicid() {
		return topicid;
	}

	public void setTopicid(String topicid) {
		this.topicid = topicid;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getError() {
		return error;
	}
}
