package com.moneycontrol.handheld.entity.market;

import java.io.Serializable;
import java.util.ArrayList;

public class MarketMoversActiveWeek52NSE_BSE_Data implements Serializable {
	private ArrayList<MarketMoversItemData> active_by_value, active_by_volume, week52_high, week52_low;

	public ArrayList<MarketMoversItemData> getActive_by_value() {
		return active_by_value;
	}

	public void setActive_by_value(ArrayList<MarketMoversItemData> active_by_value) {
		this.active_by_value = active_by_value;
	}

	public ArrayList<MarketMoversItemData> getActive_by_volume() {
		return active_by_volume;
	}

	public void setActive_by_volume(ArrayList<MarketMoversItemData> active_by_volume) {
		this.active_by_volume = active_by_volume;
	}

	public ArrayList<MarketMoversItemData> getWeek52_high() {
		return week52_high;
	}

	public void setWeek52_high(ArrayList<MarketMoversItemData> week52_high) {
		this.week52_high = week52_high;
	}

	public ArrayList<MarketMoversItemData> getWeek52_low() {
		return week52_low;
	}

	public void setWeek52_low(ArrayList<MarketMoversItemData> week52_low) {
		this.week52_low = week52_low;
	}
}
