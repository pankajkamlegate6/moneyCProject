package com.moneycontrol.handheld.entity.home;

import java.io.Serializable;
import java.util.ArrayList;

public class RegisteredAdd_Information_Data implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<AddressBean> addressBeanobj;
	private ArrayList<RegistrarsAddressBean> registrarsAddressBeanobj;
	private ArrayList<ManagementMemberBean> managementMemberBeanobj;
	private ArrayList<Object> infomationObjects;
	private String alertMessage;

	public void setAlertMessage(String alertMessage) {
		this.alertMessage = alertMessage;
	}

	public String getAlertMessage() {
		return alertMessage;
	}

	public ArrayList<Object> getInfomationObjects() {
		return infomationObjects;
	}

	public void setInfomationObjects(ArrayList<Object> infomationObjects) {
		this.infomationObjects = infomationObjects;
	}

	public ArrayList<AddressBean> getAddressBeanobj() {
		return addressBeanobj;
	}

	public void setAddressBeanobj(ArrayList<AddressBean> addressBeanobj) {
		this.addressBeanobj = addressBeanobj;
	}

	public ArrayList<RegistrarsAddressBean> getRegistrarsAddressBeanobj() {
		return registrarsAddressBeanobj;
	}

	public void setRegistrarsAddressBeanobj(
			ArrayList<RegistrarsAddressBean> registrarsAddressBeanobj) {
		this.registrarsAddressBeanobj = registrarsAddressBeanobj;
	}

	public ArrayList<ManagementMemberBean> getManagementMemberBeanobj() {
		return managementMemberBeanobj;
	}

	public void setManagementMemberBeanobj(
			ArrayList<ManagementMemberBean> managementMemberBeanobj) {
		this.managementMemberBeanobj = managementMemberBeanobj;
	}

	public RegisteredAdd_Information_Data() {

	}

}
