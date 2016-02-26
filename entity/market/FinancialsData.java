package com.moneycontrol.handheld.entity.market;

public class FinancialsData {
	private String year, month, sales_Turnover, other_Income, total_Income,
			total_Expenses, operating_Profit, profit_on_Sale_of_Assets,
			profit_on_Sale_of_Investments, gain_Loss_on_Foreign_Exchange,
			vRS_Adjustment, other_Extraordinary_Income,
			total_Extraordinary_Income, tax_on_Extraordinary_Items,
			net_Extra_Ordinary_Income, gross_Profit, interest, pBDT,
			depreciation, depreciation_on_Revaluation_of_Assets, pBT, tax,
			net_Profit, prior_Years_Income,
			depreciation_for_Previous_Years_Written_Back_Provided, dividend,
			dividend_Tax, dividend_Percent, earnings_Per_Share, book_Value,
			equity, reserves, face_Value;

	private String sales_Turnover_AttrName, other_Income_AttrName,
			total_Income_AttrName, total_Expenses_AttrName,
			operating_Profit_AttrName, profit_on_Sale_of_Assets_AttrName,
			profit_on_Sale_of_Investments_AttrName,
			gain_Loss_on_Foreign_Exchange_AttrName, vRS_Adjustment_AttrName,
			other_Extraordinary_Income_AttrName,
			total_Extraordinary_Income_AttrName,
			tax_on_Extraordinary_Items_AttrName,
			net_Extra_Ordinary_Income_AttrName, gross_Profit_AttrName,
			interest_AttrName, pBDT_AttrName, depreciation_AttrName,
			depreciation_on_Revaluation_of_Assets_AttrName, pBT_AttrName,
			tax_AttrName, net_Profit_AttrName, prior_Years_Income_AttrName,
			depreciation_for_Previous_Years_Written_Back_Provided_AttrName,
			dividend_AttrName, dividend_Tax_AttrName,
			dividend_Percent_AttrName, earnings_Per_Share_AttrName,
			book_Value_AttrName, equity_AttrName, reserves_AttrName,
			face_Value_AttrName;

	
	//Extra values not using in web service kept in data model
	private String Dividend_Per_Share, Operating_Profit_Per_Share,
			Net_Operating_Profit_Per_Share, Free_Reserves_Per_Share,
			Bonus_in_Equity_Capital, Operating_Profit_Margin,
			Profit_Before_Interest_And_Tax_Margin, Gross_Profit_Margin,
			Cash_Profit_Margin,

			Adjusted_Cash_Margin, Net_Profit_Margin,
			Adjusted_Net_Profit_Margin, Return_On_Capital_Employed,
			Return_On_Net_Worth, Adjusted_Return_on_Net_Worth,
			Return_on_Assets_Excluding_Revaluations,
			Return_on_Assets_Including_Revaluations, Return_on_Long_Term_Funds,
			Current_Ratio, Quick_Ratio, Debt_Equity_Ratio,
			Long_Term_Debt_Equity_Ratio, Interest_Cover,
			Total_Debt_to_Owners_Fund, Financial_Charges_Coverage_Ratio,
			Financial_Charges_Coverage_Ratio_Post_Tax,
			Inventory_Turnover_Ratio, Debtors_Turnover_Ratio,
			Investments_Turnover_Ratio, Fixed_Assets_Turnover_Ratio,
			Total_Assets_Turnover_Ratio, Asset_Turnover_Ratio,
			Average_Raw_Material_Holding, Average_Finished_Goods_Held,
			Number_of_Days_In_Working_Capital, Material_Cost_Composition,
			Imported_Composition_of_Raw_Materials_Consumed,
			Selling_Distribution_Cost_Composition,
			Expenses_as_Composition_of_Total_Sales,
			Dividend_Payout_Ratio_Net_Profit,
			Dividend_Payout_Ratio_Cash_Profit, Earning_Retention_Ratio,
			Cash_Earning_Retention_Ratio, AdjustedCash_Flow_Times;

	private String Dividend_Per_Share_AttrName,
			Operating_Profit_Per_Share_AttrName,
			Net_Operating_Profit_Per_Share_AttrName,
			Free_Reserves_Per_Share_AttrName, Bonus_in_Equity_Capital_AttrName,
			Operating_Profit_Margin_AttrName,
			Profit_Before_Interest_And_Tax_Margin_AttrName,
			Gross_Profit_Margin_AttrName, Cash_Profit_Margin_AttrName,

			Adjusted_Cash_Margin_AttrName, Net_Profit_Margin_AttrName,
			Adjusted_Net_Profit_Margin_AttrName,
			Return_On_Capital_Employed_AttrName, Return_On_Net_Worth_AttrName,
			Adjusted_Return_on_Net_Worth_AttrName,
			Return_on_Assets_Excluding_Revaluations_AttrName,
			Return_on_Assets_Including_Revaluations_AttrName,
			Return_on_Long_Term_Funds_AttrName, Current_Ratio_AttrName,
			Quick_Ratio_AttrName, Debt_Equity_Ratio_AttrName,
			Long_Term_Debt_Equity_Ratio_AttrName, Interest_Cover_AttrName,
			Total_Debt_to_Owners_Fund_AttrName,
			Financial_Charges_Coverage_Ratio_AttrName,
			Financial_Charges_Coverage_Ratio_Post_Tax_AttrName,
			Inventory_Turnover_Ratio_AttrName, Debtors_Turnover_Ratio_AttrName,
			Investments_Turnover_Ratio_AttrName,
			Fixed_Assets_Turnover_Ratio_AttrName,
			Total_Assets_Turnover_Ratio_AttrName,
			Asset_Turnover_Ratio_AttrName,
			Average_Raw_Material_Holding_AttrName,
			Average_Finished_Goods_Held_AttrName,
			Number_of_Days_In_Working_Capital_AttrName,
			Material_Cost_Composition_AttrName,
			Imported_Composition_of_Raw_Materials_Consumed_AttrName,
			Selling_Distribution_Cost_Composition_AttrName,
			Expenses_as_Composition_of_Total_Sales_AttrName,
			Dividend_Payout_Ratio_Net_Profit_AttrName,
			Dividend_Payout_Ratio_Cash_Profit_AttrName,
			Earning_Retention_Ratio_AttrName,
			Cash_Earning_Retention_Ratio_AttrName,
			AdjustedCash_Flow_Times_AttrName;

	private String Buffer_1, Buffer_2, Buffer_3, Buffer_4, Buffer_5, Buffer_6,
			Buffer_7, Buffer_8, Buffer_9, Buffer_10;
	private String Buffer_1_AttrName, Buffer_2_AttrName, Buffer_3_AttrName,
			Buffer_4_AttrName, Buffer_5_AttrName, Buffer_6_AttrName,
			Buffer_7_AttrName, Buffer_8_AttrName, Buffer_9_AttrName,
			Buffer_10_AttrName;

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month.substring(0,3);
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getSales_Turnover() {
		return sales_Turnover;
	}

