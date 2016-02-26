package com.moneycontrol.handheld.entity.home;

import java.io.Serializable;
import java.util.ArrayList;

import com.moneycontrol.voteonaccount.VOA_CommanEntity;
import com.moneycontrol.voteonaccount.VOA_MenuEntity;

public class VoteOnAccountNews implements Serializable {
	private String main_heading = null, bold, html_Content;
	private ArrayList<VOA_CommanEntity> newArray;
	private ArrayList<VOA_MenuEntity> menuList = null;

	public ArrayList<VOA_CommanEntity> getNewArray() {
		return newArray;
	}

	public void setNewArray(ArrayList<VOA_CommanEntity> newArray) {
		this.newArray = newArray;
	}

	public String getMain_heading() {
		return main_heading;
	}

	public void setMain_heading(String main_heading) {
		this.main_heading = main_heading;
	}

	public String getBold() {
		return bold;
	}

	public void setBold(String bold) {
		this.bold = bold;
	}

	public ArrayList<VOA_MenuEntity> getMenuList() {
		return menuList;
	}

	public void setMenuList(ArrayList<VOA_MenuEntity> menuList) {
		this.menuList = menuList;
	}

	public String getHtml_Content() {
		return html_Content;
	}

	public void setHtml_Content(String html_Content) {
		this.html_Content = html_Content;
	}

}
