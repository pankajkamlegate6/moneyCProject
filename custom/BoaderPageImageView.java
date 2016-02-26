package com.moneycontrol.handheld.custom;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.moneycontrol.handheld.BaseActivity;
import com.divum.MoneyControl.R;
import com.moneycontrol.handheld.constant.AppConstants;
import com.moneycontrol.handheld.massages.fragments.BaseFragement;
import com.moneycontrol.handheld.massages.fragments.MyMessageBoarderPageFragment;
import com.moneycontrol.handheld.massages.fragments.MyMessagesFragement;
import com.moneycontrol.handheld.massages.fragments.MyThreadMessage;
import com.moneycontrol.handheld.util.Utility;

public class BoaderPageImageView extends ImageView implements OnClickListener {

	public BoaderPageImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		init();
	}

	public BoaderPageImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init();
	}

	public BoaderPageImageView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v.getTag() != null) {
			String id = (String) v.getTag();

			BaseFragement baseFragment = (BaseFragement) ((FragmentActivity) getContext())
					.getSupportFragmentManager().findFragmentById(
							R.id.fragmentcontainer);
			if (id.equalsIgnoreCase(Utility.currentUserId(null, getContext()))
					&& baseFragment instanceof MyMessagesFragement) {
				return;
			} else if (id.equalsIgnoreCase(Utility.currentUserId(null,
					getContext()))) {
				openMyPage();
				return;
			}

			if (baseFragment instanceof MyThreadMessage) {

				Bundle bundle = new Bundle();
				if (TextUtils.isEmpty(id) || id.equalsIgnoreCase("guest")) {
					baseFragment.showToast(R.string.guest_user_msg);
				} else {
					bundle.putString("Id", id);
					MyMessageBoarderPageFragment fragment = new MyMessageBoarderPageFragment();
					fragment.setArguments(bundle);
					baseFragment.launchFragementV2(fragment, true);
				}
			} else if (!(Utility.LASTVISITEDBOARDERID.equalsIgnoreCase(id))) {
				// else if (!(baseFragment instanceof
				// MyMessageBoarderPageFragment)) {
				Bundle bundle = new Bundle();
				if (TextUtils.isEmpty(id) || id.equalsIgnoreCase("guest")) {
					baseFragment.showToast(R.string.guest_user_msg);
				} else {
					bundle.putString("Id", id);
					MyMessageBoarderPageFragment fragment = new MyMessageBoarderPageFragment();
					fragment.setArguments(bundle);
					baseFragment.launchFragement(fragment, true);
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
	}

	private void openMyPage() {
		BaseActivity activity = (BaseActivity) getContext();
		activity.openMymessageSection(0);

	}
}