	public void setSales_Turnover(String sales_Turnover) {
		this.sales_Turnover = sales_Turnover;
	}

	public String getOther_Income() {
		return other_Income;
	}

	public void setOther_Income(String other_Income) {
		this.other_Income = other_Income;
	}

	public String getTotal_Income() {
		return total_Income;
	}

	public void setTotal_Income(String total_Income) {
		this.total_Income = total_Income;
	}

	public String getTotal_Expenses() {
		return total_Expenses;
	}

	public void setTotal_Expenses(String total_Expenses) {
		this.total_Expenses = total_Expenses;
	}

	public String getOperating_Profit() {
		return operating_Profit;
	}

	public void setOperating_Profit(String operating_Profit) {
		this.operating_Profit = operating_Profit;
	}

	public String getProfit_on_Sale_of_Assets() {
		return profit_on_Sale_of_Assets;
	}

	public void setProfit_on_Sale_of_Assets(String profit_on_Sale_of_Assets) {
		this.profit_on_Sale_of_Assets = profit_on_Sale_of_Assets;
	}

	public String getProfit_on_Sale_of_Investments() {
		return profit_on_Sale_of_Investments;
	}

	public void setProfit_on_Sale_of_Investments(
			String profit_on_Sale_of_Investments) {
		this.profit_on_Sale_of_Investments = profit_on_Sale_of_Investments;
	}

	public String getGain_Loss_on_Foreign_Exchange() {
		return gain_Loss_on_Foreign_Exchange;
	}

	public void setGain_Loss_on_Foreign_Exchange(
			String gain_Loss_on_Foreign_Exchange) {
		this.gain_Loss_on_Foreign_Exchange = gain_Loss_on_Foreign_Exchange;
	}

	public String getVRS_Adjustment() {
		return vRS_Adjustment;
	}

	public void setVRS_Adjustment(String vRS_Adjustment) {
		this.vRS_Adjustment = vRS_Adjustment;
	}

	public String getOther_Extraordinary_Income() {
		return other_Extraordinary_Income;
	}

	public void setOther_Extraordinary_Income(String other_Extraordinary_Income) {
		this.other_Extraordinary_Income = other_Extraordinary_Income;
	}

	public String getTotal_Extraordinary_Income() {
		return total_Extraordinary_Income;
	}

	public void setTotal_Extraordinary_Income(String total_Extraordinary_Income) {
		this.total_Extraordinary_Income = total_Extraordinary_Income;
	}

	public String getTax_on_Extraordinary_Items() {
		return tax_on_Extraordinary_Items;
	}

	public void setTax_on_Extraordinary_Items(String tax_on_Extraordinary_Items) {
		this.tax_on_Extraordinary_Items = tax_on_Extraordinary_Items;
	}

	public String getNet_Extra_Ordinary_Income() {
		return net_Extra_Ordinary_Income;
	}

	public void setNet_Extra_Ordinary_Income(String net_Extra_Ordinary_Income) {
		this.net_Extra_Ordinary_Income = net_Extra_Ordinary_Income;
	}

	public String getGross_Profit() {
		return gross_Profit;
	}

	public void setGross_Profit(String gross_Profit) {
		this.gross_Profit = gross_Profit;
	}

	public String getInterest() {
		return interest;
	}

	public void setInterest(String interest) {
		this.interest = interest;
	}

	public String getPBDT() {
		return pBDT;
	}

	public void setPBDT(String pBDT) {
		this.pBDT = pBDT;
	}

	public String getDepreciation() {
		return depreciation;
	}

	public void setDepreciation(String depreciation) {
		this.depreciation = depreciation;
	}

	public String getDepreciation_on_Revaluation_of_Assets() {
		return depreciation_on_Revaluation_of_Assets;
	}

	public void setDepreciation_on_Revaluation_of_Assets(
			String depreciation_on_Revaluation_of_Assets) {
		this.depreciation_on_Revaluation_of_Assets = depreciation_on_Revaluation_of_Assets;
	}

	public String getPBT() {
		return pBT;
	}

	public void setPBT(String pBT) {
		this.pBT = pBT;
	}

	public String getTax() {
		return tax;
	}

	public void setTax(String tax) {
		this.tax = tax;
	}

	public String getNet_Profit() {
		return net_Profit;
	}

	public void setNet_Profit(String net_Profit) {
		this.net_Profit = net_Profit;
	}

	public String getPrior_Years_Income() {
		return prior_Years_Income;
	}

	public void setPrior_Years_Income(String prior_Years_Income) {
		this.prior_Years_Income = prior_Years_Income;
	}

	public String getDepreciation_for_Previous_Years_Written_Back_Provided() {
		return depreciation_for_Previous_Years_Written_Back_Provided;
	}

	public void setDepreciation_for_Previous_Years_Written_Back_Provided(
			String depreciation_for_Previous_Years_Written_Back_Provided) {
		this.depreciation_for_Previous_Years_Written_Back_Provided = depreciation_for_Previous_Years_Written_Back_Provided;
	}

	public String getDividend() {
		return dividend;
	}

	public void setDividend(String dividend) {
		this.dividend = dividend;
	}

	public String getDividend_Tax() {
		return dividend_Tax;
	}

	public void setDividend_Tax(String dividend_Tax) {
		this.dividend_Tax = dividend_Tax;
	}

	public String getDividend_Percent() {
		return dividend_Percent;
	}

	public void setDividend_Percent(String dividend_Percent) {
		this.dividend_Percent = dividend_Percent;
	}

	public String getEarnings_Per_Share() {
		return earnings_Per_Share;
	}

	public void setEarnings_Per_Share(String earnings_Per_Share) {
		this.earnings_Per_Share = earnings_Per_Share;
	}

	public String getBook_Value() {
		return book_Value;
	}

	public void setBook_Value(String book_Value) {
		this.book_Value = book_Value;
	}

	public String getEquity() {
		return equity;
	}

	public void setEquity(String equity) {
		this.equity = equity;
	}

	public String getReserves() {
		return reserves;
	}

	public void setReserves(String reserves) {
		this.reserves = reserves;
	}

	public String getFace_Value() {
		return face_Value;
	}

	public void setFace_Value(String face_Value) {
		this.face_Value = face_Value;
	}

	public String getSales_Turnover_AttrName() {
		return sales_Turnover_AttrName;
	}

	public void setSales_Turnover_AttrName(String sales_Turnover_AttrName) {
		this.sales_Turnover_AttrName = sales_Turnover_AttrName;
	}

	public String getOther_Income_AttrName() {
		return other_Income_AttrName;
	}

	public void setOther_Income_AttrName(String other_Income_AttrName) {
		this.other_Income_AttrName = other_Income_AttrName;
	}

	public String getTotal_Income_AttrName() {
		return total_Income_AttrName;
	}

	public void setTotal_Income_AttrName(String total_Income_AttrName) {
		this.total_Income_AttrName = total_Income_AttrName;
	}

	public String getTotal_Expenses_AttrName() {
		return total_Expenses_AttrName;
	}

