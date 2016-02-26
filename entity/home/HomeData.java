package com.moneycontrol.handheld.entity.home;

import java.io.Serializable;

public class HomeData implements Serializable {

	private String id = "";
	private String shortName = "";
	private String lastValue = "";
	private String percentChange = "";
	private String volume = "";
	private String lastUpdated = "";
	private String flag = "";
	private Integer category = -1;
	private String direction = "";
	private boolean isHeading = false;
	private String change = "";
	private String heading = "";
	private String section = "";
	private String autono = "-1";
	private boolean isportfolio = false;
	private String netWorth;
	private String exchange;
	private String expDate;
	private String link_flag;
	
	
	public String getLink_flag() {
		return link_flag;
	}

	public void setLink_flag(String link_flag) {
		this.link_flag = link_flag;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public String getExchange() {
		return exchange;
	}

	public void setExchange(String exchange) {
		this.exchange = exchange;
	}

	public String getExpDate() {
		return expDate;
	}

	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}

	public String getNetWorth() {
		return netWorth;
	}

	public void setNetWorth(String netWorth) {
		this.netWorth = netWorth;
	}

	public boolean isIsportfolio() {
		return isportfolio;
	}

	public void setIsportfolio(boolean isportfolio) {
		this.isportfolio = isportfolio;
	}

	public String getAutono() {
		return autono;
	}

	public void setAutono(String autono) {
		this.autono = autono;
	}

	public String getHeading() {
		return heading;
	}

	public void setHeading(String heading) {
		this.heading = heading;
	}

	public String getChange() {
		return change;
	}

	public void setChange(String change) {
		this.change = change;
	}

	public String getid() {
		return id;
	}

	public String getShortname() {
		return shortName;
	}

	public String getLastValue() {
		return lastValue;
	}

	public String getPercentChange() {
		return percentChange;

	}

	public String getVolume() {
		return volume;
	}

	public String getLastUpdated() {
		return lastUpdated;
	}

	public String getFlag() {
		return flag;
	}

	public String getDirection() {
		return direction;
	}

	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public void setLastUpdated(String lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	public void setShortname(String shortName) {
		this.shortName = shortName;
	}

	public void setLastValue(String lastValue) {
		this.lastValue = lastValue;
	}

	public void setPercentChange(String percentChange) {
		this.percentChange = percentChange;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setHeading(boolean isHeading) {
		this.isHeading = isHeading;
	}

	public boolean isHeading() {
		return isHeading;
	}

}
