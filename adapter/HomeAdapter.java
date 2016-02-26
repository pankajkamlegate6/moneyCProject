package com.moneycontrol.handheld.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.divum.MoneyControl.R;
import com.google.android.gms.ads.formats.NativeContentAdView;
import com.moneycontrol.handheld.AppData;
import com.moneycontrol.handheld.BaseActivity;
import com.moneycontrol.handheld.constant.AppConstants;
import com.moneycontrol.handheld.currency.fragments.CurrenciesParentFragment;
import com.moneycontrol.handheld.dynamic.menu.MenuList;
import com.moneycontrol.handheld.entity.home.HomeData;
import com.moneycontrol.handheld.fragments.CommodityFragment;
import com.moneycontrol.handheld.fragments.HomeFragment;
import com.moneycontrol.handheld.fragments.MarketMoverFragment;
import com.moneycontrol.handheld.fragments.StocksIVisitedFragment;
import com.moneycontrol.handheld.nativead.ADType;
import com.moneycontrol.handheld.nativead.ContentAdViewHolder;
import com.moneycontrol.handheld.nativead.NativeAdCategory;
import com.moneycontrol.handheld.nativead.NativeAdLoader;
import com.moneycontrol.handheld.util.ImageDownloader;
import com.moneycontrol.handheld.util.UrlUtils;
import com.moneycontrol.handheld.util.Utility;

import java.util.ArrayList;
import java.util.HashMap;

public class HomeAdapter extends BaseAdapter {
    // private boolean flag_show_change_percentage = false;
    private Activity context = null;
    private ArrayList<HomeData> homeDataList = null;
    private LayoutInflater inflate = null;
    private String url = "";

    private final byte NORMAl = 0;
    private final byte NATIVE_AD = 1;

    private ContentAdViewHolder adViewHolder = null;

    private AppData MainController = null;
    private MenuList menulist = null;
    private String serverURl = "";
    public HashMap<String, String> extraLinks = new HashMap<String, String>();
    private int placeForAd = 0;
    private String nativeAdId = "";
    private NativeAdLoader loader;

