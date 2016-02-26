package com.moneycontrol.handheld.chart.utils;

import java.text.DecimalFormat;

public class NumberFormatter implements ValueFormatter {
	private static String[] SUFFIX = new String[] { "", "k", "m", "b", "t" };
	private static int MAX_LENGTH = 4;

	@Override
	public String getFormattedValue(float value) {
		// TODO Auto-generated method stub
		return makePretty(value);
	}

	private DecimalFormat mFormat;

	public NumberFormatter() {

		mFormat = new DecimalFormat("##0E0");
	}

	private String makePretty(double number) {
		if (number == 0) {
			return "0";
		} else {
			String[] suffix = new String[] { "k", "m", "b", "t" };
			int size = (number != 0) ? (int) Math.log10(number) : 0;
			if (size >= 3) {
				while (size % 3 != 0) {
					size = size - 1;
				}
			}
			DecimalFormat df = new DecimalFormat("##.###");

			double notation = Math.pow(10, size);
			String result = (size >= 3) ? ""
					+ (df.format((number / notation * 100) / 100.0d))
					+ suffix[(size / 3) - 1] : +number + "";
			return result;

		}
	}
}