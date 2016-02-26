package com.moneycontrol.handheld.entity.market;

import java.io.Serializable;

public class CommoditiesCarouselData implements Serializable {
	private String id, name, expDate, url, lastprice, change, percentchange,
			direction, open, high, low, volume, lastupdate, exchange, topicId,
			expDate_d;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getExpDate_d() {
		return expDate_d;
	}

	public void setExpDate_d(String expDate_d) {
		this.expDate_d = expDate_d;
	}

	public String getTopicId() {
		return topicId;
	}

	public void setTopicId(String topicId) {
		this.topicId = topicId;
	}

	public void setExchange(String exchange) {
		this.exchange = exchange;
	}

	public String getExchange() {
		return exchange;
	}

	public String getExpDate() {
		return expDate;
	}

	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getLastprice() {
		return lastprice;
	}

	public void setLastprice(String lastprice) {
		this.lastprice = lastprice;
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

	public String getLastupdate() {
		return lastupdate;
	}

	public void setLastupdate(String lastupdate) {
		this.lastupdate = lastupdate;
	}

}
