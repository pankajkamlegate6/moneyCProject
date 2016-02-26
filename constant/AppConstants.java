package com.moneycontrol.handheld.constant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.apache.http.client.methods.HttpGet;
import org.xmlpull.v1.XmlPullParser;

import com.moneycontrol.handheld.entity.search.SearchStockMessagesData;
import com.moneycontrol.handheld.parser.ParseCall;
import com.moneycontrol.handheld.parser.ParseLevelSearchTopicMessage;
import com.moneycontrol.handheld.parser.ParserUtil;

public class AppConstants {

	public static final int INDICES_FEILD = 0;
	public static final int MY_PORTFOLIO = 1;
	public static final int STOCKS_LAST_VISITED = 2;
	public static final int BREAKING_NEWS = 6;
	public static final int LATEST_NEWS = 7;

	public static final String KEY_NEWS_TITLE = "KEY_NEWS_TITLE";
	public static final String KEY_NEWS_TYPE = "KEY_NEWS_TYPE";
	public static final String KEY_INDICE_TYPE = "KEY_INDICE_TYPE";
	public static final String KEY_NEWS_STORY_ID = "KEY_NEWS_STORY_ID";
	public static final String KEY_NEWS_TOTAL_COUNT = "KEY_NEWS_TOTAL_COUNT";
	public static final String KEY_NEWS_SELECTED_COUNT = "KEY_NEWS_SELECTED_COUNT";
	public static final String KEY_NEWS_DATA = "KEY_NEWS_DATA";
	public static final String KEY_NEWS_DETAIL_DATA = "KEY_NEWS_DETAIL_DATA";
	public static final String KEY_NEWS_SECTION = "KEY_NEWS_SECTION";
	public static final String KEY_VIDEO_URL = "KEY_VIDEO_URL";
	public static final String KEY_VIDEO_URL_LIST = "KEY_VIDEO_URL_LIST";
	public static final String POSITION = "POSITION";
	public static final int NEWS_TOP_NEWS = 1;
	public static final int NEWS_MARKET_NEWS = 2;
	public static final int NEWS_STOCKS_NEWS = 3;
	public static final int NEWS_BUSINESS_NEWS = 4;
	public static final int NEWS_MANAGEMENT_TALK = 5;
	public static final int NEWS_MUTUAL_FUNDS = 6;
	public static final int NEWS_COMMODITIES = 7;
	public static final int NEWS_PERSONAL_FINANCE = 8;
	public static final int NEWS_STOCK_DETAIL = 9;
	public static final int NEWS_COMMODITY_DETAIL = 10;
	public static final int NEWS_GLOBALSEARCH_DETAIL = 11;

	public static final String KEY_LIVE_TV = "KEY_LIVE_TV";
	public static final int LIVE_TV_TV18 = 1;
	public static final int LIVE_TV_AWAAZ = 2;
	public static final int LIVE_TV_TV18_PRIME = 3;
	public static final int LIVE_TV_BAJAR = 4;
	public static final int INDICE_INDIAN = 1;
	public static final int INDICE_GLOBAL = 2;
	public static final String VIDEO_PROGRESS = "VIDEO_PROGRESS";
	public static final String VIDEO_URL = "VIDEO_URL";