	public void setTotal_Expenses_AttrName(String total_Expenses_AttrName) {
		this.total_Expenses_AttrName = total_Expenses_AttrName;
	}

	public String getOperating_Profit_AttrName() {
		return operating_Profit_AttrName;
	}

	public void setOperating_Profit_AttrName(String operating_Profit_AttrName) {
		this.operating_Profit_AttrName = operating_Profit_AttrName;
	}

	public String getProfit_on_Sale_of_Assets_AttrName() {
		return profit_on_Sale_of_Assets_AttrName;
	}

	public void setProfit_on_Sale_of_Assets_AttrName(
			String profit_on_Sale_of_Assets_AttrName) {
		this.profit_on_Sale_of_Assets_AttrName = profit_on_Sale_of_Assets_AttrName;
	}

	public String getProfit_on_Sale_of_Investments_AttrName() {
		return profit_on_Sale_of_Investments_AttrName;
	}

	public void setProfit_on_Sale_of_Investments_AttrName(
			String profit_on_Sale_of_Investments_AttrName) {
		this.profit_on_Sale_of_Investments_AttrName = profit_on_Sale_of_Investments_AttrName;
	}

	public String getGain_Loss_on_Foreign_Exchange_AttrName() {
		return gain_Loss_on_Foreign_Exchange_AttrName;
	}

	public void setGain_Loss_on_Foreign_Exchange_AttrName(
			String gain_Loss_on_Foreign_Exchange_AttrName) {
		this.gain_Loss_on_Foreign_Exchange_AttrName = gain_Loss_on_Foreign_Exchange_AttrName;
	}

	public String getVRS_Adjustment_AttrName() {
		return vRS_Adjustment_AttrName;
	}

	public void setVRS_Adjustment_AttrName(String vRS_Adjustment_AttrName) {
		this.vRS_Adjustment_AttrName = vRS_Adjustment_AttrName;
	}

	public String getOther_Extraordinary_Income_AttrName() {
		return other_Extraordinary_Income_AttrName;
	}

	public void setOther_Extraordinary_Income_AttrName(
			String other_Extraordinary_Income_AttrName) {
		this.other_Extraordinary_Income_AttrName = other_Extraordinary_Income_AttrName;
	}

	public String getTotal_Extraordinary_Income_AttrName() {
		return total_Extraordinary_Income_AttrName;
	}

	public void setTotal_Extraordinary_Income_AttrName(
			String total_Extraordinary_Income_AttrName) {
		this.total_Extraordinary_Income_AttrName = total_Extraordinary_Income_AttrName;
	}

	public String getTax_on_Extraordinary_Items_AttrName() {
		return tax_on_Extraordinary_Items_AttrName;
	}

	public void setTax_on_Extraordinary_Items_AttrName(
			String tax_on_Extraordinary_Items_AttrName) {
		this.tax_on_Extraordinary_Items_AttrName = tax_on_Extraordinary_Items_AttrName;
	}

	public String getNet_Extra_Ordinary_Income_AttrName() {
		return net_Extra_Ordinary_Income_AttrName;
	}

	public void setNet_Extra_Ordinary_Income_AttrName(
			String net_Extra_Ordinary_Income_AttrName) {
		this.net_Extra_Ordinary_Income_AttrName = net_Extra_Ordinary_Income_AttrName;
	}

	public String getGross_Profit_AttrName() {
		return gross_Profit_AttrName;
	}

	public void setGross_Profit_AttrName(String gross_Profit_AttrName) {
		this.gross_Profit_AttrName = gross_Profit_AttrName;
	}

	public String getInterest_AttrName() {
		return interest_AttrName;
	}

	public void setInterest_AttrName(String interest_AttrName) {
		this.interest_AttrName = interest_AttrName;
	}

	public String getPBDT_AttrName() {
		return pBDT_AttrName;
	}

	public void setPBDT_AttrName(String pBDT_AttrName) {
		this.pBDT_AttrName = pBDT_AttrName;
	}

	public String getDepreciation_AttrName() {
		return depreciation_AttrName;
	}

	public void setDepreciation_AttrName(String depreciation_AttrName) {
		this.depreciation_AttrName = depreciation_AttrName;
	}

	public String getDepreciation_on_Revaluation_of_Assets_AttrName() {
		return depreciation_on_Revaluation_of_Assets_AttrName;
	}

	public void setDepreciation_on_Revaluation_of_Assets_AttrName(
			String depreciation_on_Revaluation_of_Assets_AttrName) {
		this.depreciation_on_Revaluation_of_Assets_AttrName = depreciation_on_Revaluation_of_Assets_AttrName;
	}

	public String getPBT_AttrName() {
		return pBT_AttrName;
	}

	public void setPBT_AttrName(String pBT_AttrName) {
		this.pBT_AttrName = pBT_AttrName;
	}

	public String getTax_AttrName() {
		return tax_AttrName;
	}

	public void setTax_AttrName(String tax_AttrName) {
		this.tax_AttrName = tax_AttrName;
	}

	public String getNet_Profit_AttrName() {
		return net_Profit_AttrName;
	}

	public void setNet_Profit_AttrName(String net_Profit_AttrName) {
		this.net_Profit_AttrName = net_Profit_AttrName;
	}

	public String getPrior_Years_Income_AttrName() {
		return prior_Years_Income_AttrName;
	}

	public void setPrior_Years_Income_AttrName(
			String prior_Years_Income_AttrName) {
		this.prior_Years_Income_AttrName = prior_Years_Income_AttrName;
	}

	public String getDepreciation_for_Previous_Years_Written_Back_Provided_AttrName() {
		return depreciation_for_Previous_Years_Written_Back_Provided_AttrName;
	}

	public void setDepreciation_for_Previous_Years_Written_Back_Provided_AttrName(
			String depreciation_for_Previous_Years_Written_Back_Provided_AttrName) {
		this.depreciation_for_Previous_Years_Written_Back_Provided_AttrName = depreciation_for_Previous_Years_Written_Back_Provided_AttrName;
	}

	public String getDividend_AttrName() {
		return dividend_AttrName;
	}

	public void setDividend_AttrName(String dividend_AttrName) {
		this.dividend_AttrName = dividend_AttrName;
	}

	public String getDividend_Tax_AttrName() {
		return dividend_Tax_AttrName;
	}

	public void setDividend_Tax_AttrName(String dividend_Tax_AttrName) {
		this.dividend_Tax_AttrName = dividend_Tax_AttrName;
	}

	public String getDividend_Percent_AttrName() {
		return dividend_Percent_AttrName;
	}

	public void setDividend_Percent_AttrName(String dividend_Percent_AttrName) {
		this.dividend_Percent_AttrName = dividend_Percent_AttrName;
	}

	public String getEarnings_Per_Share_AttrName() {
		return earnings_Per_Share_AttrName;
	}

	public void setEarnings_Per_Share_AttrName(
			String earnings_Per_Share_AttrName) {
		this.earnings_Per_Share_AttrName = earnings_Per_Share_AttrName;
	}

