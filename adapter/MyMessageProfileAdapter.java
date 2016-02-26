package com.moneycontrol.handheld.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.moneycontrol.handheld.AppData;
import com.moneycontrol.handheld.BaseActivity;
import com.divum.MoneyControl.R;
import com.moneycontrol.handheld.api.AppBeanParacable;
import com.moneycontrol.handheld.api.RequestType;
import com.moneycontrol.handheld.custom.BoarderPageTextView;
import com.moneycontrol.handheld.entity.messages.LastVisitedListBean;
import com.moneycontrol.handheld.entity.messages.StatusEntity;
import com.moneycontrol.handheld.imageUtils.ImageDownloaderUtils;
import com.moneycontrol.handheld.massages.fragments.MyMessageBoarderPageFragment;
import com.moneycontrol.handheld.netcomm.Communicator;
import com.moneycontrol.handheld.util.Constantlibs;
import com.moneycontrol.handheld.util.TaskCompleteListiners;
import com.moneycontrol.handheld.util.UrlUtils;
import com.moneycontrol.handheld.util.Utility;
import com.neopixl.pixlui.components.textview.TextView;
import com.nostra13.universalimageloader.core.ImageLoader;

public class MyMessageProfileAdapter extends BaseAdapter implements
		TaskCompleteListiners {

	private LayoutInflater inflater;
	private ArrayList<LastVisitedListBean> datalist;
	private String htmlString;
	private int selectedPosition;
	private Context mContext;
	private String userId, key;
	private RelativeLayout progressBar;

	public MyMessageProfileAdapter(Context mContext,
			ArrayList<LastVisitedListBean> arrayList, String userId,
			String key, RelativeLayout progressBar) {
		inflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.datalist = arrayList;
		htmlString = "<font color=#FFFFFF><b>%s</b></font> <font color=#CCCCCC>%s</font>";
		this.mContext = mContext;
		this.userId = userId;
		this.key = key;
		this.progressBar = progressBar;

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
		final ViewHolder viewHolder;
		LastVisitedListBean messageBean = datalist.get(position);
		if (view == null) {
			view = inflater.inflate(R.layout.row_message_lastvisited, null);
			viewHolder = new ViewHolder();
			viewHolder.userImage = (ImageView) view
					.findViewById(R.id.profileImage);
			viewHolder.dummyImage = (ImageView) view
					.findViewById(R.id.dummyProfileImage);
			viewHolder.userName = (TextView) view.findViewById(R.id.tv_name);
			viewHolder.userTotalMessage = (TextView) view
					.findViewById(R.id.tv_number_msg);
			viewHolder.number_followers = (TextView) view
					.findViewById(R.id.tv_number_followers);
			viewHolder.follow = (Button) view.findViewById(R.id.follow);
			viewHolder.badge = (ImageView) view.findViewById(R.id.boarderImage);
			view.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) view.getTag();
		}
		view.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if (viewHolder.userName instanceof BoarderPageTextView) {
					((BoarderPageTextView) viewHolder.userName)
							.onClick(viewHolder.userName);
				}

			}
		});
		ImageLoader.getInstance().displayImage(messageBean.getUserImage(),
				viewHolder.userImage,
				ImageDownloaderUtils.displayImageOptionsNormal());

		ImageLoader.getInstance().displayImage(messageBean.getUserImage(),
				viewHolder.dummyImage,
				ImageDownloaderUtils.displayImageOptionsNormal());
		// ImageLoader.getInstance().displayImage(messageBean.getMsg_icon(),
		// viewHolder.badge,
		// ImageDownloaderUtils.displayImageOptionsWithoutPlaceHolder());
		viewHolder.badge.setImageResource(Utility.getMemberTypeImage(mContext,
				messageBean.getUserBadge(), viewHolder.badge));
		viewHolder.userName.setText(messageBean.getNickName());
		// viewHolder.userTotalMessage.setText(Html.fromHtml(String.format(
		// htmlString, messageBean.getMessage_posted(), "Messages")));
		// viewHolder.number_followers.setText(Html.fromHtml(String.format(
		// htmlString, messageBean.getNumberFollow(), "Followers")));
		viewHolder.userTotalMessage.setText(Html.fromHtml(String.format(
				htmlString,
				messageBean.getMessage_posted(),
				mContext.getResources().getText(
						R.string.txt_mypages_lastvisited_messages))));
		viewHolder.number_followers.setText(Html.fromHtml(String.format(
				htmlString, messageBean.getNumberFollow(), mContext
						.getResources().getText(R.string.txt_followers))));
		// viewHolder.userName.setText(text);
		viewHolder.follow.setTag(position);
		viewHolder.userName.setTag(R.string.about_us, messageBean.getUserId());
		viewHolder.userName.setTag(R.string.txt_boaders, true);
		if (messageBean.isFollow()) {
			// viewHolder.follow.setImageResource(R.drawable.unfollow);
			viewHolder.follow.setBackgroundResource(R.drawable.follow);
			viewHolder.follow.setPadding(12, 0, 12, 0);
			viewHolder.follow.setText(R.string.unfollowtext);
		} else {
			// viewHolder.follow.setImageResource(R.drawable.follow);
			viewHolder.follow.setBackgroundResource(R.drawable.follow);
			viewHolder.follow.setPadding(30, 0, 30, 0);
			viewHolder.follow.setText(R.string.followtext);
		}

		viewHolder.userName.setTag(R.string.txt_boaders, true);
		viewHolder.userImage.setVisibility(View.VISIBLE);
		viewHolder.userImage.setTag(messageBean.getUserId());
		viewHolder.follow.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				selectedPosition = (Integer) v.getTag();
				LastVisitedListBean messageBean = datalist
						.get(selectedPosition);
				String url = "";
				if (!messageBean.isFollow()) {
					url = UrlUtils.getFollowUserAPI(mContext,
							messageBean.getUserId());
				} else {

					url = UrlUtils.getUNFollowUserAPI(mContext,
							messageBean.getUserId());

				}
				AppData.getInstance().setMessageKey(key, true);
				Communicator.getInstance().executeService(v.getContext(),
						RequestType.REQ_UNFOLLOW, url,
						MyMessageProfileAdapter.this, progressBar, false);

			}
		});
		if (getcurrentFragment(mContext) instanceof MyMessageBoarderPageFragment) {
			viewHolder.follow.setVisibility(View.GONE);
			viewHolder.follow.setOnClickListener(null);
			viewHolder.number_followers.setVisibility(View.GONE);
		}

		return view;
	}

	public Fragment getcurrentFragment(Context mContext) {
		Fragment fragment = null;
		if (mContext instanceof BaseActivity) {
			BaseActivity activty = (BaseActivity) mContext;
			fragment = activty.getSupportFragmentManager().findFragmentById(
					R.id.fragmentcontainer);
		}
		return fragment;

	}

	private static class ViewHolder {
		private TextView userName, userTotalMessage, number_followers;
		private ImageView userImage, dummyImage, badge;
		private Button follow;

	}

	@Override
	public void onTaskComplete(int requestType, AppBeanParacable appBean) {
		if (requestType == RequestType.REQ_UNFOLLOW) {
			if (appBean instanceof StatusEntity) {
				String msgtoast = ((StatusEntity) appBean).getStatus();
				Utility.getInstance().showToast(mContext, msgtoast);
				LastVisitedListBean messageBean = datalist
						.get(selectedPosition);

				if (messageBean.isFollow()) {
					messageBean.setFollow(0);
				} else {
					messageBean.setFollow(1);
				}
				datalist.set(selectedPosition, messageBean);
				notifyDataSetChanged();
			}
		}
	}

}