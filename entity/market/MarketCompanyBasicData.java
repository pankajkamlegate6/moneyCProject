package com.moneycontrol.handheld.entity.market;

import com.moneycontrol.handheld.entity.home.FieldData;

import java.io.Serializable;
import java.util.ArrayList;

public class MarketCompanyBasicData implements Serializable {
	private MarketCompanyBasicItemData bse, nse;
	private int refreshRate = -1;
	private boolean autorefreshFlag = false;
	private ArrayList<FieldData> urlList;
	private String graphUrl, movingAvgUrl;
	private ArrayList<StockAlertVO> stockAlert;

	public ArrayList<StockAlertVO> getStockAlert() {
		return stockAlert;
	}

	public void setStockAlert(ArrayList<StockAlertVO> stockAlert) {
		this.stockAlert = stockAlert;
	}

	public String getGraphUrl() {
		return graphUrl;
	}

	public void setGraphUrl(String graphUrl) {
		this.graphUrl = graphUrl;
	}

	public String getMovingAvgUrl() {
		return movingAvgUrl;
	}

	public void setMovingAvgUrl(String movingAvgUrl) {
		this.movingAvgUrl = movingAvgUrl;
	}

	public ArrayList<FieldData> getUrlList() {
		return urlList;
	}

	public void setUrlList(ArrayList<FieldData> urlList) {
		this.urlList = urlList;
	}

	public MarketCompanyBasicItemData getBse() {
		return bse;
	}

	public void setBse(MarketCompanyBasicItemData bse) {
		this.bse = bse;
	}

	public MarketCompanyBasicItemData getNse() {
		return nse;
	}

	public void setNse(MarketCompanyBasicItemData nse) {
		this.nse = nse;
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
