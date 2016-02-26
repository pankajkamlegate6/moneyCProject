package com.moneycontrol.handheld.entity.market;

public class BalanceSheetData {
	
	private String type  ;
	private String year  ;
	private String month  ;
	private String Total_Share_Capital  ;
	private String Equity_Share_Capital  ;
	private String Share_Application_Money  ;
	private String Preference_Share_Capital  ;
	private String Reserves   ;
	private String Revaluation_Reserves  ;
	private String Net_Worth  ;
	private String Deposits  ;
	private String Borrowings   ;
	private String Total_Debt   ;
	private String Other_Liabilities_and_Provisions  ;
	private String Total_Liabilities ;
	private String Cash_and_Balances_with_RBI  ;
	private String Balance_With_Banks   ;
	private String Advances  ;
	private String Investments  ;
	private String Gross_Block  ;
	private String Accumulated_Depreciation  ;
	private String Net_Block  ;
	private String Capital_Work_In_Progress   ;
	private String Other_Assets   ;
	private String Total_Assets   ;
	private String Contingent_Liabilities  ;
	private String Bills_for_collection  ;
	private String Book_Value  ;
	
	
	private String  Networth ;
	private String  Secured_Loans  ;
	private String  Unsecured_Loans;
	private String Less_Accum_Depreciation ;
	private String Inventories ;
	private String Sundry_Debtors ;
	private String Cash_and_Bank_Balance ;
	private String Total_Current_Assets ;
	private String Loans_and_Advances ;
	private String Fixed_Deposits ;
	private String Total_CA_Loans_and_Advances ;
	private String Deffered_Credit ;
	private String Current_Liabilities ;
	private String Provisions ;
	private String Total_CL_Provisions ;
	private String Net_Current_Assets ;
	private String Miscellaneous_Expenses ;
	private String Capital_Work_in_Progress ;
	private String Advances_Given_By_Banks ;
	private String Other_Assets_for_Banks ;
	private String Total_CL_and_Provisions ;
		
	
	
