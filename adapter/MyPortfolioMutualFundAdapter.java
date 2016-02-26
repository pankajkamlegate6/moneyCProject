package com.moneycontrol.handheld.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.divum.MoneyControl.R;
import com.moneycontrol.handheld.entity.mystocks.MyStocksMutualFundsItem;
import com.moneycontrol.handheld.util.Utility;

public class MyPortfolioMutualFundAdapter extends BaseAdapter {

	private Context mContext;
	private LayoutInflater mInflator;
	private ArrayList<MyStocksMutualFundsItem> list = null;
	
	public MyPortfolioMutualFundAdapter(Context context, ArrayList<MyStocksMutualFundsItem> list) {
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

			view = mInflator.inflate(R.layout.user_portfolio_item, null);

			vHolder.tvInvestments = (TextView) view
					.findViewById(R.id.tv_item_investments);
			vHolder.tvDayGain = (TextView) view
					.findViewById(R.id.tv_day_gain_change_value);
			vHolder.tvLatest = (TextView) view
					.findViewById(R.id.tv_item_latest_value);
			vHolder.ivChange = (ImageView) view
					.findViewById(R.id.iv_day_gain_change);
			vHolder.rlChange = (RelativeLayout) view
					.findViewById(R.id.rl_change);
			vHolder.tvInvestmentsValue = (TextView) view
					.findViewById(R.id.tv_item_investments_value);
			vHolder.tvNAVTitle = (TextView) view
					.findViewById(R.id.tv_item_nav);
			view.setTag(vHolder);

		} else {
			view = convertView;
		}

		ViewHolder holder = (ViewHolder) view.getTag();
		holder.tvNAVTitle.setVisibility(View.VISIBLE);
		holder.tvInvestments.setText(list.get(position).getFundname());
		holder.tvInvestmentsValue.setText(Utility.getInstance().applyDecimalPlaces(list.get(position).getLastvalue()));
		if(list.get(position).isDayGain()) {
			if (!TextUtils.isEmpty(list.get(position).getTodaysgain())) {
				Utility.getInstance().flipChangeTOPercentageData(
						holder.tvDayGain, Utility.getInstance().replaceDotApplyComma(list.get(position).getTodaysgain()),
						list.get(position).getPercentchange(),
						list.get(position).getDirection(), mContext,holder.ivChange);

			} else {
				holder.rlChange.setVisibility(View.INVISIBLE);
			}
		} else {
			if (!TextUtils.isEmpty(list.get(position).getOver_change())) {
				Utility.getInstance().flipChangeTOPercentageData(
						holder.tvDayGain, Utility.getInstance().replaceDotApplyComma(list.get(position).getOver_change()),
						list.get(position).getOver_percentchange(),
						list.get(position).getOver_direction(), mContext,holder.ivChange);

			} else {
				holder.rlChange.setVisibility(View.INVISIBLE);
			}
		}
		
//		if(list.get(position).isLatestValue()) {
//			holder.tvLatest.setText("");
//			//holder.tvLatest.setText(list.get(position).get);
//		} else {
//			holder.tvLatest.setText(list.get(position).getPurchase_price());
//		}
		
//		if(list.get(position).getLatestValuetoggle()==0) {
//			holder.tvLatest.setText(Utility.getInstance().replaceDotApplyComma(list.get(position).getLatestValue()));
//			//holder.tvLatest.setText(list.get(position).get);
//		} else if(list.get(position).getLatestValuetoggle()==1){
//			holder.tvLatest.setText(Utility.getInstance().replaceDotApplyComma(list.get(position).getPurchase_price()));
//		}
//		else if(list.get(position).getLatestValuetoggle()==2){
//			holder.tvLatest.setText(Utility.getInstance().replaceDotApplyComma(list.get(position).getQuantity()));
//		}
//		
		
		String strLv = "";

		try {
			if (list.get(position).getLatestValuetoggle() == 0) {
				strLv = Utility.getInstance().replaceDotApplyComma(
						list.get(position).getLatestValue());
			} else if (list.get(position).getLatestValuetoggle() == 1) {

				/*strLv = Utility.getInstance().replaceDotApplyComma(
						list.get(position).getInvestment_price());*/
				strLv = list.get(position).getInvestment_price();
				if(strLv.contains(",")) {
					strLv = strLv.replace(",", "");
				}
				double d = Double.parseDouble(strLv);
				Float f = new Float( Math.round(d));
				Log.e("strLv // d // f", ""+strLv + " // " + d + " // " + f);
				int x = (int) Math.round(f);
				strLv = Integer.toString(x);
			} else if (list.get(position).getLatestValuetoggle() == 2) {

				strLv = Utility.getInstance().replaceDotApplyComma(
						list.get(position).getQuantity());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			strLv = "0";
			e.printStackTrace();
		}

		if (!TextUtils.isEmpty(strLv)) {
			holder.tvLatest.setText(strLv);
		} else {
			holder.tvLatest.setText("0");
		}
		
		return view;
	}
	
	class ViewHolder {
		public TextView tvInvestments, tvDayGain, tvLatest, tvNAVTitle;
		public TextView tvInvestmentsValue;
		public ImageView ivChange;
		public RelativeLayout rlChange;
	}
}