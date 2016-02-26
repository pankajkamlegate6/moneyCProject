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
import com.moneycontrol.handheld.constant.AppConstants;
import com.moneycontrol.handheld.entity.myportfolio.MyPortfolioItemData;
import com.moneycontrol.handheld.util.Utility;

public class MyPortfolioSummaryAdapter extends BaseAdapter {

	private Context mContext;
	private LayoutInflater mInflator;
	private ArrayList<MyPortfolioItemData> summaryList = null;
	private String TAB = "";

	public MyPortfolioSummaryAdapter(Context context,
			ArrayList<MyPortfolioItemData> list, String tabTitle) {
		this.mContext = context;
		summaryList = list;
		mInflator = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		TAB = tabTitle;
	}

	@Override
	public int getCount() {
		return summaryList.size();
	}

	@Override
	public Object getItem(int position) {
		return summaryList.get(position);
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
			
			if (TAB.equals(AppConstants.MY_PORTFOLIO_TAB_SUMMARY)) {
				vHolder.tvInvestmentsValue.setVisibility(View.GONE);
				vHolder.tvNAVTitle.setVisibility(View.GONE);
				vHolder.tvInvestmentsValue.setVisibility(View.GONE);
			} else if (TAB.equals(AppConstants.MY_PORTFOLIO_TAB_STOCKS)) {
				vHolder.tvNAVTitle.setVisibility(View.GONE);
				vHolder.tvInvestmentsValue.setVisibility(View.VISIBLE);
				vHolder.tvInvestmentsValue.setVisibility(View.VISIBLE);
			} else if (TAB.equals(AppConstants.MY_PORTFOLIO_TAB_MUTUAL_FUNDS)) {
				vHolder.tvInvestmentsValue.setVisibility(View.VISIBLE);
				vHolder.tvNAVTitle.setVisibility(View.VISIBLE);
				vHolder.tvInvestmentsValue.setVisibility(View.VISIBLE);
			} else if (TAB.equals(AppConstants.MY_PORTFOLIO_TAB_ULIP)) {
				vHolder.tvInvestmentsValue.setVisibility(View.VISIBLE);
				vHolder.tvNAVTitle.setVisibility(View.VISIBLE);
				vHolder.tvInvestmentsValue.setVisibility(View.VISIBLE);
			} else if (TAB.equals(AppConstants.MY_PORTFOLIO_TAB_BULLION)) {
				vHolder.tvNAVTitle.setVisibility(View.GONE);
				vHolder.tvInvestmentsValue.setVisibility(View.VISIBLE);
				vHolder.tvInvestmentsValue.setVisibility(View.VISIBLE);
			}
			
			view.setTag(vHolder);

		} else {
			view = convertView;
		}

		ViewHolder holder = (ViewHolder) view.getTag();

		if (TAB.equals(AppConstants.MY_PORTFOLIO_TAB_SUMMARY)) {
			// No value to set for summary tab
		} else if (TAB.equals(AppConstants.MY_PORTFOLIO_TAB_STOCKS)) {
			holder.tvInvestmentsValue.setText(summaryList.get(position).getLastValue());
		} else if (TAB.equals(AppConstants.MY_PORTFOLIO_TAB_MUTUAL_FUNDS)) {
			holder.tvInvestmentsValue.setText(Utility.getInstance().applyDecimalPlaces(summaryList.get(position).getLastValue()));
		} else if (TAB.equals(AppConstants.MY_PORTFOLIO_TAB_ULIP)) {
			holder.tvInvestmentsValue.setText(Utility.getInstance().applyDecimalPlaces(summaryList.get(position).getLastValue()));
		} else if (TAB.equals(AppConstants.MY_PORTFOLIO_TAB_BULLION)) {
			holder.tvInvestmentsValue.setText(summaryList.get(position).getLastValue());
		}
		
		holder.tvInvestments.setText(summaryList.get(position).getName());
		
		if (summaryList.get(position).isDayGain()) {
			if (!TextUtils.isEmpty(summaryList.get(position).getTodaysGain())) {
				String todaysGain = summaryList.get(position).getTodaysGain();
				String percentChange = summaryList.get(position).getPercentChange();
				if(TextUtils.isEmpty(todaysGain)) {
					todaysGain = "0";
				}
				if(TextUtils.isEmpty(percentChange) || percentChange.equals("0")) {
					percentChange = "0.00";
				}
				holder.rlChange.setVisibility(View.VISIBLE);
				Utility.getInstance().flipChangeTOPercentageData(
						holder.tvDayGain,
						Utility.getInstance().replaceDotApplyComma(
								todaysGain),
								percentChange,
						summaryList.get(position).getDirection(), mContext,
						holder.ivChange);

			} else {
				holder.rlChange.setVisibility(View.INVISIBLE);
			}
		} else {
			if (!TextUtils.isEmpty(summaryList.get(position).getOverallGain())) {
				String overallGain = summaryList.get(position).getOverallGain();
				String overallPercentChange = summaryList.get(position).getOverallPercentChange();
				if(TextUtils.isEmpty(overallGain)) {
					overallGain = "0";
				}
				if(TextUtils.isEmpty(overallPercentChange) || overallPercentChange.equals("0")) {
					overallPercentChange = "0.00";
				}
				holder.rlChange.setVisibility(View.VISIBLE);
				Utility.getInstance().flipChangeTOPercentageData(
						holder.tvDayGain,
						Utility.getInstance().replaceDotApplyComma(
								overallGain),
						Utility.removeCommaPercent(overallPercentChange),
						summaryList.get(position).getOverallDirection(),
						mContext, holder.ivChange);

			} else {
				holder.rlChange.setVisibility(View.INVISIBLE);
			}
		}

