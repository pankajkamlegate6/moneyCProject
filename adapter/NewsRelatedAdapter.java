package com.moneycontrol.handheld.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.divum.MoneyControl.R;
import com.moneycontrol.handheld.entity.news.NewsInnerItem;
import com.moneycontrol.handheld.util.ImageDownloader;

import java.util.ArrayList;

public class NewsRelatedAdapter extends BaseAdapter {

	private ArrayList<NewsInnerItem> newsArray = null;
	private LayoutInflater inflate = null;
	private Activity context = null;

	public NewsRelatedAdapter(ArrayList<NewsInnerItem> newsArray, Activity con) {
		inflate = (LayoutInflater) con
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		context = con;
		this.newsArray = newsArray;
	}

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
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = inflate.inflate(R.layout.news_inner_item, null);
			convertView.findViewById(R.id.tvNextArticle).setVisibility(View.GONE);
				
			holder.tvNewsTitle = (TextView) convertView
					.findViewById(R.id.tvInnerNewsTitle);
			holder.tvNewsTime = (TextView) convertView
					.findViewById(R.id.tvNewsTime);
			holder.ivNewsThumb = (ImageView) convertView
					.findViewById(R.id.ivNewsThumb);
			holder.ivVedio = (ImageView) convertView
					.findViewById(R.id.ivVedio);
			holder.ivPlay = (ImageView) convertView
					.findViewById(R.id.ivPlay);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.tvNewsTitle.setText(newsArray.get(position).getHeading());
		holder.tvNewsTime.setText(newsArray.get(position).getEnt_date());
		if (newsArray.get(position).getImage() != null){
			new ImageDownloader().download(newsArray.get(position)
					.getImage(), holder.ivNewsThumb);
			if(newsArray.get(position).getVideo_flag().equals("1")) {
				holder.ivVedio.setVisibility(View.VISIBLE);
				holder.ivPlay.setVisibility(View.VISIBLE);
			} else {
				holder.ivVedio.setVisibility(View.GONE);
				holder.ivPlay.setVisibility(View.GONE);
			}
		}
		
		
		return convertView;
	}

	class ViewHolder {
		public TextView tvNewsTitle, tvNewsTime;
		public ImageView ivNewsThumb;
		public ImageView ivVedio;
		public ImageView ivPlay;
	}

}
