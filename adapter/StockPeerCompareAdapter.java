package com.moneycontrol.handheld.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.divum.MoneyControl.R;
import com.moneycontrol.handheld.constant.AppConstants;
import com.moneycontrol.handheld.entity.market.PeerComparisonData;

import java.util.ArrayList;
import java.util.List;

public class StockPeerCompareAdapter extends BaseAdapter {

	private Context mContext;
	private List<PeerComparisonData> listPeerComparisionData;
	private LayoutInflater mInflator;
	private int selectionType;

	public StockPeerCompareAdapter(Context context,
			ArrayList<PeerComparisonData> list, final int selectionType) {

		this.mContext = context;
		this.listPeerComparisionData = list;
		this.selectionType = selectionType;
		mInflator = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	}

	@Override
	public int getCount() {
		return listPeerComparisionData.size();
	}

	@Override
	public PeerComparisonData getItem(int position) {
		return listPeerComparisionData.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	static class ViewHolder {

		private TextView tvCompneyname;
		private TextView tvLastprice;
		private TextView tvPercentChange;
		private TextView tvSelectedValue;

	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {

		View view = null;

		if (convertView == null) {

			final ViewHolder vHolder = new ViewHolder();

			view = mInflator.inflate(R.layout.item_peer_comparision_list, null);

			vHolder.tvCompneyname = (TextView) view
					.findViewById(R.id.tvCompneyname);
			vHolder.tvLastprice = (TextView) view
					.findViewById(R.id.tvLastprice);
			vHolder.tvPercentChange = (TextView) view
					.findViewById(R.id.tvPercentChange);
			vHolder.tvSelectedValue = (TextView) view
					.findViewById(R.id.tvSelectedValue);

			view.setTag(vHolder);

		} else {
			view = convertView;

		}

		ViewHolder holder = (ViewHolder) view.getTag();
		holder.tvCompneyname.setText(listPeerComparisionData.get(position)
				.getCompany_name());
		holder.tvLastprice.setText(listPeerComparisionData.get(position)
				.getLastprice());

		if (listPeerComparisionData.get(position).getDirection().equals("-1")) {
			holder.tvPercentChange.setTextColor(mContext.getResources()
					.getColor(R.color.red));
			holder.tvPercentChange.setText(listPeerComparisionData
					.get(position).getPercentchange() + "%");
		} else {
			holder.tvPercentChange.setTextColor(mContext.getResources()
					.getColor(R.color.green));
			holder.tvPercentChange.setText("+"
					+ listPeerComparisionData.get(position).getPercentchange()
					+ "%");
		}

		if (selectionType == AppConstants.STOCK_PEER_MARKET_CAP) {
			holder.tvSelectedValue.setText(listPeerComparisionData
					.get(position).getMktcap());
		} else if (selectionType == AppConstants.STOCK_PEER_SALES) {
			holder.tvSelectedValue.setText(listPeerComparisionData
					.get(position).getNetsales());
		} else if (selectionType == AppConstants.STOCK_PEER_NET_PROFIT) {
			holder.tvSelectedValue.setText(listPeerComparisionData
					.get(position).getNetprofit());
		} else if (selectionType == AppConstants.STOCK_PEER_TOTAL_ASSETS) {
			holder.tvSelectedValue.setText(listPeerComparisionData
					.get(position).getTotal_assets());
		}

		return view;
	}
}