	public static final String SERIALIZABLE_OBJECT = "SERIALIZABLE_OBJECT";
	public static final String INDICE_ID = "INDICE_ID";
	public static final String INDICE_NAME = "INDICE_NAME";
	public static final String INDICE_LINK_FLAG = "INDICE_LINK_FLAG";
	public static final String INDICE_GRAPH_URL = "INDICE_GRAPH_URL";
	public static final String INDICE_VALUE = "INDICE_VALUE";
	public static final String INDICE_CHANGE = "INDICE_CHANGE";
	public static final String INDICE_PERCENTCHANGE = "INDICE_PERCENTCHANGE";
	public static final String INDICE_VOLUME = "INDICE_VOLUME";
	public static final String INDICE_LAST_UPDATED = "INDICE_LAST_UPDATED";
	public static final String INDICE_DIRECTION = "INDICE_DIRECTION";
	public static final String INDICE_OVERVIEW_DATA = "INDICE_OVERVIEW_DATA";
	public static final String STOCK_ID = "STOCK_ID";
	public static final String STOCK_DEFAULT_EX = "STOCK_DEFAULT_EX";
	public static final String STOCK_NAME = "STOCK_NAME";
	public static final String STOCK_GRAPH_URL = "STOCK_GRAPH_URL";
	public static final String STOCK_GRAPH_MOVING_AVG_URL = "STOCK_GRAPH_MOVING_AVG_URL";
	public static final String MARKET_CATEGORY = "MARKET_CATEGORY";
	public static final String SECTION_TYPE = "SECTION_TYPE";
	public static final String BALANCE_SHEET = "BALANCE_SHEET";
	public static final String PROFIT_AND_LOSS = "PROFIT_AND_LOSS";

	public static final int SORT_BY_NAME_ASC = 0;
	public static final int SORT_BY_NAME_DESC = 1;
	public static final int SORT_BY_CHANGE_ASC = 2;
	public static final int SORT_BY_CHANGE_DESC = 3;
	public static final int SORT_BY_WEIGHTAGE_ASC = 4;
	public static final int SORT_BY_WEIGHTAGE_DESC = 5;
	public static final int SORT_BY_PRICE_ASC = 6;
	public static final int SORT_BY_PRICE_DESC = 7;
	public static final int SORT_BY_PERCENT_CHANGE_ASC = 8;
	public static final int SORT_BY_PERCENT_CHANGE_DESC = 9;
	public static final int SORT_BY_PEER_MARKET_CAP = 10;
	public static final int SORT_BY_PEER_SALES = 11;
	public static final int SORT_BY_PEER_NET_PROFIT = 12;
	public static final int SORT_BY_PEER_TOTAL_ASSETS = 13;

	public static final int FRAGMENT_TOP_GAINERS = 0;
	public static final int FRAGMENT_TOP_LOSERS = 1;
	public static final int FRAGMENT_MOST_ACTIVE_BY_VALUE = 2;
	public static final int FRAGMENT_MOST_ACTIVE_BY_VOLUME = 3;
	public static final int FRAGMENT_52_WEEK_HIGH = 4;
	public static final int FRAGMENT_52_WEEK_LOW = 5;
	public static final int FRAGMENT_ONLY_BUYERS = 6;
	public static final int FRAGMENT_ONLY_SELLERS = 7;

	public static final int FRAGMENT_TOP_BUYER = 8;
	public static final int FRAGMENT_TOP_SELLER = 9;

	// New for FNO
	public static final int FRAGMENT_MOST_ACTIVE_BY_PUTS = 4;
	public static final int FRAGMENT_INCR_OPEN_I = 5;
	public static final int FRAGMENT_DECR_OPEN_I = 6;
	public static final int FRAGMENT_INCR_OPEN_I_AND_I_PRICE = 7;
	public static final int FRAGMENT_INCR_OPEN_I_AND_D_PRICE = 8;
	public static final String FRAGMENT_SELECTED_TAB_ID = "FRAGMENT_SELECTED_TAB_ID";

	public static final int STOCK_PEER_MARKET_CAP = 0;
	public static final int STOCK_PEER_SALES = 1;
	public static final int STOCK_PEER_NET_PROFIT = 2;
	public static final int STOCK_PEER_TOTAL_ASSETS = 3;

	public static final String PERFORMANCE_TRACKER_SCREEN = "PERFORMANCE_TRACKER_SCREEN";

	public static final int SORT_BY_FUND_NAME = 0;
	public static final int SORT_BY_FUND_RETURNS = 1;
	public static final int SORT_BY_FUND_RATING = 2;

