package com.moneycontrol.handheld.chart.dialog;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

import com.moneycontrol.handheld.chart.utils.ValueFormatter;

public class NoChangeValueFormatter implements ValueFormatter {

	private DecimalFormat formatter;

	public NoChangeValueFormatter() {
		formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
		DecimalFormatSymbols symbols = formatter.getDecimalFormatSymbols();
		symbols.setCurrencySymbol(""); // Don't use null.
		formatter.setDecimalFormatSymbols(symbols);
		// System.out.println(formatter.format(12.3456)); // 12.35
	}

	@Override
	public String getFormattedValue(float value) {
		return formatter.format(value); // append a dollar-sign
	}
}