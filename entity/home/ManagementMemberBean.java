package com.moneycontrol.handheld.entity.home;

import java.io.Serializable;

public class ManagementMemberBean implements Serializable {

	private String mgmt_nameValue, mgmt_designationValue;
	private String Tittle;

	public String getTittle() {
		return Tittle;
	}

	public void setTittle(String tittle) {
		Tittle = tittle;
	}

	public String getMgmt_nameValue() {
		return mgmt_nameValue;
	}

	public void setMgmt_nameValue(String mgmt_nameValue) {
		this.mgmt_nameValue = mgmt_nameValue;
	}

	public String getMgmt_designationValue() {
		return mgmt_designationValue;
	}

	public void setMgmt_designationValue(String mgmt_designationValue) {
		this.mgmt_designationValue = mgmt_designationValue;
	}

	public ManagementMemberBean() {

	}

}
