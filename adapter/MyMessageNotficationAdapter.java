package com.moneycontrol.handheld.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.Html;
import android.text.Html.ImageGetter;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TextView.BufferType;

import com.moneycontrol.handheld.AppData;
import com.divum.MoneyControl.R;
import com.moneycontrol.handheld.api.AppBeanParacable;
import com.moneycontrol.handheld.api.RequestType;
import com.moneycontrol.handheld.db.Dbopener;
import com.moneycontrol.handheld.entity.messages.LastVisitedListBean;
import com.moneycontrol.handheld.entity.messages.LastVisitedListData;
import com.moneycontrol.handheld.entity.messages.MyMessageNotificationBean;
import com.moneycontrol.handheld.fragments.NotificationDialogFragment;
import com.moneycontrol.handheld.imageUtils.ImageDownloaderUtils;
import com.moneycontrol.handheld.massages.fragments.BaseFragement;
import com.moneycontrol.handheld.massages.fragments.MyMessageBoarderPageFragment;
import com.moneycontrol.handheld.netcomm.Communicator;
import com.moneycontrol.handheld.util.Constantlibs;
import com.moneycontrol.handheld.util.TaskCompleteListiners;
import com.moneycontrol.handheld.util.Utility;
import com.nostra13.universalimageloader.core.ImageLoader;

