package com.moneycontrol.handheld.db;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.text.TextUtils;
import android.util.Log;

import com.moneycontrol.handheld.entity.news.NewsCategoryData;
import com.moneycontrol.handheld.entity.news.NewsInnerItem;
import com.moneycontrol.handheld.entity.news.NewsStoryData;
import com.moneycontrol.handheld.entity.vedio.VideoOnDemandData;
import com.moneycontrol.handheld.util.Constantlibs;
import com.moneycontrol.handheld.util.Utility;

public class Dbopener {
	private Context mContext;

	SQLiteDatabase db;

	private static Dbopener mInstance = null;

	public static Dbopener getInstance(Context ctx) {

		if (mInstance == null) {
			mInstance = new Dbopener(ctx);
		}
		return mInstance;
	}

	public Dbopener(Context mContext) {
		open(mContext);
		this.mContext = mContext;
	}

	public void open(Context mContext) {
		DatabaseHelper databaseHelper = new DatabaseHelper(mContext);
		db = databaseHelper.getWritableDatabase();
	}

	public boolean isOpen() {
		if (db != null)
			return db.isOpen();
		return false;
	}

	public boolean addMessages(String messageId, int status) {
		ContentValues contentValues = new ContentValues();
		if (messageId != null) {
			contentValues.put(DatabaseHelper.KEY_MESSAGE_ID, messageId);
			contentValues.put(DatabaseHelper.KEY_MESSAGE_STATUS, status);
			int statusValue = existMessage(messageId);
			if (statusValue == Constantlibs.NEW_MSG) {
				db.insert(DatabaseHelper.TABLE_MESSAGE, null, contentValues);
				return false;
			} else if (statusValue == Constantlibs.UNREAD_MSG) {
				return false;
			} else {
				return true;
			}
		} else {
			return false;
		}
	}

	public void close() {
		if (db != null && db.isOpen())
			db.close();
	}

	public int existMessage(String messageId) {
		int status = Constantlibs.READ_MSG;
		Cursor cursor = db.rawQuery("select "
				+ DatabaseHelper.KEY_MESSAGE_STATUS + " from "
				+ DatabaseHelper.TABLE_MESSAGE + " where "
				+ DatabaseHelper.KEY_MESSAGE_ID + " =?",
				new String[] { messageId });
		if (cursor.getCount() > 0) {
			cursor.moveToFirst();
			status = cursor.getInt(cursor
					.getColumnIndex(DatabaseHelper.KEY_MESSAGE_STATUS));

		} else {
			status = Constantlibs.NEW_MSG;
		}

		return status;
	}

	public void updateStatus(String messageId) {

		ContentValues contentValues = new ContentValues();
		contentValues.put(DatabaseHelper.KEY_MESSAGE_STATUS,
				Constantlibs.READ_MSG);

		// updating row
		db.update(DatabaseHelper.TABLE_MESSAGE, contentValues,
				DatabaseHelper.KEY_MESSAGE_ID + " = ?",
				new String[] { String.valueOf(messageId) });
	}

	public boolean isFavouritesNews(Context mContext, String storyId) {
		if (!isOpen()) {
			open(mContext);
		}

		Cursor cursor = db.query(
				DatabaseHelper.TABLE_NEWS_DETAILS,
				null,
				DatabaseHelper.KEY_STORY_ID + "=? and language =?",
				new String[] { String.valueOf(storyId),
						Utility.currentLanguage(mContext) }, null, null, null,
				null);

		if (cursor.getCount() > 0) {
			return true;
			// returnVal = true;
		} else {
			return false;
			// returnVal = false;
		}
	}

