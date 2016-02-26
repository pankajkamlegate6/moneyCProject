package com.moneycontrol.handheld.currency.fragments;

import com.moneycontrol.handheld.currency.entity.CurrenciesData;
import com.moneycontrol.handheld.massages.fragments.BaseFragement;

public abstract class BaseCurrencyFragment extends BaseFragement {

	abstract void onUpdateData(CurrenciesData currencydata);

	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		// TODO Auto-generated method stub
		super.setUserVisibleHint(isVisibleToUser);
		 hideAndShowAds(isVisibleToUser);

	}

}