public class MyMessageNotficationAdapter extends BaseAdapter implements
		TaskCompleteListiners {

	private Context mContext;
	private LayoutInflater layoutInflater;
	private ArrayList<MyMessageNotificationBean> list;
	private String news_type_html = "<font color= #999999><b>";
	private String reco_type_html = "<font color=#66CC00><b> ";

	private String msg_type_html = "<font color=#FFFFFF><b>";

	String normalhtmlString = "<font color=#FFFFFF><b>%s</b></font>";
	private String news_side_drawablehtml = "<img src=\"latest_arrow.png\" align=\"justify\"/>";
	private RelativeLayout progressBarr;

	public MyMessageNotficationAdapter(Context mContext,
			ArrayList<MyMessageNotificationBean> list,
			RelativeLayout progressBarr) {
		// TODO Auto-generated constructor stub
		this.mContext = mContext;
		layoutInflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.list = list;
		this.progressBarr = progressBarr;

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public MyMessageNotificationBean getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder viewHolder = null;
		View view = convertView;
		if (view == null) {
			view = layoutInflater.inflate(R.layout.row_my_message_notification,
					null);
			viewHolder = new ViewHolder();

			viewHolder.userMemberType = (TextView) view
					.findViewById(R.id.user_membertyp_text);

			viewHolder.user_icon = (ImageView) view
					.findViewById(R.id.user_icon);
			viewHolder.member_type_icon = (ImageView) view
					.findViewById(R.id.user_membertyp_icon);
			viewHolder.messsage_update = (TextView) view
					.findViewById(R.id.message_update);
			viewHolder.date_time = (TextView) view
					.findViewById(R.id.message_date_time);
			viewHolder.message_txt = (TextView) view
					.findViewById(R.id.message_txt);
			view.setTag(R.string._replies_, viewHolder);

		} else {
			viewHolder = (ViewHolder) view.getTag(R.string._replies_);
		}
		view.setTag(R.string.about_us, list.get(position));
		view.setTag(R.string.last_div, position);
		viewHolder.messsage_update.setText("");
		if (list.get(position).isRead()) {
			view.setBackgroundColor(Color.BLACK);
		} else {
			view.setBackgroundColor(mContext.getResources().getColor(
					R.color.indice_listing_header));
		}
		if (list.get(position).getMsg_type().equalsIgnoreCase("rating")) {
			viewHolder.message_txt.setVisibility(View.VISIBLE);
		} else {
			viewHolder.message_txt.setVisibility(View.GONE);
		}

		String htmlString = getMessageUpdateText(list.get(position),
				list.get(position).getMember_updt());
		Spanned htmlSpan = Html.fromHtml(htmlString != null ? htmlString : "",
				new UrlImageGetter(), null);

		viewHolder.messsage_update.setText(htmlSpan);
		if (list.get(position).getMembers_cnt() != null
				&& !list.get(position).getMembers_cnt().isEmpty()) {
			viewHolder.messsage_update.setMovementMethod(LinkMovementMethod
					.getInstance());
			Spannable clickableMessage = (Spannable) viewHolder.messsage_update
					.getText();
			viewHolder.messsage_update.setText(
					addClickablePart(mContext, clickableMessage,
							list.get(position).getMembers_cnt().trim(), list
									.get(position).getNick_name().trim()),
					BufferType.SPANNABLE);
			viewHolder.messsage_update.setLinkTextColor(Color
					.parseColor("#CCCCCC"));

		} else {
			viewHolder.messsage_update.setMovementMethod(LinkMovementMethod
					.getInstance());
			Spannable clickableMessage = (Spannable) viewHolder.messsage_update
					.getText();
			viewHolder.messsage_update.setText(
					addClickablePart(mContext, clickableMessage,
							list.get(position).getNick_name().trim()),
					BufferType.SPANNABLE);
			viewHolder.messsage_update.setLinkTextColor(Color
					.parseColor("#CCCCCC"));
		}

		if (list.get(position).getType() != null
				&& list.get(position).getType().equalsIgnoreCase("news")) {

			viewHolder.message_txt.setText(Html.fromHtml(
					news_type_html + " " + list.get(position).getType()
							+ "</b></font>" + " " + news_side_drawablehtml
							+ " " + msg_type_html
							+ list.get(position).getMessage() + "</b></font>",
					new UrlImageGetter(), null));

		}

		else if (list.get(position).getType() != null
				&& list.get(position).getReco_status()
						.equalsIgnoreCase("Buy RECO")) {

			viewHolder.message_txt.setText(Html.fromHtml(reco_type_html + " "
					+ list.get(position).getType() + "</b></font>" + " "
					+ msg_type_html + list.get(position).getMessage()
					+ "</b></font>"));
		}

		else {
			viewHolder.message_txt.setText(Html.fromHtml(String.format(
					normalhtmlString, list.get(position).getMessage())));
		}
		viewHolder.userMemberType.setText(list.get(position).getMember_type());
		viewHolder.messsage_update.setTag(list.get(position));
		String url = list.get(position).getUser_img();
		viewHolder.user_icon.setTag(list.get(position).getUser_id());
		// AQuery aq = new AQuery(getActivity().getApplicationContext());
		// aq.id(view.findViewById(R.id.user_icon)).image(url,
		// Utility.isMemCache, Utility.isFileCache, 0,
		// R.drawable.man_red);
		// aq.id(view.findViewById(R.id.user_icon)).image(url,
		// Utility.isMemCache, Utility.isFileCache, 0,
		// R.drawable.man_red);
		viewHolder.date_time.setText(list.get(position).getEnt_date());
		viewHolder.member_type_icon.setImageResource(Utility
				.getMemberTypeImage(mContext, list.get(position)
						.getMember_type(), viewHolder.member_type_icon));
		ImageLoader.getInstance().displayImage(url, viewHolder.user_icon,
				ImageDownloaderUtils.displayImageOptionsNormal());
		view.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				MyMessageNotificationBean bean = (MyMessageNotificationBean) v
						.getTag(R.string.about_us);
				int position = (Integer) v.getTag(R.string.last_div);
				Dbopener opener = new Dbopener(mContext);
				opener.updateStatus(bean.getMsg_id());
				bean.setRead(true);
				list.set(position, bean);
				notifyDataSetChanged();

				opener.close();
			}
		});
		return view;

	}

	private String getMessageUpdateText(
			MyMessageNotificationBean myMessageNotificationBean,
			String member_updt) {
		String returnString = "";
		String htmlString = "";
		if (myMessageNotificationBean.getMsg_type().equalsIgnoreCase(
				"following")) {
			if (myMessageNotificationBean.getMembers_cnt() != null
					&& !myMessageNotificationBean.getMembers_cnt().isEmpty()) {
				htmlString = "<font color=#CCCCCC><b>%s</b></font> <font color=#999999>%s</font><font color=#CCCCCC><b> %s</b></font> <font color=#999999>%s</font>";
				returnString = String.format(htmlString,
						myMessageNotificationBean.getNick_name(), "and",
						myMessageNotificationBean.getMembers_cnt(),
						myMessageNotificationBean.getMember_updt());
			} else {
				htmlString = "<font color=#CCCCCC><b>%s</b></font> <font color=#999999>%s</font>";
				returnString = String.format(htmlString,
						myMessageNotificationBean.getNick_name(),
						myMessageNotificationBean.getMember_updt());
			}

		} else if (myMessageNotificationBean.getMsg_type().equalsIgnoreCase(
				"reply")) {

			htmlString = "<font color=#CCCCCC><b>%s</b></font> <font color=#999999>%s</font><font color=#CCCCCC><b>%s</b></font>";
			returnString = String.format(htmlString,
					myMessageNotificationBean.getNick_name(),
					myMessageNotificationBean.getMember_updt(),
					myMessageNotificationBean.getTopicName());
		} else if (myMessageNotificationBean.getMsg_type().equalsIgnoreCase(
				"rating")) {
			String rating_string = "";
			double rating_star = Double.valueOf(myMessageNotificationBean
					.getRating_star());
			for (int i = 0; i < rating_star; i++) {
				rating_string = rating_string
						+ "<img src='file:///res/drawable/img.png' />";
			}
			htmlString = "<font color=#CCCCCC><b>%s</b></font> <font color=#999999>%s</font>"
					+ rating_string;
			returnString = String.format(htmlString,
					myMessageNotificationBean.getNick_name(),
					myMessageNotificationBean.getMember_updt());
		} else if (myMessageNotificationBean.getMsg_type().equalsIgnoreCase(
				"privatemsg")) {
			if (myMessageNotificationBean.getMembers_cnt() != null
					&& !myMessageNotificationBean.getMembers_cnt().isEmpty()) {
				htmlString = "<font color=#CCCCCC><b>%s</b></font> <font color=#999999>%s</font><font color=#CCCCCC><b> %s</b></font> <font color=#999999>%s</font>";
				returnString = String.format(htmlString,
						myMessageNotificationBean.getNick_name(), "and",
						myMessageNotificationBean.getMembers_cnt(),
						myMessageNotificationBean.getMember_updt());
			} else {
				htmlString = "<font color=#CCCCCC><b>%s</b></font> <font color=#999999>%s</font>";
				returnString = String.format(htmlString,
						myMessageNotificationBean.getNick_name(),
						myMessageNotificationBean.getMember_updt());
			}

		} else if (myMessageNotificationBean.getMsg_type().equalsIgnoreCase(
				"addr")) {
			if (myMessageNotificationBean.getMembers_cnt() != null
					&& !myMessageNotificationBean.getMembers_cnt().isEmpty()) {
				htmlString = "<font color=#CCCCCC><b>%s</b></font> <font color=#999999>%s</font><font color=#CCCCCC><b> %s</b></font> <font color=#999999>%s</font>";
				returnString = String.format(htmlString,
						myMessageNotificationBean.getNick_name(), "and",
						myMessageNotificationBean.getMembers_cnt(),
						myMessageNotificationBean.getMember_updt());
			} else {
				htmlString = "<font color=#CCCCCC><b>%s</b></font> <font color=#999999>%s</font>";
				returnString = String.format(htmlString,
						myMessageNotificationBean.getNick_name(),
						myMessageNotificationBean.getMember_updt());
			}

		}
		return returnString;
	}

	private SpannableStringBuilder addClickablePart(final Context mContext,
			Spannable charSequence, String... trailing) {
		SpannableStringBuilder ssb = new SpannableStringBuilder(charSequence);
		for (int i = 0; i < trailing.length; i++) {
			int idx1 = charSequence.toString().indexOf(trailing[i].trim());
			int idx2 = 0;
			if (idx1 != -1) {
				idx2 = idx1 + trailing[i].length() + 1;
				if (trailing[i].trim().contains("other")) {
					ssb.setSpan(new ClickSpan(false), idx1, idx2,
							Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
				} else {
					ssb.setSpan(new ClickSpan(true), idx1, idx2,
							Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
				}

			}
		}

		return ssb;
	}

	private class ViewHolder {
		TextView userMemberType, messsage_update, message_txt, date_time;
		ImageView user_icon, member_type_icon;

	}

	private class UrlImageGetter implements ImageGetter {

		@Override
		public Drawable getDrawable(String source) {
			// TODO Auto-generated method stub
			// BitmapDrawable draBitmap = null;
			// ArrayList<Bitmap> list = (ArrayList<Bitmap>) MemoryCacheUtils
			// .findCachedBitmapsForImageUri(source, ImageLoader
			// .getInstance().getMemoryCache());
			// if (list.size() == 0) {
			// ImageLoader.getInstance().loadImage(source,
			// ImageDownloaderUtils.displayImageOptionsNormal(), null);
			// } else {
			// draBitmap = new BitmapDrawable(mContext.getResources(),
			// list.get(0));
			// }
			Drawable d;
			if (source.equalsIgnoreCase("latest_arrow.png")) {

				d = mContext.getResources()
						.getDrawable(R.drawable.latest_arrow);

				d.setBounds(0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight());

			} else {
				d = mContext.getResources().getDrawable(R.drawable.rated_by);
				d.setBounds(0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight());

			}
			return d;
		}
	}

	private class ClickSpan extends ClickableSpan {

		public ClickSpan(boolean usertag) {
			// TODO Auto-generated constructor stub
			this.usertag = usertag;

		}

		boolean usertag = false;

		@Override
		public void onClick(View widget) {
			MyMessageNotificationBean messageBean = (MyMessageNotificationBean) widget
					.getTag();
			if (usertag) {

				BaseFragement baseFragment = (BaseFragement) ((FragmentActivity) mContext)
						.getSupportFragmentManager().findFragmentById(
								R.id.fragmentcontainer);
				Bundle bundle = new Bundle();
				bundle.putString("Id", messageBean.getUser_id());
				MyMessageBoarderPageFragment fragment = new MyMessageBoarderPageFragment();
				fragment.setArguments(bundle);
				baseFragment.launchFragement(fragment, true);
			} else {
				String value = messageBean.getMembers_cnt();
				String intValue = value.replaceAll("[^0-9]", "");
				String defaulturl = AppData.getInstance().getMenulistData()
						.getLinks().get("get_other");
				defaulturl = defaulturl.replace("chk_flag=", "chk_flag="
						+ messageBean.getMsg_type());
				defaulturl = defaulturl.replace("user_id=", "user_id="
						+ messageBean.getUser_id());
				defaulturl = defaulturl.replace("thid=",
						"thid=" + messageBean.getThreadId());
				defaulturl = defaulturl.replace("cnt=", "cnt=" + intValue);

				Communicator.getInstance().executeService(mContext,
						RequestType.REQ_GET_OTHERS, defaulturl,
						MyMessageNotficationAdapter.this, progressBarr, false);
			}
			// Toast.makeText(mContext, intValue, Toast.LENGTH_SHORT).show();

		}

		@Override
		public void updateDrawState(TextPaint ds) {
			// TODO Auto-generated method stub
			super.updateDrawState(ds);
			ds.setUnderlineText(false);
		}

	}

	@Override
	public void onTaskComplete(int requestType, AppBeanParacable appBean) {
		if (appBean instanceof LastVisitedListData) {
			ArrayList<LastVisitedListBean> list = ((LastVisitedListData) appBean)
					.getLastVisitedList();
			if (list.size() == 0) {

			} else {
				NotificationDialogFragment fragment = new NotificationDialogFragment();
				Bundle i = new Bundle();
				i.putString(Constantlibs.HEADERSTRING, "Boarders");
				i.putSerializable("list", list);
				fragment.setArguments(i);

				fragment.setRetainInstance(true);
				fragment.show(((FragmentActivity) mContext)
						.getSupportFragmentManager(), "sortDialog");
			}
		}
	}

}