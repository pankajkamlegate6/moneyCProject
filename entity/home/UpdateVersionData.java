package com.moneycontrol.handheld.entity.home;

public class UpdateVersionData {

	private int pageviews = 0, remindAfter = 0, stopRemindAfterCancel = 0;
	private float currentVersion = 0;
	private String title = "", message = "", updateUrl = "",forceUpdate="",forceUpdateVersion="";

	public String getForceUpdate() {
		return forceUpdate;
	}

	public void setForceUpdate(String forceUpdate) {
		this.forceUpdate = forceUpdate;
	}

	public String getForceUpdateVersion() {
		return forceUpdateVersion;
	}

	public void setForceUpdateVersion(String forceUpdateVersion) {
		this.forceUpdateVersion = forceUpdateVersion;
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

	public void setCurrentVersion(float currentVersion) {
		this.currentVersion = currentVersion;
	}

	public float getCurrentVersion() {
		return currentVersion;
	}

	public void setUpdateUrl(String marketUrl) {
		this.updateUrl = marketUrl;
	}

	public String getUpdateUrl() {
		return updateUrl;
	}

}