	public String getBook_Value_AttrName() {
		return book_Value_AttrName;
	}

	public void setBook_Value_AttrName(String book_Value_AttrName) {
		this.book_Value_AttrName = book_Value_AttrName;
	}

	public String getEquity_AttrName() {
		return equity_AttrName;
	}

	public void setEquity_AttrName(String equity_AttrName) {
		this.equity_AttrName = equity_AttrName;
	}

	public String getReserves_AttrName() {
		return reserves_AttrName;
	}

	public void setReserves_AttrName(String reserves_AttrName) {
		this.reserves_AttrName = reserves_AttrName;
	}

	public String getFace_Value_AttrName() {
		return face_Value_AttrName;
	}

	public void setFace_Value_AttrName(String face_Value_AttrName) {
		this.face_Value_AttrName = face_Value_AttrName;
	}

	public String getDividend_Per_Share() {
		return Dividend_Per_Share;
	}

	public void setDividend_Per_Share(String dividend_Per_Share) {
		Dividend_Per_Share = dividend_Per_Share;
	}

	public String getOperating_Profit_Per_Share() {
		return Operating_Profit_Per_Share;
	}

	public void setOperating_Profit_Per_Share(String operating_Profit_Per_Share) {
		Operating_Profit_Per_Share = operating_Profit_Per_Share;
	}

	public String getNet_Operating_Profit_Per_Share() {
		return Net_Operating_Profit_Per_Share;
	}

	public void setNet_Operating_Profit_Per_Share(
			String net_Operating_Profit_Per_Share) {
		Net_Operating_Profit_Per_Share = net_Operating_Profit_Per_Share;
	}

	public String getFree_Reserves_Per_Share() {
		return Free_Reserves_Per_Share;
	}

	public void setFree_Reserves_Per_Share(String free_Reserves_Per_Share) {
		Free_Reserves_Per_Share = free_Reserves_Per_Share;
	}

	public String getDividend_Per_Share_AttrName() {
		return Dividend_Per_Share_AttrName;
	}

	public void setDividend_Per_Share_AttrName(
			String dividend_Per_Share_AttrName) {
		Dividend_Per_Share_AttrName = dividend_Per_Share_AttrName;
	}

	public String getOperating_Profit_Per_Share_AttrName() {
		return Operating_Profit_Per_Share_AttrName;
	}

	public void setOperating_Profit_Per_Share_AttrName(
			String operating_Profit_Per_Share_AttrName) {
		Operating_Profit_Per_Share_AttrName = operating_Profit_Per_Share_AttrName;
	}

	public String getNet_Operating_Profit_Per_Share_AttrName() {
		return Net_Operating_Profit_Per_Share_AttrName;
	}

	public void setNet_Operating_Profit_Per_Share_AttrName(
			String net_Operating_Profit_Per_Share_AttrName) {
		Net_Operating_Profit_Per_Share_AttrName = net_Operating_Profit_Per_Share_AttrName;
	}

	public String getFree_Reserves_Per_Share_AttrName() {
		return Free_Reserves_Per_Share_AttrName;
	}

	public void setFree_Reserves_Per_Share_AttrName(
			String free_Reserves_Per_Share_AttrName) {
		Free_Reserves_Per_Share_AttrName = free_Reserves_Per_Share_AttrName;
	}

	public String getBonus_in_Equity_Capital() {
		return Bonus_in_Equity_Capital;
	}

	public void setBonus_in_Equity_Capital(String bonus_in_Equity_Capital) {
		Bonus_in_Equity_Capital = bonus_in_Equity_Capital;
	}

	public String getOperating_Profit_Margin() {
		return Operating_Profit_Margin;
	}

	public void setOperating_Profit_Margin(String operating_Profit_Margin) {
		Operating_Profit_Margin = operating_Profit_Margin;
	}

	public String getBonus_in_Equity_Capital_AttrName() {
		return Bonus_in_Equity_Capital_AttrName;
	}

	public void setBonus_in_Equity_Capital_AttrName(
			String bonus_in_Equity_Capital_AttrName) {
		Bonus_in_Equity_Capital_AttrName = bonus_in_Equity_Capital_AttrName;
	}

	public String getOperating_Profit_Margin_AttrName() {
		return Operating_Profit_Margin_AttrName;
	}

	public void setOperating_Profit_Margin_AttrName(
			String operating_Profit_Margin_AttrName) {
		Operating_Profit_Margin_AttrName = operating_Profit_Margin_AttrName;
	}

	public String getProfit_Before_Interest_And_Tax_Margin() {
		return Profit_Before_Interest_And_Tax_Margin;
	}

	public void setProfit_Before_Interest_And_Tax_Margin(
			String profit_Before_Interest_And_Tax_Margin) {
		Profit_Before_Interest_And_Tax_Margin = profit_Before_Interest_And_Tax_Margin;
	}

	public String getGross_Profit_Margin() {
		return Gross_Profit_Margin;
	}

	public void setGross_Profit_Margin(String gross_Profit_Margin) {
		Gross_Profit_Margin = gross_Profit_Margin;
	}

	public String getCash_Profit_Margin() {
		return Cash_Profit_Margin;
	}

	public void setCash_Profit_Margin(String cash_Profit_Margin) {
		Cash_Profit_Margin = cash_Profit_Margin;
	}

	public String getProfit_Before_Interest_And_Tax_Margin_AttrName() {
		return Profit_Before_Interest_And_Tax_Margin_AttrName;
	}

	public void setProfit_Before_Interest_And_Tax_Margin_AttrName(
			String profit_Before_Interest_And_Tax_Margin_AttrName) {
		Profit_Before_Interest_And_Tax_Margin_AttrName = profit_Before_Interest_And_Tax_Margin_AttrName;
	}

	public String getGross_Profit_Margin_AttrName() {
		return Gross_Profit_Margin_AttrName;
	}

	public void setGross_Profit_Margin_AttrName(
			String gross_Profit_Margin_AttrName) {
		Gross_Profit_Margin_AttrName = gross_Profit_Margin_AttrName;
	}

	public String getCash_Profit_Margin_AttrName() {
		return Cash_Profit_Margin_AttrName;
	}

	public void setCash_Profit_Margin_AttrName(
			String cash_Profit_Margin_AttrName) {
		Cash_Profit_Margin_AttrName = cash_Profit_Margin_AttrName;
	}

	public String getAdjusted_Cash_Margin() {
		return Adjusted_Cash_Margin;
	}

	public void setAdjusted_Cash_Margin(String adjusted_Cash_Margin) {
		Adjusted_Cash_Margin = adjusted_Cash_Margin;
	}

	public String getNet_Profit_Margin() {
		return Net_Profit_Margin;
	}

	public void setNet_Profit_Margin(String net_Profit_Margin) {
		Net_Profit_Margin = net_Profit_Margin;
	}

	public String getAdjusted_Net_Profit_Margin() {
		return Adjusted_Net_Profit_Margin;
	}

