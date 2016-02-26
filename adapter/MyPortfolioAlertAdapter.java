package com.moneycontrol.handheld.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.divum.MoneyControl.R;
import com.moneycontrol.handheld.entity.myportfolio.PortfolioAlertData;
import com.moneycontrol.handheld.util.Utility;

public class MyPortfolioAlertAdapter extends BaseAdapter {

	private Context mContext;
	private LayoutInflater mInflator;
	private ArrayList<PortfolioAlertData> list = null;

	private String greenhtmlString = "<font color=#FFFFFF><b>%s</b></font> <font color=#FFFFFF>%s</font> <font color=#66CC00>%s</font>";
	private String redhtmlString = "<font color=#FFFFFF><b>%s</b></font><font color=#FFFFFF>%s</font>  <font color=#FF0033>%s</font>";

	public MyPortfolioAlertAdapter(Context context,
			ArrayList<PortfolioAlertData> list) {
		this.mContext = context;
		this.list = list;
		mInflator = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = null;
		if (convertView == null) {
			final ViewHolder vHolder = new ViewHolder();

			view = mInflator.inflate(R.layout.alert_port_layout, null);

			vHolder.tvalerttype = (TextView) view
					.findViewById(R.id.tv_alert_type_text);
			vHolder.tvalertstockName = (TextView) view
					.findViewById(R.id.tv_alert_stock_name);
			vHolder.tvalertstockPrice = (TextView) view
					.findViewById(R.id.tv_alert_stock_price);
			vHolder.tvalertstockPriceChange = (TextView) view
					.findViewById(R.id.tv_alert_stock_price_change);
			vHolder.tvalertstockcomment = (TextView) view
					.findViewById(R.id.tv_alert_stock_comment);
			vHolder.tvalertstockcommenttime = (TextView) view
					.findViewById(R.id.tv_alert_stock_comment_time);

			view.setTag(vHolder);

		} else {
			view = convertView;
		}

		ViewHolder holder = (ViewHolder) view.getTag();
		if (list.get(position).getSection() != null)
			holder.tvalerttype.setText(list.get(position).getSection()
					.toUpperCase());

		if (!TextUtils.isEmpty(list.get(position).getShortname())) {
			holder.tvalertstockName.setText(list.get(position).getShortname());
		}
		if (!TextUtils.isEmpty(list.get(position).getLastvalue())) {
			holder.tvalertstockPrice.setText(list.get(position).getLastvalue());
		}
		if (!TextUtils.isEmpty(list.get(position).getMessage())) {
			holder.tvalertstockcomment.setText(list.get(position).getMessage());
		}
		if (!TextUtils.isEmpty(list.get(position).getEntdate())) {
			// holder.tvalertstockcommenttime.setText(list.get(position)
			// .getEntdate());

			setTextc(holder.tvalertstockcomment, list.get(position)
					.getMessage(), list.get(position).getEntdate());
		}

		if (!TextUtils.isEmpty(list.get(position).getChange())) {

			setTextValue(holder.tvalertstockPriceChange, list.get(position)
					.getChange(), list.get(position).getPercentchange());

		} else {
			// holder.tvalertstockcommenttime.setVisibility(View.INVISIBLE);
		}

		return view;
	}

	class ViewHolder {
		public TextView tvalerttype, tvalertstockName, tvalertstockPrice,
				tvalertstockPriceChange, tvalertstockcomment,
				tvalertstockcommenttime;

	}

	private void setTextValue(TextView tx, String str, String strChange) {

		String val = "";
		if (!str.contains("-")) {
			tx.setTextColor(mContext.getResources().getColor(R.color.green));
			val = "+" + str + " (" + strChange + "%)";

		} else {

			tx.setTextColor(mContext.getResources().getColor(R.color.red));
			val = "" + str + " (" + strChange + "%)";
		}

		tx.setText((Html.fromHtml(val)));
	}

	private void setTextc(TextView tx, String text, String underText) {

		Spannable span = new SpannableString(text + " " + underText);
		span.setSpan(new AbsoluteSizeSpan((int) (tx.getTextSize() - 3)),
				text.length(), text.length() + underText.length() + 1,
				Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		span.setSpan(
				new ForegroundColorSpan(mContext.getResources().getColor(
						R.color.grey)), text.length(), text.length()
						+ underText.length() + 1,
				Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		span.setSpan(new ForegroundColorSpan(Color.GRAY), text.length(),
				text.length() + underText.length() + 1,
				Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tx.setText(span);
	}
}
