package com.moneycontrol.handheld.adapter;

import android.app.Activity;
import android.content.Context;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.divum.MoneyControl.R;
import com.google.android.gms.ads.formats.NativeContentAdView;
import com.moneycontrol.handheld.AppData;
import com.moneycontrol.handheld.entity.news.NewsCategoryData;
import com.moneycontrol.handheld.nativead.ADType;
import com.moneycontrol.handheld.nativead.AppInstallerAdViewHolder;
import com.moneycontrol.handheld.nativead.ContentAdViewHolder;
import com.moneycontrol.handheld.nativead.NativeAdCategory;
import com.moneycontrol.handheld.nativead.NativeAdLoader;
import com.moneycontrol.handheld.util.ImageDownloader;

import java.util.ArrayList;

public class NewsAdapter extends BaseAdapter {

    private ArrayList<NewsCategoryData> newsArray = null;
    private LayoutInflater inflate = null;
    private Activity context = null;

    private final byte NORMAL = 0;
    private final byte NATIVE_AD = 1;
    private ContentAdViewHolder adViewHolder = null;
    private int placeForAd = 0;
    private int subplaceForAd = 0;
    private int newplaceForAd = 0;
    private String nativeAdId = "";
    private String AdType = "";
    private boolean isNativeAd = false;
    NativeAdLoader loader;

    public NewsAdapter(ArrayList<NewsCategoryData> newsArray, Activity con,
                       boolean isNativeAd) {
        inflate = (LayoutInflater) con
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        context = con;
        this.isNativeAd = isNativeAd;
        this.newsArray = newsArray;
        AppData appState = ((AppData) con.getApplicationContext());
        try {
            if (appState != null && appState.getNewsNativeAd() != null) {
                placeForAd = Integer.parseInt(appState.getNewsNativeAd()
                        .getPosition());
                nativeAdId = appState.getNewsNativeAd().getSite_id();
                AdType = appState.getNewsNativeAd().getAd_type();
                subplaceForAd = Integer.parseInt(appState.getNewsNativeAd()
                        .getSubsequent_POS());
            }


            if (this.newsArray.size() > placeForAd) {
                if (!newsArray.get(placeForAd).isAds() && this.isNativeAd
                        && placeForAd > 0) {
//                    addNativeAdObject();
//                    addSubsiquentNativeAdObject();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    private void addNativeAdObject() {
//
//        NewsCategoryData categoryData = new NewsCategoryData();
//        categoryData.setAds(true);
//        newsArray.add(placeForAd, categoryData);
//    }
//
//
//    private void addSubsiquentNativeAdObject() {
//
//
//        for (int i = 0; i < newsArray.size(); i++) {
//            if (i >= subplaceForAd) {
//
//                if (i % subplaceForAd == 0) {
//
//                    NewsCategoryData categoryData = new NewsCategoryData();
//                    categoryData.setAds(true);
//                    newsArray.add(i, categoryData);
//
//                }
//
//            }
//
//        }
//    }


    @Override
    public int getCount() {
        return newsArray.size();
    }

    @Override
    public Object getItem(int arg0) {
        return newsArray.get(arg0);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        int itemType = getItemViewType(position);

        if (convertView == null) {
            holder = new ViewHolder();

            switch (itemType) {
                case NORMAL:
                    convertView = inflate.inflate(R.layout.news_adapter, null);
                    holder.tvNewsTitle = (TextView) convertView
                            .findViewById(R.id.tvNewsTitle);
                    holder.tvNewsTime = (TextView) convertView
                            .findViewById(R.id.tvNewsTime);
                    holder.ivNewsThumb = (ImageView) convertView
                            .findViewById(R.id.ivNewsThumb);
                    holder.ivVedio = (ImageView) convertView
                            .findViewById(R.id.ivVedio);
                    holder.ivPlay = (ImageView) convertView
                            .findViewById(R.id.ivPlay);
                    holder.rlBorderBg = (RelativeLayout) convertView
                            .findViewById(R.id.rl_iv_border_bg);
                    break;

                case NATIVE_AD:

                    convertView = inflate.inflate(
                            R.layout.nativead_app_install_right_logo, null);
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
            case NORMAL:
                holder.rlBorderBg.setBackgroundResource(R.drawable.news_list_item);

                if (newsArray.get(position).getHeadline() != null) {
                    holder.tvNewsTitle.setText(Html.fromHtml(newsArray
                            .get(position).getHeadline()));
                }

                if (newsArray.get(position).getCreationtime() != null) {
                    holder.tvNewsTime.setVisibility(View.VISIBLE);
                    holder.tvNewsTime.setText(newsArray.get(position)
                            .getCreationtime());

                } else {
                    holder.tvNewsTime.setVisibility(View.GONE);
                }

                new ImageDownloader().download(newsArray.get(position)
                        .getThumbnail(), holder.ivNewsThumb);

                if (newsArray.get(position).getStory_type()
                        || newsArray.get(position).getIsVedio()) {

                    holder.ivVedio.setVisibility(View.VISIBLE);
                    holder.ivPlay.setVisibility(View.VISIBLE);

                } else {
                    holder.ivVedio.setVisibility(View.GONE);
                    if (holder.ivPlay != null) {
                        holder.ivPlay.setVisibility(View.GONE);
                    }
                }
                break;

            default:
                Log.v("rht", "Nothing to set news for the position " + position);
                break;
        }

        return convertView;
    }

    class ViewHolder {
        public TextView tvNewsTitle, tvNewsTime;
        public ImageView ivNewsThumb;
        public ImageView ivVedio;
        public ImageView ivPlay;
        public RelativeLayout rlBorderBg;
    }

    @Override
    public int getViewTypeCount() {
        // TODO Auto-generated method stub
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        // TODO Auto-generated method stub
        if (newsArray.get(position).isAds()) {
            return NATIVE_AD;
        } else {
            return NORMAL;
        }
    }

}