	public void setAdjusted_Net_Profit_Margin(String adjusted_Net_Profit_Margin) {
		Adjusted_Net_Profit_Margin = adjusted_Net_Profit_Margin;
	}

	public String getReturn_On_Capital_Employed() {
		return Return_On_Capital_Employed;
	}

	public void setReturn_On_Capital_Employed(String return_On_Capital_Employed) {
		Return_On_Capital_Employed = return_On_Capital_Employed;
	}

	public String getReturn_On_Net_Worth() {
		return Return_On_Net_Worth;
	}

	public void setReturn_On_Net_Worth(String return_On_Net_Worth) {
		Return_On_Net_Worth = return_On_Net_Worth;
	}

	public String getAdjusted_Return_on_Net_Worth() {
		return Adjusted_Return_on_Net_Worth;
	}

	public void setAdjusted_Return_on_Net_Worth(
			String adjusted_Return_on_Net_Worth) {
		Adjusted_Return_on_Net_Worth = adjusted_Return_on_Net_Worth;
	}

	public String getReturn_on_Assets_Excluding_Revaluations() {
		return Return_on_Assets_Excluding_Revaluations;
	}

	public void setReturn_on_Assets_Excluding_Revaluations(
			String return_on_Assets_Excluding_Revaluations) {
		Return_on_Assets_Excluding_Revaluations = return_on_Assets_Excluding_Revaluations;
	}

	public String getReturn_on_Assets_Including_Revaluations() {
		return Return_on_Assets_Including_Revaluations;
	}

	public void setReturn_on_Assets_Including_Revaluations(
			String return_on_Assets_Including_Revaluations) {
		Return_on_Assets_Including_Revaluations = return_on_Assets_Including_Revaluations;
	}

	public String getReturn_on_Long_Term_Funds() {
		return Return_on_Long_Term_Funds;
	}

	public void setReturn_on_Long_Term_Funds(String return_on_Long_Term_Funds) {
		Return_on_Long_Term_Funds = return_on_Long_Term_Funds;
	}

	public String getCurrent_Ratio() {
		return Current_Ratio;
	}

	public void setCurrent_Ratio(String current_Ratio) {
		Current_Ratio = current_Ratio;
	}

	public String getQuick_Ratio() {
		return Quick_Ratio;
	}

	public void setQuick_Ratio(String quick_Ratio) {
		Quick_Ratio = quick_Ratio;
	}

	public String getDebt_Equity_Ratio() {
		return Debt_Equity_Ratio;
	}

	public void setDebt_Equity_Ratio(String debt_Equity_Ratio) {
		Debt_Equity_Ratio = debt_Equity_Ratio;
	}

	public String getLong_Term_Debt_Equity_Ratio() {
		return Long_Term_Debt_Equity_Ratio;
	}

	public void setLong_Term_Debt_Equity_Ratio(
			String long_Term_Debt_Equity_Ratio) {
		Long_Term_Debt_Equity_Ratio = long_Term_Debt_Equity_Ratio;
	}

	public String getInterest_Cover() {
		return Interest_Cover;
	}

	public void setInterest_Cover(String interest_Cover) {
		Interest_Cover = interest_Cover;
	}

	public String getTotal_Debt_to_Owners_Fund() {
		return Total_Debt_to_Owners_Fund;
	}

	public void setTotal_Debt_to_Owners_Fund(String total_Debt_to_Owners_Fund) {
		Total_Debt_to_Owners_Fund = total_Debt_to_Owners_Fund;
	}

	public String getFinancial_Charges_Coverage_Ratio() {
		return Financial_Charges_Coverage_Ratio;
	}

	public void setFinancial_Charges_Coverage_Ratio(
			String financial_Charges_Coverage_Ratio) {
		Financial_Charges_Coverage_Ratio = financial_Charges_Coverage_Ratio;
	}

	public String getFinancial_Charges_Coverage_Ratio_Post_Tax() {
		return Financial_Charges_Coverage_Ratio_Post_Tax;
	}

	public void setFinancial_Charges_Coverage_Ratio_Post_Tax(
			String financial_Charges_Coverage_Ratio_Post_Tax) {
		Financial_Charges_Coverage_Ratio_Post_Tax = financial_Charges_Coverage_Ratio_Post_Tax;
	}

	public String getInventory_Turnover_Ratio() {
		return Inventory_Turnover_Ratio;
	}

	public void setInventory_Turnover_Ratio(String inventory_Turnover_Ratio) {
		Inventory_Turnover_Ratio = inventory_Turnover_Ratio;
	}

	public String getDebtors_Turnover_Ratio() {
		return Debtors_Turnover_Ratio;
	}

	public void setDebtors_Turnover_Ratio(String debtors_Turnover_Ratio) {
		Debtors_Turnover_Ratio = debtors_Turnover_Ratio;
	}

	public String getInvestments_Turnover_Ratio() {
		return Investments_Turnover_Ratio;
	}

	public void setInvestments_Turnover_Ratio(String investments_Turnover_Ratio) {
		Investments_Turnover_Ratio = investments_Turnover_Ratio;
	}

	public String getFixed_Assets_Turnover_Ratio() {
		return Fixed_Assets_Turnover_Ratio;
	}

	public void setFixed_Assets_Turnover_Ratio(
			String fixed_Assets_Turnover_Ratio) {
		Fixed_Assets_Turnover_Ratio = fixed_Assets_Turnover_Ratio;
	}

	public String getTotal_Assets_Turnover_Ratio() {
		return Total_Assets_Turnover_Ratio;
	}

	public void setTotal_Assets_Turnover_Ratio(
			String total_Assets_Turnover_Ratio) {
		Total_Assets_Turnover_Ratio = total_Assets_Turnover_Ratio;
	}

	public String getAsset_Turnover_Ratio() {
		return Asset_Turnover_Ratio;
	}

	public void setAsset_Turnover_Ratio(String asset_Turnover_Ratio) {
		Asset_Turnover_Ratio = asset_Turnover_Ratio;
	}

	public String getAverage_Raw_Material_Holding() {
		return Average_Raw_Material_Holding;
	}

	public void setAverage_Raw_Material_Holding(
			String average_Raw_Material_Holding) {
		Average_Raw_Material_Holding = average_Raw_Material_Holding;
	}

	public String getAverage_Finished_Goods_Held() {
		return Average_Finished_Goods_Held;
	}

	public void setAverage_Finished_Goods_Held(
			String average_Finished_Goods_Held) {
		Average_Finished_Goods_Held = average_Finished_Goods_Held;
	}

	public String getNumber_of_Days_In_Working_Capital() {
		return Number_of_Days_In_Working_Capital;
	}

	public void setNumber_of_Days_In_Working_Capital(
			String number_of_Days_In_Working_Capital) {
		Number_of_Days_In_Working_Capital = number_of_Days_In_Working_Capital;
	}

	public String getMaterial_Cost_Composition() {
		return Material_Cost_Composition;
	}

