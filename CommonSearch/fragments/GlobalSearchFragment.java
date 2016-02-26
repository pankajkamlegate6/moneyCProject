package com.moneycontrol.handheld.CommonSearch.fragments;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.Html;
import android.text.Html.ImageGetter;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ClickableSpan;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

import com.divum.MoneyControl.R;
import com.moneycontrol.handheld.AppData;
import com.moneycontrol.handheld.BaseActivity;
import com.moneycontrol.handheld.chart.interfaces.OnvoiceSearchInterface;
import com.moneycontrol.handheld.constant.AppConstants;
import com.moneycontrol.handheld.custom.MyNetworkException;
import com.moneycontrol.handheld.custom.PagerSlidingTabStrip;
import com.moneycontrol.handheld.dynamic.menu.MenuList;
import com.moneycontrol.handheld.entity.market.SearchData;
import com.moneycontrol.handheld.entity.messages.FilterByList;
import com.moneycontrol.handheld.entity.search.SearchMessageData;
import com.moneycontrol.handheld.fragments.NewFragment;
import com.moneycontrol.handheld.fragments.NewsListFragment;
import com.moneycontrol.handheld.fragments.VideoOnDemandFragment;
import com.moneycontrol.handheld.massages.fragments.BaseFragement;
import com.moneycontrol.handheld.massages.fragments.MessageSearchItem;
import com.moneycontrol.handheld.parser.ParseCall;
import com.moneycontrol.handheld.util.Utility;
import com.moneycontrol.handheld.watchlist.customview.CustomProgressDialog;

public class GlobalSearchFragment extends BaseFragement {

    private View inflatedView;
    private Context appContext = null;
    private int withSuggestions = 1;
    private int withOutSuggestions = 0;
    private ListView list = null;
    private ListView lastResultlist = null;
    private EditText edit_search = null;
    private TextView resultSearch = null, getquote_header_title = null;
    private RelativeLayout resultLayout = null;
    private RelativeLayout cancel_rl = null;
    private ArrayList<SearchData> lastseachData = null;
    private ArrayList<SearchData> sseachData = null;
    private ArrayList<SearchData> TMPsseachData = null;
    private ArrayList<SearchData> oldseachData = new ArrayList<SearchData>();
    private boolean isSearchwithautosuggest = false;
    private Handler handler = null;
    private Handler autohandler = null;
    private Button funds_btn, stock_btn;
    private ImageView done_btn;
    private Search_home_Adapter adapter;
    private String temp_keyword = null;
    private String temp_funds = null;
    private String search_word = null;
    private ViewPager pager = null;
    private PagerSlidingTabStrip tabs = null;
    private SearchMessageData searchMessageResult = new SearchMessageData();
    private SearchMessageData oldsearchMessageResult = new SearchMessageData();
    private HashMap<Integer, Fragment> fragmentMapping = new HashMap<Integer, Fragment>();
    private ImageView voiceSearchbtn;
    ArrayList<FilterByList> Urlfiltters = null;
    public String[] title = null;
    private String msg_type_html = "<font color=#FFFFFF><b>";
    private String category_type_html = "<font color=#999999><b>";
    private String htmlString = "<font color=#CCCCCC><b>%s</b></font> <font color=#999999>%s</font>";
    private String valhtmlString = "<font size='6' color=#FFFFFF><b>%s</b></font><font color=#999999>%s</font><font color=#999999>%s</font>";
    private String news_side_drawablehtml = "<img src=\"icon_search.png\" align=\"justify\"/>";
    private String side_drawablehtml = "<img src=\"latest_arrow.png\" align=\"justify\"/>";

    private String no_match_drawablehtml = "<img src=\"exclamation.png\" align=\"justify\"/>";
    private static final int REQUEST_CODE = 1234;
    private Thread finalSearchThread, searchThread;
    OnvoiceSearchInterface callbacklistener;
    private boolean resultFound = false;
    private int actionbtnState = 0;

    private String allserachUrl = "http://feeds.moneycontrol.com/app/mc_search.php?query=";
    private int savePosition = 0;
    private int getresult = 0;

    public static boolean externalClick = false;

    private AppData MainController = null;
    private MenuList menulist = null;
    public HashMap<String, String> extraLinks = new HashMap<String, String>();

    public String allSearch = "";
    public static boolean lastShowlist = false;
    private CustomProgressDialog progressDialog = null;
    private RelativeLayout progressBarr;
    private Timer timer = new Timer();
    private boolean showPreviousResult = false;