    public HomeAdapter(ArrayList<HomeData> homeDataList, Activity con,
                       String urls) {
        context = con;
        this.homeDataList = homeDataList;
        this.url = urls;
        inflate = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        MainController = AppData.getInstance();
        menulist = MainController.getMenulistData();
        extraLinks = menulist.getLinks();
        serverURl = extraLinks.get("");

        AppData appState = ((AppData) con.getApplicationContext());
        try {
            if (appState != null && appState.getHomeNativeAd() != null) {
                try {
                    placeForAd = Integer.parseInt(appState.getHomeNativeAd()
                            .getPosition());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                nativeAdId = appState.getHomeNativeAd().getSite_id();

                Log.v("aman", "placeForAd is:" + placeForAd);
                Log.v("aman", "nativeAdId is:" + nativeAdId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (this.homeDataList.size() > placeForAd && placeForAd > 0) {
            addNativeAdObject();
        }

    }

    private void addNativeAdObject() {
        HomeData categoryData = new HomeData();
        int count = 0;
        for (int i = 0; i < homeDataList.size(); i++) {

            if (!homeDataList.get(i).isHeading()) {
                count++;
            }

            if (count == placeForAd) {
                placeForAd = i + 1;
                homeDataList.add(i + 1, categoryData);
                break;
            }
        }
    }

    @Override
    public int getCount() {
        return homeDataList.size();
    }

    @Override
    public Object getItem(int arg0) {
        return homeDataList.get(arg0);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        HomeData homedata = homeDataList.get(position);

        int itemType = getItemViewType(position);

        if (convertView == null) {

            holder = new ViewHolder();

            switch (itemType) {
                case NORMAl:

                    convertView = inflate.inflate(R.layout.home_tupple, null);
                    holder.heading = (TextView) convertView
                            .findViewById(R.id.tv_home_item_header);
                    holder.lastPrice = (TextView) convertView
                            .findViewById(R.id.tv_home_item_lastprice);
                    holder.change = (TextView) convertView
                            .findViewById(R.id.tv_home_item_change);
                    holder.ivChange = (ImageView) convertView
                            .findViewById(R.id.iv_home_item_change);
                    holder.name = (TextView) convertView
                            .findViewById(R.id.tv_home_item_indices);
                    holder.date = (TextView) convertView
                            .findViewById(R.id.tv_home_item_timestamp);
                    holder.ivFlag = (ImageView) convertView
                            .findViewById(R.id.iv_home_item_flag);
                    holder.ivSeparator = (ImageView) convertView
                            .findViewById(R.id.separator);
                    holder.volume = (TextView) convertView
                            .findViewById(R.id.tv_home_item_volume);

                    // Utility.getInstance().setTypeface(holder.heading,
                    // context.getApplicationContext());
                    // Utility.getInstance().setTypeface(holder.lastPrice,
                    // context.getApplicationContext());
                    // Utility.getInstance().setTypeface(holder.change,
                    // context.getApplicationContext());
                    // Utility.getInstance().setTypeface(holder.name,
                    // context.getApplicationContext());
                    // Utility.getInstance().setTypeface(holder.date,
                    // context.getApplicationContext());

                    holder.llHeader = (LinearLayout) convertView
                            .findViewById(R.id.ll_home_item_header);
                    holder.layoutCommonItem = (View) convertView
                            .findViewById(R.id.ll_item_list);
                    holder.rlData = (LinearLayout) convertView
                            .findViewById(R.id.rl_home_item_maindata);
                    holder.portll = (LinearLayout) convertView
                            .findViewById(R.id.ll_home_item_port);

                    holder.pp = (TextView) convertView
                            .findViewById(R.id.tvNetworth);
                    holder.tt = (TextView) convertView.findViewById(R.id.tvDaygain);

                    convertView.setBackgroundColor(Color.BLACK);

                    break;

                case NATIVE_AD:
                    convertView = inflate.inflate(
                            R.layout.nativead_content_listing, null);
                    if (loader == null || loader.contentAd == null) {
                        adViewHolder = new ContentAdViewHolder((NativeContentAdView) convertView, NativeAdCategory.CONTENT_ONLY);
                        // NativeAdLoader loader = (NativeAdLoader) homedata;
                        loader = new NativeAdLoader(nativeAdId,
                                NativeAdCategory.CONTENT_ONLY,
                                ADType.CONTENT_AD);
                        loader.loadAd(context, adViewHolder, null, "");
                    } else {
                        adViewHolder = new ContentAdViewHolder((NativeContentAdView) convertView, NativeAdCategory.CONTENT_ONLY);
                        adViewHolder.populateListItem(adViewHolder,
                                loader.contentAd,
                                NativeAdCategory.CONTENT_ONLY);
                    }

                    break;
            }

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        switch (itemType) {
            case NORMAl:
                if (homedata.isHeading()) {

                    holder.llHeader.setVisibility(View.VISIBLE);
                    holder.rlData.setVisibility(View.GONE);
                    holder.ivSeparator.setVisibility(View.GONE);
                    holder.portll.setVisibility(View.GONE);

                    TextView temp = (TextView) holder.llHeader
                            .findViewById(R.id.tv_home_item_header);

                    if (homeDataList.get(position).getShortname() != null) {
                        Utility.getInstance().setTypeface(temp,
                                context.getApplicationContext());
                        temp.setText(Html.fromHtml(homeDataList.get(position)
                                .getShortname().toUpperCase()));
                        System.out
                                .println("Want to get the data which is going to set  "
                                        + homeDataList.get(position).getShortname()
                                        .toUpperCase());

                        Utility.getInstance().setTypeface(temp,
                                context.getApplicationContext());
                    }
                } else if (!homedata.isIsportfolio()
                        && homedata.isHeading() == false) {

                    if (position == (homeDataList.size() - 1)
                            || homeDataList.get(position + 1).isHeading()
                            || homeDataList.get(position + 1).isIsportfolio()) {
                        holder.ivSeparator.setVisibility(View.GONE);
                    } else if (position == HomeFragment.homestockitems) {
                        holder.ivSeparator.setVisibility(View.GONE);
                    } else {
                        holder.ivSeparator.setVisibility(View.VISIBLE);
                    }
                    holder.llHeader.setVisibility(View.GONE);
                    holder.rlData.setVisibility(View.VISIBLE);
                    holder.portll.setVisibility(View.GONE);

                    if (homeDataList.get(position).getShortname() != null)
                        holder.name.setText(Html.fromHtml(homeDataList
                                .get(position).getShortname()));

                    if (homeDataList.get(position).getLastUpdated() != null)
                        holder.date.setText(homeDataList.get(position)
                                .getLastUpdated());

                    if (homeDataList.get(position).getLastValue() != null) {

                        // if
                        // (homeDataAl.get(position).getLastValue().equalsIgnoreCase(context.getString(R.string.not_traded_in_last_30_days)))
                        // homeDataAl.get(position).setLastValue("*N.T");
                        if (homeDataList
                                .get(position)
                                .getLastValue()
                                .equalsIgnoreCase(
                                        Utility.getInstance()
                                                .setShowInternetConnection(
                                                        context.getApplicationContext(),
                                                        R.string.not_traded_in_last_30_days,
                                                        R.string.not_traded_in_last_30_days,
                                                        R.string.not_traded_in_last_30_days)))
                            homeDataList.get(position).setLastValue("*N.T");

                        holder.lastPrice.setText(homeDataList.get(position)
                                .getLastValue());
                        if (!TextUtils.isEmpty(homeDataList.get(position)
                                .getVolume())) {
                            String volume = "";
                            holder.volume.setVisibility(View.VISIBLE);
                            if (homeDataList.get(position).getVolume()
                                    .contains("k")
                                    || homeDataList.get(position).getVolume()
                                    .contains("m")) {
                                volume = homeDataList.get(position).getVolume();
                            } else {
                                volume = homeDataList.get(position).getVolume()
                                        + "k";
                            }
                            holder.volume.setText("Vol: " + volume);
                        } else {
                            holder.volume.setVisibility(View.GONE);
                        }

                    }

				/*
                 * if (homeDataList.get(position).getCategory() != 1 &&
				 * homeDataList.get(position).getCategory() != 0)
				 * convertView.setBackgroundColor(Color.BLACK);
				 */

                    if (homeDataList.get(position).getChange() != null) {

                        Utility.getInstance().flipChangeTOPercentageData(
                                holder.change,
                                homeDataList.get(position).getChange(),
                                homeDataList.get(position).getPercentChange(),
                                homeDataList.get(position).getDirection(), context,
                                holder.ivChange);

                    }

                    // convertView.setOnClickListener(new OnClickListener() {
                    //
                    // @Override
                    // public void onClick(View v) {
                    // showDetailActivity(position);
                    //
                    // }
                    // });

                    if (homeDataList.get(position).getFlag() != null
                            && !homeDataList.get(position).getFlag()
                            .equalsIgnoreCase("")) {
                        holder.ivFlag.setVisibility(View.VISIBLE);
                        new ImageDownloader().download(homeDataList.get(position)
                                .getFlag(), holder.ivFlag);
                    } else {
                        holder.ivFlag.setVisibility(View.GONE);
                    }

                } else {
                    if (TextUtils.isEmpty(homeDataList.get(position).getNetWorth())) {
                        holder.ivSeparator.setVisibility(View.GONE);
                        holder.llHeader.setVisibility(View.GONE);
                        holder.rlData.setVisibility(View.GONE);
                        holder.portll.setVisibility(View.GONE);
                        holder.ivSeparator.setVisibility(View.GONE);
                    } else {
                        holder.llHeader.setVisibility(View.GONE);
                        holder.rlData.setVisibility(View.GONE);
                        holder.portll.setVisibility(View.VISIBLE);
                        holder.ivSeparator.setVisibility(View.GONE);

                        holder.pp.setText(homeDataList.get(position).getNetWorth());
                        if (homeDataList.get(position).getChange().contains("-")) {
                            holder.tt.setTextColor(context.getResources().getColor(
                                    R.color.red));
                        } else {
                            holder.tt.setTextColor(context.getResources().getColor(
                                    R.color.green));
                        }
                        holder.tt.setText(homeDataList.get(position).getChange()
                                + " ("
                                + homeDataList.get(position).getPercentChange()
                                + "%)");
                    }

                }
            /*
             * } else { holder.llHeader.setVisibility(View.GONE);
			 * holder.layoutCommonItem.setVisibility(View.GONE); }
			 */

                holder.llHeader.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        switch (homeDataList.get(position).getCategory()) {
                            case 0:
                                ((BaseActivity) context).launchIndiceFragement(
                                        AppConstants.INDICE_INDIAN, url);
                                break;

                            case 1:
                                // ((BaseActivity)context).launchIndiceFragement(AppConstants.INDICE_INDIAN);
                                break;

                            case 2:
                                ((BaseActivity) context).launchFragement(
                                        new StocksIVisitedFragment(), true);
                                break;

                            case 3: {
                                String url = UrlUtils.getUrlMenu(
                                        AppConstants.UNIQUE_ID_MARKET_MOVERS, menulist);
                                Bundle bundle = new Bundle();
                                bundle.putString(AppConstants.SERVER_URL, url);
                                MarketMoverFragment marketMover = new MarketMoverFragment();
                                marketMover.setArguments(bundle);
                                ((BaseActivity) context).launchFragement(marketMover,
                                        true);
                            }
                            break;

                            case 4:

                                ((BaseActivity) context).launchFragement(
                                        new CommodityFragment(), true);
                                break;

                            case 5: {
                                String url = UrlUtils.getUrlMenu(
                                        AppConstants.UNIQUE_ID_CURRIENCES, menulist);
                                Bundle bundle = new Bundle();
                                bundle.putString(AppConstants.SERVER_URL, url);
                                CurrenciesParentFragment marketMover = new CurrenciesParentFragment();
                                marketMover.setArguments(bundle);
                                ((BaseActivity) context).launchFragement(marketMover,
                                        true);
                            }
                            break;

                            default:
                                break;
                        }

                    }
                });

                break;

            case NATIVE_AD:
                break;
        }

        return convertView;
    }

    class ViewHolder {
        public TextView name, lastPrice, change, date, heading, volume, pp, tt;
        public ImageView ivFlag, ivSeparator, ivChange;
        public LinearLayout llHeader;
        public View layoutCommonItem;
        public LinearLayout rlData, portll;
    }

    @Override
    public int getViewTypeCount() {
        // TODO Auto-generated method stub
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        // TODO Auto-generated method stub
        if (position == placeForAd && placeForAd > 0) {
            return NATIVE_AD;
        } else {
            return NORMAl;
            // HomeData homeData = homeDataList.get(position);
            // if (homeData instanceof NativeAdLoader) {
            // return NATIVE_AD;
            // } else {
            // return NORMAl;
        }
    }

}