	private String key_type  ;
	private String key_year  ;
	private String key_month  ;
	private String key_Total_Share_Capital  ;
	private String key_Equity_Share_Capital  ;
	private String key_Share_Application_Money  ;
	private String key_Preference_Share_Capital  ;
	private String key_Reserves   ;
	private String key_Revaluation_Reserves  ;
	private String key_Net_Worth  ;
	private String key_Deposits  ;
	private String key_Borrowings   ;
	private String key_Total_Debt   ;
	private String key_Other_Liabilities_and_Provisions  ;
	private String key_Total_Liabilities ;
	private String key_Cash_and_Balances_with_RBI  ;
	private String key_Balance_With_Banks   ;
	private String key_Advances  ;
	private String key_Investments  ;
	private String key_Gross_Block  ;
	private String key_Accumulated_Depreciation  ;
	private String key_Net_Block  ;
	private String key_Capital_Work_In_Progress   ;
	private String key_Other_Assets   ;
	private String key_Total_Assets   ;
	private String key_Contingent_Liabilities  ;
	private String key_Bills_for_collection  ;
	private String key_Book_Value  ;
	
	
	private String  key_Networth ;
	private String  key_Secured_Loans  ;
	private String  key_Unsecured_Loans ;
	private String key_Less_Accum_Depreciation ;
	private String key_Inventories ;
	private String key_Sundry_Debtors ;
	private String key_Cash_and_Bank_Balance ;
	private String key_Total_Current_Assets ;
	private String key_Loans_and_Advances ;
	private String key_Fixed_Deposits ;
	private String key_Total_CA_Loans_and_Advances ;
	private String key_Deffered_Credit ;
	private String key_Current_Liabilities ;
	private String key_Provisions ;
	private String key_Total_CL_Provisions ;
	private String key_Net_Current_Assets ;
	private String key_Miscellaneous_Expenses ;
	private String key_Total_Assetsv ;
	private String key_Capital_Work_in_Progress ;
	private String key_Advances_Given_By_Banks ;
	private String key_Other_Assets_for_Banks ;
	private String key_Total_CL_and_Provisions ;
	

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the year
	 */
	public String getYear() {
		return year;
	}
	/**
	 * @param year the year to set
	 */
	public void setYear(String year) {
		this.year = year;
	}
	/**
	 * @return the month
	 */
	public String getMonth() {
		return month.substring(0,3);
	}
	/**
	 * @param month the month to set
	 */
	public void setMonth(String month) {
		this.month = month;
	}
	/**
	 * @return the total_Share_Capital
	 */
	public String getTotal_Share_Capital() {
		return Total_Share_Capital;
	}
	/**
	 * @param total_Share_Capital the total_Share_Capital to set
	 */
	public void setTotal_Share_Capital(String total_Share_Capital) {
		Total_Share_Capital = total_Share_Capital;
	}
	/**
	 * @return the equity_Share_Capital
	 */
	public String getEquity_Share_Capital() {
		return Equity_Share_Capital;
	}
	/**
	 * @param equity_Share_Capital the equity_Share_Capital to set
	 */
	public void setEquity_Share_Capital(String equity_Share_Capital) {
		Equity_Share_Capital = equity_Share_Capital;
	}
	/**
	 * @return the share_Application_Money
	 */
	public String getShare_Application_Money() {
		return Share_Application_Money;
	}
	/**
	 * @param share_Application_Money the share_Application_Money to set
	 */
	public void setShare_Application_Money(String share_Application_Money) {
		Share_Application_Money = share_Application_Money;
	}
	/**
	 * @return the preference_Share_Capital
	 */
	public String getPreference_Share_Capital() {
		return Preference_Share_Capital;
	}
	/**
	 * @param preference_Share_Capital the preference_Share_Capital to set
	 */
	public void setPreference_Share_Capital(String preference_Share_Capital) {
		Preference_Share_Capital = preference_Share_Capital;
	}
	/**
	 * @return the reserves
	 */
	public String getReserves() {
		return Reserves;
	}
	/**
	 * @param reserves the reserves to set
	 */
	public void setReserves(String reserves) {
		Reserves = reserves;
	}
	/**
	 * @return the revaluation_Reserves
	 */
	public String getRevaluation_Reserves() {
		return Revaluation_Reserves;
	}
	/**
	 * @param revaluation_Reserves the revaluation_Reserves to set
	 */
	public void setRevaluation_Reserves(String revaluation_Reserves) {
		Revaluation_Reserves = revaluation_Reserves;
	}
	/**
	 * @return the net_Worth
	 */
	public String getNet_Worth() {
		return Net_Worth;
	}
	/**
	 * @param net_Worth the net_Worth to set
	 */
	public void setNet_Worth(String net_Worth) {
		Net_Worth = net_Worth;
	}
	/**
	 * @return the deposits
	 */
	public String getDeposits() {
		return Deposits;
	}
	/**
	 * @param deposits the deposits to set
	 */
	public void setDeposits(String deposits) {
		Deposits = deposits;
	}
	/**
	 * @return the borrowings
	 */
	public String getBorrowings() {
		return Borrowings;
	}
	/**
	 * @param borrowings the borrowings to set
	 */
	public void setBorrowings(String borrowings) {
		Borrowings = borrowings;
	}
	/**
	 * @return the total_Debt
	 */
	public String getTotal_Debt() {
		return Total_Debt;
	}
	/**
	 * @param total_Debt the total_Debt to set
	 */
	public void setTotal_Debt(String total_Debt) {
		Total_Debt = total_Debt;
	}
	/**
	 * @return the other_Liabilities_and_Provisions
	 */
	public String getOther_Liabilities_and_Provisions() {
		return Other_Liabilities_and_Provisions;
	}
	/**
	 * @param other_Liabilities_and_Provisions the other_Liabilities_and_Provisions to set
	 */
	public void setOther_Liabilities_and_Provisions(
			String other_Liabilities_and_Provisions) {
		Other_Liabilities_and_Provisions = other_Liabilities_and_Provisions;
	}
	/**
	 * @return the total_Liabilities
	 */
	public String getTotal_Liabilities() {
		return Total_Liabilities;
	}
	/**
	 * @param total_Liabilities the total_Liabilities to set
	 */
	public void setTotal_Liabilities(String total_Liabilities) {
		Total_Liabilities = total_Liabilities;
	}
	
