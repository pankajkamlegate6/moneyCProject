package com.moneycontrol.handheld.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.divum.MoneyControl.R;
import com.moneycontrol.handheld.AppData;
import com.moneycontrol.handheld.BaseActivity;
import com.moneycontrol.handheld.api.AppBeanParacable;
import com.moneycontrol.handheld.api.RequestType;
import com.moneycontrol.handheld.custom.BoarderPageTextView;
import com.moneycontrol.handheld.entity.messages.MessageCategoryItemData;
import com.moneycontrol.handheld.entity.messages.StatusEntity;
import com.moneycontrol.handheld.imageUtils.ImageDownloaderUtils;
import com.moneycontrol.handheld.massages.fragments.BaseFragement;
import com.moneycontrol.handheld.netcomm.Communicator;
import com.moneycontrol.handheld.parser.ParseCall;
import com.moneycontrol.handheld.util.TaskCompleteListiners;
import com.moneycontrol.handheld.util.UrlUtils;
import com.moneycontrol.handheld.util.Utility;
import com.neopixl.pixlui.components.textview.TextView;
import com.nostra13.universalimageloader.core.ImageLoader;

public class ListLastVisitedAdapter extends BaseAdapter implements
		TaskCompleteListiners {

	private LayoutInflater inflater;
	private ArrayList<MessageCategoryItemData> datalist;
	private String htmlString;
	private int selectedPosition, lastPosition;
	private Context mContext;
	private String userId;
	private String keys;
	private RelativeLayout progressBarr;

	public ListLastVisitedAdapter(Context mContext,
			ArrayList<MessageCategoryItemData> list, int lastPosition,
			String userId, String keys, RelativeLayout progressBarr) {
		inflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.datalist = list;
		htmlString = "<font color=#CCCCCC><b>%s</b></font> <font color=#999999>%s</font>";
		this.lastPosition = lastPosition;
		this.mContext = mContext;
		this.userId = userId;
		this.keys = keys;
		this.progressBarr = progressBarr;
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
		MessageCategoryItemData messageBean = datalist.get(position);
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
			LinearLayout.LayoutParams params;
			if (lastPosition <= 2) {
				params = (android.widget.LinearLayout.LayoutParams) viewHolder.userName
						.getLayoutParams();
				params.leftMargin = 0;
			} else {
				params = (android.widget.LinearLayout.LayoutParams) viewHolder.userName
						.getLayoutParams();
				params.leftMargin = mContext
						.getResources()
						.getDimensionPixelSize(R.dimen.common_left_right_margin);
			}
		} else {
			viewHolder = (ViewHolder) view.getTag();
		}

		ImageLoader.getInstance().displayImage(messageBean.getUserimg(),
				viewHolder.userImage,
				ImageDownloaderUtils.displayImageOptionsNormal());

		ImageLoader.getInstance().displayImage(messageBean.getUserimg(),
				viewHolder.dummyImage,
				ImageDownloaderUtils.displayImageOptionsNormal());

		viewHolder.badge.setImageResource(Utility.getMemberTypeImage(mContext,
				messageBean.getMembertype(), viewHolder.badge));
		if (!TextUtils.isEmpty(messageBean.getUsernickname())) {
			viewHolder.userName.setText(messageBean.getUsernickname());
		} else {
			viewHolder.userName.setText(messageBean.getTopic());
		}

		viewHolder.userTotalMessage
				.setText(Html.fromHtml(String.format(
						htmlString,
						messageBean.getMsg_count(),
						mContext.getResources().getString(
								R.string.txt_mypages_lastvisited_messages))));
		viewHolder.number_followers.setText(Html.fromHtml(String.format(
				htmlString, messageBean.getFollower(), mContext.getResources()
						.getString(R.string.txt_followers))));
		viewHolder.follow.setTag(position);

		if (lastPosition <= 2) {
			viewHolder.badge.setVisibility(View.GONE);
			viewHolder.userImage.setVisibility(View.GONE);
			viewHolder.userName.setTag(R.string.txt_boaders, false);
			viewHolder.userName.setTag(R.string.about_us, messageBean);
		} else {
			viewHolder.badge.setVisibility(View.VISIBLE);
			viewHolder.userName.setTag(R.string.txt_boaders, true);
			viewHolder.userImage.setVisibility(View.VISIBLE);
			viewHolder.userName.setTag(R.string.about_us,
					messageBean.getUserid());
		}
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
		view.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (viewHolder.userName instanceof BoarderPageTextView) {
					((BoarderPageTextView) viewHolder.userName)
							.onClick(viewHolder.userName);
				}
			}
		});
		viewHolder.follow.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				selectedPosition = (Integer) v.getTag();
				MessageCategoryItemData messageBean = datalist
						.get(selectedPosition);
				String url;
				if (ParseCall.getInstance().isLoggedIn(mContext)) {
					if (lastPosition == 3) {
						if (!messageBean.isFollow()) {
							url = UrlUtils.getFollowUserAPI(mContext,
									messageBean.getUserid());

						} else {
							url = UrlUtils.getUNFollowUserAPI(mContext,
									messageBean.getUserid());

						}
					} else {
						if (!messageBean.isFollow()) {
							url = UrlUtils.getFollowTopicAPI(mContext,
									messageBean.getTopic_id());
						} else {
							url = UrlUtils.getUNFollowTopicAPI(mContext,
									messageBean.getTopic_id());

						}
					}
					url = url + "&" + ParseCall._deviceData;
					Communicator.getInstance().executeService(v.getContext(),
							RequestType.REQ_UNFOLLOW, url,
							ListLastVisitedAdapter.this, progressBarr, false);

				} else {

					final BaseFragement frag = (BaseFragement) ((BaseActivity) mContext)
							.getCurrentfragment(((BaseActivity) mContext)
									.getCurrentFragmentName());
					frag.addLoginAlert(selectedPosition, 6, false, false,
							false, messageBean, "MyMassage");

				}
				AppData.getInstance().setMessageKey(keys, true);
			}
		});
		return view;
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
				MessageCategoryItemData messageBean = datalist
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
