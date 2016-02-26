package com.moneycontrol.handheld.entity.investor;

import java.util.ArrayList;

import com.moneycontrol.voteonaccount.VOA_CommanEntity;

public class IMN_Main {
	private String main_heading, bold;
	private ArrayList<IMN_Menu> menuArray;
	private ArrayList<IMN_SubMenu> subMenuArray;
	private ArrayList<ArrayList<VOA_CommanEntity>> catNewsArray;
	private ArrayList<IMN_ask_expert> askExpertArray;
	private ArrayList<IMN_chat_details> chatDetailsArray;

	private ArrayList<IMN_MythsFacts> mythsexpertArray;
	private ArrayList<ArrayList<VOA_CommanEntity>> videonewsArray_multiple;

	public ArrayList<ArrayList<VOA_CommanEntity>> getVideonewsArray_multiple() {
		return videonewsArray_multiple;
	}

	public void setVideonewsArray_multiple(ArrayList<ArrayList<VOA_CommanEntity>> videonewsArray_multiple) {
		this.videonewsArray_multiple = videonewsArray_multiple;
	}

	public String getMain_heading() {
		return main_heading;
	}

	public void setMain_heading(String main_heading) {
		this.main_heading = main_heading;
	}

	public ArrayList<IMN_Menu> getMenuArray() {
		return menuArray;
	}

	public void setMenuArray(ArrayList<IMN_Menu> menuArray) {
		this.menuArray = menuArray;
	}

	public ArrayList<IMN_SubMenu> getSubMenuArray() {
		return subMenuArray;
	}

	public void setSubMenuArray(ArrayList<IMN_SubMenu> subMenuArray) {
		this.subMenuArray = subMenuArray;
	}

	public ArrayList<ArrayList<VOA_CommanEntity>> getCatNewsArray() {
		return catNewsArray;
	}

	public void setCatNewsArray(ArrayList<ArrayList<VOA_CommanEntity>> catNewsArray) {
		this.catNewsArray = catNewsArray;
	}

	public ArrayList<IMN_chat_details> getChatDetailsArray() {
		return chatDetailsArray;
	}

	public void setChatDetailsArray(ArrayList<IMN_chat_details> chatDetailsArray) {
		this.chatDetailsArray = chatDetailsArray;
	}

	public ArrayList<IMN_ask_expert> getAskExpertArray() {
		return askExpertArray;
	}

	public void setAskExpertArray(ArrayList<IMN_ask_expert> askExpertArray) {
		this.askExpertArray = askExpertArray;
	}

	public ArrayList<IMN_MythsFacts> getMythsexpertArray() {
		return mythsexpertArray;
	}

	public void setMythsexpertArray(ArrayList<IMN_MythsFacts> mythsexpertArray) {
		this.mythsexpertArray = mythsexpertArray;
	}

	public String getBold() {
		return bold;
	}

	public void setBold(String bold) {
		this.bold = bold;
	}

}