	/**
	 * @return the cash_and_Balances_with_RBI
	 */
	public String getCash_and_Balances_with_RBI() {
		return Cash_and_Balances_with_RBI;
	}
	/**
	 * @param cash_and_Balances_with_RBI the cash_and_Balances_with_RBI to set
	 */
	public void setCash_and_Balances_with_RBI(String cash_and_Balances_with_RBI) {
		Cash_and_Balances_with_RBI = cash_and_Balances_with_RBI;
	}
	/**
	 * @return the balance_With_Banks
	 */
	public String getBalance_With_Banks() {
		return Balance_With_Banks;
	}
	/**
	 * @param balance_With_Banks the balance_With_Banks to set
	 */
	public void setBalance_With_Banks(String balance_With_Banks) {
		Balance_With_Banks = balance_With_Banks;
	}
	/**
	 * @return the advances
	 */
	public String getAdvances() {
		return Advances;
	}
	/**
	 * @param advances the advances to set
	 */
	public void setAdvances(String advances) {
		Advances = advances;
	}
	/**
	 * @return the investments
	 */
	public String getInvestments() {
		return Investments;
	}
	/**
	 * @param investments the investments to set
	 */
	public void setInvestments(String investments) {
		Investments = investments;
	}
	/**
	 * @return the gross_Block
	 */
	public String getGross_Block() {
		return Gross_Block;
	}
	/**
	 * @param gross_Block the gross_Block to set
	 */
	public void setGross_Block(String gross_Block) {
		Gross_Block = gross_Block;
	}
	/**
	 * @return the accumulated_Depreciation
	 */
	public String getAccumulated_Depreciation() {
		return Accumulated_Depreciation;
	}
	/**
	 * @param accumulated_Depreciation the accumulated_Depreciation to set
	 */
	public void setAccumulated_Depreciation(String accumulated_Depreciation) {
		Accumulated_Depreciation = accumulated_Depreciation;
	}
	/**
	 * @return the net_Block
	 */
	public String getNet_Block() {
		return Net_Block;
	}
	/**
	 * @param net_Block the net_Block to set
	 */
	public void setNet_Block(String net_Block) {
		Net_Block = net_Block;
	}
	/**
	 * @return the capital_Work_In_Progress
	 */
	public String getCapital_Work_In_Progress() {
		return Capital_Work_In_Progress;
	}
	/**
	 * @param capital_Work_In_Progress the capital_Work_In_Progress to set
	 */
	public void setCapital_Work_In_Progress(String capital_Work_In_Progress) {
		Capital_Work_In_Progress = capital_Work_In_Progress;
	}
	/**
	 * @return the other_Assets
	 */
	public String getOther_Assets() {
		return Other_Assets;
	}
	/**
	 * @param other_Assets the other_Assets to set
	 */
	public void setOther_Assets(String other_Assets) {
		Other_Assets = other_Assets;
	}
	/**
	 * @return the total_Assets
	 */
	public String getTotal_Assets() {
		return Total_Assets;
	}
	/**
	 * @param total_Assets the total_Assets to set
	 */
	public void setTotal_Assets(String total_Assets) {
		Total_Assets = total_Assets;
	}
	
	
	/**
	 * @return the contingent_Liabilities
	 */
	public String getContingent_Liabilities() {
		return Contingent_Liabilities;
	}
	/**
	 * @param contingent_Liabilities the contingent_Liabilities to set
	 */
	public void setContingent_Liabilities(String contingent_Liabilities) {
		Contingent_Liabilities = contingent_Liabilities;
	}
	/**
	 * @return the bills_for_collection
	 */
	public String getBills_for_collection() {
		return Bills_for_collection;
	}
	/**
	 * @param bills_for_collection the bills_for_collection to set
	 */
	public void setBills_for_collection(String bills_for_collection) {
		Bills_for_collection = bills_for_collection;
	}
	/**
	 * @return the book_Value
	 */
	public String getBook_Value() {
		return Book_Value;
	}
	/**
	 * @param book_Value the book_Value to set
	 */
	public void setBook_Value(String book_Value) {
		Book_Value = book_Value;
	}
	/**
	 * @return the networth
	 */
	public String getNetworth() {
		return Networth;
	}
	/**
	 * @param networth the networth to set
	 */
	public void setNetworth(String networth) {
		Networth = networth;
	}
	/**
	 * @return the secured_Loans
	 */
	public String getSecured_Loans() {
		return Secured_Loans;
	}
	/**
	 * @param secured_Loans the secured_Loans to set
	 */
	public void setSecured_Loans(String secured_Loans) {
		Secured_Loans = secured_Loans;
	}
	/**
	 * @return the unsecured_Loans
	 */
	public String getUnsecured_Loans() {
		return Unsecured_Loans;
	}
	/**
	 * @param unsecured_Loans the unsecured_Loans to set
	 */
	public void setUnsecured_Loans(String unsecured_Loans) {
		Unsecured_Loans = unsecured_Loans;
	}
	/**
	 * @return the less_Accum_Depreciation
	 */
	public String getLess_Accum_Depreciation() {
		return Less_Accum_Depreciation;
	}
	/**
	 * @param less_Accum_Depreciation the less_Accum_Depreciation to set
	 */
	public void setLess_Accum_Depreciation(String less_Accum_Depreciation) {
		Less_Accum_Depreciation = less_Accum_Depreciation;
	}
	/**
	 * @return the inventories
	 */
	public String getInventories() {
		return Inventories;
	}
	/**
	 * @param inventories the inventories to set
	 */
	public void setInventories(String inventories) {
		Inventories = inventories;
	}
	/**
	 * @return the sundry_Debtors
	 */
	public String getSundry_Debtors() {
		return Sundry_Debtors;
	}
	/**
	 * @param sundry_Debtors the sundry_Debtors to set
	 */
	public void setSundry_Debtors(String sundry_Debtors) {
		Sundry_Debtors = sundry_Debtors;
	}
	/**
	 * @return the cash_and_Bank_Balance
	 */
	public String getCash_and_Bank_Balance() {
		return Cash_and_Bank_Balance;
	}
	/**
	 * @param cash_and_Bank_Balance the cash_and_Bank_Balance to set
	 */
	public void setCash_and_Bank_Balance(String cash_and_Bank_Balance) {
		Cash_and_Bank_Balance = cash_and_Bank_Balance;
	}
	/**
	 * @return the total_Current_Assets
	 */
	public String getTotal_Current_Assets() {
		return Total_Current_Assets;
	}
	/**
	 * @param total_Current_Assets the total_Current_Assets to set
	 */
	public void setTotal_Current_Assets(String total_Current_Assets) {
		Total_Current_Assets = total_Current_Assets;
	}
	/**
	 * @return the loans_and_Advances
	 */
	public String getLoans_and_Advances() {
		return Loans_and_Advances;
	}
	/**
	 * @param loans_and_Advances the loans_and_Advances to set
	 */
	public void setLoans_and_Advances(String loans_and_Advances) {
		Loans_and_Advances = loans_and_Advances;
	}
	/**
	 * @return the fixed_Deposits
	 */
	public String getFixed_Deposits() {
		return Fixed_Deposits;
	}
	/**
	 * @param fixed_Deposits the fixed_Deposits to set
	 */
	public void setFixed_Deposits(String fixed_Deposits) {
		Fixed_Deposits = fixed_Deposits;
	}
	/**
	 * @return the total_CA_Loans_and_Advances
	 */
	public String getTotal_CA_Loans_and_Advances() {
		return Total_CA_Loans_and_Advances;
	}
	/**
	 * @param total_CA_Loans_and_Advances the total_CA_Loans_and_Advances to set
	 */
	public void setTotal_CA_Loans_and_Advances(String total_CA_Loans_and_Advances) {
		Total_CA_Loans_and_Advances = total_CA_Loans_and_Advances;
	}
	/**
	 * @return the deffered_Credit
	 */
	public String getDeffered_Credit() {
		return Deffered_Credit;
	}
	/**
	 * @param deffered_Credit the deffered_Credit to set
	 */
	public void setDeffered_Credit(String deffered_Credit) {
		Deffered_Credit = deffered_Credit;
	}
	/**
	 * @return the current_Liabilities
	 */
	public String getCurrent_Liabilities() {
		return Current_Liabilities;
	}
	/**
	 * @param current_Liabilities the current_Liabilities to set
	 */
	public void setCurrent_Liabilities(String current_Liabilities) {
		Current_Liabilities = current_Liabilities;
	}
	/**
	 * @return the provisions
	 */
	public String getProvisions() {
		return Provisions;
	}
	/**
	 * @param provisions the provisions to set
	 */
	public void setProvisions(String provisions) {
		Provisions = provisions;
	}
	/**
	 * @return the total_CL_Provisions
	 */
	public String getTotal_CL_Provisions() {
		return Total_CL_Provisions;
	}
	/**
	 * @param total_CL_Provisions the total_CL_Provisions to set
	 */
	public void setTotal_CL_Provisions(String total_CL_Provisions) {
		Total_CL_Provisions = total_CL_Provisions;
	}
	/**
	 * @return the net_Current_Assets
	 */
	public String getNet_Current_Assets() {
		return Net_Current_Assets;
	}
	/**
	 * @param net_Current_Assets the net_Current_Assets to set
	 */
	public void setNet_Current_Assets(String net_Current_Assets) {
		Net_Current_Assets = net_Current_Assets;
	}
	/**
	 * @return the miscellaneous_Expenses
	 */
	public String getMiscellaneous_Expenses() {
		return Miscellaneous_Expenses;
	}
	/**
	 * @param miscellaneous_Expenses the miscellaneous_Expenses to set
	 */
	public void setMiscellaneous_Expenses(String miscellaneous_Expenses) {
		Miscellaneous_Expenses = miscellaneous_Expenses;
	}
	
