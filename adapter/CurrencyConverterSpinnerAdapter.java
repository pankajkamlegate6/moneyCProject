package com.moneycontrol.handheld.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.divum.MoneyControl.R;
import com.moneycontrol.handheld.currency.entity.CurrenciesData;
import com.moneycontrol.handheld.currency.entity.CurrenciesData.CurrencyCountry;
import com.moneycontrol.handheld.imageUtils.ImageDownloaderUtils;
import com.neopixl.pixlui.components.textview.TextView;
import com.nostra13.universalimageloader.core.ImageLoader;

public class CurrencyConverterSpinnerAdapter extends
		ArrayAdapter<CurrencyCountry> {
	ArrayList<CurrenciesData.CurrencyCountry> list = new ArrayList<CurrenciesData.CurrencyCountry>();

	private Context mContext;
	LayoutInflater inflater;

	public CurrencyConverterSpinnerAdapter(Context mContext,
			ArrayList<CurrenciesData.CurrencyCountry> list) {
		super(mContext, R.layout.row_currency_converter, list);
		this.mContext = mContext;
		inflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.list = list;
	}

	@Override
	public View getDropDownView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		return dropDownView(position, convertView, parent);

	}

	public View dropDownView(int position, View convertView, ViewGroup parent) {

		View view = convertView;
		ViewHolder viewHolder;
		if (view == null) {
			view = inflater.inflate(R.layout.dropdown_currency_converter, null);
			viewHolder = new ViewHolder();
			viewHolder.countryName = (TextView) view
					.findViewById(R.id.countryName);
			viewHolder.flageImageView = (ImageView) view
					.findViewById(R.id.countryImage);
			view.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) view.getTag();
		}
		ImageLoader.getInstance().displayImage(
				list.get(position).getUrlimage(), viewHolder.flageImageView,
				ImageDownloaderUtils.displayImageOptionsNormal());
		viewHolder.countryName.setText(list.get(position).getCountryName());
		return view;

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public CurrencyCountry getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		ViewHolder viewHolder;
		if (view == null) {
			view = inflater.inflate(R.layout.row_currency_converter, null);
			viewHolder = new ViewHolder();
			viewHolder.countryName = (TextView) view
					.findViewById(R.id.countryName);
			viewHolder.flageImageView = (ImageView) view
					.findViewById(R.id.countryImage);
			view.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) view.getTag();
		}
		ImageLoader.getInstance().displayImage(
				list.get(position).getUrlimage(), viewHolder.flageImageView,
				ImageDownloaderUtils.displayImageOptionsNormal());
		viewHolder.countryName.setText(list.get(position).getCountryName());
		return view;

	}

	private class ViewHolder {
		private TextView countryName;
		private ImageView flageImageView;
	}

}