	public static final int SORT_BY_MY_PORTFOLIO_NAME = 1;
	public static final int SORT_BY_MY_PORTFOLIO_DAY_GAIN = 2;
	public static final int SORT_BY_MY_PORTFOLIO_DAY_GAIN_PERCENT_CHANGE = 3;
	public static final int SORT_BY_MY_PORTFOLIO_OVERALL_GAIN = 4;
	public static final int SORT_BY_MY_PORTFOLIO_OVERALL_GAIN_PERCENT_CHANGE = 5;
	public static final int SORT_BY_MY_PORTFOLIO_LATEST_VALUE = 6;
	public static final int SORT_BY_MY_PORTFOLIO_INVESTMENT = 7;
	public static final int SORT_BY_MY_PORTFOLIO_QUANTITY = 8;

	public static final String FUND_ID = "FUND_ID";
	public static final String FUND_NAME = "FUND_NAME";

	public static final String COMMODITY_NAME = "COMMODITY_NAME";

	public static final String INDICE_DETAIL_TAB_URL = "INDICE_DETAIL_TAB_URL";
	public static final String STOCK_DETAIL_TAB_URL = "STOCK_DETAIL_TAB_URL";
	public static final String COMMODITY_DETAIL_TAB_URL = "COMMODITY_DETAIL_TAB_URL";
	public static final String MY_PORTFOLIO_TAB_URL = "MY_PORTFOLIO_TAB_URL";

	public static final String LIVE_TV_SHOW_ID = "LIVE_TV_SHOW_ID";
	public static final String LIVE_TV_SHOW_URL = "LIVE_TV_SHOW_URL";
	public static String LIVE_TV_SCREEN = "LIVE_TV_SCREEN";
	public static final String LIVE_TV_SCREEN_CNBC_TV18 = "LIVE_TV_SCREEN_CNBC_TV18";
	public static final String LIVE_TV_SCREEN_CNBC_AWAAZ = "LIVE_TV_SCREEN_CNBC_AWAAZ";
	public static final String LIVE_TV_SCREEN_CNBC_BAJAR = "LIVE_TV_SCREEN_CNBC_BAJAR";
	public static final String LIVE_TV_SCREEN_CNBC_PRIMEHD = "LIVE_TV_SCREEN_CNBC_PRIMEHD";

	public static final String USER_ID = "USER_ID";
	public static final String USER_TOKEN = "USER_TOKEN";
	public static final String USER_NAME = "USER_NAME";
	public static final String USER_MEM_type = "USER_MEM_type";
	public static final String USER_PIC = "USER_PIC";

	public static String MY_PORTFOLIO_TAB = "MY_PORTFOLIO_TAB";
	public static String MY_PORTFOLIO_TAB_TITLE = "MY_PORTFOLIO_TAB_TITLE";
	public static int REFRESH_RATE = 5;;
	public static final String MY_PORTFOLIO_TAB_SUMMARY = "MY_PORTFOLIO_TAB_SUMMARY";
	public static final String MY_PORTFOLIO_TAB_STOCKS = "MY_PORTFOLIO_TAB_STOCKS";
	public static final String MY_PORTFOLIO_TAB_MUTUAL_FUNDS = "MY_PORTFOLIO_TAB_MUTUAL_FUNDS";
	public static final String MY_PORTFOLIO_TAB_ULIP = "MY_PORTFOLIO_TAB_ULIP";
	public static final String MY_PORTFOLIO_TAB_BULLION = "MY_PORTFOLIO_TAB_BULLION";
	public static final String MY_PORTFOLIO_TAB_ALERT = "MY_PORTFOLIO_TAB_ALERT";
	public static String MY_PORTFOLIO_USER_ACCOUNTS = "MY_PORTFOLIO_USER_ACCOUNTS";
	public static final String MY_PORTFOLIO_USER_ACCOUNTS_DATA = "MY_PORTFOLIO_USER_ACCOUNTS_DATA";
	public static final String MY_PORTFOLIO_USER_ACCOUNT_NAME = "MY_PORTFOLIO_USER_ACCOUNT_NAME";
	public static final String MY_PORTFOLIO_USER_ACCOUNT_ID = "MY_PORTFOLIO_USER_ACCOUNT_ID";
	public static final String MY_PORTFOLIO_ENTITY_ACCOUNT_ID = "MY_PORTFOLIO_ENTITY_ACCOUNT_ID";
	public static final String MY_PORTFOLIO_EDIT_DATA = "MY_PORTFOLIO_EDIT_DATA";
	public static final String MY_EDIT_ITEM_ID = "MY_EDIT_ITEM_ID";

