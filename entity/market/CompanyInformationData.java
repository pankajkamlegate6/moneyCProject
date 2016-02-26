package com.moneycontrol.handheld.entity.market;

import java.util.ArrayList;

public class CompanyInformationData {
	private String group_code, group;
	private RegisteredAddressData registered_address;
	private RegistrarsAddressData registrars_address;
	private ArrayList<ManagementData> management;

	public String getGroup_code() {
		return group_code;
	}

	public void setGroup_code(String group_code) {
		this.group_code = group_code;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public RegisteredAddressData getRegistered_address() {
		return registered_address;
	}

	public void setRegistered_address(RegisteredAddressData registered_address) {
		this.registered_address = registered_address;
	}

	public RegistrarsAddressData getRegistrars_address() {
		return registrars_address;
	}

	public void setRegistrars_address(RegistrarsAddressData registrars_address) {
		this.registrars_address = registrars_address;
	}

	public ArrayList<ManagementData> getManagement() {
		return management;
	}

	public void setManagement(ArrayList<ManagementData> management) {
		this.management = management;
	}
}