	public void setMaterial_Cost_Composition(String material_Cost_Composition) {
		Material_Cost_Composition = material_Cost_Composition;
	}

	public String getImported_Composition_of_Raw_Materials_Consumed() {
		return Imported_Composition_of_Raw_Materials_Consumed;
	}

	public void setImported_Composition_of_Raw_Materials_Consumed(
			String imported_Composition_of_Raw_Materials_Consumed) {
		Imported_Composition_of_Raw_Materials_Consumed = imported_Composition_of_Raw_Materials_Consumed;
	}

	public String getSelling_Distribution_Cost_Composition() {
		return Selling_Distribution_Cost_Composition;
	}

	public void setSelling_Distribution_Cost_Composition(
			String selling_Distribution_Cost_Composition) {
		Selling_Distribution_Cost_Composition = selling_Distribution_Cost_Composition;
	}

	public String getExpenses_as_Composition_of_Total_Sales() {
		return Expenses_as_Composition_of_Total_Sales;
	}

	public void setExpenses_as_Composition_of_Total_Sales(
			String expenses_as_Composition_of_Total_Sales) {
		Expenses_as_Composition_of_Total_Sales = expenses_as_Composition_of_Total_Sales;
	}

	public String getDividend_Payout_Ratio_Net_Profit() {
		return Dividend_Payout_Ratio_Net_Profit;
	}

	public void setDividend_Payout_Ratio_Net_Profit(
			String dividend_Payout_Ratio_Net_Profit) {
		Dividend_Payout_Ratio_Net_Profit = dividend_Payout_Ratio_Net_Profit;
	}

	public String getDividend_Payout_Ratio_Cash_Profit() {
		return Dividend_Payout_Ratio_Cash_Profit;
	}

	public void setDividend_Payout_Ratio_Cash_Profit(
			String dividend_Payout_Ratio_Cash_Profit) {
		Dividend_Payout_Ratio_Cash_Profit = dividend_Payout_Ratio_Cash_Profit;
	}

	public String getEarning_Retention_Ratio() {
		return Earning_Retention_Ratio;
	}

	public void setEarning_Retention_Ratio(String earning_Retention_Ratio) {
		Earning_Retention_Ratio = earning_Retention_Ratio;
	}

	public String getCash_Earning_Retention_Ratio() {
		return Cash_Earning_Retention_Ratio;
	}

	public void setCash_Earning_Retention_Ratio(
			String cash_Earning_Retention_Ratio) {
		Cash_Earning_Retention_Ratio = cash_Earning_Retention_Ratio;
	}

	public String getAdjustedCash_Flow_Times() {
		return AdjustedCash_Flow_Times;
	}

	public void setAdjustedCash_Flow_Times(String adjustedCash_Flow_Times) {
		AdjustedCash_Flow_Times = adjustedCash_Flow_Times;
	}

	public String getAdjusted_Cash_Margin_AttrName() {
		return Adjusted_Cash_Margin_AttrName;
	}

	public void setAdjusted_Cash_Margin_AttrName(
			String adjusted_Cash_Margin_AttrName) {
		Adjusted_Cash_Margin_AttrName = adjusted_Cash_Margin_AttrName;
	}

	public String getNet_Profit_Margin_AttrName() {
		return Net_Profit_Margin_AttrName;
	}

	public void setNet_Profit_Margin_AttrName(String net_Profit_Margin_AttrName) {
		Net_Profit_Margin_AttrName = net_Profit_Margin_AttrName;
	}

	public String getAdjusted_Net_Profit_Margin_AttrName() {
		return Adjusted_Net_Profit_Margin_AttrName;
	}

	public void setAdjusted_Net_Profit_Margin_AttrName(
			String adjusted_Net_Profit_Margin_AttrName) {
		Adjusted_Net_Profit_Margin_AttrName = adjusted_Net_Profit_Margin_AttrName;
	}

	public String getReturn_On_Capital_Employed_AttrName() {
		return Return_On_Capital_Employed_AttrName;
	}

	public void setReturn_On_Capital_Employed_AttrName(
			String return_On_Capital_Employed_AttrName) {
		Return_On_Capital_Employed_AttrName = return_On_Capital_Employed_AttrName;
	}

	public String getReturn_On_Net_Worth_AttrName() {
		return Return_On_Net_Worth_AttrName;
	}

	public void setReturn_On_Net_Worth_AttrName(
			String return_On_Net_Worth_AttrName) {
		Return_On_Net_Worth_AttrName = return_On_Net_Worth_AttrName;
	}

	public String getAdjusted_Return_on_Net_Worth_AttrName() {
		return Adjusted_Return_on_Net_Worth_AttrName;
	}

	public void setAdjusted_Return_on_Net_Worth_AttrName(
			String adjusted_Return_on_Net_Worth_AttrName) {
		Adjusted_Return_on_Net_Worth_AttrName = adjusted_Return_on_Net_Worth_AttrName;
	}

	public String getReturn_on_Assets_Excluding_Revaluations_AttrName() {
		return Return_on_Assets_Excluding_Revaluations_AttrName;
	}

	public void setReturn_on_Assets_Excluding_Revaluations_AttrName(
			String return_on_Assets_Excluding_Revaluations_AttrName) {
		Return_on_Assets_Excluding_Revaluations_AttrName = return_on_Assets_Excluding_Revaluations_AttrName;
	}

	public String getReturn_on_Assets_Including_Revaluations_AttrName() {
		return Return_on_Assets_Including_Revaluations_AttrName;
	}

	public void setReturn_on_Assets_Including_Revaluations_AttrName(
			String return_on_Assets_Including_Revaluations_AttrName) {
		Return_on_Assets_Including_Revaluations_AttrName = return_on_Assets_Including_Revaluations_AttrName;
	}

	public String getReturn_on_Long_Term_Funds_AttrName() {
		return Return_on_Long_Term_Funds_AttrName;
	}

	public void setReturn_on_Long_Term_Funds_AttrName(
			String return_on_Long_Term_Funds_AttrName) {
		Return_on_Long_Term_Funds_AttrName = return_on_Long_Term_Funds_AttrName;
	}

	public String getCurrent_Ratio_AttrName() {
		return Current_Ratio_AttrName;
	}

	public void setCurrent_Ratio_AttrName(String current_Ratio_AttrName) {
		Current_Ratio_AttrName = current_Ratio_AttrName;
	}

	public String getQuick_Ratio_AttrName() {
		return Quick_Ratio_AttrName;
	}

	public void setQuick_Ratio_AttrName(String quick_Ratio_AttrName) {
		Quick_Ratio_AttrName = quick_Ratio_AttrName;
	}

	public String getDebt_Equity_Ratio_AttrName() {
		return Debt_Equity_Ratio_AttrName;
	}

	public void setDebt_Equity_Ratio_AttrName(String debt_Equity_Ratio_AttrName) {
		Debt_Equity_Ratio_AttrName = debt_Equity_Ratio_AttrName;
	}

