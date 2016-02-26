package com.moneycontrol.handheld.entity.market;

import java.io.Serializable;

public class BonusData implements Serializable {
	private String announcement_date, bonus_ratio, record_date, exbonus_date, message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getAnnouncement_date() {
		return announcement_date;
	}

	public void setAnnouncement_date(String announcement_date) {
		this.announcement_date = announcement_date;
	}

	public String getBonus_ratio() {
		return bonus_ratio;
	}

	public void setBonus_ratio(String bonus_ratio) {
		this.bonus_ratio = bonus_ratio;
	}

	public String getRecord_date() {
		return record_date;
	}

	public void setRecord_date(String record_date) {
		this.record_date = record_date;
	}

	public String getExbonus_date() {
		return exbonus_date;
	}

	public void setExbonus_date(String exbonus_date) {
		this.exbonus_date = exbonus_date;
	}
}
