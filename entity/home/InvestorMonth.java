package com.moneycontrol.handheld.entity.home;

import java.util.ArrayList;
import java.util.HashMap;

public class InvestorMonth {

	private String main_heading, ask_expert_image, ask_expert_url;
	private ArrayList<InvestorMonthNews_VideoDetails> menuArray;
	private ArrayList<InvestorMonthNews_VideoDetails> subMenuArray;
	private ArrayList<HashMap<String, ArrayList<InvestorMonthNews_VideoDetails>>> valueArray;
	private ArrayList<InvestorMonthNews_VideoDetails> newsArray;
	private ArrayList<InvestorMonthNews_VideoDetails> videoArray;
	// private InvestorMonthChatDetails chat;
	private ArrayList<InvestorMonthNews_VideoDetails> chatdetails;

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

	public ArrayList<InvestorMonthNews_VideoDetails> getNewsArray() {
		return newsArray;
	}

	public void setNewsArray(ArrayList<InvestorMonthNews_VideoDetails> newsArray) {
		this.newsArray = newsArray;
	}

	public ArrayList<InvestorMonthNews_VideoDetails> getVideoArray() {
		return videoArray;
	}

	public void setVideoArray(ArrayList<InvestorMonthNews_VideoDetails> videoArray) {
		this.videoArray = videoArray;
	}

	// public String getAsk_expert() {
	// return ask_expert;
	// }
	//
	// public void setAsk_expert(String ask_expert) {
	// this.ask_expert = ask_expert;
	// }

	// public InvestorMonthChatDetails getChat() {
	// return chat;
	// }
	//
	// public void setChat(InvestorMonthChatDetails chat) {
	// this.chat = chat;
	// }

	public ArrayList<InvestorMonthNews_VideoDetails> getChatdetails() {
		return chatdetails;
	}

	public void setChatdetails(ArrayList<InvestorMonthNews_VideoDetails> chatdetails) {
		this.chatdetails = chatdetails;
	}

	public String getAsk_expert_image() {
		return ask_expert_image;
	}

	public void setAsk_expert_image(String ask_expert_image) {
		this.ask_expert_image = ask_expert_image;
	}

	public String getAsk_expert_url() {
		return ask_expert_url;
	}

	public void setAsk_expert_url(String ask_expert_url) {
		this.ask_expert_url = ask_expert_url;
	}

	public ArrayList<InvestorMonthNews_VideoDetails> getSubMenuArray() {
		return subMenuArray;
	}

	public void setSubMenuArray(ArrayList<InvestorMonthNews_VideoDetails> subMenuArray) {
		this.subMenuArray = subMenuArray;
	}

	public ArrayList<HashMap<String, ArrayList<InvestorMonthNews_VideoDetails>>> getValueArray() {
		return valueArray;
	}

	public void setValueArray(ArrayList<HashMap<String, ArrayList<InvestorMonthNews_VideoDetails>>> valueArray) {
		this.valueArray = valueArray;
	}

}
