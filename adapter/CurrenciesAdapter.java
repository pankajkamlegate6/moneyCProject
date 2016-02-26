package com.moneycontrol.handheld.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.divum.MoneyControl.R;
import com.moneycontrol.handheld.currency.entity.CurrenciesCarouselData;
import com.moneycontrol.handheld.util.ImageDownloader;
import com.moneycontrol.handheld.util.Utility;

public class CurrenciesAdapter extends BaseAdapter {

	private ArrayList<CurrenciesCarouselData> listCurrencies = null;
	private LayoutInflater inflate = null;
	private Activity context = null;

	public CurrenciesAdapter(ArrayList<CurrenciesCarouselData> list,
			Activity con) {
		inflate = (LayoutInflater) con
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		context = con;
		listCurrencies = list;
	}

	@Override
	public int getCount() {
		return listCurrencies.size();
	}

	@Override
	public Object getItem(int arg0) {
		return listCurrencies.get(arg0);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = inflate.inflate(R.layout.common_list_item, null);
			// convertView = inflate.inflate(R.layout.currencies_adapter, null);
			holder.name = (TextView) convertView
					.findViewById(R.id.tv_home_item_indices);
			holder.value = (TextView) convertView
					.findViewById(R.id.tv_home_item_lastprice);
			holder.flag = (ImageView) convertView
					.findViewById(R.id.iv_home_item_flag);
			holder.change = (TextView) convertView
					.findViewById(R.id.tv_home_item_change);
			holder.ivChange = (ImageView) convertView
					.findViewById(R.id.iv_home_item_change);
			holder.layout = (LinearLayout) convertView
					.findViewById(R.id.rl_home_item_maindata);
			convertView.findViewById(R.id.tv_home_item_timestamp)
					.setVisibility(View.GONE);
			convertView.findViewById(R.id.tv_home_item_volume).setVisibility(
					View.GONE);

			convertView.setTag(holder);
		} else
			holder = (ViewHolder) convertView.getTag();

		if (listCurrencies.get(position).getName() != null)
			holder.name.setText(Html.fromHtml(listCurrencies.get(position)
					.getName()));

		if (listCurrencies.get(position).getChange() != null) {

			// holder.change.setText(listCurrencies.get(position).getChange()
			// + "\n" + listCurrencies.get(position).getPerchange());

			Utility.getInstance().flipChangeTOPercentageData(holder.change,
					listCurrencies.get(position).getChange(),
					listCurrencies.get(position).getPerchange(),
					listCurrencies.get(position).getDirection(), context,
					holder.ivChange);

		}

		if (listCurrencies.get(position).getFlag() != null)
			new ImageDownloader().download(listCurrencies.get(position)
					.getFlag(), holder.flag);

		if (listCurrencies.get(position).getLastprice() != null)
			holder.value.setText(listCurrencies.get(position).getLastprice());
		holder.layout.setBackgroundColor(Color.BLACK);
		return convertView;
	}

	class ViewHolder {
		public TextView name, value;
		public TextView change;
		public ImageView ivChange;
		public ImageView flag;
		public LinearLayout layout;

	}

}
