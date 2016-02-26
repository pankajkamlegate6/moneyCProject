package com.moneycontrol.handheld.currency.entity;

import com.moneycontrol.handheld.api.AppBeanParacable;

public class CurrencyTable implements AppBeanParacable {

	String source;

	String compare_to;

	double lastprice;

	String image;

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getCompare_to() {
		return compare_to;
	}

	public void setCompare_to(String compare_to) {
		this.compare_to = compare_to;
	}

	public double getLastprice() {
		return lastprice;
	}

	public void setLastprice(double lastprice) {
		this.lastprice = lastprice;
	}

}
