package com.moneycontrol.handheld.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.divum.MoneyControl.R;
import com.moneycontrol.handheld.entity.messages.DropDownBean;
import com.neopixl.pixlui.components.textview.TextView;

public class ChartDropdownAdapter extends ArrayAdapter<DropDownBean> {
	ArrayList<DropDownBean> list = new ArrayList<DropDownBean>();

	private Context mContext;
	LayoutInflater inflater;

	public ChartDropdownAdapter(Context mContext, ArrayList<DropDownBean> list) {
		super(mContext, R.layout.row_spinner, list);
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
			view = inflater.inflate(R.layout.dropdown_item, null);
			viewHolder = new ViewHolder();
			viewHolder.name = (TextView) view.findViewById(R.id.chart_name);

			view.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) view.getTag();
		}

		viewHolder.name.setText(list.get(position).getName());
		return view;

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public DropDownBean getItem(int position) {
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
			view = inflater.inflate(R.layout.row_spinner, null);
			viewHolder = new ViewHolder();
			viewHolder.name = (TextView) view.findViewById(R.id.chart_name);

			view.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) view.getTag();
		}

		viewHolder.name.setText(list.get(position).getName());
		return view;

	}

	private class ViewHolder {
		private TextView name;

	}

}