	public void addFavourites(NewsStoryData newsStoryData, String languange) {
		if (!TextUtils.isEmpty(newsStoryData.getStory_id())) {
			ContentValues contentValues = new ContentValues();
			contentValues.put(DatabaseHelper.KEY_HEADLINE,
					newsStoryData.getHeadline());
			contentValues.put(DatabaseHelper.KEY_STORY_ID,
					newsStoryData.getStory_id());
			contentValues.put(DatabaseHelper.KEY_BODY, newsStoryData.getBody());
			contentValues.put(DatabaseHelper.KEY_FULLIMAGE,
					newsStoryData.getFullimage());
			contentValues.put(DatabaseHelper.KEY_STORYURL,
					newsStoryData.getStoryurl());
			contentValues.put(DatabaseHelper.KEY_SOURCE,
					newsStoryData.getSource());
			contentValues.put(DatabaseHelper.KEY_VIDEO_FLAG,
					newsStoryData.getVideo_flag());
			contentValues.put(DatabaseHelper.KEY_VIDEO_URL,
					newsStoryData.getVideo_url());
			contentValues.put(DatabaseHelper.KEY_CREATIONTIME,
					newsStoryData.getCreationtime());
			contentValues.put(DatabaseHelper.KEY_READ_MORE,
					newsStoryData.getReadMoreOn());
			contentValues.put(DatabaseHelper.KEY_OBJECT,
					serializeObject(newsStoryData));
			contentValues.put(DatabaseHelper.KEY_LANGUAGE, languange);

			db.insert(DatabaseHelper.TABLE_NEWS_DETAILS, null, contentValues);
			// addRelatedItems(newsStoryData);
		}
	}

	public byte[] serializeObject(Object o) {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();

		try {
			ObjectOutput out = new ObjectOutputStream(bos);
			out.writeObject(o);
			out.close();

			// Get the bytes of the serialized object
			byte[] buf = bos.toByteArray();

			return buf;
		} catch (IOException ioe) {
			Log.e("serializeObject", "error", ioe);

			return null;
		}
	}

	public void removeFavourites(NewsStoryData newsStoryData, String languange) {
		if (!TextUtils.isEmpty(newsStoryData.getStory_id())) {
			String table = DatabaseHelper.TABLE_NEWS_DETAILS;
			String whereClause = DatabaseHelper.KEY_STORY_ID + "=? and "
					+ DatabaseHelper.KEY_LANGUAGE + "=?";
			String[] whereArgs = new String[] {
					String.valueOf(newsStoryData.getStory_id()), languange };
			db.delete(table, whereClause, whereArgs);
//			db.delete(DatabaseHelper.TABLE_RELEATED_NEWS_DETAILS, whereClause,
//					whereArgs);
		}
	}

	private void addRelatedItems(NewsStoryData newsStoryData) {
		String sql = "INSERT INTO "
				+ DatabaseHelper.TABLE_RELEATED_NEWS_DETAILS
				+ " VALUES (?,?,?,?,?,?);";
		SQLiteStatement statement = db.compileStatement(sql);
		db.beginTransaction();
		for (NewsInnerItem item : newsStoryData.getRelated_news()) {
			statement.clearBindings();
			statement.bindString(1, item.getAutono());
			statement.bindString(2, item.getHeading());
			statement.bindString(3, item.getImage());

			statement.bindString(4, item.getEnt_date());
			statement.bindString(5, newsStoryData.getStory_id());

			statement.bindString(6,
					item.getMessage() == null ? "" : item.getMessage());

			statement.execute();
		}
		db.setTransactionSuccessful();
		db.endTransaction();

	}