	public static final String TOPIC_ID = "Topic_id";
	public static final String THREAD_ID = "Thread_id";
	public static final String KEY_THUMBNAIL = "thumbnail";
	public static final String KEY_TIME = "time";
	public static final String SERVER_URL = "";
	public static final String SCREEN_MY_PORTFOLIO = "SCREEN_MY_PORTFOLIO";
	public static final String SCREEN_MY_PORTFOLIO_TAB_MUTUAL_FUNDS = "SCREEN_MY_PORTFOLIO_TAB_MUTUAL_FUNDS";
	public static final String SCREEN_MY_PORTFOLIO_SORT_BY = "SCREEN_MY_PORTFOLIO_SORT_BY";
	public static final String SCREEN_LIVE_TV = "SCREEN_LIVE_TV";
	public static final String SCREEN_STOCK_DETAIL = "SCREEN_STOCK_DETAIL";
	public static final String SCREEN_MUTUAL_FUND_DETAIL = "SCREEN_MUTUAL_FUND_DETAIL";
	public static final String SCREEN = "SCREEN";
	public static final String DATE_PICKER_YEAR = "DATE_PICKER_YEAR";
	public static final String DATE_PICKER_MONTH = "DATE_PICKER_MONTH";
	public static final String DATE_PICKER_DAY = "DATE_PICKER_DAY";
	public static final int TARGET_REQUEST_CODE = 12033;
	public static final int TARGET_REQUEST_NEXT_CODE = 120333;
	public static final int TARGET_REQUEST_STOCK_DETAIL = 140333;
	public static final int TARGET_REQUEST_LOGOUT_SESSION = 111111;
	public static final String DFP_NATIVE_AD_UNIT_ID = "/6499/example/native";
	public static final String EXPIRY_DATE = "date";
	public static final String EXCHANGE = "ex";
	public static final String DATE_FORMAT_dd_MM_yyyy_with_dash = "dd-MM-yyyy";
	public static final String DATE_FORMAT_dd_MMM_yyyy = "dd MMM yyyy";
	public static final String DATE_FORMAT_dd_MMM_yyyy_with_dash = "dd-MMM-yyyy";
	public static final String DATE_FORMAT_MMM_dd_yyyy_with_dash = "MMM-dd-yyyy";
	public static final String DATE_FORMAT_MMM_dd_yyyy_with_comma = "MMM dd, yyyy";
	public static String TOP_NEWS_URL = "";

	public static ArrayList<String> menuItemsName = new ArrayList<String>(
			Arrays.asList("Home", "Market", "Markets", "News", "LiveTv",
					"mystocks", "commodities", "mutualfunds", "messages",
					"Settings", "favourite", " sharethisapp", "sharethis",
					"ratethis", "ratethisapp", "moreapp", "moreapplication"));

	public static final String UPDATE_PRICE = "UPDATE_PRICE";
	public static final String LB_FUND_CAT = "KEY_FUND_CAT";

	public static final String FUND_CATEGORY = "FUND_CATEGORY";

	// default index selection.

	public static final byte DEFAULT_SORT_INDEX_PERFORMANCE_TRACKER = 2;

