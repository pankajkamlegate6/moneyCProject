package com.moneycontrol.handheld.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.Html.ImageGetter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.divum.MoneyControl.R;
import com.moneycontrol.handheld.api.AppBeanParacable;
import com.moneycontrol.handheld.entity.messages.LastVisitedListBean;
import com.moneycontrol.handheld.imageUtils.ImageDownloaderUtils;
import com.moneycontrol.handheld.util.Constantlibs;
import com.moneycontrol.handheld.util.TaskCompleteListiners;
import com.moneycontrol.handheld.util.Utility;
import com.neopixl.pixlui.components.textview.TextView;
import com.nostra13.universalimageloader.core.ImageLoader;

public class NotificationDialogAdapter extends BaseAdapter implements
		TaskCompleteListiners {

	private LayoutInflater inflater;
	private ArrayList<LastVisitedListBean> datalist;
	private Context mContext;

	public NotificationDialogAdapter(Context mContext,
			ArrayList<LastVisitedListBean> arrayList) {
		inflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.datalist = arrayList;
		this.mContext = mContext;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return datalist.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return datalist.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View view = convertView;
		ViewHolder viewHolder;
		LastVisitedListBean messageBean = datalist.get(position);
		if (view == null) {
			view = inflater.inflate(R.layout.row_notificaiton_adapter, null);
			viewHolder = new ViewHolder();
			viewHolder.userImage = (ImageView) view
					.findViewById(R.id.profileImage);

			viewHolder.userName = (TextView) view.findViewById(R.id.tv_name);
			viewHolder.userBadge = (TextView) view
					.findViewById(R.id.tv_border_type);
			viewHolder.badge = (ImageView) view.findViewById(R.id.borderImage);
			viewHolder.userRate = (TextView) view.findViewById(R.id.tv_no_rate);
			viewHolder.ratingDummytxt = (TextView) view
					.findViewById(R.id.tv_no_rate_dammy);
			view.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) view.getTag();
		}

		ImageLoader.getInstance().displayImage(messageBean.getUserImage(),
				viewHolder.userImage,
				ImageDownloaderUtils.displayImageOptionsNormal());

		// ImageLoader.getInstance().displayImage(messageBean.getMsg_icon(),
		// viewHolder.badge,
		// ImageDownloaderUtils.displayImageOptionsWithoutPlaceHolder());
		if (messageBean.getUserBadge() == null
				|| TextUtils.isEmpty(messageBean.getUserBadge())) {
			messageBean.setUserBadge(Constantlibs.MEMBER_TYPE_DEFAULT);
		}
		viewHolder.badge.setImageResource(Utility.getMemberTypeImage(mContext,
				messageBean.getUserBadge(), viewHolder.badge));
		viewHolder.userBadge.setText(messageBean.getUserBadge());
		viewHolder.userName.setText(messageBean.getNickName());
		// viewHolder.userName.setText(text);
		viewHolder.userName.setTag(R.string.about_us, messageBean.getUserId());
		viewHolder.userName.setTag(R.string.txt_boaders, true);

		viewHolder.userName.setTag(R.string.txt_boaders, true);
		viewHolder.userImage.setVisibility(View.VISIBLE);
		viewHolder.userImage.setTag(messageBean.getUserId());
		if (messageBean.getNoofRate() != null) {
			viewHolder.userRate.setText(messageBean.getNoofRate());
			viewHolder.userRate.setVisibility(View.VISIBLE);
		} else {
			viewHolder.userRate.setText("");
			viewHolder.userRate.setVisibility(View.GONE);
		}
		if (messageBean.getNoofRate() != null) {

			float f = Float.parseFloat(messageBean.getNoofRate());

			if ((int) f >= 1) {

				String htmlString = ratingBar(messageBean.getNoofRate());
				Spanned htmlSpan = Html.fromHtml(
						htmlString != null ? htmlString : "", rateimgGetter,
						null);
				viewHolder.userRate.setText(htmlSpan);
				viewHolder.userRate.setVisibility(View.VISIBLE);
				viewHolder.ratingDummytxt.setVisibility(View.VISIBLE);
			}

			else {

				viewHolder.userRate.setVisibility(View.GONE);
				viewHolder.ratingDummytxt.setVisibility(View.GONE);

			}

		} else {
			viewHolder.userRate.setVisibility(View.GONE);
			viewHolder.ratingDummytxt.setVisibility(View.GONE);
		}
		return view;
	}

	private static class ViewHolder {
		private TextView userName, userBadge, userRate, ratingDummytxt;
		private ImageView userImage, badge;

	}

	@Override
	public void onTaskComplete(int requestType, AppBeanParacable appBean) {

	}

	private ImageGetter rateimgGetter = new ImageGetter() {

		public Drawable getDrawable(String source) {
			Drawable drawable = null;

			drawable = mContext.getResources().getDrawable(R.drawable.rated_by);

			drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
					drawable.getIntrinsicHeight());
			return drawable;
		}
	};

	public String ratingBar(String norate) {
		String rating_string = "";
		double rating_star = Double.valueOf(norate);
		for (int i = 0; i < rating_star; i++) {
			rating_string = rating_string
					+ "<img src='file:///res/drawable/rated_by.png'/>";
		}

		String htmlString = rating_string + "<font>%s</font>&nbsp;";

		rating_string = String.format(htmlString, " ");
		return rating_string;
	}

}