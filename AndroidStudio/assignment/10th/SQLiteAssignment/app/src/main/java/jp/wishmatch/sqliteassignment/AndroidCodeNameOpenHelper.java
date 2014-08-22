package jp.wishmatch.sqliteassignment;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AndroidCodeNameOpenHelper extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 1;
	public static final String DATABASE_NAME = "practice";

	private static final String TABLE_CREATE =
		"CREATE TABLE " + AndroidCodeName.TABLE_NAME + " (" +
				AndroidCodeName.COLUMN_ID + " INTEGER PRIMARY KEY, " +
				AndroidCodeName.COLUMN_NAME + " TEXT NOT NULL, " +
				AndroidCodeName.COLUMN_VERSION + " TEXT" +
		");";

	private static final String TABLE_DELETE =
			"DROP TABLE IF EXISTS" + AndroidCodeName.TABLE_NAME;


	public AndroidCodeNameOpenHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(TABLE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(TABLE_DELETE);
		onCreate(db);
	}
}