package com.moneycontrol.handheld.entity.home;

import java.io.Serializable;

public class Header_entity implements Serializable{

	private String html_Content = null, onClick = null, height = null, siteID = null, admob_site_id = null, textlink_flag, dfp_banner, dfp_interstitial;
	private int showAfter = -1;

	public String getHtml_Content() {
		return html_Content;
	}

	public void setHtml_Content(String html_Content) {
		this.html_Content = html_Content;
	}

	public String getOnClick() {
		return onClick;
	}

	public void setOnClick(String onClick) {
		this.onClick = onClick;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public int getShowAfter() {
		return showAfter;
	}

	public void setShowAfter(int showAfter) {
		this.showAfter = showAfter;
	}

	public String getSiteID() {
		return siteID;
	}

	public void setSiteID(String siteID) {
		this.siteID = siteID;
	}

	public String getAdmob_site_id() {
		return admob_site_id;
	}

	public void setAdmob_site_id(String admob_site_id) {
		this.admob_site_id = admob_site_id;
	}

	public String getTextlink_flag() {
		return textlink_flag;
	}

	public void setTextlink_flag(String textlink_flag) {
		this.textlink_flag = textlink_flag;
	}

	public String getDfp_interstitial() {
		return dfp_interstitial;
	}

	public void setDfp_interstitial(String dfp_interstitial) {
		this.dfp_interstitial = dfp_interstitial;
	}

	public String getDfp_banner() {
		return dfp_banner;
	}

	public void setDfp_banner(String dfp_banner) {
		this.dfp_banner = dfp_banner;
	}

}
