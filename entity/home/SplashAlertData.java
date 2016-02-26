package com.moneycontrol.handheld.entity.home;

public class SplashAlertData {
	private String stocks, video, AdRefesh;
	private SplashData splash;
	private PopupTimerData popup_timer;

	public String getStocks() {
		return stocks;
	}

	public void setStocks(String stocks) {
		this.stocks = stocks;
	}

	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}

	public String getAdRefesh() {
		return AdRefesh;
	}

	public void setAdRefesh(String adRefesh) {
		AdRefesh = adRefesh;
	}

	public SplashData getSplash() {
		return splash;
	}

	public void setSplash(SplashData splash) {
		this.splash = splash;
	}

	public PopupTimerData getPopup_timer() {
		return popup_timer;
	}

	public void setPopup_timer(PopupTimerData popup_timer) {
		this.popup_timer = popup_timer;
	}
}
