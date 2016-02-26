package com.moneycontrol.handheld.entity.investor;

import java.io.Serializable;

public class IMN_MythsFacts implements Serializable {
	String myth, fact;

	public String getMyth() {
		return myth;
	}

	public void setMyth(String myth) {
		this.myth = myth;
	}

	public String getFact() {
		return fact;
	}

	public void setFact(String fact) {
		this.fact = fact;
	}
}