	public ArrayList<NewsCategoryData> getFavouritesNews(String languange) {
		ArrayList<NewsCategoryData> list = new ArrayList<NewsCategoryData>();
		if (!isOpen()) {
			open(mContext);
		}
		String whereClause = "" + DatabaseHelper.KEY_LANGUAGE + "=?";
		String[] whereArgs = new String[] { languange };
		Cursor cursor = db.query(DatabaseHelper.TABLE_NEWS_DETAILS, null,
				whereClause, whereArgs, null, null, null, null);

		try {

			// looping through all rows and adding to list
			if (cursor.moveToFirst()) {
				do {
					NewsCategoryData obj = new NewsCategoryData();
					// only one column
					obj.setCreationtime(cursor.getString(cursor
							.getColumnIndex(DatabaseHelper.KEY_CREATIONTIME)));
					obj.setFullImage(cursor.getString(cursor
							.getColumnIndex(DatabaseHelper.KEY_FULLIMAGE)));
					obj.setThumbnail(cursor.getString(cursor
							.getColumnIndex(DatabaseHelper.KEY_FULLIMAGE)));

					obj.setHeadline(cursor.getString(cursor
							.getColumnIndex(DatabaseHelper.KEY_HEADLINE)));

					obj.setStory_type(cursor.getString(cursor
							.getColumnIndex(DatabaseHelper.KEY_VIDEO_FLAG)));

					// obj.setLargeImage(cursor.getString(cursor
					// .getColumnIndex(DatabaseHelper.KEY_)));

					obj.setStory_id(cursor.getString(cursor
							.getColumnIndex(DatabaseHelper.KEY_STORY_ID)));

					// obj.setThumbnail(cursor.getString(cursor
					// .getColumnIndex(DatabaseHelper.KEY_)));

					obj.setVideoUrl(cursor.getString(cursor
							.getColumnIndex(DatabaseHelper.KEY_VIDEO_URL)));
					list.add(obj);
				} while (cursor.moveToNext());
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return list;

	}

	public NewsStoryData getFavouritesNews(String storyId, String languange) {
		if (!isOpen()) {
			open(mContext);
		}
		String whereClause = DatabaseHelper.KEY_STORY_ID + "=? and "
				+ DatabaseHelper.KEY_LANGUAGE + "=?";
		String[] whereArgs = new String[] { String.valueOf(storyId), languange };
		NewsStoryData obj = null;
		Cursor cursor = db.query(DatabaseHelper.TABLE_NEWS_DETAILS, null,
				whereClause, whereArgs, null, null, null, null);

		try {

			// looping through all rows and adding to list
			if (cursor.moveToFirst()) {
				do {
					byte[] data = cursor.getBlob(cursor
							.getColumnIndex(DatabaseHelper.KEY_OBJECT));
					obj = (NewsStoryData) deserialize(data);
				} while (cursor.moveToNext());
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return obj;

	}

	public Object deserialize(byte[] data) throws IOException,
			ClassNotFoundException {
		ByteArrayInputStream in = new ByteArrayInputStream(data);
		ObjectInputStream is = new ObjectInputStream(in);
		return is.readObject();
	}

	public ArrayList<NewsInnerItem> getRelatedNews(String storyId) {
		ArrayList<NewsInnerItem> list = new ArrayList<NewsInnerItem>();
		if (!isOpen()) {
			open(mContext);
		}

		Cursor cursor = db.query(DatabaseHelper.TABLE_RELEATED_NEWS_DETAILS,
				null, null, null, null, null, null, null);

		try {

			// looping through all rows and adding to list
			if (cursor.moveToFirst()) {
				do {
					NewsInnerItem obj = new NewsInnerItem();
					// only one column
					obj.setAutono(cursor.getString(cursor
							.getColumnIndex(DatabaseHelper.KEY_AUTONO)));
					obj.setEnt_date(cursor.getString(cursor
							.getColumnIndex(DatabaseHelper.KEY_ENT_DATE)));
					obj.setHeading(cursor.getString(cursor
							.getColumnIndex(DatabaseHelper.KEY_HEADING)));

					obj.setImage(cursor.getString(cursor
							.getColumnIndex(DatabaseHelper.KEY_IMAGE)));

					// obj.setThumbnail(cursor.getString(cursor
					// .getColumnIndex(DatabaseHelper.KEY_)));

					obj.setMessage(cursor.getString(cursor
							.getColumnIndex(DatabaseHelper.KEY_MESSAGE)));

					list.add(obj);
				} while (cursor.moveToNext());
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return list;

	}

	public void removeVideoFavourites(NewsCategoryData newsStoryData) {
		if (!TextUtils.isEmpty(newsStoryData.getStory_id())) {
			String table = DatabaseHelper.TABLE_NEWS_DETAILS;
			String whereClause = DatabaseHelper.KEY_STORY_ID + "=?";
			String[] whereArgs = new String[] { String.valueOf(newsStoryData
					.getStory_id()) };
			db.delete(table, whereClause, whereArgs);
			db.delete(DatabaseHelper.TABLE_VIDEO, whereClause, whereArgs);
		}
	}

	public void removeVideoFavourites(String Id) {
		if (!TextUtils.isEmpty(Id)) {
			String table = DatabaseHelper.TABLE_NEWS_DETAILS;
			String whereClause = DatabaseHelper.KEY_STORY_ID + "=?";
			String[] whereArgs = new String[] { String.valueOf(Id) };
			db.delete(table, whereClause, whereArgs);
			db.delete(DatabaseHelper.TABLE_VIDEO, whereClause, whereArgs);
		}
	}

	public boolean isFavouritesVideo(Context mContext, String story_id) {
		// TODO Auto-generated method stub
		if (!isOpen()) {
			open(mContext);
		}
		Cursor cursor = db.query(DatabaseHelper.TABLE_VIDEO, null,
				DatabaseHelper.KEY_STORY_ID + "=?",
				new String[] { String.valueOf(story_id) }, null, null, null,
				null);

		if (cursor.getCount() > 0) {
			return true;
			// returnVal = true;
		} else {
			return false;
			// returnVal = false;
		}
	}

	public void addVideoDemandFavourites(NewsCategoryData newsStoryData,
			String thumbnail) {
		if (!TextUtils.isEmpty(newsStoryData.getStory_id())) {

			ContentValues contentValues = new ContentValues();
			contentValues.put(DatabaseHelper.KEY_HEADLINE,
					newsStoryData.getHeadline());
			contentValues.put(DatabaseHelper.KEY_STORY_ID,
					newsStoryData.getStory_id());
			contentValues.put(DatabaseHelper.KEY_CREATIONTIME,
					newsStoryData.getCreationtime());
			contentValues.put(DatabaseHelper.KEY_FULLIMAGE, thumbnail);
			contentValues.put(DatabaseHelper.KEY_VIDEO_FLAG,
					newsStoryData.getIsVedio());
			contentValues.put(DatabaseHelper.KEY_VIDEO_URL,
					newsStoryData.getVideoUrl());
			contentValues.put(DatabaseHelper.KEY_STORYURL,
					newsStoryData.getStoryUrl());
			contentValues.put(DatabaseHelper.KEY_TAB, "");

			db.insert(DatabaseHelper.TABLE_VIDEO, null, contentValues);
		}

	}

	public void addVideoFavouritesRemaining(String showId, String showUrl,
			String thumbnail, String time, String name, String tab) {
		if (!TextUtils.isEmpty(showId)) {

			ContentValues contentValues = new ContentValues();
			contentValues.put(DatabaseHelper.KEY_HEADLINE, name);
			contentValues.put(DatabaseHelper.KEY_STORY_ID, showId);
			contentValues.put(DatabaseHelper.KEY_CREATIONTIME, time);
			contentValues.put(DatabaseHelper.KEY_FULLIMAGE, thumbnail);
			contentValues.put(DatabaseHelper.KEY_VIDEO_FLAG, "1");
			contentValues.put(DatabaseHelper.KEY_VIDEO_URL, showUrl);
			contentValues.put(DatabaseHelper.KEY_STORYURL, showUrl);
			contentValues.put(DatabaseHelper.KEY_TAB, tab);

			db.insert(DatabaseHelper.TABLE_VIDEO, null, contentValues);
		}

	}

	public ArrayList<VideoOnDemandData> getFavouritesVideos() {
		ArrayList<VideoOnDemandData> list = new ArrayList<VideoOnDemandData>();
		if (!isOpen()) {
			open(mContext);
		}
		Cursor cursor = db.query(DatabaseHelper.TABLE_VIDEO, null, null, null,
				null, null, null, null);

		try {

			// looping through all rows and adding to list
			if (cursor.moveToFirst()) {
				do {
					VideoOnDemandData obj = new VideoOnDemandData();
					// only one column
					obj.setCreationtime(cursor.getString(cursor
							.getColumnIndex(DatabaseHelper.KEY_CREATIONTIME)));
					obj.setFullimage(cursor.getString(cursor
							.getColumnIndex(DatabaseHelper.KEY_FULLIMAGE)));
					obj.setThumbnail(cursor.getString(cursor
							.getColumnIndex(DatabaseHelper.KEY_FULLIMAGE)));

					obj.setHeadline(cursor.getString(cursor
							.getColumnIndex(DatabaseHelper.KEY_HEADLINE)));
					obj.setContentid(cursor.getString(cursor
							.getColumnIndex(DatabaseHelper.KEY_STORY_ID)));
					obj.setContentid(cursor.getString(cursor
							.getColumnIndex(DatabaseHelper.KEY_STORY_ID)));

					// obj.setLargeImage(cursor.getString(cursor
					// .getColumnIndex(DatabaseHelper.KEY_)));

					obj.setTab(cursor.getString(cursor
							.getColumnIndex(DatabaseHelper.KEY_TAB)));

					obj.setVideo_url(cursor.getString(cursor
							.getColumnIndex(DatabaseHelper.KEY_VIDEO_URL)));
					list.add(obj);
				} while (cursor.moveToNext());
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return list;
	}

}
