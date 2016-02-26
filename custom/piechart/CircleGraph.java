package com.moneycontrol.handheld.custom.piechart;

import android.graphics.Color;

public class CircleGraph {

	private String name = null;
	private int color = Color.BLUE;
	private float angleArr ;
	private float angleDegree ;
	private String weightage;
	
	private int bitmapResource = -1;
	
	
	public CircleGraph(String name, int color, String weight, float angleArr) {
		this.name = name;
		this.color = color;
		weightage = weight;
		this.setAngleArr(angleArr);
	}

	public CircleGraph(String name, int color, float angleArr, int bitmapResource) {
		this.name = name;
		this.color = color;
		this.setAngleArr(angleArr);
		this.bitmapResource = bitmapResource;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getColor() {
		return color;
	}
	public void setColor(int color) {
		this.color = color;
	}
	
	public String getWeightage() {
		return weightage;
	}

	public void setWeightage(String weightage) {
		this.weightage = weightage;
	}

	
	public float getAngleArr() {
		return angleArr;
	}

	public void setAngleArr(float angleArr) {
		this.angleArr = angleArr;
	}

	public int getBitmapResource() {
		return bitmapResource;
	}
	public void setBitmapResource(int bitmapResource) {
		this.bitmapResource = bitmapResource;
	}

	public float getAngleDegree() {
		return angleDegree;
	}

	public void setAngleDegree(float angleDegree) {
		this.angleDegree = angleDegree;
	}
}
