package com.moneycontrol.handheld.dynamic.menu;

import java.io.Serializable;
import java.util.ArrayList;

import android.widget.RadioButton;

public class SubMenuJson implements Serializable {
	public int uniqueId;
	private transient RadioButton rd;
	private String image;
	String name, node, url;
	ArrayList<SubMenuJson> subMenu = new ArrayList<SubMenuJson>();

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public RadioButton getRd() {
		return rd;
	}

	public void setRd(RadioButton rd) {
		this.rd = rd;
	}

	public void setUniqueId(int uniqueId) {
		this.uniqueId = uniqueId;
	}

	public int getUniqueId() {
		return uniqueId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNode() {
		return node;
	}

	public void setNode(String node) {
		this.node = node;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public ArrayList<SubMenuJson> getSubMenu() {
		return subMenu;
	}

	public void setSubMenu(ArrayList<SubMenuJson> subMenu) {
		this.subMenu = subMenu;
	}

}
