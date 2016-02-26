package com.moneycontrol.handheld.entity.home;

import java.io.Serializable;

@SuppressWarnings("serial")
public class SerializeBitmap implements Serializable {

	byte bitma[];

	public byte[] getBitma() {
		return bitma;
	}

	public void setBitma(byte[] bitma) {
		this.bitma = bitma;
	}

}
