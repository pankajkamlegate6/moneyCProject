package com.moneycontrol.handheld.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.Html;
import android.text.Html.ImageGetter;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.divum.MoneyControl.R;
import com.moneycontrol.handheld.AppData;
import com.moneycontrol.handheld.BaseActivity;
import com.moneycontrol.handheld.anaylatics.AnalyticsTag;
import com.moneycontrol.handheld.api.AppBeanParacable;
import com.moneycontrol.handheld.api.RequestType;
import com.moneycontrol.handheld.constant.AppConstants;
import com.moneycontrol.handheld.entity.messages.LastVisitedListBean;
import com.moneycontrol.handheld.entity.messages.LastVisitedListData;
import com.moneycontrol.handheld.entity.messages.MessageCategoryItemData;
import com.moneycontrol.handheld.entity.news.NewsCategoryData;
import com.moneycontrol.handheld.fragments.CommodityDetailFragment;
import com.moneycontrol.handheld.fragments.NewsPagerFragment;
import com.moneycontrol.handheld.fragments.NotificationDialogFragment;
import com.moneycontrol.handheld.fragments.StockDetailFragment;
import com.moneycontrol.handheld.imageUtils.ImageDownloaderUtils;
import com.moneycontrol.handheld.login.MessagesLogin;
import com.moneycontrol.handheld.massages.fragments.BaseFragement;
import com.moneycontrol.handheld.massages.fragments.MyThreadMessage;
import com.moneycontrol.handheld.massages.fragments.PostMessage;
import com.moneycontrol.handheld.nativead.ADType;
import com.moneycontrol.handheld.nativead.AppInstallerAdViewHolder;
import com.moneycontrol.handheld.nativead.NativeAdCategory;
import com.moneycontrol.handheld.nativead.NativeAdLoader;
import com.moneycontrol.handheld.nativead.onAdLoadListiners;
import com.moneycontrol.handheld.parser.ParseCall;
import com.moneycontrol.handheld.util.ClickSpan;
import com.moneycontrol.handheld.util.Constantlibs;
import com.moneycontrol.handheld.util.TaskCompleteListiners;
import com.moneycontrol.handheld.util.Utility;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class MyMessageRepliesAdapter extends BaseAdapter implements
        TaskCompleteListiners, onAdLoadListiners {

    private Context mContext;
    private LayoutInflater layoutInflater;
    private ArrayList<MessageCategoryItemData> list;
    private String reco_type_html = "<font color=#66CC00><b> ";

    private String msg_type_html = "<font color=#FFFFFF><b>";
    private String disable_font_color = "<font color=#cccccc><b>%s</b></font>";

    private String enable_font_color = "<font color=#FF9A34><b>%s</b></font> ";
    private String buy_reco_type_html = "<font color=#518F3A><b> ";
    private String sell_reco_type_html = "<font color=#FF0000><b> ";
    private String hold_reco_type_html = "<font color=#5A5A5A><b> ";
    private String news_type_html = "<font color= #999999><b>";
    String normalhtmlString = "<font color=#FFFFFF><b>%s</b></font>";
    private String news_side_drawablehtml = "<img src=\"latest_arrow.png\" align=\"justify\"/>";
    private Handler handler = null, handlerShare = null;
    private ArrayList<RelativeLayout> ratedlayouts = null;
    private String replyTxt = null, response = null;
    private boolean clicked = false;
    private String lastScreenName = "";
    private String thisScreenTag = "";
    private String keys;

    private final byte NORMAL = 0;
    private final byte NATIVE_AD = 1;
    private AppInstallerAdViewHolder adViewHolder = null;
    private int placeForAd = 0;
    private String nativeAdId = "";
    public HashMap<String, String> extraLinks = null;
    private boolean isNativeAd = false;

    public String KEY_SHARE_NEWS_SUBJECT = "<body><p>%heading %news_headline</p></body>";
    public String KEY_SHARE_NEWS_GMAIL = "<p>%summary_text</p>";
    private NativeAdLoader loader;

    public MyMessageRepliesAdapter(Context mContext,
                                   ArrayList<MessageCategoryItemData> list, boolean Clicked,
                                   String name, boolean isNativeAd) {
        // TODO Auto-generated constructor stub
        this.mContext = mContext;
        layoutInflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.list = list;
        ratedlayouts = new ArrayList<RelativeLayout>();
        clicked = Clicked;
        lastScreenName = name;
        thisScreenTag = ((BaseActivity) mContext).getCurrentFragmentTagName();
        Log.i("String value :", lastScreenName + "///"
                + Utility.MESSAGELASTPAGE);
        AppData appState = ((AppData) mContext.getApplicationContext());
        extraLinks = appState.getExtra_url();
        this.isNativeAd = isNativeAd;
        try {
            if (appState != null && appState.getMessageNativeAd() != null) {
                placeForAd = Integer.parseInt(appState.getMessageNativeAd()
                        .getPosition());
                nativeAdId = appState.getMessageNativeAd().getSite_id();
            }
            Log.v("aman", "placeForAd is:" + placeForAd);
            Log.v("aman", "nativeAdId is:" + nativeAdId);

            if (Utility.MESSAGEOPENSECTION.equalsIgnoreCase("NEWS")) {
                this.isNativeAd = false;
                placeForAd = 0;

            }
            if (this.list.size() > placeForAd && placeForAd > 0
                    && this.isNativeAd) {
                addNativeAdObject();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void addNativeAdObject() {
        if (!list.get(placeForAd).isAds() && placeForAd > 0) {
            MessageCategoryItemData data = new MessageCategoryItemData();
            data.setAds(true);
            list.add(placeForAd, data);

        }
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list.size();
    }

    @Override
    public MessageCategoryItemData getItem(int position) {
        // TODO Auto-generated method stub
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        final int pos = position;
        boolean val = false;
        int itemType = getItemViewType(position);
        final MessageCategoryItemData massageData = (MessageCategoryItemData) getItem(position);
        // rateBoxState[pos] = false;
        if (convertView == null) {

            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            switch (itemType) {
                case NORMAL:
                    convertView = inflater.inflate(R.layout.latest_massage_layout,
                            null);

                    holder.user_icon = (ImageView) convertView
                            .findViewById(R.id.user_icon);
                    holder.tvuserNickName = (TextView) convertView
                            .findViewById(R.id.user_nick_name);

                    holder.userMembertypIcon = (ImageView) convertView
                            .findViewById(R.id.user_membertyp_icon);
                    holder.userMembertypText = (TextView) convertView
                            .findViewById(R.id.user_membertyp_text);

                    holder.tvMassagePosttime = (TextView) convertView
                            .findViewById(R.id.tvmassage_post_time);

                    holder.tvmassageTopicType = (TextView) convertView
                            .findViewById(R.id.tvmassage_topic_type);

                    holder.usermsgTxt = (TextView) convertView
                            .findViewById(R.id.user_psot_msg_typeAnd_msg_txt);

                    holder.lastpriceTxt = (TextView) convertView
                            .findViewById(R.id.msg_last_price_txt);

                    holder.totalNoPosts = (TextView) convertView
                            .findViewById(R.id.total_no_posts);

                    holder.totalNoReposts = (TextView) convertView
                            .findViewById(R.id.total_no_reposts);

                    holder.postandreplayBtn = (ImageView) convertView
                            .findViewById(R.id.postandreplay_btn);

                    holder.gaveRatingBtn = (RelativeLayout) convertView
                            .findViewById(R.id.gave_rating_btn);
                    holder.repostamassageBtn = (ImageView) convertView
                            .findViewById(R.id.repost_massage_btn);
                    holder.markoffensiveBtn = (ImageView) convertView
                            .findViewById(R.id.offensive_massage_btn);
                    holder.postreplyshareBtn = (ImageView) convertView
                            .findViewById(R.id.share_massage_btn);

                    holder.postandreplayll = (LinearLayout) convertView
                            .findViewById(R.id.postandreplay_ll);

                    holder.ratethismassagell = (RelativeLayout) convertView
                            .findViewById(R.id.ratethismassage);

                    holder.gave_rating_gray = (ImageView) convertView
                            .findViewById(R.id.gave_rating_gray);
                    holder.gave_rating_white = (ImageView) convertView
                            .findViewById(R.id.gave_rating_white);

                    holder.ratesubmit = (Button) convertView
                            .findViewById(R.id.Submit_rating_btn);

                    holder.repostmsg_layout = (LinearLayout) convertView
                            .findViewById(R.id.repostmsg_layout);

                    holder.ratedBy = (TextView) convertView
                            .findViewById(R.id.massage_rating_text);
                    holder.ratedByDummy = (TextView) convertView
                            .findViewById(R.id.massage_rating_text_dummy);

                    holder.ratell = (LinearLayout) convertView
                            .findViewById(R.id.row_third);

                    holder.coloum1 = (ImageView) convertView
                            .findViewById(R.id.fisrtcoloumnstrip);
                    holder.coloum2 = (ImageView) convertView
                            .findViewById(R.id.secondcoloumnstrip);
                    holder.coloum3 = (ImageView) convertView
                            .findViewById(R.id.thirdcoloumnstrip);
                    holder.reposted_response_txt = (TextView) convertView
                            .findViewById(R.id.repost_response_txt);

                    holder.ratethismassagell.setTag(new Integer(position));
                    holder.tvmassageTopicType.setTag(new Integer(position));
                    holder.postandreplayll.setTag(new Integer(position));

                    holder.repostmsg_layout.setTag(new Integer(position));
                    holder.ratell.setTag(new Integer(position));
                    break;

                case NATIVE_AD:
                    convertView = inflater.inflate(R.layout.nativead_app_install,
                            null);
                    if (loader == null || loader.contentInstallAd != null) {
                        adViewHolder = new AppInstallerAdViewHolder(convertView, NativeAdCategory.APP_INSTALL_RIGHT_LOGO);
                        loader = new NativeAdLoader(nativeAdId,
                                NativeAdCategory.APP_INSTALL_RIGHT_LOGO,
                                ADType.APP_INSTALL_AD);
                        loader.loadAd(mContext, null, adViewHolder, "");
                    } else {
                        adViewHolder = new AppInstallerAdViewHolder(convertView, NativeAdCategory.APP_INSTALL_RIGHT_LOGO);
                        adViewHolder.populateListItem(adViewHolder,
                                loader.contentInstallAd,
                                NativeAdCategory.APP_INSTALL_RIGHT_LOGO);
                    }
                    break;

            }
            convertView.setTag(holder);

        } else

        {

            holder = (ViewHolder) convertView.getTag();
        }

        switch (itemType) {
            case NATIVE_AD:
                return convertView;

        }

        holder.gaveRatingBtn.setTag(position);
        holder.repostamassageBtn.setTag(position);
        holder.gave_rating_gray.setTag(position);
        holder.gave_rating_white.setTag(position);
        holder.totalNoPosts.setTag(position);
        holder.markoffensiveBtn.setTag(position);
        holder.usermsgTxt.setTag(position);
        holder.postreplyshareBtn.setTag(position);
        holder.totalNoReposts.setTag(position);
        holder.tvmassageTopicType.setTag(position);

        holder.ratedBy.setTag(position);
        holder.ratedByDummy.setTag(position);
        holder.ratell.setTag(new Integer(position));
        holder.postandreplayll.setTag(new Integer(position));
        holder.postandreplayll.setOnClickListener(listenerforClicked);

        holder.ratedBy.setOnClickListener(listenerforClicked);
        holder.ratedByDummy.setOnClickListener(listenerforClicked);

        holder.totalNoPosts.setOnClickListener(listenerforClicked);
        holder.gaveRatingBtn.setOnClickListener(listenerforClicked);
        holder.repostamassageBtn.setOnClickListener(listenerforClicked);

        holder.markoffensiveBtn.setOnClickListener(listenerforClicked);
        holder.ratethismassagell.setTag(new Integer(position));

        holder.postreplyshareBtn.setOnClickListener(listenerforClicked);

        holder.tvmassageTopicType.setOnClickListener(listenerforClicked);
        holder.usermsgTxt.setOnClickListener(listenerforClicked);
        holder.totalNoReposts.setOnClickListener(listenerforClicked);

        holder.ref = position;

        holder.tvuserNickName
                .setTag(R.string.about_us, massageData.getUserid());
        holder.user_icon.setTag(massageData.getUserid());
        holder.tvuserNickName.setTag(R.string.txt_boaders, true);
        holder.userMembertypIcon
                .setImageResource(Utility.getMemberTypeImage(mContext,
                        massageData.getMembertype(), holder.userMembertypIcon));
        ratedlayouts.add(holder.ratethismassagell);

        if (list.get(position).isOpenRate()) {
            holder.ratethismassagell.setVisibility(View.VISIBLE);
            holder.gave_rating_gray.setVisibility(View.GONE);
            holder.gave_rating_white.setVisibility(View.VISIBLE);

        } else {
            holder.ratethismassagell.setVisibility(View.GONE);
            holder.gave_rating_gray.setVisibility(View.VISIBLE);
            holder.gave_rating_white.setVisibility(View.GONE);

        }

        if (!TextUtils.isEmpty(massageData.getRepost_entdate())) {
            holder.reposted_response_txt.setText(mContext.getResources()
                    .getText(R.string.reposted)
                    + " "
                    + massageData.getRepost_entdate()
                    + " "
                    + mContext.getResources().getText(R.string.by)
                    + " "
                    + massageData.getRepost_user());
            holder.repostmsg_layout.setVisibility(View.VISIBLE);
            // massageData.setIsrepost(true);
        } else {
            holder.repostmsg_layout.setVisibility(View.GONE);
        }
        if (massageData.isIsrepost()) {
            holder.repostamassageBtn
                    .setImageResource(R.drawable.post_reply_sent_white);

        } else {

            holder.repostamassageBtn
                    .setImageResource(R.drawable.post_reply_sent_grey);
        }

        if (massageData.getTopic() != null)
            holder.tvmassageTopicType.setText(massageData.getTopic());
        if (massageData.getUsernickname() != null)
            holder.tvuserNickName.setText(massageData.getUsernickname());
        if (massageData.getMembertype() != null)
            holder.userMembertypText.setText(massageData.getMembertype());
        if (massageData.getTimestamp() != null)
            holder.tvMassagePosttime.setText(massageData.getTimestamp());

        if (!TextUtils.isEmpty(massageData.getLastprice())) {

            if (massageData.getLastprice().contains("%")) {
                // massageData.setLastprice(massageData.getLastprice()
                // .replace("%", ""));
            }
            holder.lastpriceTxt.setText(massageData.getLastprice());

        } else {
            holder.lastpriceTxt.setVisibility(View.GONE);
        }

        String msgText = "";
        String message = "";
        if (massageData.getMsg_type() != null) {
            if (massageData.getMsg_type().equalsIgnoreCase("news")) {
                message = massageData.getMessage();
            } else {
                message = massageData.getMessage();
            }
        }

        if (!lastScreenName.equalsIgnoreCase("ThreadMessagePage")) {
            if (massageData.getMsg_type() != null) {
                if (message.trim().length() < 200) {
                    if (massageData.getMsg_type().equalsIgnoreCase("news")) {

                        msgText = news_type_html
                                + " "
                                + massageData.getMsg_type().toUpperCase()
                                + "</b></font>"
                                + " "
                                + news_side_drawablehtml
                                + " "
                                + msg_type_html
                                + message
                                + ". "
                                + mContext
                                .getString(R.string.have_an_opinion_on_this)
                                // + " Have an opinion on this?"
                                + " "
                                + mContext.getString(R.string.read_full_story)
                                + "</b></font>";

                    } else if (!TextUtils.isEmpty(massageData.getReco_status())) {

                        msgText = getMessageStatusString(massageData);

                    } else if (massageData.getMsg_type().equalsIgnoreCase("msg")) {

                        msgText = msg_type_html + message + "</b></font>";

                    }
                } else {
                    if (massageData.getMsg_type().equalsIgnoreCase("news")) {

                        msgText = news_type_html + " "
                                + massageData.getMsg_type().toUpperCase()
                                + "</b></font>" + " " + news_side_drawablehtml
                                + " " + msg_type_html + message + "..." + " "
                                + mContext.getString(R.string.see_more)
                                + "</b></font>";

                    } else if (!TextUtils.isEmpty(massageData.getReco_status())) {

                        msgText = getMessageStatusString(massageData);

                    } else if (massageData.getMsg_type().equalsIgnoreCase("msg")) {

                        msgText = msg_type_html + message + "..." + " "
                                + mContext.getString(R.string.see_more)
                                + "</b></font>";

                    }
                }

                if (massageData.getMsg_type().equalsIgnoreCase("news")) {

                    holder.usermsgTxt.setText(Html.fromHtml(msgText, imgGetter,
                            null));
                } else {
                    holder.usermsgTxt.setText(Html.fromHtml(msgText));
                }

                final MessageCategoryItemData obj = massageData;
                if (!lastScreenName.equalsIgnoreCase("ThreadMessagePage")) {
                    Utility.getInstance().clickify(holder.usermsgTxt,
                            " " + mContext.getString(R.string.see_more),
                            new ClickSpan.OnClickListener() {
                                @Override
                                public void onClick() {

                                    if (obj.getReply() != null) {
                                        // if (Integer.parseInt(obj.getReply())
                                        // > 0)
                                        // {
                                        if (!massageData.getMsg_type()
                                                .equalsIgnoreCase("news")) {
                                            launchThreadScreen(obj);
                                        }

                                        // }
                                    }

                                }
                            }, R.color.grey);
                }

                Utility.getInstance().clickify(holder.usermsgTxt,
                        mContext.getString(R.string.read_full_story),
                        new ClickSpan.OnClickListener() {
                            @Override
                            public void onClick() {
                                // Utility.MESSAGEOPENSECTION = "NEWS";
                                // launchNewsScreen(obj);

                            }
                        }, R.color.orange);
            }
        } else {

            if (massageData.getMsg_type().equalsIgnoreCase("news")) {

                msgText = news_type_html + " "
                        + massageData.getMsg_type().toUpperCase()
                        + "</b></font>" + " " + news_side_drawablehtml + " "
                        + msg_type_html + message + ". "
                        + mContext.getString(R.string.have_an_opinion_on_this)
                        // + " Have an opinion on this?"
                        + " " + mContext.getString(R.string.read_full_story)
                        + "</b></font>";

            } else if (!TextUtils.isEmpty(massageData.getReco_status())) {

                msgText = getMessageStatusString(massageData);

            } else if (massageData.getMsg_type().equalsIgnoreCase("msg")) {

                msgText = msg_type_html + message + "</b></font>";

            }

            if (massageData.getMsg_type().equalsIgnoreCase("news")) {

                holder.usermsgTxt.setText(Html.fromHtml(msgText, imgGetter,
                        null));
            } else {
                holder.usermsgTxt.setText(Html.fromHtml(msgText));
            }

            final MessageCategoryItemData obj = massageData;

            Utility.getInstance().clickify(holder.usermsgTxt,
                    mContext.getString(R.string.read_full_story),
                    new ClickSpan.OnClickListener() {
                        @Override
                        public void onClick() {
                            // Utility.MESSAGEOPENSECTION = "NEWS";
                            // launchNewsScreen(obj);

                        }
                    }, R.color.orange);

        }
        if (massageData.getReply() != null)
            if (massageData.getReply().equalsIgnoreCase("0")
                    || TextUtils.isEmpty(massageData.getReply())) {
                holder.totalNoPosts.setVisibility(View.GONE);

            } else {
                holder.totalNoPosts.setVisibility(View.VISIBLE);

            }

        if (massageData.getRating_star() != null)
            if (massageData.getRating_star().equalsIgnoreCase("0")
                    || TextUtils.isEmpty(massageData.getRating_star())) {
                holder.ratedBy.setVisibility(View.GONE);
                holder.ratedByDummy.setVisibility(View.GONE);

            } else {
                holder.ratedBy.setVisibility(View.VISIBLE);
                holder.ratedByDummy.setVisibility(View.VISIBLE);

            }

        if (massageData.getRepost_count() != null)
            if (massageData.getRepost_count().equalsIgnoreCase("0")
                    || TextUtils.isEmpty(massageData.getRepost_count())) {
                holder.totalNoReposts.setVisibility(View.GONE);

            } else {
                holder.totalNoReposts.setVisibility(View.VISIBLE);

            }

        if (massageData.getRating_star() != null) {

            float f = Float.parseFloat(massageData.getRating_star());

            if ((int) f >= 1) {

                String htmlString = ratingBar(massageData.getRating_star());
                Spanned htmlSpan = Html.fromHtml(
                        htmlString != null ? htmlString : "", rateimgGetter,
                        null);
                holder.ratedBy.setVisibility(View.VISIBLE);
                holder.ratedByDummy.setVisibility(View.VISIBLE);
                holder.ratedBy.setText(htmlSpan);

            } else {

                holder.ratedBy.setVisibility(View.GONE);
                holder.ratedByDummy.setVisibility(View.GONE);

            }

        }
        if (massageData.getReply() != null) {

            if (Integer.parseInt(massageData.getReply()) > 0) {
                holder.totalNoPosts.setText(massageData.getReply() + " "
                        + mContext.getString(R.string.posts));
                holder.totalNoReposts.setVisibility(View.VISIBLE);
                val = true;
            } else {

                holder.totalNoPosts.setVisibility(View.GONE);
                val = false;
            }

        } else {
            holder.totalNoPosts.setVisibility(View.GONE);
            val = false;
        }

        if (massageData.getRepost_count() != null) {

            if (Integer.parseInt(massageData.getRepost_count()) > 0) {
                holder.totalNoReposts.setText(massageData.getRepost_count()
                        + " " + mContext.getString(R.string.reposts));
                holder.totalNoReposts.setVisibility(View.VISIBLE);

            } else {

                holder.totalNoReposts.setVisibility(View.GONE);
                // massageData.setIsrepost(false);

            }

        } else {
            holder.totalNoReposts.setVisibility(View.GONE);
            massageData.setIsrepost(false);
        }

        if (massageData.getReply() != null
                && massageData.getRating_star() != null
                && massageData.getRepost_count() != null)
            if (massageData.getReply().equalsIgnoreCase("0")
                    && massageData.getRating_star().equalsIgnoreCase("0")
                    && massageData.getRepost_count().equalsIgnoreCase("0")) {
                holder.ratell.setVisibility(View.GONE);
            } else {
                holder.ratell.setVisibility(View.VISIBLE);
            }

        if (!TextUtils.isEmpty(massageData.getReply())
                && !massageData.getReply().equalsIgnoreCase("0")
                && !TextUtils.isEmpty(massageData.getRating_star())
                && !massageData.getRating_star().equalsIgnoreCase("0")) {

            holder.coloum1.setVisibility(View.VISIBLE);

        } else {
            holder.coloum1.setVisibility(View.GONE);
        }

        if (!TextUtils.isEmpty(massageData.getRating_star())
                && !massageData.getRating_star().equalsIgnoreCase("0")
                && !TextUtils.isEmpty(massageData.getRepost_count())
                && !massageData.getRepost_count().equalsIgnoreCase("0")) {

            holder.coloum2.setVisibility(View.VISIBLE);

        } else {
            holder.coloum2.setVisibility(View.GONE);
        }

        if (!TextUtils.isEmpty(massageData.getReply())
                && !massageData.getReply().equalsIgnoreCase("0")
                && !TextUtils.isEmpty(massageData.getRepost_count())
                && !massageData.getRepost_count().equalsIgnoreCase("0")) {

            holder.coloum3.setVisibility(View.VISIBLE);

        } else {
            holder.coloum3.setVisibility(View.GONE);
        }

        if (massageData.getUserimg() != null
                && !massageData.getUsernickname().equalsIgnoreCase("guest"))
            ImageLoader.getInstance().displayImage(massageData.getUserimg(),
                    holder.user_icon,
                    ImageDownloaderUtils.displayImageOptionsNormal());
        else
            holder.user_icon.setImageDrawable(mContext.getResources()
                    .getDrawable(R.drawable.usr_pic));

        convertView.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                holder.totalNoPosts.performClick();

            }
        });

        Utility.identifyUrl(holder.usermsgTxt);
        holder.usermsgTxt.setClickable(true);
        holder.usermsgTxt
                .setMovementMethod(com.moneycontrol.handheld.api.LinkMovementMethod
                        .getInstance(mContext));
        doRate(convertView, position);
        return convertView;
    }

    @Override
    public int getViewTypeCount() {
        // TODO Auto-generated method stub
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        // TODO Auto-generated method stub
        if (position == placeForAd && placeForAd > 0 && isNativeAd) {
            return NATIVE_AD;
        } else {
            return NORMAL;
        }
    }

    class ViewHolder {

        TextView tvuserNickName, userMembertypText, tvMassagePosttime,
                tvmassageTopicType, lastpriceTxt, usermsgTxt, totalNoPosts,
                ratedBy, ratedByDummy;
        TextView massageRatingText, totalNoReposts, reposted_response_txt;
        ImageView user_icon, userMembertypIcon, postandreplayBtn,
                repostamassageBtn, markoffensiveBtn, gave_rating_gray,
                gave_rating_white, postreplyshareBtn;
        LinearLayout postandreplayll, repostmsg_layout, ratell;
        RelativeLayout ratethismassagell, gaveRatingBtn;
        int ref;

        ImageView coloum1, coloum2, coloum3;

        Button ratesubmit;

    }

    private ImageGetter rateimgGetter = new ImageGetter() {

        public Drawable getDrawable(String source) {
            Drawable drawable = null;

            drawable = mContext.getResources().getDrawable(R.drawable.rated_by);

            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
                    drawable.getIntrinsicHeight());
            return drawable;
        }
    };

    public String ratingBar(String norate) {
        String rating_string = "";
        double rating_star = Double.valueOf(norate);
        for (int i = 0; i < rating_star; i++) {
            rating_string = rating_string
                    + "<img src='file:///res/drawable/rated_by.png'/>";
        }

        String htmlString = rating_string + "<font>%s</font>&nbsp;";

        rating_string = String.format(htmlString, "");
        return rating_string;
    }

    private OnClickListener listenerforClicked = new OnClickListener() {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub

            final int id = (Integer) v.getTag();

            final MessageCategoryItemData msgObj = list.get(id);
            final BaseFragement frag = (BaseFragement) ((BaseActivity) mContext)
                    .getCurrentfragment(((BaseActivity) mContext)
                            .getCurrentFragmentName());

            switch (v.getId()) {

                case R.id.massage_rating_text_dummy:
                case R.id.massage_rating_text:

                    if (list.get(id).getRating_star() != null) {

                        frag.findwhoareRatethisMessages(id, msgObj,
                                MyMessageRepliesAdapter.this);

                    }

                    break;

                case R.id.total_no_reposts:

                    if (list.get(id).getRepost_count() != null) {
                        frag.findwhoareRepostedthisMessages(id, msgObj,
                                MyMessageRepliesAdapter.this);
                    }

                    break;
                case R.id.postandreplay_ll:

				/* For replay_massage_btn Massages */

                    if (ParseCall.getInstance().isLoggedIn(mContext)) {

                        frag.showpostReply(msgObj, false, "reply");
                        Utility.addGoogleEvents(mContext, AnalyticsTag.MESSAGES,
                                AnalyticsTag.REPLY);
                    } else {
                        frag.showLoginScreen(id, 0, true, false, false, msgObj,
                                lastScreenName);

                    }

                    break;

                case R.id.gave_rating_btn:

				/* For Rate_massage_btn Massages */

                    if (ParseCall.getInstance().isLoggedIn(mContext)) {
                        ratePopUp(id);
                        Utility.addGoogleEvents(mContext, AnalyticsTag.MESSAGES,
                                AnalyticsTag.RATE);
                    } else {

                        frag.addLoginAlert(id, 1, false, true, false, msgObj,
                                lastScreenName);

                    }

                    break;

                case R.id.repost_massage_btn:

				/* For Repost Massages */

                    if (ParseCall.getInstance().isLoggedIn(mContext)) {
                        // repostmsg(id);
                        repostmsg(id, msgObj);
                        Utility.addGoogleEvents(mContext, AnalyticsTag.MESSAGES,
                                AnalyticsTag.REPOST);
                    } else {

                        frag.addLoginAlert(id, 2, false, false, true, msgObj,
                                lastScreenName);

                    }

                    break;

                case R.id.offensive_massage_btn:

				/* For offensive_massage_btn Massages */

                    if (ParseCall.getInstance().isLoggedIn(mContext)) {
                        // markOffensive(id);
                        markthisMessageAsOffensive((BaseActivity) mContext, id);
                        Utility.addGoogleEvents(mContext, AnalyticsTag.MESSAGES,
                                AnalyticsTag.REPORT);
                    } else {

                        frag.addLoginAlert(id, 3, false, false, true, msgObj,
                                lastScreenName);

                    }

                    break;

                case R.id.share_massage_btn:

				/* For Share_massage_btn Massages */
                    Utility.addGoogleEvents(mContext, AnalyticsTag.MESSAGES,
                            AnalyticsTag.SHARE);

                    KEY_SHARE_NEWS_SUBJECT = KEY_SHARE_NEWS_SUBJECT.replace(
                            "%heading", Utility.getKeysPrefrence(mContext,
                                    AppConstants.KEY_SHARE_MMB_TITLE));
                    KEY_SHARE_NEWS_SUBJECT = KEY_SHARE_NEWS_SUBJECT.replace(
                            "%news_headline", list.get(id).getHeading());
                    String subjectString = Html.fromHtml(KEY_SHARE_NEWS_SUBJECT)
                            .toString().trim();
                    // KEY_SHARE_NEWS_GMAIL = KEY_SHARE_NEWS_GMAIL.replace(
                    // "%summary_text",
                    // Utility.getKeysPrefrence(mContext,
                    // AppConstants.KEY_SHARE_SUMM_TITLE)
                    // + list.get(id).getMessage());
                    // String content =
                    // Html.fromHtml(KEY_SHARE_NEWS_GMAIL).toString();

                    Utility.getInstance().shareArticleV2(mContext, subjectString,
                            list.get(id).getMessage(), "");

                    break;

                case R.id.tvmassage_topic_type:

				/* For open Topic page */

                    if ((!lastScreenName.equalsIgnoreCase("ThreadMessagePage") && (!lastScreenName
                            .equalsIgnoreCase(Utility.MESSAGELASTPAGE)))
                            && (!lastScreenName
                            .equalsIgnoreCase(StockDetailFragment.class
                                    .getSimpleName()))
                            && (!lastScreenName
                            .equalsIgnoreCase(CommodityDetailFragment.class
                                    .getSimpleName()))) {

                        frag.launchTopicScreen(list.get(id), lastScreenName);

                    } else if (Utility.MESSAGEOPENSECTION.equalsIgnoreCase("NEWS")) {
                        frag.launchTopicScreen(msgObj, lastScreenName);
                    }

                    break;

                case R.id.user_psot_msg_typeAnd_msg_txt:

                    if (!list.get(id).getMsg_type().equalsIgnoreCase("news")) {
                        if (!lastScreenName.equalsIgnoreCase("ThreadMessagePage")
                                && !TextUtils.isEmpty(list.get(id).getTopic())) {
                            launchThreadScreen(list.get(id));
                        }
                    } else {
                        Utility.MESSAGEOPENSECTION = "NEWS";
                        launchNewsScreen(list.get(id));
                    }

                    break;
                case R.id.total_no_posts:
                    if (!lastScreenName.equalsIgnoreCase("ThreadMessagePage")
                            && !TextUtils.isEmpty(list.get(id).getTopic())) {
                        frag.launchThreadScreen(msgObj);
                    }

                    break;

                default:
                    break;
            }

        }
    };

    private String getMessageStatusString(MessageCategoryItemData massageData) {

        String msgText = "";
        if (massageData.getMessage().trim().length() < 200) {

            if (massageData.getReco_status().equalsIgnoreCase("BUY RECO")) {
                msgText = buy_reco_type_html + " "
                        + massageData.getReco_status().toUpperCase()
                        + "</b></font>" + " " + msg_type_html
                        + massageData.getMessage() + "</b></font>";

            } else if (massageData.getReco_status().equalsIgnoreCase(
                    "SELL RECO")) {
                msgText = sell_reco_type_html + " "
                        + massageData.getReco_status().toUpperCase()
                        + "</b></font>" + " " + msg_type_html
                        + massageData.getMessage() + "</b></font>";

            } else if (massageData.getReco_status().equalsIgnoreCase(
                    "HOLD RECO")) {
                msgText = hold_reco_type_html + " "
                        + massageData.getReco_status().toUpperCase()
                        + "</b></font>" + " " + msg_type_html
                        + massageData.getMessage() + "</b></font>";

            }

        } else {

            if (massageData.getReco_status().equalsIgnoreCase("BUY RECO")) {
                msgText = buy_reco_type_html + " "
                        + massageData.getReco_status().toUpperCase()
                        + "</b></font>" + " " + msg_type_html
                        + massageData.getMessage() + "..." + " See more"
                        + "</b></font>";

            } else if (massageData.getReco_status().equalsIgnoreCase(
                    "SELL RECO")) {
                msgText = sell_reco_type_html + " "
                        + massageData.getReco_status().toUpperCase()
                        + "</b></font>" + " " + msg_type_html
                        + massageData.getMessage() + "..." + " See more"
                        + "</b></font>";

            } else if (massageData.getReco_status().equalsIgnoreCase(
                    "HOLD RECO")) {
                msgText = hold_reco_type_html + " "
                        + massageData.getReco_status().toUpperCase()
                        + "</b></font>" + " " + msg_type_html
                        + massageData.getMessage() + "..." + " See more"
                        + "</b></font>";

            }
        }
        return msgText;

    }

    public void launchNewsScreen(MessageCategoryItemData msgObject) {

        ArrayList<NewsCategoryData> newsList = new ArrayList<NewsCategoryData>();
        NewsCategoryData newsobj = new NewsCategoryData();
        newsobj.setStory_id(msgObject.getNews_autono());
        newsobj.setHeadline("NEWS");
        newsList.add(newsobj);

        Bundle bundle = new Bundle();
        bundle.putInt(AppConstants.POSITION, 0);
        bundle.putSerializable(AppConstants.KEY_NEWS_DATA, newsList);
        bundle.putString(AppConstants.KEY_NEWS_TITLE, "NEWS");
        bundle.putString(AppConstants.KEY_NEWS_STORY_ID,
                msgObject.getNews_autono());
        bundle.putString(AppConstants.KEY_NEWS_SECTION, "rank");
        bundle.putBoolean("IsParent", false);
        bundle.putInt("ClickedPosotion", 0);
        Fragment newsPagerFragment = new NewsPagerFragment();
        newsPagerFragment.setArguments(bundle);
        ((BaseActivity) mContext).launchFragement(newsPagerFragment, true);

    }

    private void launchTopicScreen(MessageCategoryItemData msgObject) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(AppConstants.SERIALIZABLE_OBJECT, msgObject);
        bundle.putString(AppConstants.THREAD_ID, msgObject.getThread_id());
        bundle.putString(AppConstants.TOPIC_ID, msgObject.getTopic_id());
        bundle.putString("replyCount", msgObject.getReply());
        bundle.putString("topicName", msgObject.getTopic());

        MyThreadMessage threadFragment = new MyThreadMessage();
        threadFragment.setArguments(bundle);

        ((BaseActivity) mContext).launchFragement(threadFragment, true);

        // Utility.LASTVISITEDTHREADID = msgObject.getThread_id();
    }

    private void launchThreadScreen(MessageCategoryItemData msgObject) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(AppConstants.SERIALIZABLE_OBJECT, msgObject);
        bundle.putString(AppConstants.THREAD_ID, msgObject.getThread_id());
        bundle.putString(AppConstants.TOPIC_ID, msgObject.getTopic_id());
        bundle.putString("replyCount", msgObject.getReply());
        bundle.putString("topicName", msgObject.getTopic());

        MyThreadMessage threadFragment = new MyThreadMessage();
        threadFragment.setArguments(bundle);

        ((BaseActivity) mContext).launchFragement(threadFragment, true);

        // Utility.LASTVISITEDTHREADID = msgObject.getThread_id();
    }

    private void addLoginAlert(int item, int c, boolean r, boolean iso,
                               boolean rate) {

        final int ClickedReq = item;
        final int option = c;

        final boolean isreplay = r;
        final boolean isoffensive = iso;
        final boolean israted = rate;

        String msg = Utility.getInstance().setShowInternetConnection(mContext,
                R.string.login_alert, R.string.login_alert,
                R.string.login_alert);
        String title = Utility.getInstance().setShowInternetConnection(
                mContext, R.string.status, R.string.status, R.string.status);
        String ok = Utility.getInstance().setShowInternetConnection(mContext,
                R.string.ok, R.string.ok, R.string.ok);
        String cancel = Utility.getInstance().setShowInternetConnection(
                mContext, R.string.cancel, R.string.cancel, R.string.cancel);

        // AlertDialog alertDialog = new AlertDialog.Builder(mContext)
        // .setTitle(title).setMessage(msg)
        // .setPositiveButton(ok, new DialogInterface.OnClickListener() {
        // @Override
        // public void onClick(DialogInterface dialog, int which) {
        // Bundle bundle = new Bundle();
        //
        // bundle.putString("lastScreen", lastScreenName);
        // bundle.putString("msg_id", list.get(ClickedReq)
        // .getMsg_id());
        // bundle.putString("topic_id", list.get(ClickedReq)
        // .getTopic_id());
        // bundle.putInt("action", option);
        // bundle.putInt("id", ClickedReq);
        //
        // bundle.putBoolean("isReply", isreplay);
        // bundle.putBoolean("isoffensive", isoffensive);
        // bundle.putBoolean("israting", israted);
        //
        // MessagesLogin myMessagesloginFragementobj = new MessagesLogin();
        //
        // myMessagesloginFragementobj.setTargetFragment(
        // ((BaseActivity) mContext)
        // .getCurrentfragment(((BaseActivity) mContext)
        // .getCurrentFragmentName()), 1);
        // if (lastScreenName
        // .equalsIgnoreCase("MessageTopicDetail")
        // || lastScreenName
        // .equalsIgnoreCase("ThreadMessagePage")) {
        // bundle.putBoolean("israting", israted);
        // myMessagesloginFragementobj.setArguments(bundle);
        // // ((BaseActivity) mContext)
        // // .launchNavigateFragementonTop(myMessagesloginFragementobj);
        // } else {
        // myMessagesloginFragementobj.setArguments(bundle);
        // // ((BaseActivity) mContext)
        // // .launchNavigateFragement(myMessagesloginFragementobj);
        //
        // }
        //
        // }
        // }).setNegativeButton(cancel, null).show();

        final Dialog dialog = new Dialog(mContext);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.custom_dialog_layout);

        TextView titletxt = (TextView) dialog.findViewById(R.id.header1);
        titletxt.setText(title);
        titletxt.setVisibility(View.GONE);

        TextView msgtxt = (TextView) dialog.findViewById(R.id.header2);
        msgtxt.setText(msg);
        msgtxt.setVisibility(View.VISIBLE);

        Button okButton = (Button) dialog.findViewById(R.id.btnExit);
        okButton.setText(ok);

        View saperator = (View) dialog.findViewById(R.id.saperator);
        saperator.setVisibility(View.GONE);

        // if (msg == null || msg.equals("")) {
        // saperator.setVisibility(View.GONE);
        // } else {
        // saperator.setVisibility(View.VISIBLE);
        // }

        okButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Bundle bundle = new Bundle();

                bundle.putString("lastScreen", lastScreenName);
                bundle.putString("msg_id", list.get(ClickedReq).getMsg_id());
                bundle.putString("topic_id", list.get(ClickedReq).getTopic_id());
                bundle.putInt("action", option);
                bundle.putInt("id", ClickedReq);

                bundle.putBoolean("isReply", isreplay);
                bundle.putBoolean("isoffensive", isoffensive);
                bundle.putBoolean("israting", israted);

                MessagesLogin myMessagesloginFragementobj = new MessagesLogin();

                myMessagesloginFragementobj.setTargetFragment(
                        ((BaseActivity) mContext)
                                .getCurrentfragment(((BaseActivity) mContext)
                                        .getCurrentFragmentName()), 1);
                if (lastScreenName.equalsIgnoreCase("MessageTopicDetail")
                        || lastScreenName.equalsIgnoreCase("ThreadMessagePage")) {
                    bundle.putBoolean("israting", israted);
                    myMessagesloginFragementobj.setArguments(bundle);
                    // ((BaseActivity) mContext)
                    // .launchNavigateFragementonTop(myMessagesloginFragementobj);
                } else {
                    myMessagesloginFragementobj.setArguments(bundle);
                    // ((BaseActivity) mContext)
                    // .launchNavigateFragement(myMessagesloginFragementobj);

                }
                dialog.dismiss();
            }
        });
        Button cancelButton = (Button) dialog.findViewById(R.id.btnCancel);
        cancelButton.setText(cancel);
        cancelButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                dialog.dismiss();
            }
        });
        dialog.show();

        // final TextView btn1 = (TextView) alertDialog
        // .findViewById(android.R.id.button1);
        // final TextView btn2 = (TextView) alertDialog
        // .findViewById(android.R.id.button2);
        // final TextView message = (TextView) alertDialog
        // .findViewById(android.R.id.message);
        // Utility.getInstance().setTypeface(btn1, mContext);
        // Utility.getInstance().setTypeface(btn2, mContext);
        // Utility.getInstance().setTypeface(message, mContext);

    }

    protected void showpostReply(int id) {
        // TODO Auto-generated method stub

        // bundle.putString(AppConstants.USER_NAME, Utility
        // .getInstance().getUSER_Name());
        // bundle.putString(AppConstants.USER_MEM_type, Utility
        // .getInstance().getUSER_mambership_type());
        // bundle.putString(AppConstants.USER_PIC, Utility
        // .getInstance().getUSER_profile_pic());
        // bundle.putString(AppConstants.USER_TOKEN, Utility
        // .getInstance().getUSER_TOKEN());
        Bundle bundle = new Bundle();
        bundle.putString(AppConstants.USER_ID, Utility.getInstance()
                .getUSER_ID());
        bundle.putString("RequestType", "isReply");
        bundle.putString("TopicName", list.get(id).getTopic());

        bundle.putString(AppConstants.TOPIC_ID, list.get(id).getTopic_id());
        bundle.putString("Msg_id", list.get(id).getMsg_id());
        bundle.putBoolean("ShowSentimentOrNot", list.get(id)
                .isShowReplaySentiment());

        PostMessage postfragment = new PostMessage();
        postfragment.setArguments(bundle);
        // ((BaseActivity) mContext)
        // .delaunchOldFragementAddNewFragment(postfragment);
    }

    // public void ratePopUp(int a) {
    //
    // final int itemID = a;
    //
    // HidePopUpifAnyOpen(itemID);
    //
    // if (list.get(itemID).isOpenRate()) {
    // list.get(itemID).setOpenRate(false);
    // } else {
    // list.get(itemID).setOpenRate(true);
    // }
    // notifyDataSetChanged();
    //
    // RelativeLayout ll = ratedlayouts.get(itemID); //
    // (RelativeLayout)list.get(itemID).getLl();
    // Button bbn = (Button) ll.findViewById(R.id.Submit_rating_btn);
    //
    // bbn.setOnClickListener(new OnClickListener() {
    //
    // @Override
    // public void onClick(View v) {
    // // TODO Auto-generated method stub
    // rateMessage(itemID);
    // if (list.get(itemID).isOpenRate()) {
    // list.get(itemID).setOpenRate(false);
    // } else {
    // list.get(itemID).setOpenRate(true);
    // }
    // notifyDataSetChanged();
    //
    // }
    // });
    //
    // }

    public void ratePopUp(int a) {

        final int itemID = a;

        HidePopUpifAnyOpen(itemID);

        if (list.get(itemID).isOpenRate()) {
            list.get(itemID).setOpenRate(false);
        } else {
            list.get(itemID).setOpenRate(true);
        }
        notifyDataSetChanged();

    }

    private void rateMessage(int a, String b) {

        final int itemID = a;
        final String rating = b;
        final String url = extraLinks.get("msg_rate");
        handler = new Handler() {
            @Override
            public void handleMessage(android.os.Message msg) {

                if (response == null)
                    return;
                Utility.getInstance().setToastTypefaceResponse(
                        mContext.getApplicationContext(), response, response,
                        response);
                // Toast.makeText(MessageDetail.this, response,
                // Toast.LENGTH_SHORT).show();

            }

            ;

        };

        new Thread() {
            @Override
            public void run() {
                try {

                    response = ParseCall.getInstance()
                            .setMessageRating(mContext,
                                    list.get(itemID).getMsg_id(), rating, url);
                    // String.valueOf(ratinBar.getRating()));

                } catch (Exception e) {
                    e.printStackTrace();
                }
                handler.sendEmptyMessage(0);
            }
        }.start();

    }

    // private void rateMessage(int a) {
    //
    // final int itemID = a;
    // // final String url = extraLinks.get("msg_rate");
    // handler = new Handler() {
    // @Override
    // public void handleMessage(android.os.Message msg) {
    //
    // if (response == null)
    // return;
    // Utility.getInstance().setToastTypefaceResponse(
    // mContext.getApplicationContext(), response, response,
    // response);
    // // Toast.makeText(MessageDetail.this, response,
    // // Toast.LENGTH_SHORT).show();
    //
    // };
    //
    // };
    //
    // new Thread() {
    // @Override
    // public void run() {
    // try {
    //
    // response = ParseCall.getInstance().setMessageRating(
    // mContext, list.get(itemID).getMsg_id(), "3", "");
    // // String.valueOf(ratinBar.getRating()));
    //
    // } catch (Exception e) {
    // e.printStackTrace();
    // }
    // handler.sendEmptyMessage(0);
    // }
    // }.start();
    //
    // }

    // Repost a Msg

    public void doRate(View view, int itemID) {
        try {
            // (RelativeLayout)tempData.get(itemID).getLl();
            if (view != null) {
                Button bbn = (Button) view.findViewById(R.id.Submit_rating_btn);
                final RatingBar ratingbar = (RatingBar) view
                        .findViewById(R.id.ratingbar2);
                ratingbar.setStepSize(1);
                bbn.setTag(itemID);
                bbn.setOnClickListener(new OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        int itemID = (Integer) v.getTag();
                        String rate = String.valueOf(ratingbar.getRating());
                        // if ((int) ratingbar.getRating() > 0) {
                        rateMessage(itemID,
                                String.valueOf(ratingbar.getRating()));
                        // } else {
                        // Utility.getInstance().showToast(getActivity(),
                        // "Please give some rating..");
                        // }
                        if (list.get(itemID).isOpenRate()) {
                            list.get(itemID).setOpenRate(false);
                        } else {
                            list.get(itemID).setOpenRate(true);
                        }
                        notifyDataSetChanged();

                    }
                });
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    protected void repostmsg(int id, MessageCategoryItemData obj) {
        // TODO Auto-generated method stub
        final int itemID = id;
        final MessageCategoryItemData Mobj = obj;
        final String url = extraLinks.get("msg_repost");

        if (list.get(itemID).isIsrepost()) {
            // tempData.get(itemID).setOpenRate(false);
        } else {
            list.get(itemID).setIsrepost(true);
        }
        notifyDataSetChanged();

        handler = new Handler() {
            @Override
            public void handleMessage(android.os.Message msg) {

                if (response == null)
                    return;

                Utility.getInstance().setToastTypefaceResponse(
                        mContext.getApplicationContext(), response, response,
                        response);

                // if (!Mobj.isIsrepost()) {
                // Mobj.setIsrepost(true);
                // } else {
                // Mobj.setIsrepost(false);
                // }
                // adapter.notifyDataSetChanged();

            }

            ;

        };

        new Thread() {
            @Override
            public void run() {
                try {

                    response = ParseCall.getInstance().setMessageRepost(
                            mContext, list.get(itemID).getMsg_id(), "", url);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                handler.sendEmptyMessage(0);
            }
        }.start();

    }

    public void markthisMessageAsOffensive(Activity context, int a) {
        final int itemID = a;

        if (list.get(itemID).isIsoffensiveMark()) {
            markOffensive(itemID);

        } else {

            showoffensiveAlertDialog((BaseActivity) context, itemID);

        }

        // adapter.notifyDataSetChanged();

    }

    public void showoffensiveAlertDialog(final Activity context, int a) {
        final int itemID = a;

        try {

            String ok = null;

            ok = mContext.getResources().getString(R.string.retry);

            /************************************************************/
            final Dialog dialog = new Dialog(context);

            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.custom_dialog_layout);
            dialog.setCancelable(false);

            TextView titletext = (TextView) dialog.findViewById(R.id.header1);
            titletext.setText(mContext.getResources().getString(
                    R.string.are_you_sure_want_to_mark_offensive));
            String message = "";
            TextView messagetext = (TextView) dialog.findViewById(R.id.header2);
            messagetext.setText(message);
            messagetext.setVisibility(View.VISIBLE);

            View saperator = (View) dialog.findViewById(R.id.saperator);

            if (mContext.getResources().getString(R.string.alert) == null
                    || mContext.getResources().getString(R.string.alert)
                    .equals("")) {
                titletext.setVisibility(View.GONE);
                saperator.setVisibility(View.GONE);
            } else if (message == null || message.equals("")) {
                messagetext.setVisibility(View.GONE);
                saperator.setVisibility(View.GONE);
            }

            Button okButton = (Button) dialog.findViewById(R.id.btnExit);
            okButton.setText(mContext.getResources().getString(R.string.yes));
            okButton.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    list.get(itemID).setIsoffensiveMark(true);
                    markOffensive(itemID);
                    dialog.dismiss();
                }
            });
            Button cancelButton = (Button) dialog.findViewById(R.id.btnCancel);
            cancelButton.setVisibility(View.VISIBLE);
            cancelButton.setText(mContext.getResources().getString(
                    R.string.cancel));
            cancelButton.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View arg0) {

                    dialog.dismiss();
                }
            });
            dialog.show();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Mark Message Offensive
     */
    private void markOffensive(int a) {
        final int itemID = a;
        final String url = extraLinks.get("msg_offensive");

        handler = new Handler() {
            @Override
            public void handleMessage(android.os.Message msg) {

                if (response == null)
                    return;

                Utility.getInstance().setToastTypefaceResponse(
                        mContext.getApplicationContext(), response, response,
                        response);
                // Toast.makeText(MessageDetail.this, response,
                // Toast.LENGTH_SHORT).show();

            }

            ;

        };

        new Thread() {
            @Override
            public void run() {
                try {

                    response = ParseCall.getInstance().setMessageOffensive(
                            mContext, list.get(itemID).getMsg_id(), "", url);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                handler.sendEmptyMessage(0);
            }
        }.start();

    }

    private ImageGetter imgGetter = new ImageGetter() {

        public Drawable getDrawable(String source) {
            Drawable drawable = null;

            drawable = mContext.getResources().getDrawable(
                    R.drawable.latestnews);

            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
                    drawable.getIntrinsicHeight());
            return drawable;
        }
    };

    public void HidePopUpifAnyOpen(int a) {
        // TODO Auto-generated method stub
        for (int i = 0; i < list.size(); i++) {

            if (i != a) {
                list.get(i).setOpenRate(false);
            }

        }
        notifyDataSetChanged();
    }

    @Override
    public void onTaskComplete(int requestType, AppBeanParacable appBean) {
        // TODO Auto-generated method stub
        if (appBean == null) {
            return;

        } else if (requestType == RequestType.REQ_WHO_RATED_BY)
            if (appBean instanceof LastVisitedListData) {
                ArrayList<LastVisitedListBean> list = ((LastVisitedListData) appBean)
                        .getLastVisitedList();
                NotificationDialogFragment fragment = new NotificationDialogFragment();
                Bundle i = new Bundle();
                i.putString(Constantlibs.HEADERSTRING, "Boarders");
                i.putSerializable("list", list);
                fragment.setArguments(i);

                fragment.setRetainInstance(true);
                fragment.show(((FragmentActivity) mContext)
                        .getSupportFragmentManager(), "sortDialog");
            }

    }

    public void doAction(int itemClicked, int optionSelect) {

        MessageCategoryItemData obj = list.get(itemClicked);
        switch (optionSelect) {
            case 0:

                Fragment fragment = ((BaseActivity) mContext)
                        .getCurrentfragment(((BaseActivity) mContext)
                                .getCurrentFragmentName());

                if (fragment instanceof BaseFragement) {
                    ((BaseFragement) fragment).showpostReply(obj, false, "reply");
                }
                break;
            case 1:
                ratePopUp(itemClicked);
                break;
            case 2:
                repostmsg(itemClicked, obj);

                break;
            case 3:
                markOffensive(itemClicked);
                break;
            case 4:

                break;

            default:
                break;
        }

    }

    @Override
    public void onAddFailed(Serializable object) {
        if (list != null && object != null) {
            list.remove(object);
            notifyDataSetChanged();
        }

    }
}
