package com.moneycontrol.handheld.dynamic.menu;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import com.moneycontrol.handheld.api.AppBeanParacable;

public class MenuList implements AppBeanParacable, Serializable {

	public ArrayList<MenuJson> menulist = new ArrayList<MenuJson>();

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

	public ArrayList<MenuJson> getMenulist() {
		if (menulist.size() == 0) {

		} else {

		}
		return menulist;
	}

	public void setMenulist(ArrayList<MenuJson> menulist) {
		this.menulist = menulist;
	}

	public HashMap<String, String> getLinks() {
		return links;
	}

	public void setLinks(HashMap<String, String> links) {
		this.links = links;
	}

	HashMap<String, String> links = new HashMap<String, String>();
}
