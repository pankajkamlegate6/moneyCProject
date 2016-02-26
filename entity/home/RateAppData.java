package com.moneycontrol.handheld.entity.home;

public class RateAppData {

	private int status = 0, showAfter = 0, pageviews = 0, remindAfter = 0, stopRemindAfterCancel = 0;
	private String title = "", message = "", marketURL = "", shareURL = "";

	public void setStatus(int status) {
		this.status = status;
	}

	public int getStatus() {
		return status;
	}

	public void setShowAfter(int showAfter) {
		this.showAfter = showAfter;
	}

	public int getShowAfter() {
		return showAfter;
	}

	public void setPageviews(int pageviews) {
		this.pageviews = pageviews;
	}

	public int getPageviews() {
		return pageviews;
	}

	public void setRemindAfter(int remindAfter) {
		this.remindAfter = remindAfter;
	}

	public int getRemindAfter() {
		return remindAfter;
	}

	public void setStopRemindAfterCancel(int stopRemindAfterCancel) {
		this.stopRemindAfterCancel = stopRemindAfterCancel;
	}

	public int getStopRemindAfterCancel() {
		return stopRemindAfterCancel;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMarketURL(String marketURL) {
		this.marketURL = marketURL;
	}

	public String getMarketURL() {
		return marketURL;
	}

	public String getShareURL() {
		return shareURL;
	}

	public void setShareURL(String shareURL) {
		this.shareURL = shareURL;
	}

}
