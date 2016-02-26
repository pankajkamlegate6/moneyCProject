package com.moneycontrol.handheld.currency.entity;

import java.util.ArrayList;
import java.util.HashMap;

import com.moneycontrol.handheld.api.AppBeanParacable;
import com.moneycontrol.handheld.entity.messages.DropDownBean;
import com.moneycontrol.handheld.util.Constantlibs;

public class CurrenciesData implements AppBeanParacable {

	Refreshdata refreshdata;

	private ArrayList<CurrenciesCarouselData> list;

	private ArrayList<DropDownBean> tabs = new ArrayList<DropDownBean>();

	public ArrayList<DropDownBean> getTabs() {
		return tabs;
	}

	public void setTabs(ArrayList<DropDownBean> tabs) {
		this.tabs = tabs;
	}

	private ArrayList<CurrencyTable> table = new ArrayList<CurrencyTable>();;

	public HashMap<String, Double> getConvertedData() {
		countrylist.clear();
		String countryName = "";
		HashMap<String, Double> list = new HashMap<String, Double>();
		for (int i = 0; i < table.size(); i++) {
			CurrencyCountry currency = new CurrencyCountry();
			currency.setCountryName(table.get(i).getSource());
			currency.setUrlimage(table.get(i).getImage());
			if (!countryName.equalsIgnoreCase(table.get(i).getSource())) {
				countryName = table.get(i).getSource();
				countrylist.add(currency);
			}

			list.put(String.format(Constantlibs.CURRENT_CONVERTER_PATTERN,
					table.get(i).getSource(), table.get(i).getCompare_to()),
					table.get(i).getLastprice());
		}
		return list;
	}

	ArrayList<CurrencyCountry> countrylist = new ArrayList<CurrenciesData.CurrencyCountry>();

	public ArrayList<CurrencyCountry> getCountrylist() {
		return countrylist;
	}

	public static class Refreshdata implements AppBeanParacable {
		public boolean flag;

		String rate;

		public void setFlag(boolean flag) {
			this.flag = flag;
		}

		public void setRate(String rate) {
			this.rate = rate;
		}

		public String getRate() {
			return rate;
		}

		public boolean isFlag() {
			return flag;
		}
	}

	public Refreshdata getRefreshdata() {
		return refreshdata;
	}

	public void setRefreshdata(Refreshdata refreshdata) {
		this.refreshdata = refreshdata;
	}

	public ArrayList<CurrenciesCarouselData> getList() {
		return list;
	}

	public void setList(ArrayList<CurrenciesCarouselData> list) {
		this.list = list;
	}

	public ArrayList<CurrencyTable> getTable() {
		return table;
	}

	public void setTable(ArrayList<CurrencyTable> table) {
		this.table = table;
	}

	public class CurrencyCountry implements AppBeanParacable {
		String urlimage;
		String countryName;

		public void setCountryName(String countryName) {
			this.countryName = countryName;
		}

		public void setUrlimage(String urlimage) {
			this.urlimage = urlimage;
		}

		public String getCountryName() {
			return countryName;
		}

		public String getUrlimage() {
			return urlimage;
		}
	}

}
