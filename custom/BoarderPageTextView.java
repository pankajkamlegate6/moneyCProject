package com.moneycontrol.handheld.custom;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;

import com.moneycontrol.handheld.BaseActivity;
import com.divum.MoneyControl.R;
import com.moneycontrol.handheld.constant.AppConstants;
import com.moneycontrol.handheld.entity.messages.MessageCategoryItemData;
import com.moneycontrol.handheld.massages.fragments.BaseFragement;
import com.moneycontrol.handheld.massages.fragments.MessageTopicDetail;
import com.moneycontrol.handheld.massages.fragments.MyMessageBoarderPageFragment;
import com.moneycontrol.handheld.massages.fragments.MyMessagesFragement;
import com.moneycontrol.handheld.massages.fragments.MyThreadMessage;
import com.moneycontrol.handheld.util.Utility;
import com.neopixl.pixlui.components.textview.TextView;

public class BoarderPageTextView extends TextView implements OnClickListener {

	public BoarderPageTextView(Context context, AttributeSet attrs,
			boolean canBeEllipsized) {
		super(context, attrs, canBeEllipsized);
		// TODO Auto-generated constructor stub
		init();
	}

	public BoarderPageTextView(Context context, AttributeSet attrs,
			int defStyle, boolean canBeEllipsized) {
		super(context, attrs, defStyle, canBeEllipsized);
		init();
	}

	public BoarderPageTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		init();
	}

	public BoarderPageTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init();
	}

	public BoarderPageTextView(Context context, boolean canBeEllipsized) {
		super(context, canBeEllipsized);
		// TODO Auto-generated constructor stub
		init();
	}

	public BoarderPageTextView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init();
	}

	@Override
	public void onClick(View v) {
		MessageCategoryItemData itemdata = null;
		String id = null;
		BaseFragement baseFragment = (BaseFragement) ((FragmentActivity) getContext())
				.getSupportFragmentManager().findFragmentById(
						R.id.fragmentcontainer);
		if (v.getTag(R.string.about_us) instanceof String) {
			id = (String) v.getTag(R.string.about_us);

			if (id.equalsIgnoreCase(Utility.currentUserId(null, getContext()))
					&& baseFragment instanceof MyMessagesFragement) {
				return;
			} else if (id.equalsIgnoreCase(Utility.currentUserId(null,
					getContext()))) {
				openMyPage();
			}
		} else {
			itemdata = (MessageCategoryItemData) v.getTag(R.string.about_us);
		}

		// TODO Auto-generated method stub

		// ((BaseActivity) v.getContext()).RemoveAllUpperScreen();
		boolean boader = (Boolean) v.getTag(R.string.txt_boaders);

		if (boader) {

			// TODO Auto-generated method stub

			// if (baseFragment instanceof MyMessageBoarderPageFragment ==
			// true)
			// {
			//
			// } else

			if (baseFragment instanceof MyThreadMessage) {
				if (TextUtils.isEmpty(id) || id.equalsIgnoreCase("guest")) {
					baseFragment.showToast(R.string.guest_user_msg);
				} else {

					if (id.equalsIgnoreCase(Utility.currentUserId(null,
							getContext()))) {

					} else {
						Bundle bundle = new Bundle();
						bundle.putString("Id", id);
						MyMessageBoarderPageFragment fragment = new MyMessageBoarderPageFragment();
						fragment.setArguments(bundle);
						baseFragment.launchFragementV2(fragment, true);
					}
				}
			} else if (!(Utility.LASTVISITEDBOARDERID.equalsIgnoreCase(id))) {
				// else if (!(baseFragment instanceof
				// MyMessageBoarderPageFragment)) {
				if (TextUtils.isEmpty(id) || id.equalsIgnoreCase("guest")) {
					baseFragment.showToast(R.string.guest_user_msg);
				} else {
					if (id.equalsIgnoreCase(Utility.currentUserId(null,
							getContext()))) {
						openMyPage();
					} else {
						Bundle bundle = new Bundle();
						bundle.putString("Id", id);
						MyMessageBoarderPageFragment fragment = new MyMessageBoarderPageFragment();
						fragment.setArguments(bundle);
						baseFragment.launchFragement(fragment, true);
					}
				}

			}

			// Bundle bundle = new Bundle();
			// bundle.putString(AppConstants.THREAD_ID, id);
			// bundle.putString("replyCount", "25");
			// bundle.putString("topicName", "tata");
			//
			// MyThreadMessage threadFragment = new MyThreadMessage();
			// threadFragment.setArguments(bundle);
		}

		else {
			if (baseFragment instanceof MessageTopicDetail == false) {
				Bundle bundle = new Bundle();

				bundle.putSerializable(AppConstants.SERIALIZABLE_OBJECT,
						itemdata);
				MessageTopicDetail topicDetailFragment = new MessageTopicDetail();
				topicDetailFragment.setArguments(bundle);

				baseFragment.launchFragement(topicDetailFragment, true);
			}
		}
	}

	private void init() {
		setOnClickListener(this);

	}

	public void openMyPage() {
		BaseActivity activity = (BaseActivity) getContext();
		activity.openMymessageSection(0);
	}
}
