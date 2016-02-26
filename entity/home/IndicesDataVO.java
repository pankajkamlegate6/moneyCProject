package com.moneycontrol.handheld.entity.home;

import java.io.Serializable;
import java.util.ArrayList;

public class IndicesDataVO implements Serializable {

	private ArrayList<HomeMarketMainIndices> indicesList;
	private int refreshRate = -1;
	private boolean autorefreshFlag = false;

	public ArrayList<HomeMarketMainIndices> getIndicesList() {
		return indicesList;
	}

	public void setIndicesList(ArrayList<HomeMarketMainIndices> indicesList) {
		this.indicesList = indicesList;
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
