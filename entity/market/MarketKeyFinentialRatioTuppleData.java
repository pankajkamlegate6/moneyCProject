package com.moneycontrol.handheld.entity.market;

import java.util.ArrayList;

public class MarketKeyFinentialRatioTuppleData {
	private ArrayList<MarketKeyFinentialRatioTimestamp> timeStamp;
	private ArrayList<MarketKeyFinentialRatioTuppleItemData> tuple;

	public ArrayList<MarketKeyFinentialRatioTimestamp> getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(ArrayList<MarketKeyFinentialRatioTimestamp> timeStamp) {
		this.timeStamp = timeStamp;
	}

	public ArrayList<MarketKeyFinentialRatioTuppleItemData> getTuple() {
		return tuple;
	}

	public void setTuple(ArrayList<MarketKeyFinentialRatioTuppleItemData> tuple) {
		this.tuple = tuple;
	}
}
