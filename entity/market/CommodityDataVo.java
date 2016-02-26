package com.moneycontrol.handheld.entity.market;

import java.io.Serializable;
import java.util.ArrayList;

public class CommodityDataVo implements Serializable {

	private ArrayList<CommoditiesCarouselData> commoditylist;

	private int refreshRate = -1;
	private boolean autorefreshFlag = false;

	public ArrayList<CommoditiesCarouselData> getCommoditylist() {
		return commoditylist;
	}

	public void setCommoditylist(
			ArrayList<CommoditiesCarouselData> commoditylist) {
		this.commoditylist = commoditylist;
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