    @Override
    public void onRefresh() {
        // TODO Auto-generated method stub

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        // TODO Auto-generated method stub
        super.onSaveInstanceState(outState);
        if (getresult == 0) {
            outState.putSerializable("SaveData", oldsearchMessageResult);
        }
        outState.putString("SearchWord", search_word);
        outState.putInt("resultType", getresult);
        if (pager != null) {
            outState.putInt("ViewPosition", pager.getCurrentItem());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        inflatedView = inflater.inflate(R.layout.common_search_layout, null);
        AppData appdata = AppData.getInstance();
        appContext = appdata.getApplicationContext();

        BaseActivity activity = (BaseActivity) getActivity();
        activity.hideNshowTicker();
        activity.searchOpen = true;

        if (activity != null && isAdded()) {
            ((BaseActivity) getActivity()).hideBannerad();

            ((BaseActivity) getActivity()).HideSearchIcon();
        }

        try {
            MainController = AppData.getInstance();
            menulist = MainController.getMenulistData();
            extraLinks = menulist.getLinks();
            allSearch = extraLinks.get("all_search");
        } catch (Exception e) {

        }

        return inflatedView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        BaseActivity activity = (BaseActivity) getActivity();
        // activity.hideNshowTickerbySetting();
        activity.searchOpen = false;
        activity.HideSearchIcon();
        // activity.showBannerad();
        if (finalSearchThread != null) {
            finalSearchThread.interrupt();
        }
        if (searchThread != null) {
            searchThread.interrupt();
        }
        if (timer != null) {
            timer.cancel();
        }

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setDefaultBehaviour();
        if (saveBundle != null) {

            getresult = saveBundle.getInt("resultType");
            try {
                if (getresult == 0) {

                    searchMessageResult = (SearchMessageData) saveBundle
                            .getSerializable("SaveData");
                    sseachData = searchMessageResult.getSerachMsglist();
                    Urlfiltters = searchMessageResult.getSearchFillterList();
                }
                search_word = saveBundle.getString("SearchWord");
                savePosition = saveBundle.getInt("ViewPosition");

                edit_search.setText(search_word);

                if (sseachData != null && getresult == 0
                        && showPreviousResult == false) {
                    list.setAdapter(new Search_home_Adapter(sseachData,
                            getActivity(), false));
                    setclickedonlist();
                } else if (sseachData == null && getresult == 0
                        && showPreviousResult == true) {
                    showPreviousResults();
                }
                if (getresult == 1) {
                    if (searchMessageResult != null) {
                        // fetchfinalSearchData(search_word);

                        resultLayout.setVisibility(View.VISIBLE);
                        lastShowlist = false;
                        showPreviousResult = false;
                        if (isAdded()) {
                            setSearchResultPage(searchMessageResult
                                    .getSearchFillterList());

                        }

                    }
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } else if (saveBundle == null && getresult == 0
                && showPreviousResult == true && sseachData == null) {

            // showPreviousResults();

        } else if (externalClick) {
            try {
                search_word = getArguments().getString("SearchKey");
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            edit_search.setText(search_word);
            fetchfinalSearchData(search_word);
        }

    }

    @Override
    public void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        hideBannerAds();
    }

    private void setclickedonlist() {
        // TODO Auto-generated method stub
        lastShowlist = false;

        list.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,
                                    int position, long arg3) {

                Utility.getInstance().hideKeyBoard(getActivity());

                lastShowlist = false;
                Utility.getInstance().hideKeyBoard(getActivity());
                try {
                    if (sseachData != null
                            && sseachData.get(position).getType()
                            .equalsIgnoreCase("searchCatgory")) {

                        fetchloadFinalResultWithCategorySearchData(
                                temp_keyword, sseachData.get(position)
                                        .getLayout_position());

                    } else if (sseachData != null
                            && sseachData.get(position) != null
                            && sseachData.get(position).getId() != null
                            && !sseachData.get(position).getType()
                            .equalsIgnoreCase("searchCatgory")
                            && (!sseachData.get(position).getId()
                            .equalsIgnoreCase("null"))) {

                        try {
                            if (TextUtils.isEmpty(sseachData.get(position)
                                    .getExpiry_date_d())) {
                                sseachData.get(position).setExpiry_date_d(
                                        "null");
                            }
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                            sseachData.get(position).setExpiry_date_d("null");
                            e.printStackTrace();
                        }

                        try {
                            if (TextUtils.isEmpty(sseachData.get(position)
                                    .getInstrument())) {
                                sseachData.get(position).setInstrument("null");
                            }
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                            sseachData.get(position).setInstrument("null");
                            e.printStackTrace();
                        }

                        String name = oldseachData.get(position).getShortname()
                                + "@" + oldseachData.get(position).getId()
                                + "@"
                                + oldseachData.get(position).getCategory_id()
                                + "@" + oldseachData.get(position).getEx()
                                + "@"
                                + oldseachData.get(position).getExpiry_date()
                                + "@"
                                + sseachData.get(position).getExpiry_date_d()
                                + "@"
                                + sseachData.get(position).getInstrument()
                                + "@"
                                + Utility.getInstance().getSELECTED_LANGUAGE();

                        Utility.getInstance().addLastCommonSearch(name,
                                getActivity());

                        if (sseachData.get(position).getCategory_id()
                                .equalsIgnoreCase("1")) {

                            showStockDetails(sseachData.get(position));

                        } else if (sseachData.get(position).getCategory_id()
                                .equalsIgnoreCase("2")) {

                            showMutualFundskDetails(sseachData.get(position));

                        } else if (sseachData.get(position).getCategory_id()
                                .equalsIgnoreCase("3")) {

                            showCommodityDetails(sseachData.get(position));

                        } else if (sseachData.get(position).getCategory_id()
                                .equalsIgnoreCase("4")) {

                            showFutureDetails(sseachData.get(position));

                        } else if (sseachData.get(position).getCategory_id()
                                .equalsIgnoreCase("5")) {
                            // news

                        } else if (sseachData.get(position).getCategory_id()
                                .equalsIgnoreCase("6")) {
                            // videos
                        } else if (sseachData.get(position).getCategory_id()
                                .equalsIgnoreCase("7")) {
                            // message

                        } else if (sseachData.get(position).getCategory_id()
                                .equalsIgnoreCase("8")) {
                            // boarder
                            lunchBoarderScreen(sseachData.get(position).getId());
                        } else if (sseachData.get(position).getCategory_id()
                                .equalsIgnoreCase("9")) {
                            // topic
                            launchTopicScreen(sseachData.get(position)
                                    .getTopicid(), sseachData.get(position)
                                    .getCategory_id(), "GlobalSearchFragment");
                        } else if (sseachData.get(position).getCategory_id()
                                .equalsIgnoreCase("10")) {
                            // lunchIndicesDeatilsScreen
                            lunchIndicesDeatilsScreen(sseachData.get(position));
                        }

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        list.post(new Runnable() {

            @Override
            public void run() {
                saveBundle = null;
            }
        });
    }

    private void setDefaultBehaviour() {

        LinearLayout headerll = (LinearLayout) inflatedView
                .findViewById(R.id.getquote_header);
        list = (ListView) inflatedView.findViewById(R.id.getQuote_list);
        edit_search = (EditText) inflatedView
                .findViewById(R.id.getQuote_editSearchBox);
        lastResultlist = (ListView) inflatedView
                .findViewById(R.id.last_search_list);

        progressBarr = (RelativeLayout) inflatedView
                .findViewById(R.id.progressBarr);

        RelativeLayout rr = (RelativeLayout) inflatedView
                .findViewById(R.id.pare);
        rr.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Utility.getInstance().hideKeyBoard(getActivity());

            }
        });

        headerll.setVisibility(View.GONE);
        resultSearch = (TextView) inflatedView
                .findViewById(R.id.getquote_noSearchFound);
        getquote_header_title = (TextView) inflatedView
                .findViewById(R.id.getquote_header_title);
        resultLayout = (RelativeLayout) inflatedView
                .findViewById(R.id.search_result_con);
        cancel_rl = (RelativeLayout) inflatedView.findViewById(R.id.cancel_rrl);

        voiceSearchbtn = (ImageView) inflatedView
                .findViewById(R.id.voice_cancel_search_btn);

        // final View parent = (View) voiceSearchbtn.getParent();
        // parent.post(new Runnable() {
        // public void run() {
        // final Rect rect = new Rect();
        // voiceSearchbtn.getHitRect(rect);
        // rect.top -= 100; // increase top hit area
        // rect.left -= 100; // increase left hit area
        // rect.bottom += 100; // increase bottom hit area
        // rect.right += 100; // increase right hit area
        // parent.setTouchDelegate(new TouchDelegate(rect, voiceSearchbtn));
        // }
        // });

        cancel_rl.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (actionbtnState == 0) {

                    actionbtnState = 1;
                } else {

                    actionbtnState = 0;
                }
                performUpadteAction();
            }
        });

        pager = (ViewPager) inflatedView.findViewById(R.id.searchpager);
        tabs = (PagerSlidingTabStrip) inflatedView
                .findViewById(R.id.searchtabs);

        resultLayout.setVisibility(View.GONE);

        // edit_search.setFocusable(true);

