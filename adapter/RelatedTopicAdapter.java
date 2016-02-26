package com.moneycontrol.handheld.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;

import com.divum.MoneyControl.R;
import com.moneycontrol.handheld.api.AppBeanParacable;
import com.moneycontrol.handheld.api.RequestType;
import com.moneycontrol.handheld.entity.messages.MessageCategoryItemData;
import com.moneycontrol.handheld.entity.messages.StatusEntity;
import com.moneycontrol.handheld.netcomm.Communicator;
import com.moneycontrol.handheld.util.Constantlibs;
import com.moneycontrol.handheld.util.TaskCompleteListiners;
import com.moneycontrol.handheld.util.Utility;
import com.neopixl.pixlui.components.textview.TextView;

public class RelatedTopicAdapter extends BaseAdapter implements
		TaskCompleteListiners {

	private LayoutInflater inflater;
	private ArrayList<MessageCategoryItemData> datalist;
	private String htmlString;
	private int selectedPosition, lastPosition;
	private Context mContext;
	private String userId;

	public RelatedTopicAdapter(Context mContext,
			ArrayList<MessageCategoryItemData> list, int lastPosition,
			String userId) {
		inflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.datalist = list;
		htmlString = "<font color=#CCCCCC><b>%s</b></font> <font color=#999999>%s</font>";
		this.lastPosition = lastPosition;
		this.mContext = mContext;
		this.userId = userId;
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
		MessageCategoryItemData messageBean = datalist.get(position);
		if (view == null) {
			view = inflater.inflate(R.layout.trending_layout, null);
			viewHolder = new ViewHolder();
			viewHolder.topicName = (TextView) view
					.findViewById(R.id.tv_message_topic_name);
			viewHolder.totalMessage = (TextView) view
					.findViewById(R.id.tv_message_count);
			viewHolder.number_followers = (TextView) view
					.findViewById(R.id.tv_follower_count);

			viewHolder.follow = (Button) view.findViewById(R.id.ib_follow);
			view.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) view.getTag();
		}

		viewHolder.topicName.setText(messageBean.getTopic());
		// viewHolder.totalMessage.setText(Html.fromHtml(String.format(htmlString,
		// messageBean.getMsg_posted_count(), "Messages")));
		viewHolder.totalMessage.setText(Html.fromHtml(String.format(htmlString,
				messageBean.getMsg_posted_count(), mContext.getResources()
						.getText(R.string.txt_mypages_lastvisited_messages))));
		viewHolder.number_followers.setText(Html.fromHtml(String.format(
				htmlString, messageBean.getFollower(), mContext.getResources()
						.getText(R.string.txt_followers))));
		viewHolder.follow.setTag(position);
		viewHolder.topicName.setTag(R.string.about_us, messageBean.getUserid());
		viewHolder.topicName.setTag(R.string.txt_boaders, false);
		if (messageBean.isFollow()) {
			// viewHolder.follow.setImageResource(R.drawable.unfollow);
			viewHolder.follow.setBackgroundResource(R.drawable.unfollow);
		} else {
			// viewHolder.follow.setImageResource(R.drawable.follow);
			viewHolder.follow.setBackgroundResource(R.drawable.follow);
		}
		viewHolder.follow.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				selectedPosition = (Integer) v.getTag();
				MessageCategoryItemData messageBean = datalist
						.get(selectedPosition);

				String url;
				if (lastPosition == 2) {
					if (!messageBean.isFollow()) {
						url = String.format(Constantlibs.URL_FOLLOW_USER,
								Utility.currentUserId(null, v.getContext()),
								messageBean.getUserid(),
								Utility.currentToken(v.getContext()));
					} else {

						url = String.format(Constantlibs.URL_UNFOLLOW_USER,
								Utility.currentUserId(null, v.getContext()),
								messageBean.getUserid(),
								Utility.currentToken(v.getContext()));

					}
				} else {
					if (!messageBean.isFollow()) {

						url = String.format(Constantlibs.URL_FOLLOW_TOPIC,
								Utility.currentUserId(null, v.getContext()),
								messageBean.getTopic_id(),
								Utility.currentToken(v.getContext()));
					} else {
						url = String.format(Constantlibs.URL_UNFOLLOW_TOPIC,
								Utility.currentUserId(null, v.getContext()),
								messageBean.getTopic_id(),
								Utility.currentToken(v.getContext()));
					}
				}
				Communicator.getInstance().executeService(v.getContext(),
						RequestType.REQ_UNFOLLOW, url,
						RelatedTopicAdapter.this, null, false);

			}
		});
		return view;
	}

	private static class ViewHolder {
		private TextView topicName, totalMessage, number_followers;
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