		String strLv = "";
		int latValue;
		
		
		if (TAB.equals(AppConstants.MY_PORTFOLIO_TAB_SUMMARY)) {
			if (summaryList.get(position).getLatestValuetoggle() == 0) {
				strLv = summaryList.get(position).getLatestValue();
				if (!TextUtils.isEmpty(strLv)) {
					holder.tvLatest.setText(Utility.getInstance()
							.replaceDotApplyComma(strLv));
				}
			} else {
				try {
					//strLv = summaryList.get(position).getInvestment_price();
					strLv = summaryList.get(position).getInvestmentPrice();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					strLv = "0";
					e.printStackTrace();
				}
				if (!TextUtils.isEmpty(strLv)) {
					holder.tvLatest.setText(Utility.getInstance()
							.replaceDotApplyComma(strLv));
				} else {
					holder.tvLatest.setText("0");
				}

			}
		} else {
			if (TAB.equals(AppConstants.MY_PORTFOLIO_TAB_STOCKS)) {
				try {
					if (summaryList.get(position).getLatestValuetoggle() == 0) {
						strLv = Utility.getInstance().replaceDotApplyComma(
								summaryList.get(position).getLatestValue());
					} else if (summaryList.get(position).getLatestValuetoggle() == 1) {

						strLv = Utility.getInstance().replaceDotApplyComma(
								summaryList.get(position).getInvestmentPrice());
					} else if (summaryList.get(position).getLatestValuetoggle() == 2) {

						strLv = Utility.getInstance().replaceDotApplyComma(
								summaryList.get(position).getQuantity());
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					strLv = "0";
					e.printStackTrace();
				}
			} else if (TAB.equals(AppConstants.MY_PORTFOLIO_TAB_MUTUAL_FUNDS)) {
				try {
					if (summaryList.get(position).getLatestValuetoggle() == 0) {
						strLv = Utility.getInstance().replaceDotApplyComma(
								summaryList.get(position).getLatestValue());
					} else if (summaryList.get(position).getLatestValuetoggle() == 1) {

						/*strLv = Utility.getInstance().replaceDotApplyComma(
								list.get(position).getInvestment_price());*/
						strLv = summaryList.get(position).getInvestmentPrice();
						if(strLv.contains(",")) {
							strLv = strLv.replace(",", "");
						}
						double d = Double.parseDouble(strLv);
						Float f = new Float( Math.round(d));
						Log.e("strLv // d // f", ""+strLv + " // " + d + " // " + f);
						int x = (int) Math.round(f);
						strLv = Integer.toString(x);
						strLv = Utility.getInstance().applyCommaforNumericString(strLv);
					} else if (summaryList.get(position).getLatestValuetoggle() == 2) {
						/*strLv = Utility.getInstance().replaceDotApplyComma(
								summaryList.get(position).getQuantity());*/
						strLv = Utility.getInstance().applyDecimalPlaces(summaryList.get(position).getQuantity());
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					strLv = "0";
					e.printStackTrace();
				}
			} else if (TAB.equals(AppConstants.MY_PORTFOLIO_TAB_ULIP)) {
				try {
					if (summaryList.get(position).getLatestValuetoggle() == 0) {
						strLv = Utility.getInstance().replaceDotApplyComma(
								summaryList.get(position).getLatestValue());
					} else if (summaryList.get(position).getLatestValuetoggle() == 1) {

						strLv = Utility.getInstance().replaceDotApplyComma(
								summaryList.get(position).getInvestmentPrice());
					} else if (summaryList.get(position).getLatestValuetoggle() == 2) {

						/*strLv = Utility.getInstance().replaceDotApplyComma(
								list.get(position).getQuantity());*/
						strLv = Utility.getInstance().applyDecimalPlaces(summaryList.get(position).getQuantity());
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					strLv = "0";
					e.printStackTrace();

				}
			} else if (TAB.equals(AppConstants.MY_PORTFOLIO_TAB_BULLION)) {
				try {
					if (summaryList.get(position).getLatestValuetoggle() == 0) {
						strLv = Utility.getInstance().replaceDotApplyComma(
								summaryList.get(position).getLatestValue());
					} else if (summaryList.get(position).getLatestValuetoggle() == 1) {
						strLv = Utility.getInstance().replaceDotApplyComma(
								summaryList.get(position).getInvestmentPrice());
					} else if (summaryList.get(position).getLatestValuetoggle() == 2) {
						/*strLv = Utility.getInstance().replaceDotApplyComma(
								summaryList.get(position).getQuantity());*/
						strLv = Utility.getInstance().applyDecimalPlaces(summaryList.get(position).getQuantity());
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					strLv = "0";
					e.printStackTrace();
				}
			}
			if (!TextUtils.isEmpty(strLv)) {
				holder.tvLatest.setText(strLv);
			} else {
				holder.tvLatest.setText("0");
			}
			
		}

		return view;
	}

	public class ViewHolder {
		public TextView tvInvestments, tvDayGain, tvLatest, tvNAVTitle;
		public TextView tvInvestmentsValue;
		public ImageView ivChange;
		public RelativeLayout rlChange;
	}

}