	public String getLong_Term_Debt_Equity_Ratio_AttrName() {
		return Long_Term_Debt_Equity_Ratio_AttrName;
	}

	public void setLong_Term_Debt_Equity_Ratio_AttrName(
			String long_Term_Debt_Equity_Ratio_AttrName) {
		Long_Term_Debt_Equity_Ratio_AttrName = long_Term_Debt_Equity_Ratio_AttrName;
	}

	public String getInterest_Cover_AttrName() {
		return Interest_Cover_AttrName;
	}

	public void setInterest_Cover_AttrName(String interest_Cover_AttrName) {
		Interest_Cover_AttrName = interest_Cover_AttrName;
	}

	public String getTotal_Debt_to_Owners_Fund_AttrName() {
		return Total_Debt_to_Owners_Fund_AttrName;
	}

	public void setTotal_Debt_to_Owners_Fund_AttrName(
			String total_Debt_to_Owners_Fund_AttrName) {
		Total_Debt_to_Owners_Fund_AttrName = total_Debt_to_Owners_Fund_AttrName;
	}

	public String getFinancial_Charges_Coverage_Ratio_AttrName() {
		return Financial_Charges_Coverage_Ratio_AttrName;
	}

	public void setFinancial_Charges_Coverage_Ratio_AttrName(
			String financial_Charges_Coverage_Ratio_AttrName) {
		Financial_Charges_Coverage_Ratio_AttrName = financial_Charges_Coverage_Ratio_AttrName;
	}

	public String getFinancial_Charges_Coverage_Ratio_Post_Tax_AttrName() {
		return Financial_Charges_Coverage_Ratio_Post_Tax_AttrName;
	}

	public void setFinancial_Charges_Coverage_Ratio_Post_Tax_AttrName(
			String financial_Charges_Coverage_Ratio_Post_Tax_AttrName) {
		Financial_Charges_Coverage_Ratio_Post_Tax_AttrName = financial_Charges_Coverage_Ratio_Post_Tax_AttrName;
	}

	public String getInventory_Turnover_Ratio_AttrName() {
		return Inventory_Turnover_Ratio_AttrName;
	}

	public void setInventory_Turnover_Ratio_AttrName(
			String inventory_Turnover_Ratio_AttrName) {
		Inventory_Turnover_Ratio_AttrName = inventory_Turnover_Ratio_AttrName;
	}

	public String getDebtors_Turnover_Ratio_AttrName() {
		return Debtors_Turnover_Ratio_AttrName;
	}

	public void setDebtors_Turnover_Ratio_AttrName(
			String debtors_Turnover_Ratio_AttrName) {
		Debtors_Turnover_Ratio_AttrName = debtors_Turnover_Ratio_AttrName;
	}

	public String getInvestments_Turnover_Ratio_AttrName() {
		return Investments_Turnover_Ratio_AttrName;
	}

	public void setInvestments_Turnover_Ratio_AttrName(
			String investments_Turnover_Ratio_AttrName) {
		Investments_Turnover_Ratio_AttrName = investments_Turnover_Ratio_AttrName;
	}

	public String getFixed_Assets_Turnover_Ratio_AttrName() {
		return Fixed_Assets_Turnover_Ratio_AttrName;
	}

	public void setFixed_Assets_Turnover_Ratio_AttrName(
			String fixed_Assets_Turnover_Ratio_AttrName) {
		Fixed_Assets_Turnover_Ratio_AttrName = fixed_Assets_Turnover_Ratio_AttrName;
	}

	public String getTotal_Assets_Turnover_Ratio_AttrName() {
		return Total_Assets_Turnover_Ratio_AttrName;
	}

	public void setTotal_Assets_Turnover_Ratio_AttrName(
			String total_Assets_Turnover_Ratio_AttrName) {
		Total_Assets_Turnover_Ratio_AttrName = total_Assets_Turnover_Ratio_AttrName;
	}

	public String getAsset_Turnover_Ratio_AttrName() {
		return Asset_Turnover_Ratio_AttrName;
	}

	public void setAsset_Turnover_Ratio_AttrName(
			String asset_Turnover_Ratio_AttrName) {
		Asset_Turnover_Ratio_AttrName = asset_Turnover_Ratio_AttrName;
	}

	public String getAverage_Raw_Material_Holding_AttrName() {
		return Average_Raw_Material_Holding_AttrName;
	}

	public void setAverage_Raw_Material_Holding_AttrName(
			String average_Raw_Material_Holding_AttrName) {
		Average_Raw_Material_Holding_AttrName = average_Raw_Material_Holding_AttrName;
	}

	public String getAverage_Finished_Goods_Held_AttrName() {
		return Average_Finished_Goods_Held_AttrName;
	}

	public void setAverage_Finished_Goods_Held_AttrName(
			String average_Finished_Goods_Held_AttrName) {
		Average_Finished_Goods_Held_AttrName = average_Finished_Goods_Held_AttrName;
	}

	public String getNumber_of_Days_In_Working_Capital_AttrName() {
		return Number_of_Days_In_Working_Capital_AttrName;
	}

	public void setNumber_of_Days_In_Working_Capital_AttrName(
			String number_of_Days_In_Working_Capital_AttrName) {
		Number_of_Days_In_Working_Capital_AttrName = number_of_Days_In_Working_Capital_AttrName;
	}

	public String getMaterial_Cost_Composition_AttrName() {
		return Material_Cost_Composition_AttrName;
	}

	public void setMaterial_Cost_Composition_AttrName(
			String material_Cost_Composition_AttrName) {
		Material_Cost_Composition_AttrName = material_Cost_Composition_AttrName;
	}

	public String getImported_Composition_of_Raw_Materials_Consumed_AttrName() {
		return Imported_Composition_of_Raw_Materials_Consumed_AttrName;
	}

	public void setImported_Composition_of_Raw_Materials_Consumed_AttrName(
			String imported_Composition_of_Raw_Materials_Consumed_AttrName) {
		Imported_Composition_of_Raw_Materials_Consumed_AttrName = imported_Composition_of_Raw_Materials_Consumed_AttrName;
	}

	public String getSelling_Distribution_Cost_Composition_AttrName() {
		return Selling_Distribution_Cost_Composition_AttrName;
	}

	public void setSelling_Distribution_Cost_Composition_AttrName(
			String selling_Distribution_Cost_Composition_AttrName) {
		Selling_Distribution_Cost_Composition_AttrName = selling_Distribution_Cost_Composition_AttrName;
	}

	public String getExpenses_as_Composition_of_Total_Sales_AttrName() {
		return Expenses_as_Composition_of_Total_Sales_AttrName;
	}

	public void setExpenses_as_Composition_of_Total_Sales_AttrName(
			String expenses_as_Composition_of_Total_Sales_AttrName) {
		Expenses_as_Composition_of_Total_Sales_AttrName = expenses_as_Composition_of_Total_Sales_AttrName;
	}

	public String getDividend_Payout_Ratio_Net_Profit_AttrName() {
		return Dividend_Payout_Ratio_Net_Profit_AttrName;
	}

