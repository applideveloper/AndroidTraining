
package jp.mixi.practice.sqlite;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.Insert).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                insert();
            }
        });
        findViewById(R.id.Delete).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                delete();
            }
        });
        findViewById(R.id.Update).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                update();
            }
        });
        findViewById(R.id.Query).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                query();
            }
        });

    }

    private void insert() {
        // TODO:ここにinsert処理を実装してください
	    BookOpenHelper bookOpenHelper = new BookOpenHelper(this);
	    SQLiteDatabase sqLiteDatabase = bookOpenHelper.getWritableDatabase();

	    ContentValues contentValues = new ContentValues();
	    contentValues.put(Book.COLUMN_NAME_BOOK_TITLE, "TITLE1");
	    contentValues.put(Book.COLUMN_NAME_BOOK_PUBLISHER, "PUBLISHER1");
	    contentValues.put(Book.COLUMN_NAME_BOOK_PRICE, "PRICE1");

	    long rowId = sqLiteDatabase.insert(Book.BOOK_TABLE_NAME, null, contentValues);
	    Log.v("result", "insert: " + Long.toString(rowId));
    }

    private void delete() {
        // TODO:ここにdelete処理を実装してください
	    BookOpenHelper bookOpenHelper = new BookOpenHelper(this);
	    SQLiteDatabase sqLiteDatabase = bookOpenHelper.getWritableDatabase();

	    // 条件を指定
	    String selection = Book.COLUMN_NAME_BOOK_PRICE + " = ?";
	    String[] selectionArgs = {
			    "PRICE1"
	    };
	    int deletedCount = sqLiteDatabase.delete(Book.BOOK_TABLE_NAME, selection, selectionArgs);
	    Log.v("result", "delete: " + Long.toString(deletedCount));
    }

    private void update() {
        // TODO:ここにupdate処理を実装してください
	    BookOpenHelper bookOpenHelper = new BookOpenHelper(this);
	    SQLiteDatabase sqLiteDatabase = bookOpenHelper.getWritableDatabase();

	    // update情報を設定する
	    ContentValues contentValues = new ContentValues();
	    contentValues.put(Book.COLUMN_NAME_BOOK_TITLE, "NEW_TITLE");

	    // 条件を指定
	    String selection = Book.COLUMN_NAME_BOOK_TITLE + " LIKE ?";
	    String[] selectionArgs = {
			    "TITLE%"
	    };

	    int updatedCount = sqLiteDatabase.update(Book.BOOK_TABLE_NAME, contentValues, selection, selectionArgs);
	    Log.v("result", "update: " + Long.toString(updatedCount));
    }

    private void query() {
        // TODO:ここにquery処理を実装してください
	    BookOpenHelper bookOpenHelper = new BookOpenHelper(this);
	    SQLiteDatabase sqLiteDatabase = bookOpenHelper.getReadableDatabase();

	    // 取得する情報を指定
	    String[] projection = {
			    Book._ID,
			    Book.COLUMN_NAME_BOOK_TITLE,
			    Book.COLUMN_NAME_BOOK_PUBLISHER,
			    Book.COLUMN_NAME_BOOK_PRICE
	    };

	    // 条件を指定
	    String selection = Book.COLUMN_NAME_BOOK_PRICE + " = ?";
	    String[] selectionArgs = {
			    "PRICE1"
	    };

	    Cursor cursor = sqLiteDatabase.query(Book.BOOK_TABLE_NAME, projection, selection, selectionArgs, null, null, null);

	    while(cursor.moveToNext()) {
		    Log.v("result", bookToString(cursor));
	    }
    }

	private String bookToString(Cursor cursor) {
		String _id = cursor.getString(cursor.getColumnIndexOrThrow(Book._ID));
		String title = cursor.getString(cursor.getColumnIndexOrThrow(Book.COLUMN_NAME_BOOK_TITLE));
		String publish = cursor.getString(cursor.getColumnIndexOrThrow(Book.COLUMN_NAME_BOOK_PUBLISHER));
		String price = cursor.getString(cursor.getColumnIndexOrThrow(Book.COLUMN_NAME_BOOK_PRICE));

		return "id: " + _id + " title: " + title + " publisher: " + publish + " price: " + price;
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
