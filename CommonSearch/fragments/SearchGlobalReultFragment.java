package com.moneycontrol.handheld.CommonSearch.fragments;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources.NotFoundException;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.Html;
import android.text.Html.ImageGetter;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.ClickableSpan;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.moneycontrol.handheld.AppData;
import com.moneycontrol.handheld.BaseActivity;
import com.divum.MoneyControl.R;
import com.moneycontrol.handheld.constant.AppConstants;
import com.moneycontrol.handheld.entity.market.SearchData;
import com.moneycontrol.handheld.entity.search.SearchMessageData;
import com.moneycontrol.handheld.fragments.StockDetailFragment;
import com.moneycontrol.handheld.massages.fragments.BaseFragement;
import com.moneycontrol.handheld.massages.fragments.MessageTopicDetail;
import com.moneycontrol.handheld.massages.fragments.SearchMessageFragment;
import com.moneycontrol.handheld.massages.fragments.SearchMessageReultFragment.Search_home_Adapter;
import com.moneycontrol.handheld.parser.ParseCall;
import com.moneycontrol.handheld.util.Utility;

public class SearchGlobalReultFragment extends BaseFragement implements
		StockDetailFragment.OnCategoryClicked {

	private View viewInflated;

	private LinearLayout llSpinner;
	private String spinnerItems[];
	private PullToRefreshListView list = null;
	private ArrayList<SearchData> resultsseachData = null;
	private ArrayList<SearchData> tempData = new ArrayList<SearchData>();
	private Search_home_Adapter adapter;
	private String htmlwhiteOrangeString = "<font color=#CCCCCC><b>%s</b></font> <font color=#FF9933>%s</font>";
	private String msg_type_html = "<font color=#FFFFFF><b>";
	private String category_type_html = "<font color=#999999><b>";
	private String htmlString = "<font size='6' color=#FFFFFF><b></b>";
	private String htmlString1 = "<font size='6' color=#FFFFFF><b></b>";
	private String htmlString2 = "<font size='6' color=#999999><b></b>";
	private String valhtmlString = "<font size='6' color=#FFFFFF><b>%s</b></font><font color=#999999>%s</font><font color=#999999>%s</font>";
	private String news_side_drawablehtml = "<img src=\"icon_search.png\" align=\"justify\"/>";
	private String smallside_drawablehtml = "<img src=\"latest11.png\" align=\"justify\"/>";
	private String valhtmltwoString = "<font size='6' color=#FFFFFF><b>%s</b></font><font color=#999999>%s</font>";
	private String no_match_drawablehtml = "<img src=\"exclamation.png\" align=\"justify\"/>";
	private static final int REQUEST_CODE = 1234;
	private TextView no_recordFoundtxt = null;
	private String search_word = "";
	private String screen_name = "";
	private String screenID = "";
	private String base_url = "";
	private SearchMessageData searchMessageObj = new SearchMessageData();
	private Handler handler = null;
	private boolean pulltorefresh = false;
	private Handler myHandler = new Handler();
	public boolean isPagination = false;
	private int currentPageMessages = 0;
	private boolean isProcessingNetworkThread = false;
	boolean noLoaderwhilePull;
	private RelativeLayout progressBarr;
	private int postion = 0;

	@Override
	public void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);

		outState.putSerializable("SaveData", tempData);
		outState.putString("UNIQUE_ID", screenID);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		postion = getArguments().getInt("Position");
		tag = "" + postion;
		super.onCreateView(inflater, container, savedInstanceState);

		viewInflated = inflater.inflate(R.layout.search_result_blank_layout,
				container, false);

		return viewInflated;

	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);

		// if (savedInstanceState != null) {
		// if (saveBundle == null) {
		// saveBundle = savedInstanceState;
		// }
		// }

		if (saveBundle != null) {

			tempData = (ArrayList<SearchData>) saveBundle
					.getSerializable("SaveData");
			screenID = saveBundle.getString("UNIQUE_ID");

		} else {

			search_word = getArguments().getString("KEY");
			searchMessageObj = (SearchMessageData) getArguments()
					.getSerializable("Object");
			tempData = (ArrayList<SearchData>) getArguments().getSerializable(
					"list");
			screenID = getArguments().getString("UNIQUE_ID");
			screen_name = getArguments().getString("ScreenName");
			base_url = getArguments().getString(AppConstants.SERVER_URL);

		}
		isPagination = true;
		initViews();

	}

	public void sendResult(String arg1, String arg3, int arg2) {

		Intent i = new Intent();
		Bundle informacion = new Bundle();
		informacion.putString("arg1", arg1);
		informacion.putInt("arg2", arg2);
		informacion.putString("arg3", arg3);
		i.putExtras(informacion);
		if (resultsseachData != null) {
			Fragment frag = getTargetFragment();
			if (frag != null) {
				frag.onActivityResult(getTargetRequestCode(), 1, i);
			} else {

				Fragment bfrag = ((BaseActivity) getActivity())
						.getCurrentfragment("GlobalSearchFragment");
				bfrag.onActivityResult(getTargetRequestCode(), 1, i);
			}
		}

	}

	private void initViews() {

		// list = (ListView) viewInflated.findViewById(R.id.getQuote_list1);
		// tempData = resultsseachData;
		list = (PullToRefreshListView) viewInflated
				.findViewById(R.id.getQuote_list1);
		progressBarr = (RelativeLayout) viewInflated
				.findViewById(R.id.progressBarr);
		dismissProgressLoader();
		list.setOnRefreshListener(new OnRefreshListener<ListView>() {
			@Override
			public void onRefresh(PullToRefreshBase<ListView> refreshView) {
				// doGetNews(false);
				noLoaderwhilePull = true;
				pulltorefresh = true;
				currentPageMessages = 0;
				isProcessingNetworkThread = true;
				doRequest();

			}
		});

		list.setOnScrollListener(new OnScrollListener() {

			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {

				if (tempData.size() > 1) {

					if (view.getLastVisiblePosition() > (tempData.size() - 1)
							&& !isProcessingNetworkThread && isPagination) {
						try {

							isProcessingNetworkThread = true;
							currentPageMessages++;
							doRequest(); // (++currentPageMessages);
						} catch (Exception e) {
							e.printStackTrace();
							isProcessingNetworkThread = false;
						}
					}
				}
			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				// TODO Auto-generated method stub

			}
		});

		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				// TODO Auto-generated method stub
				Utility.getInstance().hideKeyBoard(getActivity());
				int pos = position - 1;

				try {
					if (screenID.equalsIgnoreCase("1")) {

						if (pos > -1)
							if (tempData.get(pos) != null
									&& tempData.get(pos).getCategory_id() != null
									&& tempData.get(pos).getType()
											.equalsIgnoreCase("searchCatgory")) {

								if (tempData.get(pos).getCategory_id()
										.equalsIgnoreCase("1")) {

									sendResult(screen_name, tempData.get(pos)
											.getCategory(), 1);
								} else if (tempData.get(pos).getCategory_id()
										.equalsIgnoreCase("2")) {
									sendResult(screen_name, tempData.get(pos)
											.getCategory(), 2);
								} else if (tempData.get(pos).getCategory_id()
										.equalsIgnoreCase("3")) {

									sendResult(screen_name, tempData.get(pos)
											.getCategory_id(), 3);

								} else if (tempData.get(pos).getCategory_id()
										.equalsIgnoreCase("4")) {

									sendResult(screen_name, tempData.get(pos)
											.getCategory(), 4);

								} else if (tempData.get(pos).getCategory_id()
										.equalsIgnoreCase("5")) {
									sendResult(screen_name, tempData.get(pos)
											.getCategory(), 5);

								} else if (tempData.get(pos).getCategory_id()
										.equalsIgnoreCase("6")) {
									sendResult(screen_name, tempData.get(pos)
											.getCategory(), 6);

								} else if (tempData.get(pos).getCategory_id()
										.equalsIgnoreCase("7")) {
									sendResult(screen_name, tempData.get(pos)
											.getCategory(), 7);
								}
							}

					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				try {
					if (tempData != null
							&& tempData.get(pos) != null
							&& tempData.get(pos).getId() != null
							&& (!tempData.get(pos).getId()
									.equalsIgnoreCase("null"))
							&& (!tempData.get(pos).getId()
									.equalsIgnoreCase("item"))) {

						try {
							if (TextUtils.isEmpty(tempData.get(pos)
									.getExpiry_date_d())) {
								tempData.get(pos).setExpiry_date_d("null");
							}
						} catch (Exception e) {
							// TODO Auto-generated catch block
							tempData.get(pos).setExpiry_date_d("null");
							e.printStackTrace();
						}

						try {
							if (TextUtils.isEmpty(tempData.get(pos)
									.getInstrument())) {
								tempData.get(pos).setInstrument("null");
							}
						} catch (Exception e) {
							// TODO Auto-generated catch block
							tempData.get(pos).setInstrument("null");
							e.printStackTrace();
						}

						String name = tempData.get(pos).getShortname() + "@"
								+ tempData.get(pos).getId() + "@"
								+ tempData.get(pos).getCategory_id() + "@"
								+ tempData.get(pos).getEx() + "@"
								+ tempData.get(pos).getExpiry_date() + "@"
								+ tempData.get(pos).getExpiry_date_d() + "@"
								+ tempData.get(pos).getInstrument() + "@"
								+ Utility.getInstance().getSELECTED_LANGUAGE();

						Utility.getInstance().addLastCommonSearch(name,
								getActivity());

						if (tempData.get(pos).getCategory_id()
								.equalsIgnoreCase("1")) {

							showStockDetails(tempData.get(pos));

						} else if (tempData.get(pos).getCategory_id()
								.equalsIgnoreCase("2")) {

							showMutualFundskDetails(tempData.get(pos));

						} else if (tempData.get(pos).getCategory_id()
								.equalsIgnoreCase("3")) {

							showCommodityDetails(tempData.get(pos));

						} else if (tempData.get(pos).getCategory_id()
								.equalsIgnoreCase("4")) {

							showFutureDetails(tempData.get(pos));
							// }

						} else if (tempData.get(pos).getCategory_id()
								.equalsIgnoreCase("5")) {

						} else if (tempData.get(pos).getCategory_id()
								.equalsIgnoreCase("6")) {

						} else if (tempData.get(pos).getCategory_id()
								.equalsIgnoreCase("7")) {

						} else if (tempData.get(pos).getCategory_id()
								.equalsIgnoreCase("8")) {
							// boarder
							lunchBoarderScreen(tempData.get(pos).getId());
						}

						else if (tempData.get(pos).getCategory_id()
								.equalsIgnoreCase("9")) {
							// topic
							launchTopicScreen(tempData.get(pos).getTopicid(),
									tempData.get(pos).getCategory_id(),
									"GlobalSearchFragment");
						}

						else if (tempData.get(pos).getCategory_id()
								.equalsIgnoreCase("10")) {
							// boarder
							lunchIndicesDeatilsScreen(tempData.get(pos));
						}

					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		});

		no_recordFoundtxt = (TextView) viewInflated
				.findViewById(R.id.no_recordFound);

		// AddData();

		if (saveBundle != null) {

			// myHandler.post(updateRunnable);

			AddData();

		}

		else {

			doRequest();

		}

	}

	public void doRequest() {
		if (isCompataible11()) {
			new NetworkTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
		} else {
			new NetworkTask().execute();
		}
	}

	public void AddData() {

		try {

			list.setVisibility(View.VISIBLE);

			if (tempData.size() > 0) {
				try {
					if (tempData.get(0).getId() != null) {
						// do nothing
					} else {
						tempData.get(0).setId("null");
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					tempData.get(0).setId("null");
					e.printStackTrace();
				}
			}

			if (tempData.size() > 0) {
				try {

					if (!tempData.get(0).getId().equalsIgnoreCase("null")) {

						adapter = new Search_home_Adapter(tempData,
								getActivity(), false);
						list.getRefreshableView().setAdapter(adapter);
						adapter.notifyDataSetChanged();
						list.onRefreshComplete();
						pulltorefresh = false;

					} else {

						list.setVisibility(View.GONE);

					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					list.setVisibility(View.GONE);
					e.printStackTrace();
				}
			} else {

				list.setVisibility(View.GONE);

			}
			list.onRefreshComplete();

			if (tempData != null && tempData.size() > 0
					&& tempData.get(0).getId().equalsIgnoreCase("null")
					&& (!tempData.get(0).getId().equalsIgnoreCase("item"))) {

				no_recordFoundtxt.setText(getActivity().getResources()
						.getString(R.string.no_record_found));
				no_recordFoundtxt.setVisibility(View.VISIBLE);
				list.setVisibility(View.GONE);

			} else {
				if (tempData == null
						|| (tempData != null && tempData.size() == 0)) {
					no_recordFoundtxt.setText(getActivity().getResources()
							.getString(R.string.no_record_found));
					no_recordFoundtxt.setVisibility(View.VISIBLE);
					list.setVisibility(View.GONE);
				}

			}

			isProcessingNetworkThread = false;

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public class Search_home_Adapter extends BaseAdapter {
		private LayoutInflater inflate = null;
		private ArrayList<SearchData> items = null;
		private final Context context;
		private boolean isSettings = false;

		// private Context context = null;

		public Search_home_Adapter(ArrayList<SearchData> item, Context con,
				boolean isSetting) {
			inflate = (LayoutInflater) con
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			// context = con;
			items = item;
			context = con;
			isSettings = isSetting;
		}

		@Override
		public int getCount() {
			return items.size();
		}

		@Override
		public Object getItem(int arg0) {
			return items.get(arg0);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder;

			SearchData searchObj = items.get(position);

			if (convertView == null) {

				LayoutInflater inflater = (LayoutInflater) getActivity()
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = inflater.inflate(
						R.layout.search_message_item_layout, null);

				holder = new ViewHolder();
				holder.heading = (TextView) convertView
						.findViewById(R.id.search_item_name);
				holder.marketmovers_item_name_ll = (LinearLayout) convertView
						.findViewById(R.id.search_item_name_ll);
				Utility.getInstance().setTypeface(holder.heading,
						context.getApplicationContext());

				convertView.setTag(holder);

			} else {

				holder = (ViewHolder) convertView.getTag();
			}

			try {
				if (screenID.equalsIgnoreCase("1")) {

					if (items.get(position).getType()
							.equalsIgnoreCase("header")) {

						holder.heading.setText(Html.fromHtml(msg_type_html
								+ items.get(position).getCategory()
								+ "</b></font>"));
						holder.marketmovers_item_name_ll
								.setBackgroundColor(Color.BLACK);

					}

					else if (items.get(position).getType()
							.equalsIgnoreCase("searchCatgory")) {
						holder.heading
								.setText(Html.fromHtml(String.format(
										htmlwhiteOrangeString,
										items.get(position).getCategory(), "("
												+ items.get(position)
														.getMsg_counts() + ")")));

						holder.marketmovers_item_name_ll
								.setBackgroundColor(Color.TRANSPARENT);
					}

					else if (items.get(position).getType()
							.equalsIgnoreCase("serachOBJ")) {

						String str = createString(items.get(position), items
								.get(position).getCategory_id());
						Spanned htmlSpan = null;

						if (items.get(position) != null) {

							String categoryName = Utility.getInstance()
									.toTitleCase(
											items.get(position).getCategory());

							if (items.get(position).getCategory_id()
									.equalsIgnoreCase("4")
									|| items.get(position).getCategory_id()
											.equalsIgnoreCase("3")) {

								if (str.length() > 0) {

									htmlSpan = Html.fromHtml(String.format(
											valhtmlString, items.get(position)
													.getShortname()
													+ " "
													+ items.get(position)
															.getExpiry_date_d()
													+ "&nbsp"
													+ "&nbsp"
													+ "&nbsp", categoryName
													+ "<br>", str, ""));

								} else {

									htmlSpan = Html.fromHtml(String.format(
											valhtmlString, items.get(position)
													.getShortname()
													+ " "
													+ items.get(position)
															.getExpiry_date_d()
													+ "&nbsp"
													+ "&nbsp"
													+ "&nbsp", categoryName
													+ "<br>", "", ""));
								}

								Drawable d = getResources().getDrawable(
										R.drawable.latest2);
								d.setBounds(0, 0, d.getIntrinsicWidth(),
										d.getIntrinsicHeight());

								SpannableString spantxt = new SpannableString(
										htmlSpan);
								int index = spantxt.toString().indexOf(
										categoryName);
								if (index != -1) {
									ImageSpan span = new ImageSpan(d,
											ImageSpan.ALIGN_BASELINE);
									spantxt.setSpan(span, index - 1, index,
											Spannable.SPAN_INCLUSIVE_EXCLUSIVE);

									holder.heading
											.setText(addClickablePart(
													getActivity(), spantxt,
													search_word));
									holder.heading
											.setLinkTextColor(getActivity()
													.getResources().getColor(
															R.color.orange));
								}

							} else {
								if (str.length() > 0) {

									// htmlSpan = Html.fromHtml(String.format(
									// valhtmlString, items.get(position)
									// .getShortname()
									// + "&nbsp"
									// + "&nbsp" + "&nbsp",
									// categoryName, str, ""));

									htmlSpan = Html.fromHtml(String.format(
											valhtmlString, items.get(position)
													.getShortname()
													+ "&nbsp"
													+ "&nbsp" + "&nbsp",
											categoryName + "<br>", str, ""));

								} else {
									htmlSpan = Html.fromHtml(String.format(
											valhtmlString, items.get(position)
													.getShortname()
													+ "&nbsp"
													+ "&nbsp" + "&nbsp",
											categoryName, ""));

								}
								Drawable d = getResources().getDrawable(
										R.drawable.latest2);
								d.setBounds(0, 0, d.getIntrinsicWidth(),
										d.getIntrinsicHeight());

								SpannableString spantxt = new SpannableString(
										htmlSpan);
								int index = spantxt.toString().indexOf(
										categoryName);
								if (index != -1) {
									ImageSpan span = new ImageSpan(d,
											ImageSpan.ALIGN_BASELINE);
									spantxt.setSpan(span, index - 1, index,
											Spannable.SPAN_INCLUSIVE_EXCLUSIVE);

									holder.heading
											.setText(addClickablePart(
													getActivity(), spantxt,
													search_word));
									holder.heading
											.setLinkTextColor(getActivity()
													.getResources().getColor(
															R.color.orange));
								}

							}

						}
						holder.marketmovers_item_name_ll
								.setBackgroundColor(Color.parseColor("#000000"));

					}

				} else if (screenID.equalsIgnoreCase("2")
						|| screenID.equalsIgnoreCase("3")
						|| screenID.equalsIgnoreCase("4")
						|| screenID.equalsIgnoreCase("5")) {

					if (items.get(position).getType()
							.equalsIgnoreCase("header")) {
						holder.heading.setText(Html.fromHtml(msg_type_html
								+ items.get(position).getCategory()
								+ "</b></font>" + msg_type_html
								+ items.get(position).getMsg_counts()));
						holder.marketmovers_item_name_ll
								.setBackgroundColor(Color.parseColor("#343434"));

					} else if (items.get(position).getType()
							.equalsIgnoreCase("nomatch_found")) {

						holder.heading.setText(Html.fromHtml(""
								+ no_match_drawablehtml + "" + msg_type_html
								+ '"' + "NO Matches" + '"' + "</b></font>"
								+ category_type_html + "-"
								+ items.get(position).getCategory()
								+ "</b></font>", exclationimgGetter, null));

					}

					else if (items.get(position).getType()
							.equalsIgnoreCase("searchCatgory")) {
						// holder.heading.setText(Html.fromHtml(
						// "" + news_side_drawablehtml + "" + msg_type_html
						// + '"' + search_word + '"' + "</b></font>"
						// + category_type_html
						// + items.get(position).getCategory()
						// + "</b></font>", imgGetter, null));
						// holder.marketmovers_item_name_ll.setBackgroundColor(Color
						// .parseColor("#FF9933"));
					}

					else if (items.get(position).getType()
							.equalsIgnoreCase("serachOBJ")) {

						String str = createString(items.get(position), items
								.get(position).getCategory_id());
						Spanned htmlSpan = null;

						if (items.get(position) != null) {

							String categoryName = Utility.getInstance()
									.toTitleCase(
											items.get(position).getCategory());

							if (items.get(position).getCategory_id()
									.equalsIgnoreCase("4")
									|| items.get(position).getCategory_id()
											.equalsIgnoreCase("3")) {

								if (str.length() > 0) {

									htmlSpan = Html.fromHtml(String.format(
											valhtmlString, items.get(position)
													.getShortname()
													+ " "
													+ items.get(position)
															.getExpiry_date_d()
													+ "&nbsp"
													+ "&nbsp"
													+ "&nbsp", categoryName
													+ "<br>", str, ""));

								} else {

									htmlSpan = Html.fromHtml(String.format(
											valhtmlString, items.get(position)
													.getShortname()
													+ " "
													+ items.get(position)
															.getExpiry_date_d()
													+ "&nbsp"
													+ "&nbsp"
													+ "&nbsp", categoryName
													+ "<br>", "", ""));
								}
								Drawable d = getResources().getDrawable(
										R.drawable.latest2);
								d.setBounds(0, 0, d.getIntrinsicWidth(),
										d.getIntrinsicHeight());

								SpannableString spantxt = new SpannableString(
										htmlSpan);
								int index = spantxt.toString().indexOf(
										categoryName);
								if (index != -1 && index > 1) {
									ImageSpan span = new ImageSpan(d,
											ImageSpan.ALIGN_BASELINE);
									spantxt.setSpan(span, index - 1, index,
											Spannable.SPAN_INCLUSIVE_EXCLUSIVE);

									holder.heading
											.setText(addClickablePart(
													getActivity(), spantxt,
													search_word));
									holder.heading
											.setLinkTextColor(getActivity()
													.getResources().getColor(
															R.color.orange));
								}

							} else {
								if (str.length() > 0) {
									// htmlSpan = Html.fromHtml(String.format(
									// valhtmlString, items.get(position)
									// .getShortname()
									// + "&nbsp"
									// + "&nbsp" + "&nbsp",
									// categoryName, str, ""));

									htmlSpan = Html.fromHtml(String.format(
											valhtmlString, items.get(position)
													.getShortname()
													+ "&nbsp"
													+ "&nbsp" + "&nbsp",
											categoryName + "<br>", str, ""));

								} else {
									htmlSpan = Html
											.fromHtml(String.format(
													valhtmltwoString, items
															.get(position)
															.getShortname()
															+ "&nbsp"
															+ "&nbsp"
															+ "&nbsp",
													categoryName, ""));

								}
								Drawable d = getResources().getDrawable(
										R.drawable.latest2);
								d.setBounds(0, 0, d.getIntrinsicWidth(),
										d.getIntrinsicHeight());

								SpannableString spantxt = new SpannableString(
										htmlSpan);
								int index = spantxt.toString().indexOf(
										categoryName);
								if (index != -1 && index > 1) {
									ImageSpan span = new ImageSpan(d,
											ImageSpan.ALIGN_BASELINE);
									spantxt.setSpan(span, index - 1, index,
											Spannable.SPAN_INCLUSIVE_EXCLUSIVE);

									holder.heading
											.setText(addClickablePart(
													getActivity(), spantxt,
													search_word));
									holder.heading
											.setLinkTextColor(getActivity()
													.getResources().getColor(
															R.color.orange));
								}
							}
						}

						holder.marketmovers_item_name_ll
								.setBackgroundColor(getActivity()
										.getResources().getColor(
												R.color.black_transparency));

					}

				}
			} catch (NotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return convertView;
		}

		class ViewHolder {
			public TextView heading;
			public LinearLayout marketmovers_item_name_ll;
		}

	}

	public String createString(SearchData searchData, String id) {
		Log.i("createStringid = ", id);

		String resultString = "";
		String resultString1 = "";
		String resultString2 = "";
		String resultString3 = "";
		String resultString4 = "";
		String resultString5 = "";

		if (!id.equalsIgnoreCase("4") && !id.equalsIgnoreCase("3")) {
			if (searchData.getIsinid() != null
					&& searchData.getIsinid().toString().length() > 0) {

				resultString1 = "" + " " + searchData.getIsinid();

			} else {
				resultString1 = "";
			}

			if (searchData.getNseid() != null
					&& searchData.getNseid().toString().length() > 0) {

				if (resultString1.trim().length() == 0) {
					resultString2 = "" + " " + searchData.getNseid();

				} else {
					resultString2 = "," + " " + searchData.getNseid();
				}

			} else {
				resultString2 = "";
			}

			if (searchData.getBseid() != null
					&& searchData.getBseid().toString().length() > 0) {
				if (resultString2.trim().length() == 0) {
					resultString3 = "" + " " + searchData.getBseid();

				} else {
					resultString3 = "," + " " + searchData.getBseid();
				}

			} else {
				resultString3 = "";
			}

		}

		else {

			if (searchData.getIsinid() != null
					&& searchData.getIsinid().toString().length() > 0) {
				resultString1 = "" + " " + searchData.getIsinid();
			} else {
				resultString1 = "";
			}

			if (searchData.getNseid() != null
					&& searchData.getNseid().toString().length() > 0) {
				if (resultString1.trim().length() == 0) {
					resultString2 = "" + " " + searchData.getNseid();
				} else {
					resultString2 = "," + " " + searchData.getNseid();
				}
			} else {
				resultString2 = "";
			}

			if (searchData.getBseid() != null
					&& searchData.getBseid().toString().length() > 0) {
				if (resultString2.trim().length() == 0) {
					resultString3 = "" + " " + searchData.getBseid();
				} else {
					resultString3 = "," + " " + searchData.getBseid();
				}
			} else {
				resultString3 = "";
			}
			if (searchData.getSymbol_name() != null
					&& searchData.getSymbol_name().toString().length() > 0) {

				if (resultString3.trim().length() == 0) {
					resultString4 = searchData.getSymbol_name();

				} else {
					resultString4 = "," + " " + searchData.getSymbol_name();
				}

			} else {
				resultString4 = "";
			}

			if (searchData.getEx() != null) {
				if (resultString4.trim().length() == 0) {
					if (searchData.getEx().equalsIgnoreCase("N")) {
						resultString5 = "NSE";

					} else if (searchData.getEx().equalsIgnoreCase("B")) {
						resultString5 = "BSE";
					}
				} else {
					if (searchData.getEx().equalsIgnoreCase("N")) {
						resultString5 = "," + " " + "NSE";

					} else if (searchData.getEx().equalsIgnoreCase("B")) {
						resultString5 = "," + " " + "BSE";
					}

				}
			}

		}

		resultString = resultString1 + resultString2 + resultString3
				+ resultString4 + resultString5;
		return resultString;
	}

	public void loadrelatedPage(int id) {

		if (screen_name.equalsIgnoreCase("Stocks")) {

			if (resultsseachData != null) {

				Bundle bundle = new Bundle();
				bundle.putString(AppConstants.TOPIC_ID, resultsseachData
						.get(id).getTopicid());
				Log.i("Topic id = ", "" + resultsseachData.get(id).getTopicid());
				MessageTopicDetail topicDetailFragment = new MessageTopicDetail();
				topicDetailFragment.setArguments(bundle);
			}
			//
			// ((BaseActivity) getActivity())
			// .launchNavigateFragement(topicDetailFragment);

		} else {

			Intent i = new Intent();
			Bundle informacion = new Bundle();
			// informacion.putString("value",
			// resultsseachData.get(id).getCategory());
			informacion.putInt("value", id);
			i.putExtras(informacion);
			if (resultsseachData != null) {

				Fragment f = getTargetFragment();

				if (f != null) {

					if (f instanceof SearchMessageFragment) {
						f.onActivityResult(getTargetRequestCode(), 2, i);

					}
				}

			}

		}

	}

	private ImageGetter imgGetter = new ImageGetter() {

		public Drawable getDrawable(String source) {
			Drawable drawable = null;

			drawable = getResources().getDrawable(R.drawable.icon_search);

			drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
					drawable.getIntrinsicHeight());
			return drawable;
		}
	};

	private ImageGetter exclationimgGetter = new ImageGetter() {

		public Drawable getDrawable(String source) {
			Drawable drawable = null;

			drawable = getResources().getDrawable(R.drawable.exclamation);

			drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
					drawable.getIntrinsicHeight());
			return drawable;
		}
	};

	@Override
	public void isNSEClicked(boolean isNSESelected, int pagerPosition) {
		// TODO Auto-generated method stub

	}

	private SpannableStringBuilder addClickablePart(
			final FragmentActivity mContext, Spanned charSequence,
			String trailing) {
		SpannableStringBuilder ssb = new SpannableStringBuilder(charSequence);

		int idx1 = charSequence.toString().toLowerCase()
				.indexOf(trailing.toLowerCase().trim());
		int idx2 = 0;
		if (idx1 != -1) {
			idx2 = idx1 + trailing.length();

			ssb.setSpan(new ClickSpan(false), idx1, idx2,
					Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

		}

		return ssb;
	}

	private class ClickSpan extends ClickableSpan {

		public ClickSpan(boolean usertag) {
			// TODO Auto-generated constructor stub
			this.usertag = usertag;

		}

		boolean usertag = false;

		@Override
		public void onClick(View widget) {

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
	public void onRefresh() {
		// TODO Auto-generated method stub

	}

	// For Refresh Loading Data

	private class NetworkTask extends AsyncTask<Integer, Void, Bundle> {

		// private CustomProgressDialog progressDialog = null;
		private ProgressBar progressBar = null;
		private boolean showProgressBar = false;

		public NetworkTask() {
			// TODO Auto-generated constructor stub
		}

		public NetworkTask(boolean showProgressBar) {
			// TODO Auto-generated constructor stub
			this.showProgressBar = showProgressBar;
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			if (!pulltorefresh) {
				if (noLoaderwhilePull == false) {
					startProgressLoader();
				}
			}

		}

		@Override
		protected Bundle doInBackground(Integer... params) {

			// int pageNo = params[0];
			Bundle bundle = new Bundle();
			SearchMessageData searchObj = null;
			final AppData MainController = AppData.getInstance();

			if (!MainController.hasConnection()) {
				((getActivity())).runOnUiThread(new Runnable() {
					@Override
					public void run() {

						Utility.getInstance().showAlertDialog(
								getActivity(),
								getActivity().getResources().getString(
										R.string.no_internet), null);

					}
				});
				return null;
			} else {

				try {

					searchObj = ParseCall.getInstance()
							.getallNewSerachDataWithoutSuggestions(
									getActivity(), currentPageMessages,
									base_url, "");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

			if (searchObj != null) {
				bundle.putSerializable("obj", searchObj);
			}

			return bundle;
		}

		@Override
		protected void onPostExecute(Bundle result) {
			super.onPostExecute(result);
			noLoaderwhilePull = false;

			dismissProgressLoader();

			if (result != null) {

				searchMessageObj = (SearchMessageData) result
						.getSerializable("obj");

				if (searchMessageObj != null) {

					myHandler.post(updateRunnable);

				}

			}

		}

	}

	private void startProgressLoader() {
		// TODO Auto-generated method stub
		// if(pageNo[0]==0||pageNo[1]==0){
		if (getUserVisibleHint()) {
			progressBarr.setVisibility(View.VISIBLE);
		}
	}

	private void dismissProgressLoader() {
		// TODO Auto-generated method stub

		progressBarr.setVisibility(View.GONE);

	}

	final Runnable updateRunnable = new Runnable() {
		public void run() {
			try {
				// call the activity method that updates the UI
				if (isAdded()) {
					if (searchMessageObj != null) {
						no_recordFoundtxt.setVisibility(View.GONE);
						if (resultsseachData != null) {

							if (resultsseachData.size() > 0)
								resultsseachData.clear();
						}

						resultsseachData = searchMessageObj.getSerachMsglist();

						if (resultsseachData != null) {

							if (screenID.equalsIgnoreCase("1")) {
								String a = searchMessageObj
										.getPoppularCategories();
								String name[] = a.split(",");
								ArrayList<SearchData> TMPsseachData = new ArrayList<SearchData>();
								SearchData headerobj = null;
								if (name.length > 1) {

									for (int i = 0; i < name.length; i++) {
										if (i == 0) {
											headerobj = new SearchData();
											headerobj.setType("header");
											headerobj.setId("item");
											headerobj.setCategory(name[0]);
											// headerobj.setPlaceid(0);

										} else if (i > 0) {
											SearchData obj = new SearchData();
											obj.setId("item");
											String b = name[i];
											if (b.contains("&")) {

												String splitarr[] = b
														.split("&");
												ArrayList<String> fields = new ArrayList<String>();
												if (splitarr.length > 1) {
													fields.add(splitarr[0]);
													fields.add(splitarr[1]);
												} else {
													fields.add(splitarr[0]);
													fields.add("0");
												}

												String str = fields.get(0);
												String[] splitids = str
														.split("@");
												if (splitids.length > 0) {
													obj.setType("searchCatgory");
													obj.setCategory(splitids[0]);
													obj.setCategory_id(splitids[1]);
													obj.setMsg_counts(fields
															.get(1));
												} else {
													obj.setType("searchCatgory");
													obj.setCategory(splitids[0]);
													obj.setCategory_id("0");
													obj.setMsg_counts(fields
															.get(1));

												}

											} else {
												obj.setType("searchCatgory");
												obj.setCategory(name[i]);
												obj.setCategory_id("0");
												obj.setMsg_counts("0");
											}

											TMPsseachData.add(i - 1, obj);

										}

										if (i == name.length - 1) {
											TMPsseachData.add(i, headerobj);

										}
									}
								}

								try {
									if (!resultsseachData.get(0).getId()
											.equalsIgnoreCase("null")) {

										TMPsseachData.addAll(resultsseachData);
										resultsseachData.clear();
										resultsseachData.addAll(TMPsseachData);
									}

									else if (TMPsseachData.size() > 0
											&& resultsseachData.get(0).getId()
													.equalsIgnoreCase("null")
											&& currentPageMessages == 0) {

										// TMPsseachData.addAll(resultsseachData);
										resultsseachData.clear();
										resultsseachData.addAll(TMPsseachData);

									}
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}

							}

						}

						if (currentPageMessages != 0) {

							if (resultsseachData != null
									&& resultsseachData.size() > 0) {
								if (!resultsseachData.get(0).getId()
										.equalsIgnoreCase("null")) {
									isPagination = true;
								} else {
									isPagination = false;
								}

							} else {

								isPagination = false;
							}

							if (resultsseachData != null
									&& resultsseachData.size() > 0) {
								if (!resultsseachData.get(0).getId()
										.equalsIgnoreCase("null")) {
									tempData.addAll(resultsseachData);
								}
							}
							adapter.notifyDataSetChanged();
							list.onRefreshComplete();
							pulltorefresh = false;
						} else {
							if (tempData != null) {
								tempData.clear();
							} else {
								tempData = new ArrayList<SearchData>();
							}
							if (resultsseachData != null
									&& resultsseachData.size() > 0) {
								// if (!resultsseachData.get(0).getId()
								// .equalsIgnoreCase("null")) {
								//
								// }

								tempData.addAll(resultsseachData);
							}
							try {
								adapter = new Search_home_Adapter(tempData,
										getActivity(), false);
								list.getRefreshableView().setAdapter(adapter);
								adapter.notifyDataSetChanged();
								list.onRefreshComplete();
								pulltorefresh = false;
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}

						if (tempData != null && tempData.size() > 0) {
						} else {
							if (tempData != null && tempData.size() == 0) {
								try {
									no_recordFoundtxt.setText(getActivity()
											.getResources().getString(
													R.string.no_record_found));
									no_recordFoundtxt
											.setVisibility(View.VISIBLE);
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							} else {
								no_recordFoundtxt.setVisibility(View.GONE);
							}

						}

						isProcessingNetworkThread = false;

					}
				}
			} catch (Exception e) {

			}
		}
	};

}
