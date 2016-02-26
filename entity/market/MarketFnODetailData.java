package com.moneycontrol.handheld.entity.market;

import java.util.ArrayList;

public class MarketFnODetailData {
	ArrayList<String> InstrumentType, OptionType, Expiry_Date;

	public ArrayList<String> getInstrumentType() {
		return InstrumentType;
	}

	public void setInstrumentType(ArrayList<String> instrumentType) {
		InstrumentType = instrumentType;
	}

	public ArrayList<String> getOptionType() {
		return OptionType;
	}

	public void setOptionType(ArrayList<String> optionType) {
		OptionType = optionType;
	}

	public ArrayList<String> getExpiry_Date() {
		return Expiry_Date;
	}

	public void setExpiry_Date(ArrayList<String> expiry_Date) {
		Expiry_Date = expiry_Date;
	}
}
