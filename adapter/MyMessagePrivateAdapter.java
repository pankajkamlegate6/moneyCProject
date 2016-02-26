package com.moneycontrol.handheld.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.text.Html.ImageGetter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.divum.MoneyControl.R;
import com.moneycontrol.handheld.entity.messages.MessageCategoryItemData;
import com.moneycontrol.handheld.imageUtils.ImageDownloaderUtils;
import com.moneycontrol.handheld.massages.fragments.BaseFragement;
import com.moneycontrol.handheld.massages.fragments.PrivatePostMessageFragment;
import com.moneycontrol.handheld.util.Utility;
import com.nostra13.universalimageloader.core.ImageLoader;

public class MyMessagePrivateAdapter extends BaseAdapter {

	private Context mContext;
	private LayoutInflater layoutInflater;
	private ArrayList<MessageCategoryItemData> list;
	private String reco_type_html = "<font color=#66CC00><b> ";

	private String msg_type_html = "<font color=#FFFFFF><b>";

	private String news_type_html = "<font color= #999999><b>";
	String normalhtmlString = "<font color=#FFFFFF><b>%s</b></font>";
	private String news_side_drawablehtml = "<img src=\"latest_arrow.png\" align=\"justify\"/>";
	private Handler handler = null, handlerShare = null;
	private ArrayList<RelativeLayout> ratedlayouts = null;
	private ArrayList<LinearLayout> repostlayout = null;
	private String replyTxt = null, response = null;
	private boolean clicked = false;

	public MyMessagePrivateAdapter(Context mContext,
			ArrayList<MessageCategoryItemData> list, boolean Clicked) {
		// TODO Auto-generated constructor stub
		this.mContext = mContext;
		layoutInflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.list = list;
		ratedlayouts = new ArrayList<RelativeLayout>();
		clicked = Clicked;
		repostlayout = new ArrayList<LinearLayout>();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public MessageCategoryItemData getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		final ViewHolder holder;
		final int pos = position;

		// rateBoxState[pos] = false;
		if (convertView == null) {

			convertView = layoutInflater.inflate(R.layout.row_private_layout,
					null);

			holder = new ViewHolder();
			holder.user_icon = (ImageView) convertView
					.findViewById(R.id.user_icon);
			holder.tvuserNickName = (TextView) convertView
					.findViewById(R.id.user_nick_name);

			holder.userMembertypIcon = (ImageView) convertView
					.findViewById(R.id.user_membertyp_icon);
			holder.userMembertypText = (TextView) convertView
					.findViewById(R.id.user_membertyp_text);

			holder.tvMassagePosttime = (TextView) convertView
					.findViewById(R.id.tvmassage_post_time);

			holder.usermsgTxt = (TextView) convertView
					.findViewById(R.id.user_psot_msg_typeAnd_msg_txt);

			convertView.setTag(holder);

		} else

		{

			holder = (ViewHolder) convertView.getTag();
		}

		holder.usermsgTxt.setTag(position);

		MessageCategoryItemData massageData = (MessageCategoryItemData) getItem(position);
		holder.user_icon.setTag(massageData.getUserid());
		// addresultll =
		// list.get(holder.ref).setLl(holder.ratethismassagell);

		holder.userMembertypIcon
				.setImageResource(Utility.getMemberTypeImage(mContext,
						massageData.getMembertype(), holder.userMembertypIcon));
		holder.tvuserNickName.setText(massageData.getUsernickname());
		// holder.tvuserNickName.setTag(massageData.getUserid());
		holder.tvuserNickName
				.setTag(R.string.about_us, massageData.getUserid());
		holder.tvuserNickName.setTag(R.string.txt_boaders, true);
		holder.userMembertypText.setText(massageData.getMembertype());
		holder.tvMassagePosttime.setText(massageData.getEnt_date());

		if (massageData.getMsg_type() != null
				&& massageData.getMsg_type().equalsIgnoreCase("news")) {

			holder.usermsgTxt
					.setText(Html.fromHtml(
							news_type_html + " " + massageData.getMsg_type()
									+ "</b></font>" + " "
									+ news_side_drawablehtml + " "
									+ msg_type_html + massageData.getMessage()
									+ "</b></font>", imgGetter, null));

		}

		else if (massageData.getMsg_type() != null
				&& massageData.getReco_status().equalsIgnoreCase("BUY RECO")) {

			holder.usermsgTxt
					.setText(Html.fromHtml(reco_type_html + " "
							+ massageData.getReco_status() + "</b></font>"
							+ " " + msg_type_html + massageData.getMessage()
							+ "</b></font>"));
		} else {
			holder.usermsgTxt.setText(Html.fromHtml(msg_type_html
					+ massageData.getMessage() + "</b></font>"));
		}

		ImageLoader.getInstance().displayImage(massageData.getUserimg(),
				holder.user_icon,
				ImageDownloaderUtils.displayImageOptionsNormal());
		convertView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (Utility.getCurrentFragment(mContext) instanceof BaseFragement) {
					BaseFragement baseFragment = (BaseFragement) Utility
							.getCurrentFragment(mContext);
					Bundle bundle = new Bundle();
					bundle.putSerializable("data", list.get(position));
					PrivatePostMessageFragment postmessage = new PrivatePostMessageFragment();
					postmessage.setArguments(bundle);
					baseFragment.launchFragement(postmessage, true);
				}

			}
		});
		return convertView;
	}

	private class ViewHolder {
		TextView tvuserNickName, userMembertypText, tvMassagePosttime,
				usermsgTxt;
		ImageView user_icon, userMembertypIcon;
		private LinearLayout row_third;

	}

	private ImageGetter imgGetter = new ImageGetter() {

		public Drawable getDrawable(String source) {
			Drawable drawable = null;

			drawable = mContext.getResources().getDrawable(
					R.drawable.latest_arrow);

			drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
					drawable.getIntrinsicHeight());
			return drawable;
		}
	};

	public void HidePopUpifAnyOpen(int a) {
		// TODO Auto-generated method stub
		for (int i = 0; i < list.size(); i++) {

			if (i != a) {
				list.get(i).setOpenRate(false);
			}

		}
		notifyDataSetChanged();
	}
}
