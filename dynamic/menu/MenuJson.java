package com.moneycontrol.handheld.dynamic.menu;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import android.widget.ImageView;
import android.widget.LinearLayout;

public class MenuJson implements Serializable {

	String logo, logo_hover, name, node, url, image;
	boolean subMenuOpen = false;
	private int uniqueId;
	ArrayList<SubMenuJson> subMenu = new ArrayList<SubMenuJson>();

	HashMap<String, String> links = new HashMap<String, String>();

	private transient LinearLayout subMenuView;
	private transient LinearLayout menuView;

	private transient ImageView isExpandimg;
	private transient ImageView menuicon;

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public ImageView getMenuicon() {
		return menuicon;
	}

	public void setMenuicon(ImageView menuicon) {
		this.menuicon = menuicon;
	}

	public ImageView getIsExpandimg() {
		return isExpandimg;
	}

	public void setIsExpandimg(ImageView isExpandimg) {
		this.isExpandimg = isExpandimg;
	}

	public void setMenuView(LinearLayout menuView) {
		this.menuView = menuView;
	}

	public LinearLayout getMenuView() {
		return menuView;
	}

	public void setSubMenuOpen(boolean subMenuOpen) {
		this.subMenuOpen = subMenuOpen;
	}

	public boolean isSubMenuOpen() {
		return subMenuOpen;
	}

	public void setUniqueId(int uniqueId) {
		this.uniqueId = uniqueId;
	}

	public int getUniqueId() {
		return uniqueId;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getLogo_hover() {
		return logo_hover;
	}

	public void setLogo_hover(String logo_hover) {
		this.logo_hover = logo_hover;
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

	public void setSubMenuView(LinearLayout ll) {
		this.subMenuView = ll;
	}

	public LinearLayout getSubMenuView() {
		return subMenuView;
	}
}
