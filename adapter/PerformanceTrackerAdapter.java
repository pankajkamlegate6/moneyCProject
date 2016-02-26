package com.moneycontrol.handheld.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.text.Html.ImageGetter;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.divum.MoneyControl.R;
import com.moneycontrol.handheld.entity.mutualfunds.PTItemData;
import com.moneycontrol.handheld.util.Utility;

import java.util.ArrayList;

public class PerformanceTrackerAdapter extends BaseAdapter {
	private Context context;
	private LayoutInflater inflate = null;
	private ArrayList<PTItemData> ptItemData;
	private String ratedBy = "<img src=\"rated_by.png\" align=\"justify\"/>";

	public PerformanceTrackerAdapter(Context cnxt, ArrayList<PTItemData> data) {
		context = cnxt;
		inflate = (LayoutInflater) cnxt
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		ptItemData = data;
	}

	@Override
	public int getCount() {
		return ptItemData.size();
	}

	@Override
	public Object getItem(int position) {
		return ptItemData.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = inflate.inflate(
					R.layout.performance_tracker_item_layout, null);
			holder.tvFundName = (TextView) convertView
					.findViewById(R.id.tv_pt_fund_name);
			holder.tvFundReturns = (TextView) convertView
					.findViewById(R.id.tv_pt_fund_returns);
			// holder.rbFundRating = (RatingBar) convertView
			// .findViewById(R.id.ratingbar);
			holder.ivItemTopSeperator = (ImageView) convertView
					.findViewById(R.id.iv_pt_item_seperator);

			convertView.setTag(holder);
		} else
			holder = (ViewHolder) convertView.getTag();

		if (position == 0) {
			holder.ivItemTopSeperator.setVisibility(View.GONE);
		} else {
			holder.ivItemTopSeperator.setVisibility(View.VISIBLE);
		}
		if (!TextUtils.isEmpty(ptItemData.get(position).getFundName())) {
			holder.tvFundName.setText(ptItemData.get(position).getFundName());
		}
		if (!TextUtils.isEmpty(ptItemData.get(position).getFundReturns())) {
			String fundReturns = ptItemData.get(position).getFundReturns()
					+ "%";
			Utility.getInstance().addMinusPlusSign(context, fundReturns,
					holder.tvFundReturns);
		} else {

			holder.tvFundReturns.setText("");

		}
		String htmlString = ratingBar(ptItemData.get(position));
		Spanned htmlSpan = Html.fromHtml(htmlString != null ? htmlString : "",
				imgGetter, null);

		holder.tvFundName.setText(htmlSpan);

		return convertView;
	}

	class ViewHolder {
		public ImageView ivItemTopSeperator;
		public TextView tvFundName, tvFundReturns, tvdummyString;
		// public RatingBar rbFundRating;
	}

	private ImageGetter imgGetter = new ImageGetter() {

		public Drawable getDrawable(String source) {
			Drawable drawable = null;

			drawable = context.getResources().getDrawable(R.drawable.rated_by);

			drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
					drawable.getIntrinsicHeight());
			return drawable;
		}
	};

	public String ratingBar(PTItemData ptItemData) {
		String rating_string = "";
		double rating_star = Double.valueOf(ptItemData.getFundRating());

		if (rating_star > 0) {
			rating_string = "&nbsp;<br>";
		}

		for (int i = 0; i < rating_star; i++) {
			rating_string = rating_string
					+ "<img src='file:///res/drawable/img.png'/>";
		}

		String htmlString = "<font>%s</font>" + rating_string;

		rating_string = String.format(htmlString, ptItemData.getFundName());
		return rating_string;
	}

}