	public static final byte CORPORATE_ACTION_RECENT = 0;
	public static final byte CORPORATE_ACTION_BOARD_MEETING = 1;
	public static final byte CORPORATE_ACTION_DIVIDENTS = 2;
	public static final byte CORPORATE_ACTION_BONUS = 3;
	public static final byte CORPORATE_ACTION_SPLITS = 4;
	public static final byte CORPORATE_ACTION_RIGHTS = 5;
	public static final byte CORPORATE_ACTION_AGM = 6;

	public static final String KEY_LANGUAGE = "KEY_LANGUAGE";
	//

	public static final String KEY_HEADER = "key_header";

	public static final String KEY_CUSTOMER_KEY = "key_customer_key";

	public static final String KEY_SHARE_HEAD_TITLE = "key_share_head_title";
	public static final String KEY_SHARE_SUMM_TITLE = "key_share_summ_title";
	public static final String KEY_SHARE_MMB_TITLE = "key_share_mmb_title";

	// public static String KEY_SHARE_NEWS_SUBJECT =
	// "<body><p>%heading %news_headline</p></body>";
	// public static String KEY_SHARE_NEWS_GMAIL = "<p>%summary_text</p>";
	public static final String KEY_PUBLISH_SECRET = "key_publish_secret";

	public static final String KEY_TRACKING_ID = "key_tracking_id";

	public static final String KEY_SENDER_ID = "key_sender_id";
	public static final String KEY_FLURRY_ID = "key_flurry_id";

	public static final String KEY_NOT_TICKER_SHOW = "key_ticker_show";

	public static final int UNIQUE_ID_HOME = 1;
	public static final int UNIQUE_ID_INDICES = 3;

	public static final int UNIQUE_ID_NEWS = 8;
	public static final int UNIQUE_ID_CURRIENCES = 6;
	public static final int UNIQUE_ID_MARKET_MOVERS = 5;
	public static final int UNIQUE_ID_MYPORTFOLIO = 23;
	public static final int UNIQUE_ID_MY_WATCHLIST = 24;
	public static final int UNIQUE_ID_STOCKIVISITED = 25;

	public static final String KEY_MENU_EPOCH_ID = "key_menu_epoch_id";
	public static final String KEY_MENU_UPDATE = "key_menu_udpate";
	public static final String KEY_CONFIG= "key_CONFIG";

	public static String UNIQUE_ID_1 = "1";
	public static String UNIQUE_ID_2 = "2";
	public static String UNIQUE_ID_3 = "3";
	public static String UNIQUE_ID_4 = "4";
	public static String UNIQUE_ID_5 = "5";
	public static String UNIQUE_ID_6 = "6";
	public static String UNIQUE_ID_7 = "7";
	public static String UNIQUE_ID_8 = "8";
	public static String UNIQUE_ID_9 = "9";
	public static String UNIQUE_ID_10 = "10";
	public static String UNIQUE_ID_11 = "11";
	public static String UNIQUE_ID_12 = "12";
	public static String UNIQUE_ID_13 = "13";
	public static String UNIQUE_ID_14 = "14";
	public static String UNIQUE_ID_15 = "15";

	// Push message keys

	public static String KEY_PUSH_MESSAGE = "message";
	public static String KEY_PUSH_CATEGORY = "category";
	public static String KEY_PUSH_LANG = "lang";
	public static String KEY_PUSH_ARTICLEID = "articleid";
	public static String KEY_PUSH_PHOTO = "photo";
	public static String KEY_PUSH_IMAGE_URL = "image_big_URL";
	public static String KEY_PUSH_ISNETWORTH = "isNetworth";
	public static final String INVALID_TOKEN = "Invalid Token";
	public static final HashMap<String, HttpGet> HTTPGET_ABORDER = new HashMap<String, HttpGet>();

	public static final String KEY_SPLASH_HTML = "key_splash_html";

}
