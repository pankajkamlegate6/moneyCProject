package com.moneycontrol.handheld.chart.utils;

import java.util.ArrayList;

import android.util.Log;

import com.moneycontrol.handheld.chart.entity.StickChartEntity.Values;

public class MovingAverage {
	int days;
	ArrayList<Values> fulllist;
	ArrayList<Values> rangelist;

	private ArrayList<Float> averagelist = new ArrayList<Float>();

	public MovingAverage(int days, ArrayList<Values> fulllist,
			ArrayList<Values> rangelist) {
		this.days = days;
		this.fulllist = fulllist;
		this.rangelist = rangelist;
	}

	private boolean add = false;

	public boolean canAdded() {
		return add;
	}

	public ArrayList<Float> getAveragelist() {
		return averagelist;
	}

	public void doCompute() {
		averagelist.clear();
		try {
			for (int i = 0; i < rangelist.size(); i++) {
				float sum = 0;
				String date = rangelist.get(i).getTime();
				String fulldatelist = fulllist.get(i).getTime();
				int index = 0;
				if (fulldatelist.equalsIgnoreCase(date)) {
					index = i;
				} else {
					index = getIndex(fulllist, date);
				}

				if (index != -1) {

					for (int j = index + 1 - days; j < index + 1; j++) {
						if (j < fulllist.size()) {
							sum = sum + fulllist.get(j).getValue();
						}
					}
				}
				float average = sum / days;
				if (average == 0) {
					add = false;
				} else {
					add = true;
				}
				averagelist.add(average);
			}
		} catch (Exception e) {
			averagelist.clear();
		}

	}

	public int getIndex(ArrayList<Values> list, String date) {
		int index = -1;
		for (int i = 0; i < list.size(); i++) {
			if (fulllist.get(i).getTime().equalsIgnoreCase(date)) {
				index = i;
				break;
			}

		}
		return index;
	}
}