	public void setDividend_Payout_Ratio_Net_Profit_AttrName(
			String dividend_Payout_Ratio_Net_Profit_AttrName) {
		Dividend_Payout_Ratio_Net_Profit_AttrName = dividend_Payout_Ratio_Net_Profit_AttrName;
	}

	public String getDividend_Payout_Ratio_Cash_Profit_AttrName() {
		return Dividend_Payout_Ratio_Cash_Profit_AttrName;
	}

	public void setDividend_Payout_Ratio_Cash_Profit_AttrName(
			String dividend_Payout_Ratio_Cash_Profit_AttrName) {
		Dividend_Payout_Ratio_Cash_Profit_AttrName = dividend_Payout_Ratio_Cash_Profit_AttrName;
	}

	public String getEarning_Retention_Ratio_AttrName() {
		return Earning_Retention_Ratio_AttrName;
	}

	public void setEarning_Retention_Ratio_AttrName(
			String earning_Retention_Ratio_AttrName) {
		Earning_Retention_Ratio_AttrName = earning_Retention_Ratio_AttrName;
	}

	public String getCash_Earning_Retention_Ratio_AttrName() {
		return Cash_Earning_Retention_Ratio_AttrName;
	}

	public void setCash_Earning_Retention_Ratio_AttrName(
			String cash_Earning_Retention_Ratio_AttrName) {
		Cash_Earning_Retention_Ratio_AttrName = cash_Earning_Retention_Ratio_AttrName;
	}

	public String getAdjustedCash_Flow_Times_AttrName() {
		return AdjustedCash_Flow_Times_AttrName;
	}

	public void setAdjustedCash_Flow_Times_AttrName(
			String adjustedCash_Flow_Times_AttrName) {
		AdjustedCash_Flow_Times_AttrName = adjustedCash_Flow_Times_AttrName;
	}

	public String getvRS_Adjustment() {
		return vRS_Adjustment;
	}

	public void setvRS_Adjustment(String vRS_Adjustment) {
		this.vRS_Adjustment = vRS_Adjustment;
	}

	public String getpBDT() {
		return pBDT;
	}

	public void setpBDT(String pBDT) {
		this.pBDT = pBDT;
	}

	public String getpBT() {
		return pBT;
	}

	public void setpBT(String pBT) {
		this.pBT = pBT;
	}

	public String getBuffer_1() {
		return Buffer_1;
	}

	public void setBuffer_1(String buffer_1) {
		Buffer_1 = buffer_1;
	}

	public String getBuffer_2() {
		return Buffer_2;
	}

	public void setBuffer_2(String buffer_2) {
		Buffer_2 = buffer_2;
	}

	public String getBuffer_3() {
		return Buffer_3;
	}

	public void setBuffer_3(String buffer_3) {
		Buffer_3 = buffer_3;
	}

	public String getBuffer_4() {
		return Buffer_4;
	}

	public void setBuffer_4(String buffer_4) {
		Buffer_4 = buffer_4;
	}

	public String getBuffer_5() {
		return Buffer_5;
	}

	public void setBuffer_5(String buffer_5) {
		Buffer_5 = buffer_5;
	}

	public String getBuffer_6() {
		return Buffer_6;
	}

	public void setBuffer_6(String buffer_6) {
		Buffer_6 = buffer_6;
	}

	public String getBuffer_7() {
		return Buffer_7;
	}

	public void setBuffer_7(String buffer_7) {
		Buffer_7 = buffer_7;
	}

	public String getBuffer_8() {
		return Buffer_8;
	}

	public void setBuffer_8(String buffer_8) {
		Buffer_8 = buffer_8;
	}

	public String getBuffer_9() {
		return Buffer_9;
	}

	public void setBuffer_9(String buffer_9) {
		Buffer_9 = buffer_9;
	}

	public String getBuffer_10() {
		return Buffer_10;
	}

	public void setBuffer_10(String buffer_10) {
		Buffer_10 = buffer_10;
	}

	public String getBuffer_1_AttrName() {
		return Buffer_1_AttrName;
	}

	public void setBuffer_1_AttrName(String buffer_1_AttrName) {
		Buffer_1_AttrName = buffer_1_AttrName;
	}

	public String getBuffer_2_AttrName() {
		return Buffer_2_AttrName;
	}

	public void setBuffer_2_AttrName(String buffer_2_AttrName) {
		Buffer_2_AttrName = buffer_2_AttrName;
	}

	public String getBuffer_3_AttrName() {
		return Buffer_3_AttrName;
	}

	public void setBuffer_3_AttrName(String buffer_3_AttrName) {
		Buffer_3_AttrName = buffer_3_AttrName;
	}

	public String getBuffer_4_AttrName() {
		return Buffer_4_AttrName;
	}

	public void setBuffer_4_AttrName(String buffer_4_AttrName) {
		Buffer_4_AttrName = buffer_4_AttrName;
	}

	public String getBuffer_5_AttrName() {
		return Buffer_5_AttrName;
	}

	public void setBuffer_5_AttrName(String buffer_5_AttrName) {
		Buffer_5_AttrName = buffer_5_AttrName;
	}

	public String getBuffer_6_AttrName() {
		return Buffer_6_AttrName;
	}

	public void setBuffer_6_AttrName(String buffer_6_AttrName) {
		Buffer_6_AttrName = buffer_6_AttrName;
	}

	public String getBuffer_7_AttrName() {
		return Buffer_7_AttrName;
	}

	public void setBuffer_7_AttrName(String buffer_7_AttrName) {
		Buffer_7_AttrName = buffer_7_AttrName;
	}

	public String getBuffer_8_AttrName() {
		return Buffer_8_AttrName;
	}

	public void setBuffer_8_AttrName(String buffer_8_AttrName) {
		Buffer_8_AttrName = buffer_8_AttrName;
	}

	public String getBuffer_9_AttrName() {
		return Buffer_9_AttrName;
	}

	public void setBuffer_9_AttrName(String buffer_9_AttrName) {
		Buffer_9_AttrName = buffer_9_AttrName;
	}

	public String getBuffer_10_AttrName() {
		return Buffer_10_AttrName;
	}

	public void setBuffer_10_AttrName(String buffer_10_AttrName) {
		Buffer_10_AttrName = buffer_10_AttrName;
	}

	public String getvRS_Adjustment_AttrName() {
		return vRS_Adjustment_AttrName;
	}

	public void setvRS_Adjustment_AttrName(String vRS_Adjustment_AttrName) {
		this.vRS_Adjustment_AttrName = vRS_Adjustment_AttrName;
	}

	public String getpBDT_AttrName() {
		return pBDT_AttrName;
	}

	public void setpBDT_AttrName(String pBDT_AttrName) {
		this.pBDT_AttrName = pBDT_AttrName;
	}

	public String getpBT_AttrName() {
		return pBT_AttrName;
	}

	public void setpBT_AttrName(String pBT_AttrName) {
		this.pBT_AttrName = pBT_AttrName;
	}
}
