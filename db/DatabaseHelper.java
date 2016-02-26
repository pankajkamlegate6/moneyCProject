package com.moneycontrol.handheld.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

	// Logcat tag
	private static final String LOG = "DatabaseHelper";

	// Database Version
	private static final int DATABASE_VERSION = 7;

	// Database Name
	private static final String DATABASE_NAME = "History";

	// Table Names
	public static final String TABLE_MESSAGE = "message";
	public static final String TABLE_NEWS_DETAILS = "newsdetails";
	public static final String TABLE_VIDEO = "videos";

	public static final String TABLE_RELEATED_NEWS_DETAILS = "releated_newsdetails";
	// Common column names
	public static final String KEY_ID = "id";
	public static final String KEY_MESSAGE_ID = "messageId";
	public static final String KEY_MESSAGE_STATUS = "status";

	// Common column names for news details
	public static String KEY_HEADLINE = "headline";
	public static String KEY_STORY_ID = "story_id";
	public static String KEY_BODY = "body";
	public static String KEY_FULLIMAGE = "fullimage";
	public static String KEY_STORYURL = "storyurl";
	public static String KEY_SOURCE = "source";
	public static String KEY_VIDEO_FLAG = "video_flag";
	public static String KEY_VIDEO_URL = "video_url";
	public static String KEY_CREATIONTIME = "creationtime";
	public static String KEY_OBJECT = "Object_value";
	public static String KEY_LANGUAGE = "language";
	public static String KEY_READ_MORE = "readMoreOn";
	public static final String KEY_TAB = "tab";
	// Common column names for related news details
	public static String KEY_AUTONO = "autono";
	public static String KEY_HEADING = "heading";
	public static String KEY_IMAGE = "image";
	public static String KEY_ENT_DATE = "ent_date";
	public static String KEY_MESSAGE = "message";

	// Table Create Statements
	// table create statement
	private static final String CREATE_TABLE_MESSAGE = "CREATE TABLE IF NOT EXISTS "
			+ TABLE_MESSAGE
			+ "("
			+ KEY_ID
			+ " INTEGER PRIMARY KEY,"
			+ KEY_MESSAGE_ID + " TEXT ," + KEY_MESSAGE_STATUS + " INT  )";
	private static final String CREATE_TABLE_NEWS_DETAILS = "CREATE TABLE IF NOT EXISTS "
			+ TABLE_NEWS_DETAILS
			+ "("
			+ KEY_STORY_ID
			+ " TEXT , "
			+ KEY_HEADLINE
			+ " TEXT ,  "
			+ KEY_BODY
			+ " TEXT , "
			+ KEY_FULLIMAGE
			+ " TEXT , "
			+ KEY_STORYURL
			+ " TEXT , "
			+ KEY_SOURCE
			+ " TEXT , "
			+ KEY_VIDEO_FLAG
			+ " TEXT , "
			+ KEY_READ_MORE
			+ " TEXT , "
			+ KEY_VIDEO_URL
			+ " TEXT , "
			+ KEY_CREATIONTIME
			+ " TEXT, "
			+ KEY_LANGUAGE
			+ " TEXT, "
			+ KEY_OBJECT + " BLOB  )";

	private static final String CREATE_TABLE_VIDEOS = "CREATE TABLE IF NOT EXISTS "
			+ TABLE_VIDEO
			+ "("
			+ KEY_STORY_ID
			+ " TEXT , "
			+ KEY_HEADLINE
			+ " TEXT ,  "
			+ KEY_TAB
			+ " TEXT ,  "
			+ KEY_FULLIMAGE
			+ " TEXT , "
			+ KEY_STORYURL + " TEXT , "

			+ KEY_VIDEO_FLAG + " TEXT , "

			+ KEY_VIDEO_URL + " TEXT , " + KEY_CREATIONTIME + " TEXT  )";
	private static final String CREATE_TABLE_RELATED_NEWS_DETAILS = "CREATE TABLE IF NOT EXISTS "
			+ TABLE_RELEATED_NEWS_DETAILS
			+ "("
			+ KEY_AUTONO
			+ " TEXT , "
			+ KEY_HEADING
			+ " TEXT ,  "
			+ KEY_IMAGE
			+ " TEXT , "
			+ KEY_ENT_DATE
			+ " TEXT , " + KEY_STORY_ID + " TEXT , " + KEY_MESSAGE + " TEXT  )";

	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		// creating required tables
		db.execSQL(CREATE_TABLE_MESSAGE);
		db.execSQL(CREATE_TABLE_NEWS_DETAILS);
		db.execSQL(CREATE_TABLE_RELATED_NEWS_DETAILS);
		db.execSQL(CREATE_TABLE_VIDEOS);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// on upgrade drop older tables

		db.execSQL("DROP TABLE IF EXISTS " + TABLE_MESSAGE);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NEWS_DETAILS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_RELEATED_NEWS_DETAILS);

		// create new tables
		onCreate(db);
	}
}