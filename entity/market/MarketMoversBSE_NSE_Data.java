package com.moneycontrol.handheld.entity.market;

import java.io.Serializable;
import java.util.ArrayList;

public class MarketMoversBSE_NSE_Data implements Serializable {
	private ArrayList<MarketMoversItemData> topgainer, toploser;

	public ArrayList<MarketMoversItemData> getTopgainer() {
		return topgainer;
	}

	public void setTopgainer(ArrayList<MarketMoversItemData> topgainer) {
		this.topgainer = topgainer;
	}

	public ArrayList<MarketMoversItemData> getToploser() {
		return toploser;
	}

	public void setToploser(ArrayList<MarketMoversItemData> toploser) {
		this.toploser = toploser;
	}
}