        edit_search.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                if (timer != null) {
                    timer.cancel();
                }

            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            public void afterTextChanged(Editable s) {
                // if (saveBundle == null) {
                // isSearchwithautosuggest = true;
                // withOutSuggestions = 0;
                // if (resultFound) {
                // resultFound = false;
                // resultLayout.setVisibility(View.INVISIBLE);
                //
                // }
                //
                // if (edit_search.getText().toString().length() > 0) {
                // actionbtnState = 1;
                // updateBtn();
                //
                // } else {
                // actionbtnState = 0;
                // updateBtn();
                // // performCancelAction();
                //
                // }
                // if (edit_search.getText().toString().trim().length() > 0)
                // checkForEditBoxValues(isSearchwithautosuggest,
                // edit_search.getText().toString().trim(),
                // withOutSuggestions);
                //
                // if (edit_search.getText().toString().length() == 0) {
                // edit_search.setHint(Utility.getInstance()
                // .setShowInternetConnection(getActivity(),
                // R.string.search_all_hint,
                // R.string.search_all_hint,
                // R.string.search_all_hint));
                // }
                // }

                if (saveBundle == null) {
                    isSearchwithautosuggest = true;
                    withOutSuggestions = 0;
                    if (resultFound) {
                        resultFound = false;
                        resultLayout.setVisibility(View.INVISIBLE);

                    }

                    if (edit_search.getText().toString().length() > 0) {
                        actionbtnState = 1;
                        updateBtn();

                    } else {
                        actionbtnState = 0;
                        updateBtn();
                        // performCancelAction();

                    }
                    if (edit_search.getText().toString().trim().length() > 2) {
                        if (timer != null) {
                            timer.cancel();
                        }
                        timer = new Timer();
                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                if (isAdded()) {
                                    getActivity().runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            checkForEditBoxValues(
                                                    isSearchwithautosuggest,
                                                    edit_search.getText()
                                                            .toString().trim(),
                                                    withOutSuggestions);

                                        }
                                    });
                                }
                            }

                        }, 500);
                    } else {

                        if (edit_search.getText().toString().length() == 0) {
                            edit_search.setHint(Utility.getInstance()
                                    .setShowInternetConnection(getActivity(),
                                            R.string.search_all_hint,
                                            R.string.search_all_hint,
                                            R.string.search_all_hint));
                        } else if (edit_search.getText().toString().length() <= 2) {
                            checkForEditBoxValues(false, edit_search.getText()
                                    .toString().trim(), 1);
                        }
                    }
                }

            }
        });

        edit_search.setOnEditorActionListener(new OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId,
                                          KeyEvent event) {

                isSearchwithautosuggest = false;
                withOutSuggestions = 0;
                if (actionId == EditorInfo.IME_ACTION_SEARCH || actionId == 0) {
                    Utility.getInstance().hideKeyBoard(getActivity());
                    if (edit_search.getText().toString().trim().length() <= 2) {

                        Utility.getInstance().showAlertDialog(
                                getActivity(),
                                getResources().getString(
                                        R.string.search_minimumCharacters), "");

                    } else {
                        startProgressDialog();
                        checkForEditBoxValues(isSearchwithautosuggest,
                                edit_search.getText().toString().trim(),
                                withOutSuggestions);
                    }

                    return true;
                } else {

                }
                return false;
            }

        });

        edit_search.setOnFocusChangeListener(new OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                // TODO Auto-generated method stub

                if (!hasFocus) {

                } else {
                    edit_search.setHint("");
                }
            }
        });
        if (saveBundle == null) {

            showPreviousResults();
        }

    }

    // private void startProgressDialog() {
    // progressDialog = CustomProgressDialog.ctor(getActivity());
    // progressDialog.show();
    // }
    //
    // private void dismissProgressDialog() {
    // if (progressDialog != null && progressDialog.isShowing()) {
    // progressDialog.dismiss();
    // }
    // }
    private void startProgressDialog() {
        progressBarr.setVisibility(View.VISIBLE);
    }

    private void dismissProgressDialog() {
        progressBarr.setVisibility(View.GONE);
    }

    private void showPreviousResults() {
        try {
            lastShowlist = true;

            showPreviousResult = true;
            ArrayList<String> arrlastVisitedCompanyId = Utility.getInstance()
                    .getLastCommonSearch(getActivity());
            Collections.reverse(arrlastVisitedCompanyId);
            lastseachData = new ArrayList<SearchData>();
            resultSearch.setVisibility(View.GONE);
            if (arrlastVisitedCompanyId != null
                    && arrlastVisitedCompanyId.size() > 0) {

                for (int i = 0; i < arrlastVisitedCompanyId.size(); i++) {

                    try {
                        String[] Arr = arrlastVisitedCompanyId.get(i)
                                .split("@");
                        SearchData obj = new SearchData();
                        obj.setType("last_SearchItem");

                        // String name = sseachData.get(position).getShortname()
                        // +
                        // "@"
                        // + sseachData.get(position).getId() + "@"
                        // + sseachData.get(position).getCategory_id() + "@"
                        // + sseachData.get(position).getEx() + "@"
                        // + sseachData.get(position).getExpiry_date();
                        if (Arr[0] != null) {
                            obj.setShortname(Arr[0]);
                        }
                        if (Arr[1] != null) {
                            obj.setId(Arr[1]);
                        }
                        if (Arr[2] != null) {
                            obj.setCategory_id(Arr[2]);
                        }
                        if (Arr[3] != null) {
                            obj.setEx(Arr[3]);
                        }
                        if (Arr[4] != null) {
                            obj.setExpiry_date(Arr[4]);
                        }
                        if (Arr[5] != null) {
                            obj.setExpiry_date_d(Arr[5]);
                        }
                        if (Arr[6] != null) {
                            obj.setInstrument(Arr[6]);
                        }
                        if (Arr[7] != null) {
                            obj.setLang_type(Arr[7]);
                        }

                        if (Arr[7].equalsIgnoreCase(Utility.getInstance()
                                .getSELECTED_LANGUAGE())) {
                            lastseachData.add(obj);
                        }
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        // i--;
                        e.printStackTrace();
                    }
                }

                if (adapter == null) {
                    lastResultlist.setAdapter(new Search_home_Adapter(
                            lastseachData, getActivity(), false));

                    lastResultlist
                            .setOnItemClickListener(new OnItemClickListener() {

                                @Override
                                public void onItemClick(AdapterView<?> arg0,
                                                        View arg1, int position, long arg3) {

                                    // Utility.getInstance().addLastCommonSearch(
                                    // search_word, getActivity());
                                    // lastShowlist = false;
                                    // String name = data.getShortname();
                                    // Utility.getInstance().addLastCommonSearch(name,
                                    // getActivity());
                                    //
                                    // temp_keyword = null;
                                    // edit_search.setText(data.getShortname());
                                    // int pos = data.getShortname().length();
                                    // // Editable etext =
                                    // edit_search.getText();
                                    // // Selection.setSelection(etext, pos);
                                    // edit_search.setSelection(pos);
                                    // fetchSearchData(data.getShortname());
                                    // lastResultlist.setVisibility(View.GONE);
                                    // list.setVisibility(View.VISIBLE);

                                    SearchData data = (SearchData) arg0
                                            .getItemAtPosition(position);

                                    Utility.getInstance().hideKeyBoard(
                                            getActivity());
                                    try {
                                        if (data != null) {

                                            String name = data.getShortname();
                                            // Utility.getInstance()
                                            // .addLastCommonSearch(name,
                                            // getActivity());

                                            if (data.getCategory_id()
                                                    .equalsIgnoreCase("1")) {

                                                showStockDetails(data);

                                            } else if (data.getCategory_id()
                                                    .equalsIgnoreCase("2")) {

                                                showMutualFundskDetails(data);

                                            } else if (data.getCategory_id()
                                                    .equalsIgnoreCase("3")) {

                                                showCommodityDetails(data);

                                            } else if (data.getCategory_id()
                                                    .equalsIgnoreCase("4")) {

                                                showFutureDetails(data);

                                            } else if (data.getCategory_id()
                                                    .equalsIgnoreCase("5")) {
                                                // news

                                            } else if (data.getCategory_id()
                                                    .equalsIgnoreCase("6")) {
                                                // videos
                                            } else if (data.getCategory_id()
                                                    .equalsIgnoreCase("7")) {
                                                // message

                                            } else if (data.getCategory_id()
                                                    .equalsIgnoreCase("8")) {
                                                // boarder
                                                lunchBoarderScreen(data.getId());
                                            } else if (data.getCategory_id()
                                                    .equalsIgnoreCase("9")) {
                                                // topic
                                                launchTopicScreen(
                                                        data.getTopicid(),
                                                        data.getCategory_id(),
                                                        "GlobalSearchFragment");
                                            } else if (data.getCategory_id()
                                                    .equalsIgnoreCase("10")) {
                                                // boarder
                                                lunchIndicesDeatilsScreen(data);
                                            }

                                        }

                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }

                                }
                            });
                }
            }

            lastResultlist.setVisibility(View.VISIBLE);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void onBackPress() {

        if (actionbtnState == 0) {

            actionbtnState = 1;
        } else {

            actionbtnState = 0;
        }
        performUpadteAction();
    }

    public void performUpadteAction() {

        try {
            if (actionbtnState == 0) {
                // voiceSearchbtn.setImageResource(getActivity().getResources()(R.drawable.icon_close));
                performCancelAction();
                voiceSearchbtn.setImageDrawable(getActivity().getResources()
                        .getDrawable(R.drawable.mic_shape));

            } else {

                performVoiceSearch();
                voiceSearchbtn.setImageDrawable(getActivity().getResources()
                        .getDrawable(R.drawable.cross1));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void updateBtn() {

        try {
            if (actionbtnState == 0) {
                // voiceSearchbtn.setImageResource(getActivity().getResources()(R.drawable.icon_close));
                voiceSearchbtn.setImageDrawable(getActivity().getResources()
                        .getDrawable(R.drawable.mic_shape));
                if (sseachData != null && sseachData.size() > 0) {
                    sseachData.clear();
                }
                if (adapter == null) {
                    list.setVisibility(View.GONE);

                    //
                } else {
                    adapter.notifyDataSetChanged();
                    list.setVisibility(View.GONE);

                }

                showPreviousResults();

            } else {
                voiceSearchbtn.setImageDrawable(getActivity().getResources()
                        .getDrawable(R.drawable.cross1));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void performCancelAction() {
        // TODO Auto-generated method stub

        //
        if (sseachData != null && sseachData.size() > 0) {
            sseachData.clear();
            resultSearch.setVisibility(View.GONE);

        }

        if (adapter == null) {
            list.setVisibility(View.GONE);
            edit_search.setText("");
            //
        } else {
            adapter.notifyDataSetChanged();
            list.setVisibility(View.GONE);
            edit_search.setText("");
        }

    }

    public void performVoiceSearch() {
        callbacklistener.onItemPicked(5);
    }

    public void performVoiceSearchaction(String data) {
        edit_search.setText(data);
        checkForEditBoxValues(true, data, 0);
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {

            callbacklistener = (OnvoiceSearchInterface) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }

    }

    private void checkForEditBoxValues(boolean isSearch, String keyword,
                                       int value) {
        try {

            if (keyword.length() > 2) {
                search_word = keyword;
                if (ParseCall.getInstance().isOnlineWithoutException(
                        getActivity())) {
                    if (isSearch && withOutSuggestions == 0) {
                        fetchSearchData(keyword);

                    } else {
                        savePosition = 0;
                        fetchfinalSearchData(keyword);
                    }
                } else {
                    Utility.getInstance().showAlertDialogWhileOffline(
                            getActivity(),
                            getActivity().getResources().getString(
                                    R.string.alert_need_internet_connection),
                            null);

                }
            } else {

                if (!(keyword.length() == 0) || value == 1) {
                    Utility.getInstance().showAlertDialogWhileOffline(
                            getActivity(),
                            getActivity().getResources().getString(
                                    R.string.search_minimumCharacters), null);

                } else {

                    Utility.getInstance().showAlertDialogWhileOffline(
                            getActivity(),
                            getActivity().getResources().getString(
                                    R.string.search_minimumCharacters), null);

                }
                if (sseachData != null && sseachData.size() > 0) {
                    sseachData.clear();
                    temp_keyword = "";
                }

                if (adapter == null) {
                    list.setVisibility(View.GONE);

                } else {
                    adapter.notifyDataSetChanged();
                    list.setVisibility(View.GONE);

                }
                // edit_search.setHint(getResources().getString(R.string.search_hint));
                dismissProgressDialog();
                edit_search.setHint(Utility.getInstance()
                        .setShowInternetConnection(getActivity(),
                                R.string.search_hint, R.string.search_hint,
                                R.string.search_hint));

            }
        } catch (Exception e) {
            Log.d("sugestionError", e.getMessage());
        }

    }

    // private void checkForEditBoxValues(final boolean isSearch,
    // final String keyword, final int value) {
    //
    // if (saveBundle == null) {
    // try {
    // if (timer != null) {
    // timer.cancel();
    // }
    // timer = new Timer();
    // timer.schedule(new TimerTask() {
    // @Override
    // public void run() {
    // if (isAdded()) {
    // getActivity().runOnUiThread(new Runnable() {
    //
    // @Override
    // public void run() {
    // fetchdata(isSearch, keyword, value);
    //
    // }
    // });
    // }
    // }
    //
    // }, 500);
    //
    // } catch (Exception e) {
    // Log.d("sugestionError", e.getMessage());
    // }
    // } else {
    //
    // }
    //
    // }

    // public void fetchdata(final boolean isSearch, final String keyword,
    // final int value) {
    //
    // // TODO: do what you need here (refresh list)
    // // you will probably need to use
    // // runOnUiThread(Runnable action) for some specific
    // // actions
    // if (keyword.length() > 2) {
    // search_word = keyword;
    // if (ParseCall.getInstance().isOnlineWithoutException(getActivity())) {
    // if (isSearch && withOutSuggestions == 0) {
    // fetchSearchData(keyword);
    //
    // } else {
    // savePosition = 0;
    // fetchfinalSearchData(keyword);
    // }
    // } else {
    // Utility.getInstance().showAlertDialogWhileOffline(
    // getActivity(),
    // getActivity().getResources().getString(
    // R.string.alert_need_internet_connection), null);
    //
    // }
    // } else {
    // if (!(keyword.length() == 0) || value == 1) {
    // Utility.getInstance().showAlertDialogWhileOffline(
    // getActivity(),
    // getActivity().getResources().getString(
    // R.string.search_minimumCharacters), null);
    //
    // } else {
    //
    // Utility.getInstance().showAlertDialogWhileOffline(
    // getActivity(),
    // getActivity().getResources().getString(
    // R.string.search_minimumCharacters), null);
    //
    // }
    // if (sseachData != null && sseachData.size() > 0) {
    // sseachData.clear();
    // }
    //
    // if (adapter == null) {
    // list.setVisibility(View.GONE);
    //
    // } else {
    // adapter.notifyDataSetChanged();
    // list.setVisibility(View.GONE);
    //
    // }
    // // edit_search.setHint(getResources().getString(R.string.search_hint));
    // dismissProgressDialog();
    // edit_search.setHint(Utility.getInstance()
    // .setShowInternetConnection(getActivity(),
    // R.string.search_hint, R.string.search_hint,
    // R.string.search_hint));
    // }
    //
    // }

    private synchronized void fetchSearchData(final String keyword) {

        autohandler = new Handler() {
            public void handleMessage(android.os.Message msg) {


                if (searchMessageResult != null) {
                    oldsearchMessageResult = searchMessageResult;
                    if (sseachData != null && sseachData.size() > 0) {
                        sseachData.clear();
                    }

                    try {
                        sseachData = searchMessageResult.getSerachMsglist();
                        lastResultlist.setVisibility(View.GONE);
                        if (list.getVisibility() == View.GONE)
                            list.setVisibility(View.VISIBLE);

                        resultSearch.setVisibility(View.GONE);
                        showPreviousResult = false;
                        if (sseachData == null
                                || (sseachData != null && sseachData.size() == 0)) {

                            // resultSearch.setText(Html.fromHtml(String.format(
                            // valhtmlString,
                            // getResources().getString(
                            // R.string.no_record_found), " ",
                            // " ", "")));

                            resultSearch.setText(Html.fromHtml(
                                    ""
                                            + no_match_drawablehtml
                                            + ""
                                            + msg_type_html
                                            + " "
                                            + getResources().getString(
                                            R.string.no_record_found)
                                            + "</b></font>",
                                    exclationimgGetter, null));

                            resultSearch.setVisibility(View.VISIBLE);
                            list.setVisibility(View.GONE);

                        } else {

                            String a = searchMessageResult
                                    .getPoppularCategories();
                            String name[] = a.split(","); // new
                            // String[sseachData.size()];.

                            if (name.length > 0) {

                                for (int i = 0; i < name.length; i++) {

                                    if (i == 0) {
                                        SearchData obj = new SearchData();
                                        obj.setType("header");
                                        obj.setCategory(name[i]);
                                        sseachData.add(obj);
                                    } else {
                                        SearchData obj = new SearchData();
                                        obj.setType("searchCatgory");
                                        obj.setCategory(name[i]);
                                        if (name[i].contains("in Stocks")) {
                                            obj.setLayout_position(1);
                                        } else if (name[i].contains("in Funds")) {
                                            obj.setLayout_position(2);
                                        } else if (name[i]
                                                .contains("in Commodities")) {
                                            obj.setLayout_position(3);
                                        } else if (name[i]
                                                .contains("in Futures")) {
                                            obj.setLayout_position(4);
                                        } else if (name[i].contains("in News")) {
                                            obj.setLayout_position(5);
                                        } else if (name[i]
                                                .contains("in Videos")) {
                                            obj.setLayout_position(6);
                                        } else if (name[i]
                                                .contains("in Messages")) {
                                            obj.setLayout_position(7);

                                        }

                                        sseachData.add(obj);
                                        Log.d("name[i] ", name[i]);
                                    }
                                }

                                oldseachData = sseachData;

                            }
                            getresult = 0;
                            lastShowlist = false;
                            if (adapter == null) {
                                list.setAdapter(new Search_home_Adapter(
                                        sseachData, getActivity(), false));
                            } else {
                                adapter.notifyDataSetChanged();
                            }

                        }
                    } catch (Exception e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }

                    list.setOnItemClickListener(new OnItemClickListener() {

                        @Override
                        public void onItemClick(AdapterView<?> arg0, View arg1,
                                                int position, long arg3) {
                            lastShowlist = false;
                            try {
                                Utility.getInstance().hideKeyBoard(getActivity());
                                if (sseachData != null
                                        && sseachData
                                        .get(position)
                                        .getType()
                                        .equalsIgnoreCase(
                                                "searchCatgory")) {

                                    fetchloadFinalResultWithCategorySearchData(
                                            temp_keyword,
                                            sseachData.get(position)
                                                    .getLayout_position());

                                } else if (sseachData != null
                                        && sseachData.get(position) != null
                                        && sseachData.get(position).getId() != null
                                        && !sseachData
                                        .get(position)
                                        .getType()
                                        .equalsIgnoreCase(
                                                "searchCatgory")
                                        && (!sseachData.get(position).getId()
                                        .equalsIgnoreCase("null"))) {

                                    try {
                                        if (TextUtils.isEmpty(sseachData.get(
                                                position).getExpiry_date_d())) {
                                            sseachData.get(position)
                                                    .setExpiry_date_d("null");
                                        }
                                    } catch (Exception e) {
                                        // TODO Auto-generated catch block
                                        sseachData.get(position)
                                                .setExpiry_date_d("null");
                                        e.printStackTrace();
                                    }

                                    try {
                                        if (TextUtils.isEmpty(sseachData.get(
                                                position).getInstrument())) {
                                            sseachData.get(position)
                                                    .setInstrument("null");
                                        }
                                    } catch (Exception e) {
                                        // TODO Auto-generated catch block
                                        sseachData.get(position).setInstrument(
                                                "null");
                                        e.printStackTrace();
                                    }

                                    String name = oldseachData.get(position)
                                            .getShortname()
                                            + "@"
                                            + oldseachData.get(position)
                                            .getId()
                                            + "@"
                                            + oldseachData.get(position)
                                            .getCategory_id()
                                            + "@"
                                            + oldseachData.get(position)
                                            .getEx()
                                            + "@"
                                            + oldseachData.get(position)
                                            .getExpiry_date()
                                            + "@"
                                            + sseachData.get(position)
                                            .getExpiry_date_d()
                                            + "@"
                                            + sseachData.get(position)
                                            .getInstrument()
                                            + "@"
                                            + Utility.getInstance()
                                            .getSELECTED_LANGUAGE();

                                    Utility.getInstance().addLastCommonSearch(
                                            name, getActivity());

                                    if (sseachData.get(position)
                                            .getCategory_id()
                                            .equalsIgnoreCase("1")) {

                                        showStockDetails(sseachData
                                                .get(position));

                                    } else if (sseachData.get(position)
                                            .getCategory_id()
                                            .equalsIgnoreCase("2")) {

                                        showMutualFundskDetails(sseachData
                                                .get(position));

                                    } else if (sseachData.get(position)
                                            .getCategory_id()
                                            .equalsIgnoreCase("3")) {

                                        showCommodityDetails(sseachData
                                                .get(position));

                                    } else if (sseachData.get(position)
                                            .getCategory_id()
                                            .equalsIgnoreCase("4")) {

                                        showFutureDetails(sseachData
                                                .get(position));

                                    } else if (sseachData.get(position)
                                            .getCategory_id()
                                            .equalsIgnoreCase("5")) {
                                        // news

                                    } else if (sseachData.get(position)
                                            .getCategory_id()
                                            .equalsIgnoreCase("6")) {
                                        // videos
                                    } else if (sseachData.get(position)
                                            .getCategory_id()
                                            .equalsIgnoreCase("7")) {
                                        // message

                                    } else if (sseachData.get(position)
                                            .getCategory_id()
                                            .equalsIgnoreCase("8")) {
                                        // boarder
                                        lunchBoarderScreen(sseachData.get(
                                                position).getId());
                                    } else if (sseachData.get(position)
                                            .getCategory_id()
                                            .equalsIgnoreCase("9")) {
                                        // topic
                                        launchTopicScreen(
                                                sseachData.get(position)
                                                        .getTopicid(),
                                                sseachData.get(position)
                                                        .getCategory_id(),
                                                "GlobalSearchFragment");
                                    } else if (sseachData.get(position)
                                            .getCategory_id()
                                            .equalsIgnoreCase("10")) {
                                        // boarder
                                        lunchIndicesDeatilsScreen(sseachData
                                                .get(position));
                                    }

                                }

                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }
                    });
                } else {


                    try {
                        if (isAdded()) {
                            dismissProgressDialog();

                            if (getresult == 1) {
                                resultSearch.setText(Html.fromHtml(
                                        ""
                                                + no_match_drawablehtml
                                                + ""
                                                + msg_type_html
                                                + " "
                                                + getResources().getString(
                                                R.string.no_record_found)
                                                + "</b></font>", exclationimgGetter,
                                        null));

                                resultSearch.setVisibility(View.VISIBLE);
                                list.setVisibility(View.GONE);
                            }

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                }
            }

        };

        if (temp_keyword != null && temp_keyword.equalsIgnoreCase(keyword))
            return;

        temp_keyword = keyword;
        if (searchThread != null) {
            searchThread.interrupt();
        }
        searchThread = new Thread() {
            public void run() {
                try {

                    // http://feeds.moneycontrol.com/app/mmb/mmb_search.php?f_criteria=All&f_search=
                    searchMessageResult = null;
                    searchMessageResult = ParseCall.getInstance()
                            .AllNewSerachDataWithSuggestions(getActivity(),
                                    allSearch, keyword);

                    autohandler.sendEmptyMessage(0);
                } catch (MyNetworkException e) {
                    e.printStackTrace();
                    autohandler.sendEmptyMessage(1);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                    autohandler.sendEmptyMessage(1);

                } catch (Exception e) {
                    e.printStackTrace();
                    autohandler.sendEmptyMessage(2);
                }
            }

            ;

        };
        searchThread.start();

    }

    // @SuppressWarnings("unused")
    // private synchronized void fetchloadFinalResultWithCategorySearchData(
    // final String keyword, int pos) {
    // searchMessageResult = null;
    // savePosition = (pos);
    // fetchfinalSearchData(keyword);
    //
    // }
    private void lastRelatedClicked() {
        list.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,
                                    int position, long arg3) {
                lastShowlist = false;
                Utility.getInstance().hideKeyBoard(getActivity());
                try {

                    if (oldseachData != null
                            && oldseachData.get(position) != null
                            && oldseachData.get(position).getId() != null
                            && !oldseachData.get(position).getType()
                            .equalsIgnoreCase("searchCatgory")) {

                        try {
                            if (TextUtils.isEmpty(oldseachData.get(position)
                                    .getExpiry_date_d())) {
                                oldseachData.get(position).setExpiry_date_d(
                                        "null");
                            }
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                            oldseachData.get(position).setExpiry_date_d("null");
                            e.printStackTrace();
                        }

                        try {
                            if (TextUtils.isEmpty(oldseachData.get(position)
                                    .getInstrument())) {
                                oldseachData.get(position)
                                        .setInstrument("null");
                            }
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                            oldseachData.get(position).setInstrument("null");
                            e.printStackTrace();
                        }

                        String name = oldseachData.get(position).getShortname()
                                + "@" + oldseachData.get(position).getId()
                                + "@"
                                + oldseachData.get(position).getCategory_id()
                                + "@" + oldseachData.get(position).getEx()
                                + "@"
                                + oldseachData.get(position).getExpiry_date()
                                + "@"
                                + oldseachData.get(position).getExpiry_date_d()
                                + "@"
                                + oldseachData.get(position).getInstrument()
                                + "@"
                                + Utility.getInstance().getSELECTED_LANGUAGE();

                        Utility.getInstance().addLastCommonSearch(name,
                                getActivity());

                        if (oldseachData.get(position).getCategory_id()
                                .equalsIgnoreCase("1")) {

                            showStockDetails(oldseachData.get(position));

                        } else if (oldseachData.get(position).getCategory_id()
                                .equalsIgnoreCase("2")) {

                            showMutualFundskDetails(oldseachData.get(position));

                        } else if (oldseachData.get(position).getCategory_id()
                                .equalsIgnoreCase("3")) {

                            showCommodityDetails(oldseachData.get(position));

                        } else if (oldseachData.get(position).getCategory_id()
                                .equalsIgnoreCase("4")) {

                            showFutureDetails(oldseachData.get(position));

                        } else if (oldseachData.get(position).getCategory_id()
                                .equalsIgnoreCase("5")) {
                            // news

                        } else if (oldseachData.get(position).getCategory_id()
                                .equalsIgnoreCase("6")) {
                            // videos
                        } else if (oldseachData.get(position).getCategory_id()
                                .equalsIgnoreCase("7")) {
                            // message

                        } else if (oldseachData.get(position).getCategory_id()
                                .equalsIgnoreCase("8")) {
                            // boarder
                            lunchBoarderScreen(oldseachData.get(position)
                                    .getId());
                        } else if (oldseachData.get(position).getCategory_id()
                                .equalsIgnoreCase("9")) {
                            // topic
                            launchTopicScreen(oldseachData.get(position)
                                    .getTopicid(), oldseachData.get(position)
                                    .getCategory_id(), "GlobalSearchFragment");
                        } else if (oldseachData.get(position).getCategory_id()
                                .equalsIgnoreCase("10")) {
                            // boarder
                            lunchIndicesDeatilsScreen(oldseachData
                                    .get(position));
                        }

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }

    @SuppressWarnings("unused")
    private synchronized void fetchloadFinalResultWithCategorySearchData(
            final String keyword, int pos) {
        searchMessageResult = null;
        savePosition = (pos);
        fetchfinalSearchData(keyword);

    }

    public void lunchNewsScreen(String tID) {

        // Bundle bundle = new Bundle();
        // NewsMessageFragment MessagessearchFragment = new
        // NewsMessageFragment();
        // bundle.putString("SCREENTYPE", "NEWS");
        // bundle.putString("KeyWord", keyWord);
        // bundle.putString(AppConstants.SERVER_URL, ServarURLNEWS);
        // MessagessearchFragment.setArguments(bundle);
        // ((BaseActivity)
        // getActivity()).launchFragement(MessagessearchFragment,
        // true);
    }

    private synchronized void fetchfinalSearchData(final String keyword1) {

        handler = new Handler() {
            public void handleMessage(android.os.Message msg) {

                try {
                    if (isAdded() && searchMessageResult != null) {
                        // oldsearchMessageResult = searchMessageResult;
                        list.setVisibility(View.GONE);
                        lastResultlist.setVisibility(View.GONE);

                        String msg_txt = getResources().getString(
                                R.string.search_result_txt);
                        resultSearch.setVisibility(View.VISIBLE);
                        resultSearch.setText(Html.fromHtml(String.format(
                                valhtmlString, msg_txt + "&nbsp" + '"' + keyword1
                                        + '"', " ", " ", "")));

                        sseachData = searchMessageResult.getSerachMsglist();
                        showPreviousResult = false;
                        if (sseachData == null
                                || (sseachData != null && sseachData.size() == 0)) {


                            resultSearch.setText(Html.fromHtml(
                                    ""
                                            + no_match_drawablehtml
                                            + ""
                                            + msg_type_html
                                            + " "
                                            + getResources().getString(
                                            R.string.no_record_found)
                                            + "</b></font>", exclationimgGetter,
                                    null));

                            resultSearch.setVisibility(View.VISIBLE);
                        } else {

                            String a = searchMessageResult.getPoppularCategories();
                            resultSearch.setVisibility(View.VISIBLE);
                            String name[] = a.split(",");
                            TMPsseachData = new ArrayList<SearchData>();
                            SearchData headerobj = null;
                            if (name.length > 1) {

                                for (int i = 0; i < name.length; i++) {
                                    if (i == 0) {
                                        headerobj = new SearchData();
                                        headerobj.setType("header");
                                        headerobj.setCategory(name[0]);
                                        // headerobj.setPlaceid(0);

                                    } else if (i > 0) {
                                        SearchData obj = new SearchData();
                                        String b = name[i];

                                        if (b.contains("&")) {

                                            String splitarr[] = b.split("&");
                                            ArrayList<String> fields = new ArrayList<String>();
                                            if (splitarr.length > 1) {
                                                fields.add(splitarr[0]);
                                                fields.add(splitarr[1]);
                                            } else {
                                                fields.add(splitarr[0]);
                                                fields.add("0");
                                            }

                                            String str = fields.get(0);
                                            String[] splitids = str.split("@");
                                            if (splitids.length > 0) {
                                                obj.setType("searchCatgory");
                                                obj.setCategory(splitids[0]);
                                                obj.setCategory_id(splitids[1]);
                                                obj.setMsg_counts(fields.get(1));
                                            } else {
                                                obj.setType("searchCatgory");
                                                obj.setCategory(splitids[0]);
                                                obj.setCategory_id("0");
                                                obj.setMsg_counts(fields.get(1));

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

                                resultLayout.setVisibility(View.VISIBLE);
                                lastShowlist = false;

                                if (isAdded()) {
                                    setSearchResultPage(searchMessageResult
                                            .getSearchFillterList());

                                }
                            }


                        }

                    } else {

                        dismissProgressDialog();


                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        };
        if (finalSearchThread != null) {
            finalSearchThread.interrupt();
        }
        finalSearchThread = new Thread() {
            public void run() {
                try {
                    searchMessageResult = null;
                    searchMessageResult = ParseCall.getInstance()
                            .getallNewSerachDataWithoutSuggestions(
                                    getActivity(), 0, allserachUrl, keyword1);

                    handler.sendEmptyMessage(0);

                } catch (MyNetworkException e) {
                    e.printStackTrace();
                    handler.sendEmptyMessage(1);

                } catch (InterruptedException e) {
                    e.printStackTrace();

                } catch (Exception e) {
                    e.printStackTrace();
                    handler.sendEmptyMessage(2);

                }
            }

            ;

        };
        finalSearchThread.start();

    }

    protected synchronized void setSearchResultPage(
            ArrayList<FilterByList> listoffillter) {

        title = new String[listoffillter.size()];
        Urlfiltters = searchMessageResult.getSearchFillterList();

        for (int i = 0; i < listoffillter.size(); i++) {

            title[i] = listoffillter.get(i).getFilterName();

        }

        for (int j = 0; j < sseachData.size(); j++) {
            sseachData.get(j).setType("serachOBJ");
            TMPsseachData.add(sseachData.get(j));

        }

        Log.i("xxxxx_data size",
                sseachData.size() + "///" + TMPsseachData.size());

        getresult = 1;
        resultFound = true;
        pager.setAdapter(new SearchFragmentAdapter(getChildFragmentManager()));

        tabs.setViewPager(pager);
        tabs.setIndicatorColor(getResources().getColor(R.color.orange));
        tabs.setTextColor(getResources().getColor(R.color.white));
        tabs.setTypeface(Typeface.createFromAsset(getActivity().getAssets(),
                "fonts/Roboto-Regular.ttf"), Typeface.NORMAL);
        tabs.setTextSize((int) getActivity().getResources().getDimension(
                R.dimen.view_pager_tab_text_size));

        if (pager != null) {
            resultSearch.setVisibility(View.VISIBLE);
            // resultSearch.setText(Html.fromHtml(String.format(valhtmlString,
            // getResources().getString(R.string.search_result_txt)
            // + "&nbsp" + '"' + search_word + '"', "", " ", "")));

            resultSearch.setText(Html.fromHtml(String.format(valhtmlString,
                    getResources().getString(R.string.search_result_txt)
                            + "&nbsp" + '"' + search_word + '"', "", " ", "")));

            pager.setCurrentItem(savePosition);
            pager.post(new Runnable() {

                @Override
                public void run() {
                    saveBundle = null;
                }
            });

        }

        if (externalClick) {
            if (pager != null) {
                pager.setCurrentItem(5);
            }
        }

        dismissProgressDialog();

    }

    public class Search_home_Adapter extends BaseAdapter {
        private LayoutInflater inflate = null;
        private ArrayList<SearchData> items = null;
        private final Context context;
        private boolean isSettings = false;

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
            final ViewHolder holder;
            final int pos = position;

            if (convertView == null) {
                holder = new ViewHolder();
                convertView = inflate.inflate(
                        R.layout.search_message_item_layout, null);
                holder.heading = (TextView) convertView
                        .findViewById(R.id.search_item_name);
                holder.marketmovers_item_name_ll = (LinearLayout) convertView
                        .findViewById(R.id.search_item_name_ll);
                holder.bottomline = (ImageView) convertView
                        .findViewById(R.id.bottomline);
                holder.topline = (ImageView) convertView
                        .findViewById(R.id.topline);

                Utility.getInstance().setTypeface(holder.heading,
                        context.getApplicationContext());

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            if (items.get(pos).getType().equalsIgnoreCase("header")) {

                holder.marketmovers_item_name_ll
                        .setBackgroundColor(getActivity().getResources()
                                .getColor(R.color.slide_menu_item));

            } else if (items.get(pos).getType().equalsIgnoreCase("searchCatgory")) {
                holder.marketmovers_item_name_ll
                        .setBackgroundColor(Color.TRANSPARENT);
            }

            if (items.get(pos).getType().equalsIgnoreCase("header")) {
                holder.heading.setText(Html.fromHtml(msg_type_html
                        + items.get(pos).getCategory() + "</b></font>"));
                holder.marketmovers_item_name_ll
                        .setBackgroundColor(getActivity().getResources()
                                .getColor(R.color.slide_menu_item));

            } else if (items.get(pos).getType()
                    .equalsIgnoreCase("nomatch_found")) {

                holder.heading.setText(Html.fromHtml("" + no_match_drawablehtml
                        + "" + msg_type_html + "  No Matches" + "</b></font>"
                        + msg_type_html + "-" + items.get(pos).getCategory()
                        + "</b></font>", exclationimgGetter, null));

            } else if (items.get(pos).getType()
                    .equalsIgnoreCase("No Records Found")) {

                holder.heading.setText(Html.fromHtml("" + no_match_drawablehtml
                        + "" + msg_type_html + items.get(pos).getShortname()
                        + "</b></font>", exclationimgGetter, null));

            } else if (items.get(pos).getType()
                    .equalsIgnoreCase("last_SearchItem")) {
                if (items.get(position).getCategory_id().equalsIgnoreCase("3")
                        || items.get(position).getCategory_id()
                        .equalsIgnoreCase("4")) {
                    holder.heading.setText(Html
                            .fromHtml(msg_type_html
                                    + items.get(pos).getShortname()
                                    + "</b></font>" + "&nbsp" + msg_type_html
                                    + items.get(pos).getExpiry_date_d()
                                    + "</b></font>"));
                } else {

                    holder.heading.setText(Html.fromHtml(msg_type_html
                            + items.get(pos).getShortname() + "</b></font>"));
                }
                holder.marketmovers_item_name_ll
                        .setBackgroundColor(Color.TRANSPARENT);
                if (pos + 1 == getCount()) {
                    holder.bottomline.setVisibility(View.VISIBLE);
                } else if (pos == 0) {
                    holder.bottomline.setVisibility(View.GONE);
                }

            } else if (items.get(pos).getType().equalsIgnoreCase("searchCatgory")) {

                holder.heading.setText(Html.fromHtml(""
                        + news_side_drawablehtml + "" + msg_type_html + '"'
                        + search_word + '"' + "</b></font>"
                        + category_type_html + items.get(pos).getCategory()
                        + "</b></font>", imgGetter, null));
                holder.marketmovers_item_name_ll
                        .setBackgroundColor(Color.TRANSPARENT);

            } else if (items.get(pos).getType().equalsIgnoreCase("serachOBJ")) {

                String str = createString(items.get(pos), items.get(pos)
                        .getCategory_id());
                Spanned htmlSpan = null;
                if (items.get(pos) != null) {

                    String categoryName = Utility.getInstance().toTitleCase(
                            items.get(position).getCategory());

                    if (items.get(pos).getCategory_id().equalsIgnoreCase("4")
                            || items.get(pos).getCategory_id()
                            .equalsIgnoreCase("3")) {

                        if (str.length() > 0) {

                            htmlSpan = Html.fromHtml(String.format(
                                    valhtmlString,
                                    items.get(pos).getShortname()
                                            + " "
                                            + items.get(position)
                                            .getExpiry_date_d()
                                            + "&nbsp" + "&nbsp" + "&nbsp",
                                    categoryName + "<br>", str, ""));

                        } else {

                            htmlSpan = Html.fromHtml(String.format(
                                    valhtmlString,
                                    items.get(pos).getShortname()
                                            + " "
                                            + items.get(position)
                                            .getExpiry_date_d()
                                            + "&nbsp" + "&nbsp" + "&nbsp",
                                    categoryName + "<br>", "", ""));
                        }

                        Drawable d = getResources().getDrawable(
                                R.drawable.latest2);
                        d.setBounds(0, 0, d.getIntrinsicWidth(),
                                d.getIntrinsicHeight());

                        SpannableString spantxt = new SpannableString(htmlSpan);
                        int index = spantxt.toString().indexOf(categoryName);
                        if (index != -1 && index > 1) {
                            ImageSpan span = new ImageSpan(d,
                                    ImageSpan.ALIGN_BASELINE);
                            spantxt.setSpan(span, index - 1, index,
                                    Spannable.SPAN_INCLUSIVE_EXCLUSIVE);

                            holder.heading.setText(addClickablePart(
                                    getActivity(), spantxt, search_word));
                            holder.heading.setLinkTextColor(getActivity()
                                    .getResources().getColor(R.color.orange));
                        }

                    } else if (items.get(pos).getCategory_id()
                            .equalsIgnoreCase("0")) {

                        if (TextUtils.isEmpty(items.get(pos).getCategory())) {
                            items.get(pos).setCategory("");
                        }
                        holder.heading.setText(Html.fromHtml(""
                                + no_match_drawablehtml + "" + msg_type_html
                                + " " + items.get(pos).getShortname()
                                + "</b></font>", exclationimgGetter, null));

                    } else {
                        if (str.length() > 0) {
                            htmlSpan = Html.fromHtml(String.format(
                                    valhtmlString, items.get(pos)
                                            .getShortname()
                                            + "&nbsp"
                                            + "&nbsp"
                                            + "&nbsp", categoryName + "<br>",
                                    str, ""));

                        } else {
                            htmlSpan = Html.fromHtml(String.format(
                                    valhtmlString, items.get(pos)
                                            .getShortname()
                                            + "&nbsp"
                                            + "&nbsp"
                                            + "&nbsp", categoryName, str, ""));

                        }

                        Drawable d = getResources().getDrawable(
                                R.drawable.latest2);
                        d.setBounds(0, 0, d.getIntrinsicWidth(),
                                d.getIntrinsicHeight());

                        SpannableString spantxt = new SpannableString(htmlSpan);
                        int index = spantxt.toString().indexOf(categoryName);
                        if (index != -1 && index > 1) {
                            ImageSpan span = new ImageSpan(d,
                                    ImageSpan.ALIGN_BASELINE);
                            spantxt.setSpan(span, index - 1, index,
                                    Spannable.SPAN_INCLUSIVE_EXCLUSIVE);

                            holder.heading.setText(addClickablePart(
                                    getActivity(), spantxt, search_word));
                            holder.heading.setLinkTextColor(getActivity()
                                    .getResources().getColor(R.color.orange));
                        }

                    }

                }

                holder.marketmovers_item_name_ll
                        .setBackgroundColor(getActivity().getResources()
                                .getColor(R.color.black_transparency));

            }

            return convertView;
        }

        class ViewHolder {
            public TextView heading;
            public LinearLayout marketmovers_item_name_ll;
            public ImageView bottomline, topline;
        }

    }

    public String createString(SearchData searchData, String id) {

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

        } else {

            if (searchData.getIsinid() != null
                    && searchData.getIsinid().toString().length() > 0) {

                resultString1 = searchData.getIsinid();

            } else {
                resultString1 = "";
            }

            if (searchData.getNseid() != null
                    && searchData.getNseid().toString().length() > 0) {

                if (resultString1.trim().length() == 0) {
                    resultString2 = searchData.getNseid();

                } else {
                    resultString2 = "," + " " + searchData.getNseid();
                }

            } else {
                resultString2 = "";
            }

            if (searchData.getBseid() != null
                    && searchData.getBseid().toString().length() > 0) {
                if (resultString2.trim().length() == 0) {
                    resultString3 = searchData.getBseid();

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

    private ImageGetter imgGetter = new ImageGetter() {

        public Drawable getDrawable(String source) {
            Drawable drawable = null;

            drawable = getResources().getDrawable(R.drawable.icon_search);

            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
                    drawable.getIntrinsicHeight());
            return drawable;
        }
    };

    private ImageGetter smallimgGetter = new ImageGetter() {

        public Drawable getDrawable(String source) {
            Drawable drawable = null;

            drawable = getResources().getDrawable(R.drawable.latest11);

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

    class SearchFragmentAdapter extends FragmentStatePagerAdapter {
        private FragmentManager fragmentManager;

        public SearchFragmentAdapter(FragmentManager fm) {
            super(fm);
            fragmentManager = fm;
        }

        @Override
        public Fragment getItem(int position) {
            Bundle bundle = new Bundle();
            if (setFragment(position, bundle) != null) {
                return setFragment(position, bundle);
            } else {
                NewFragment newFragment = new NewFragment();
                newFragment.setArguments(bundle);
                fragmentMapping.put(position, newFragment);
                return newFragment;
            }
        }

        @Override
        public int getCount() {
            return title.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return title[position];
        }

    }

    // public interface OnvoiceSearchInterface {
    // public void onItemPicked(int position);
    // }
    private Fragment setFragment(int position, Bundle bundle) {

        Fragment MessagessearchFragment = null;
        bundle.putString("ScreenName", title[position]);
        bundle.putString(AppConstants.SERVER_URL, Urlfiltters.get(position)
                .getFilterUrl());
        bundle.putString("KEY", search_word);
        bundle.putInt("Position", position);
        bundle.putString("UNIQUE_ID", Urlfiltters.get(position).getUniqueId());

        // switch (position) {
        if (Urlfiltters.get(position).getUniqueId()
                .equalsIgnoreCase(AppConstants.UNIQUE_ID_1)) {
            MessagessearchFragment = new SearchGlobalReultFragment();
            bundle.putSerializable("Object", searchMessageResult);
            bundle.putSerializable("list", null);

        } else if (Urlfiltters.get(position).getUniqueId()
                .equalsIgnoreCase(AppConstants.UNIQUE_ID_2)) {
            MessagessearchFragment = new SearchGlobalReultFragment();
            bundle.putSerializable("Object", null);
            bundle.putSerializable("list", null);

        } else if (Urlfiltters.get(position).getUniqueId()
                .equalsIgnoreCase(AppConstants.UNIQUE_ID_3)) {
            MessagessearchFragment = new SearchGlobalReultFragment();
            bundle.putSerializable("Object", null);
            bundle.putSerializable("list", null);

        } else if (Urlfiltters.get(position).getUniqueId()
                .equalsIgnoreCase(AppConstants.UNIQUE_ID_4)) {
            MessagessearchFragment = new SearchGlobalReultFragment();
            bundle.putSerializable("Object", null);
            bundle.putSerializable("list", null);

        } else if (Urlfiltters.get(position).getUniqueId()
                .equalsIgnoreCase(AppConstants.UNIQUE_ID_5)) {
            MessagessearchFragment = new SearchGlobalReultFragment();
            bundle.putSerializable("Object", null);
            bundle.putSerializable("list", null);

        } else if (Urlfiltters.get(position).getUniqueId()
                .equalsIgnoreCase(AppConstants.UNIQUE_ID_6)) {
            MessagessearchFragment = new NewsListFragment();
            bundle.putSerializable("Object", searchMessageResult);
            bundle.putSerializable("list", null);
            bundle.putString(AppConstants.KEY_NEWS_TITLE,
                    Urlfiltters.get(position).getFilterName());
            bundle.putInt(AppConstants.KEY_NEWS_TYPE,
                    AppConstants.NEWS_GLOBALSEARCH_DETAIL);
            bundle.putBoolean(AppConstants.KEY_NOT_TICKER_SHOW, true);
            bundle.putBoolean("showTittleVisible", true);
            bundle.putBoolean("showTittleVisible", true);

        } else if (Urlfiltters.get(position).getUniqueId()
                .equalsIgnoreCase(AppConstants.UNIQUE_ID_7)) {
            MessagessearchFragment = new VideoOnDemandFragment();
            bundle.putSerializable("Object", null);
            bundle.putSerializable("list", null);
            bundle.putString("ScreenName", "CommonSearch");

        } else if (Urlfiltters.get(position).getUniqueId()
                .equalsIgnoreCase(AppConstants.UNIQUE_ID_8)) {
            // MessagessearchFragment = new LatestMassageFragment();
            // bundle.putSerializable("Object", searchMessageResult);
            // bundle.putSerializable("ResultObj", null);

            MessagessearchFragment = new MessageSearchItem();
            bundle.putSerializable("Object", null);
            bundle.putSerializable("ResultObj", null);

            // MessagessearchFragment = new MyMessageFollowingFragment();
            // bundle.putSerializable("Object", null);
            // bundle.putSerializable("list", null);
        }
        // }

        // SearchMessageReultFragment MessagessearchFragment = new
        // SearchMessageReultFragment();
        MessagessearchFragment.setTargetFragment(((BaseActivity) getActivity())
                .getCurrentfragment("GlobalSearchFragment"), 1);
        MessagessearchFragment.setArguments(bundle);
        fragmentMapping.put(position, MessagessearchFragment);

        return MessagessearchFragment;
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == 1) {

            if (data != null) {

                // Boolean b = data.getBooleanExtra("completed", false);

                int a = data.getIntExtra("arg2", 0);

                getrefresh(a);
            }
        }

        if (resultCode == 2) {

        }

    }

    public void getrefresh(int resultval) {
        Fragment page = fragmentMapping.get(pager.getCurrentItem());
        if (pager.getCurrentItem() <= 7 && page != null) {

            pager.setCurrentItem(resultval); // this
        }
    }

}