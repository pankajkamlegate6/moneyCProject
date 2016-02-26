package com.moneycontrol.handheld.entity.home;

import java.io.Serializable;
import java.util.ArrayList;

public class HomeDataVo implements Serializable {

	private ArrayList<HomeData> arHomeData;
	private int refreshRate = -1;
	private boolean autorefreshFlag = false;
	

	public ArrayList<HomeData> getArHomeData() {
		return arHomeData;
	}

	public void setArHomeData(ArrayList<HomeData> arHomeData) {
		this.arHomeData = arHomeData;
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