	/**
	 * @return the capital_Work_in_Progress
	 */
	public String getCapital_Work_in_Progress() {
		return Capital_Work_in_Progress;
	}
	/**
	 * @param capital_Work_in_Progress the capital_Work_in_Progress to set
	 */
	public void setCapital_Work_in_Progress(String capital_Work_in_Progress) {
		Capital_Work_in_Progress = capital_Work_in_Progress;
	}
	/**
	 * @return the advances_Given_By_Banks
	 */
	public String getAdvances_Given_By_Banks() {
		return Advances_Given_By_Banks;
	}
	/**
	 * @param advances_Given_By_Banks the advances_Given_By_Banks to set
	 */
	public void setAdvances_Given_By_Banks(String advances_Given_By_Banks) {
		Advances_Given_By_Banks = advances_Given_By_Banks;
	}
	/**
	 * @return the other_Assets_for_Banks
	 */
	public String getOther_Assets_for_Banks() {
		return Other_Assets_for_Banks;
	}
	/**
	 * @param other_Assets_for_Banks the other_Assets_for_Banks to set
	 */
	public void setOther_Assets_for_Banks(String other_Assets_for_Banks) {
		Other_Assets_for_Banks = other_Assets_for_Banks;
	}
	/**
	 * @return the total_CL_and_Provisions
	 */
	public String getTotal_CL_and_Provisions() {
		return Total_CL_and_Provisions;
	}
	/**
	 * @param total_CL_and_Provisions the total_CL_and_Provisions to set
	 */
	public void setTotal_CL_and_Provisions(String total_CL_and_Provisions) {
		Total_CL_and_Provisions = total_CL_and_Provisions;
	}
	/**
	 * @return the key_type
	 */
	public String getKey_type() {
		return key_type;
	}
	/**
	 * @param key_type the key_type to set
	 */
	public void setKey_type(String key_type) {
		this.key_type = key_type;
	}
	/**
	 * @return the key_year
	 */
	public String getKey_year() {
		return key_year;
	}
	/**
	 * @param key_year the key_year to set
	 */
	public void setKey_year(String key_year) {
		this.key_year = key_year;
	}
	/**
	 * @return the key_month
	 */
	public String getKey_month() {
		return key_month;
	}
	/**
	 * @param key_month the key_month to set
	 */
	public void setKey_month(String key_month) {
		this.key_month = key_month;
	}
	/**
	 * @return the key_Total_Share_Capital
	 */
	public String getKey_Total_Share_Capital() {
		return key_Total_Share_Capital;
	}
	/**
	 * @param key_Total_Share_Capital the key_Total_Share_Capital to set
	 */
	public void setKey_Total_Share_Capital(String key_Total_Share_Capital) {
		this.key_Total_Share_Capital = key_Total_Share_Capital;
	}
	/**
	 * @return the key_Equity_Share_Capital
	 */
	public String getKey_Equity_Share_Capital() {
		return key_Equity_Share_Capital;
	}
	/**
	 * @param key_Equity_Share_Capital the key_Equity_Share_Capital to set
	 */
	public void setKey_Equity_Share_Capital(String key_Equity_Share_Capital) {
		this.key_Equity_Share_Capital = key_Equity_Share_Capital;
	}
	/**
	 * @return the key_Share_Application_Money
	 */
	public String getKey_Share_Application_Money() {
		return key_Share_Application_Money;
	}
	/**
	 * @param key_Share_Application_Money the key_Share_Application_Money to set
	 */
	public void setKey_Share_Application_Money(String key_Share_Application_Money) {
		this.key_Share_Application_Money = key_Share_Application_Money;
	}
	/**
	 * @return the key_Preference_Share_Capital
	 */
	public String getKey_Preference_Share_Capital() {
		return key_Preference_Share_Capital;
	}
	/**
	 * @param key_Preference_Share_Capital the key_Preference_Share_Capital to set
	 */
	public void setKey_Preference_Share_Capital(String key_Preference_Share_Capital) {
		this.key_Preference_Share_Capital = key_Preference_Share_Capital;
	}
	/**
	 * @return the key_Reserves
	 */
	public String getKey_Reserves() {
		return key_Reserves;
	}
	/**
	 * @param key_Reserves the key_Reserves to set
	 */
	public void setKey_Reserves(String key_Reserves) {
		this.key_Reserves = key_Reserves;
	}
	/**
	 * @return the key_Revaluation_Reserves
	 */
	public String getKey_Revaluation_Reserves() {
		return key_Revaluation_Reserves;
	}
	/**
	 * @param key_Revaluation_Reserves the key_Revaluation_Reserves to set
	 */
	public void setKey_Revaluation_Reserves(String key_Revaluation_Reserves) {
		this.key_Revaluation_Reserves = key_Revaluation_Reserves;
	}
	/**
	 * @return the key_Net_Worth
	 */
	public String getKey_Net_Worth() {
		return key_Net_Worth;
	}
	/**
	 * @param key_Net_Worth the key_Net_Worth to set
	 */
	public void setKey_Net_Worth(String key_Net_Worth) {
		this.key_Net_Worth = key_Net_Worth;
	}
	/**
	 * @return the key_Deposits
	 */
	public String getKey_Deposits() {
		return key_Deposits;
	}
	/**
	 * @param key_Deposits the key_Deposits to set
	 */
	public void setKey_Deposits(String key_Deposits) {
		this.key_Deposits = key_Deposits;
	}
	/**
	 * @return the key_Borrowings
	 */
	public String getKey_Borrowings() {
		return key_Borrowings;
	}
	/**
	 * @param key_Borrowings the key_Borrowings to set
	 */
	public void setKey_Borrowings(String key_Borrowings) {
		this.key_Borrowings = key_Borrowings;
	}
	/**
	 * @return the key_Total_Debt
	 */
	public String getKey_Total_Debt() {
		return key_Total_Debt;
	}
	/**
	 * @param key_Total_Debt the key_Total_Debt to set
	 */
	public void setKey_Total_Debt(String key_Total_Debt) {
		this.key_Total_Debt = key_Total_Debt;
	}
	/**
	 * @return the key_Other_Liabilities_and_Provisions
	 */
	public String getKey_Other_Liabilities_and_Provisions() {
		return key_Other_Liabilities_and_Provisions;
	}
	/**
	 * @param key_Other_Liabilities_and_Provisions the key_Other_Liabilities_and_Provisions to set
	 */
	public void setKey_Other_Liabilities_and_Provisions(
			String key_Other_Liabilities_and_Provisions) {
		this.key_Other_Liabilities_and_Provisions = key_Other_Liabilities_and_Provisions;
	}
	/**
	 * @return the key_Total_Liabilities
	 */
	public String getKey_Total_Liabilities() {
		return key_Total_Liabilities;
	}
	/**
	 * @param key_Total_Liabilities the key_Total_Liabilities to set
	 */
	public void setKey_Total_Liabilities(String key_Total_Liabilities) {
		this.key_Total_Liabilities = key_Total_Liabilities;
	}
	/**
	 * @return the key_Cash_and_Balances_with_RBI
	 */
	public String getKey_Cash_and_Balances_with_RBI() {
		return key_Cash_and_Balances_with_RBI;
	}
	/**
	 * @param key_Cash_and_Balances_with_RBI the key_Cash_and_Balances_with_RBI to set
	 */
	public void setKey_Cash_and_Balances_with_RBI(
			String key_Cash_and_Balances_with_RBI) {
		this.key_Cash_and_Balances_with_RBI = key_Cash_and_Balances_with_RBI;
	}
	/**
	 * @return the key_Balance_With_Banks
	 */
	public String getKey_Balance_With_Banks() {
		return key_Balance_With_Banks;
	}
	/**
	 * @param key_Balance_With_Banks the key_Balance_With_Banks to set
	 */
	public void setKey_Balance_With_Banks(String key_Balance_With_Banks) {
		this.key_Balance_With_Banks = key_Balance_With_Banks;
	}
	/**
	 * @return the key_Advances
	 */
	public String getKey_Advances() {
		return key_Advances;
	}
	/**
	 * @param key_Advances the key_Advances to set
	 */
	public void setKey_Advances(String key_Advances) {
		this.key_Advances = key_Advances;
	}
	/**
	 * @return the key_Investments
	 */
	public String getKey_Investments() {
		return key_Investments;
	}
	/**
	 * @param key_Investments the key_Investments to set
	 */
	public void setKey_Investments(String key_Investments) {
		this.key_Investments = key_Investments;
	}
	/**
	 * @return the key_Gross_Block
	 */
	public String getKey_Gross_Block() {
		return key_Gross_Block;
	}
	/**
	 * @param key_Gross_Block the key_Gross_Block to set
	 */
	public void setKey_Gross_Block(String key_Gross_Block) {
		this.key_Gross_Block = key_Gross_Block;
	}
	/**
	 * @return the key_Accumulated_Depreciation
	 */
	public String getKey_Accumulated_Depreciation() {
		return key_Accumulated_Depreciation;
	}
	/**
	 * @param key_Accumulated_Depreciation the key_Accumulated_Depreciation to set
	 */
	public void setKey_Accumulated_Depreciation(String key_Accumulated_Depreciation) {
		this.key_Accumulated_Depreciation = key_Accumulated_Depreciation;
	}
	/**
	 * @return the key_Net_Block
	 */
	public String getKey_Net_Block() {
		return key_Net_Block;
	}
	/**
	 * @param key_Net_Block the key_Net_Block to set
	 */
	public void setKey_Net_Block(String key_Net_Block) {
		this.key_Net_Block = key_Net_Block;
	}
	/**
	 * @return the key_Capital_Work_In_Progress
	 */
	public String getKey_Capital_Work_In_Progress() {
		return key_Capital_Work_In_Progress;
	}
	/**
	 * @param key_Capital_Work_In_Progress the key_Capital_Work_In_Progress to set
	 */
	public void setKey_Capital_Work_In_Progress(String key_Capital_Work_In_Progress) {
		this.key_Capital_Work_In_Progress = key_Capital_Work_In_Progress;
	}
	/**
	 * @return the key_Other_Assets
	 */
	public String getKey_Other_Assets() {
		return key_Other_Assets;
	}
	/**
	 * @param key_Other_Assets the key_Other_Assets to set
	 */
	public void setKey_Other_Assets(String key_Other_Assets) {
		this.key_Other_Assets = key_Other_Assets;
	}
	/**
	 * @return the key_Total_Assets
	 */
	public String getKey_Total_Assets() {
		return key_Total_Assets;
	}
	/**
	 * @param key_Total_Assets the key_Total_Assets to set
	 */
	public void setKey_Total_Assets(String key_Total_Assets) {
		this.key_Total_Assets = key_Total_Assets;
	}
	/**
	 * @return the key_Contingent_Liabilities
	 */
	public String getKey_Contingent_Liabilities() {
		return key_Contingent_Liabilities;
	}
	/**
	 * @param key_Contingent_Liabilities the key_Contingent_Liabilities to set
	 */
	public void setKey_Contingent_Liabilities(String key_Contingent_Liabilities) {
		this.key_Contingent_Liabilities = key_Contingent_Liabilities;
	}
	/**
	 * @return the key_Bills_for_collection
	 */
	public String getKey_Bills_for_collection() {
		return key_Bills_for_collection;
	}
	/**
	 * @param key_Bills_for_collection the key_Bills_for_collection to set
	 */
	public void setKey_Bills_for_collection(String key_Bills_for_collection) {
		this.key_Bills_for_collection = key_Bills_for_collection;
	}
	/**
	 * @return the key_Book_Value
	 */
	public String getKey_Book_Value() {
		return key_Book_Value;
	}
	/**
	 * @param key_Book_Value the key_Book_Value to set
	 */
	public void setKey_Book_Value(String key_Book_Value) {
		this.key_Book_Value = key_Book_Value;
	}
	/**
	 * @return the key_Networth
	 */
	public String getKey_Networth() {
		return key_Networth;
	}
	/**
	 * @param key_Networth the key_Networth to set
	 */
	public void setKey_Networth(String key_Networth) {
		this.key_Networth = key_Networth;
	}
	/**
	 * @return the key_Secured_Loans
	 */
	public String getKey_Secured_Loans() {
		return key_Secured_Loans;
	}
	/**
	 * @param key_Secured_Loans the key_Secured_Loans to set
	 */
	public void setKey_Secured_Loans(String key_Secured_Loans) {
		this.key_Secured_Loans = key_Secured_Loans;
	}
	/**
	 * @return the key_Unsecured_Loans
	 */
	public String getKey_Unsecured_Loans() {
		return key_Unsecured_Loans;
	}
	/**
	 * @param key_Unsecured_Loans the key_Unsecured_Loans to set
	 */
	public void setKey_Unsecured_Loans(String key_Unsecured_Loans) {
		this.key_Unsecured_Loans = key_Unsecured_Loans;
	}
	/**
	 * @return the key_Less_Accum_Depreciation
	 */
	public String getKey_Less_Accum_Depreciation() {
		return key_Less_Accum_Depreciation;
	}
	/**
	 * @param key_Less_Accum_Depreciation the key_Less_Accum_Depreciation to set
	 */
	public void setKey_Less_Accum_Depreciation(String key_Less_Accum_Depreciation) {
		this.key_Less_Accum_Depreciation = key_Less_Accum_Depreciation;
	}
	/**
	 * @return the key_Inventories
	 */
	public String getKey_Inventories() {
		return key_Inventories;
	}
	/**
	 * @param key_Inventories the key_Inventories to set
	 */
	public void setKey_Inventories(String key_Inventories) {
		this.key_Inventories = key_Inventories;
	}
	/**
	 * @return the key_Sundry_Debtors
	 */
	public String getKey_Sundry_Debtors() {
		return key_Sundry_Debtors;
	}
	/**
	 * @param key_Sundry_Debtors the key_Sundry_Debtors to set
	 */
	public void setKey_Sundry_Debtors(String key_Sundry_Debtors) {
		this.key_Sundry_Debtors = key_Sundry_Debtors;
	}
	/**
	 * @return the key_Cash_and_Bank_Balance
	 */
	public String getKey_Cash_and_Bank_Balance() {
		return key_Cash_and_Bank_Balance;
	}
	/**
	 * @param key_Cash_and_Bank_Balance the key_Cash_and_Bank_Balance to set
	 */
	public void setKey_Cash_and_Bank_Balance(String key_Cash_and_Bank_Balance) {
		this.key_Cash_and_Bank_Balance = key_Cash_and_Bank_Balance;
	}
	/**
	 * @return the key_Total_Current_Assets
	 */
	public String getKey_Total_Current_Assets() {
		return key_Total_Current_Assets;
	}
	/**
	 * @param key_Total_Current_Assets the key_Total_Current_Assets to set
	 */
	public void setKey_Total_Current_Assets(String key_Total_Current_Assets) {
		this.key_Total_Current_Assets = key_Total_Current_Assets;
	}
	/**
	 * @return the key_Loans_and_Advances
	 */
	public String getKey_Loans_and_Advances() {
		return key_Loans_and_Advances;
	}
	/**
	 * @param key_Loans_and_Advances the key_Loans_and_Advances to set
	 */
	public void setKey_Loans_and_Advances(String key_Loans_and_Advances) {
		this.key_Loans_and_Advances = key_Loans_and_Advances;
	}
	/**
	 * @return the key_Fixed_Deposits
	 */
	public String getKey_Fixed_Deposits() {
		return key_Fixed_Deposits;
	}
	/**
	 * @param key_Fixed_Deposits the key_Fixed_Deposits to set
	 */
	public void setKey_Fixed_Deposits(String key_Fixed_Deposits) {
		this.key_Fixed_Deposits = key_Fixed_Deposits;
	}
	/**
	 * @return the key_Total_CA_Loans_and_Advances
	 */
	public String getKey_Total_CA_Loans_and_Advances() {
		return key_Total_CA_Loans_and_Advances;
	}
	/**
	 * @param key_Total_CA_Loans_and_Advances the key_Total_CA_Loans_and_Advances to set
	 */
	public void setKey_Total_CA_Loans_and_Advances(
			String key_Total_CA_Loans_and_Advances) {
		this.key_Total_CA_Loans_and_Advances = key_Total_CA_Loans_and_Advances;
	}
	/**
	 * @return the key_Deffered_Credit
	 */
	public String getKey_Deffered_Credit() {
		return key_Deffered_Credit;
	}
	/**
	 * @param key_Deffered_Credit the key_Deffered_Credit to set
	 */
	public void setKey_Deffered_Credit(String key_Deffered_Credit) {
		this.key_Deffered_Credit = key_Deffered_Credit;
	}
	/**
	 * @return the key_Current_Liabilities
	 */
	public String getKey_Current_Liabilities() {
		return key_Current_Liabilities;
	}
	/**
	 * @param key_Current_Liabilities the key_Current_Liabilities to set
	 */
	public void setKey_Current_Liabilities(String key_Current_Liabilities) {
		this.key_Current_Liabilities = key_Current_Liabilities;
	}
	/**
	 * @return the key_Provisions
	 */
	public String getKey_Provisions() {
		return key_Provisions;
	}
	/**
	 * @param key_Provisions the key_Provisions to set
	 */
	public void setKey_Provisions(String key_Provisions) {
		this.key_Provisions = key_Provisions;
	}
	/**
	 * @return the key_Total_CL_Provisions
	 */
	public String getKey_Total_CL_Provisions() {
		return key_Total_CL_Provisions;
	}
	/**
	 * @param key_Total_CL_Provisions the key_Total_CL_Provisions to set
	 */
	public void setKey_Total_CL_Provisions(String key_Total_CL_Provisions) {
		this.key_Total_CL_Provisions = key_Total_CL_Provisions;
	}
	/**
	 * @return the key_Net_Current_Assets
	 */
	public String getKey_Net_Current_Assets() {
		return key_Net_Current_Assets;
	}
	/**
	 * @param key_Net_Current_Assets the key_Net_Current_Assets to set
	 */
	public void setKey_Net_Current_Assets(String key_Net_Current_Assets) {
		this.key_Net_Current_Assets = key_Net_Current_Assets;
	}
	/**
	 * @return the key_Miscellaneous_Expenses
	 */
	public String getKey_Miscellaneous_Expenses() {
		return key_Miscellaneous_Expenses;
	}
	/**
	 * @param key_Miscellaneous_Expenses the key_Miscellaneous_Expenses to set
	 */
	public void setKey_Miscellaneous_Expenses(String key_Miscellaneous_Expenses) {
		this.key_Miscellaneous_Expenses = key_Miscellaneous_Expenses;
	}
	/**
	 * @return the key_Total_Assetsv
	 */
	public String getKey_Total_Assetsv() {
		return key_Total_Assetsv;
	}
	/**
	 * @param key_Total_Assetsv the key_Total_Assetsv to set
	 */
	public void setKey_Total_Assetsv(String key_Total_Assetsv) {
		this.key_Total_Assetsv = key_Total_Assetsv;
	}
	/**
	 * @return the key_Capital_Work_in_Progress
	 */
	public String getKey_Capital_Work_in_Progress() {
		return key_Capital_Work_in_Progress;
	}
	/**
	 * @param key_Capital_Work_in_Progress the key_Capital_Work_in_Progress to set
	 */
	public void setKey_Capital_Work_in_Progress(String key_Capital_Work_in_Progress) {
		this.key_Capital_Work_in_Progress = key_Capital_Work_in_Progress;
	}
	/**
	 * @return the key_Advances_Given_By_Banks
	 */
	public String getKey_Advances_Given_By_Banks() {
		return key_Advances_Given_By_Banks;
	}
	/**
	 * @param key_Advances_Given_By_Banks the key_Advances_Given_By_Banks to set
	 */
	public void setKey_Advances_Given_By_Banks(String key_Advances_Given_By_Banks) {
		this.key_Advances_Given_By_Banks = key_Advances_Given_By_Banks;
	}
	/**
	 * @return the key_Other_Assets_for_Banks
	 */
	public String getKey_Other_Assets_for_Banks() {
		return key_Other_Assets_for_Banks;
	}
	/**
	 * @param key_Other_Assets_for_Banks the key_Other_Assets_for_Banks to set
	 */
	public void setKey_Other_Assets_for_Banks(String key_Other_Assets_for_Banks) {
		this.key_Other_Assets_for_Banks = key_Other_Assets_for_Banks;
	}
	/**
	 * @return the key_Total_CL_and_Provisions
	 */
	public String getKey_Total_CL_and_Provisions() {
		return key_Total_CL_and_Provisions;
	}
	/**
	 * @param key_Total_CL_and_Provisions the key_Total_CL_and_Provisions to set
	 */
	public void setKey_Total_CL_and_Provisions(String key_Total_CL_and_Provisions) {
		this.key_Total_CL_and_Provisions = key_Total_CL_and_Provisions;
	}
	
	
}
