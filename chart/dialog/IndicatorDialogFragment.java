package com.moneycontrol.handheld.chart.dialog;

import java.util.ArrayList;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.divum.MoneyControl.R;
import com.moneycontrol.handheld.entity.messages.FilterByList;
import com.moneycontrol.handheld.util.Utility;
import com.neopixl.pixlui.components.edittext.EditText;

public class IndicatorDialogFragment extends DialogFragment implements
		OnClickListener, TextWatcher {

	private ImageView option2, option3, option4;
	private LinearLayout Li1, Li2, Li3, Li4;
	private static String SORTTYPE = "ASC";

	private int sortId = -1;
	private String ParnetName = "";
	private TextView txt1, txt2, txt3, txt4;
	public static int LastSortTpe = -1;
	public static int count = 0;
	private ArrayList<FilterByList> textlist = new ArrayList<FilterByList>();
	private LinearLayout mainLayout;
	private TextView headertextView;;
	private String headerTextViewString;
	int lastposition = 0;
	String userId;
	boolean volumeshow = true;
	CheckBox volume_toggle, check_day_1, check_day_2;
	private Button save;
	private EditText edit_day_1, edit_day_2;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

	}

	private onDialogUpdateCallback ondialogCallUpdate;

	public void setOndialogCallUpdate(onDialogUpdateCallback ondialogCallUpdate) {
		this.ondialogCallUpdate = ondialogCallUpdate;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Dialog dialog = super.onCreateDialog(savedInstanceState);

		return dialog;
	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		if (getDialog() != null) {

		}
		// getDialog().getWindow().setLayout(LayoutParams.FILL_PARENT,
		// LayoutParams.FILL_PARENT);
	}

	public IndicatorDialogFragment() {
		// Empty constructor required for DialogFragment
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.dialog_chart_indicator,
				container);

		volume_toggle = (CheckBox) rootView.findViewById(R.id.volume_toggle);
		check_day_1 = (CheckBox) rootView.findViewById(R.id.day_average_check1);
		check_day_2 = (CheckBox) rootView.findViewById(R.id.day_average_check2);
		edit_day_1 = (EditText) rootView.findViewById(R.id.edt_day_average_1);
		edit_day_2 = (EditText) rootView.findViewById(R.id.edt_day_average_2);
		// getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(0));
		// FAndOActionChildFragment.lastSelect_Sorting_TYPE = 0;
		int dividerId = getDialog().getContext().getResources()
				.getIdentifier("android:id/titleDivider", null, null);
		if (dividerId != 0) {
			View divider = getDialog().findViewById(dividerId);
			if (divider != null)
				divider.setBackgroundColor(Color.TRANSPARENT);
		}
		volumeshow = Utility.getVolumeIndicator(getActivity());
		check_day_1.setChecked(Utility.enabledayAverage1(getActivity()));
		edit_day_1.append("" + Utility.getAverageDay1(getActivity()));
		edit_day_2.append("" + Utility.getAverageDay2(getActivity()));
		check_day_2.setChecked(Utility.enabledayAverage2(getActivity()));
		volume_toggle.setChecked(volumeshow);
		save = (Button) rootView.findViewById(R.id.save_indicator);
		save.setOnClickListener(this);
		save = (Button) rootView.findViewById(R.id.save_indicator);
		save.setOnClickListener(this);
		edit_day_1.addTextChangedListener(this);
		edit_day_2.addTextChangedListener(this);
		return rootView;
	}

	@Override
	public void onClick(View view) {
		if (save.getId() == view.getId()) {
			doSave();

		}

	}

	private void doSave() {
		if (check_day_1.isChecked()) {
			if (edit_day_1.getText().toString().trim().length() <= 0) {
				Utility.getInstance().showToast(
						getActivity(),
						getResources().getString(
								R.string.please_enter_value_on_moving_average));
				return;
			} else if (Double.valueOf(edit_day_1.getText().toString().trim()) > 250
					|| Double.valueOf(edit_day_1.getText().toString().trim()) < 3) {
				Utility.getInstance().showToast(
						getActivity(),
						getResources().getString(
								R.string.moving_average_value_should_be_in,
								Toast.LENGTH_SHORT));

				return;
			} else {
				Utility.setdayAverage1(getActivity(), true,
						Integer.valueOf(edit_day_1.getText().toString().trim()));
			}
		} else {
			Utility.setdayAverage1(getActivity(), false, 0);
		}
		if (check_day_2.isChecked()) {
			if (edit_day_2.getText().toString().trim().length() <= 0) {
				Utility.getInstance().showToast(
						getActivity(),
						getResources().getString(
								R.string.please_enter_value_on_moving_average),
						Toast.LENGTH_LONG);

				return;
			} else if (Double.valueOf(edit_day_2.getText().toString().trim()) > 250
					|| Double.valueOf(edit_day_2.getText().toString().trim()) < 3) {

				Utility.getInstance().showToast(
						getActivity(),
						getResources().getString(
								R.string.moving_average_value_should_be_in),
						Toast.LENGTH_LONG);
				return;
			} else {
				Utility.setdayAverage2(getActivity(), true,
						Integer.valueOf(edit_day_2.getText().toString().trim()));
			}
		} else {
			Utility.setdayAverage2(getActivity(), false, 0);
		}

		Utility.setVolumeIndicator(getActivity(), volume_toggle.isChecked());
		dismiss();
		if (ondialogCallUpdate != null) {
			ondialogCallUpdate.onDialogUpdate();
		}
	}

	public interface onDialogUpdateCallback {
		void onDialogUpdate();
	}

	@Override
	public void afterTextChanged(Editable arg0) {
		String value;
		if (edit_day_1.hasFocus()) {
			value = edit_day_1.getText().toString().trim();
			if (value.length() > 0) {
				if (Double.valueOf(value) > 0) {
					check_day_1.setChecked(true);
				} else {
					check_day_1.setChecked(false);

				}
			} else {
				check_day_1.setChecked(false);
			}
		}
		if (edit_day_2.hasFocus()) {
			value = edit_day_2.getText().toString().trim();
			if (value.length() > 0) {
				if (Double.valueOf(value) > 0) {
					check_day_2.setChecked(true);
				} else {
					check_day_2.setChecked(false);

				}

			} else {
				check_day_2.setChecked(false);
			}
		}

	}

	@Override
	public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
			int arg3) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub

	}
}