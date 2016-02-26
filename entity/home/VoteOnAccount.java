package com.moneycontrol.handheld.entity.home;

import java.util.ArrayList;

public class VoteOnAccount {

	private VOA_Flashes flashesArray;
	private String isFleshesActive = null, isNewsActive = null, isVideoActive = null, isReportsActive = null, isVoicesActive = null, isLiveTVActive = null;
	private String main_heading;
	private ArrayList<InvestorMonthNews_VideoDetails> newsArray;
	private ArrayList<InvestorMonthNews_VideoDetails> voicesArray;
	private ArrayList<InvestorMonthNews_VideoDetails> reportsArray;
	private ArrayList<InvestorMonthNews_VideoDetails> videosArray;
	private ArrayList<InvestorMonthNews_VideoDetails> menuArray;
	private int livetv;

	public String getIsNewsActive() {
		return isNewsActive;
	}

	public void setIsNewsActive(String isNewsActive) {
		this.isNewsActive = isNewsActive;
	}

	public String getIsVideoActive() {
		return isVideoActive;
	}

	public void setIsVideoActive(String isVideoActive) {
		this.isVideoActive = isVideoActive;
	}

	public String getIsReportsActive() {
		return isReportsActive;
	}

	public void setIsReportsActive(String isReportsActive) {
		this.isReportsActive = isReportsActive;
	}

	public String getIsVoicesActive() {
		return isVoicesActive;
	}

	public void setIsVoicesActive(String isVoicesActive) {
		this.isVoicesActive = isVoicesActive;
	}

	public String getIsLiveTVActive() {
		return isLiveTVActive;
	}

	public void setIsLiveTVActive(String isLiveTVActive) {
		this.isLiveTVActive = isLiveTVActive;
	}

	public int getLivetv() {
		return livetv;
	}

	public void setLivetv(int livetv) {
		this.livetv = livetv;
	}

	public VOA_Flashes getFlashesArray() {
		return flashesArray;
	}

	public void setFlashesArray(VOA_Flashes flashesArray) {
		this.flashesArray = flashesArray;
	}

	public ArrayList<InvestorMonthNews_VideoDetails> getNewsArray() {
		return newsArray;
	}

	public void setNewsArray(ArrayList<InvestorMonthNews_VideoDetails> newsArray) {
		this.newsArray = newsArray;
	}

	public ArrayList<InvestorMonthNews_VideoDetails> getVoicesArray() {
		return voicesArray;
	}

	public void setVoicesArray(ArrayList<InvestorMonthNews_VideoDetails> voicesArray) {
		this.voicesArray = voicesArray;
	}

	public ArrayList<InvestorMonthNews_VideoDetails> getReportsArray() {
		return reportsArray;
	}

	public void setReportsArray(ArrayList<InvestorMonthNews_VideoDetails> reportsArray) {
		this.reportsArray = reportsArray;
	}

	public ArrayList<InvestorMonthNews_VideoDetails> getVideosArray() {
		return videosArray;
	}

	public void setVideosArray(ArrayList<InvestorMonthNews_VideoDetails> videosArray) {
		this.videosArray = videosArray;
	}

	public String getIsFleshesActive() {
		return isFleshesActive;
	}

	public void setIsFleshesActive(String isFleshesActive) {
		this.isFleshesActive = isFleshesActive;
	}

	public String getMain_heading() {
		return main_heading;
	}

	public void setMain_heading(String main_heading) {
		this.main_heading = main_heading;
	}

	public ArrayList<InvestorMonthNews_VideoDetails> getMenuArray() {
		return menuArray;
	}

	public void setMenuArray(ArrayList<InvestorMonthNews_VideoDetails> menuArray) {
		this.menuArray = menuArray;
	}

}
