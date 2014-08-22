
package jp.mixi.sample.contentprovider;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends Activity implements LoaderManager.LoaderCallbacks<Cursor>{

    @SuppressWarnings("unused")
    private static final String TAG = MainActivity.class.getSimpleName();

	private SimpleCursorAdapter mSimpleCursorAdapter;
	private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

	    String[] from = {
			    Book.COLUMN_NAME_BOOK_TITLE,
			    Book.COLUMN_NAME_BOOK_PUBLISHER,
			    Book.COLUMN_NAME_BOOK_PRICE
	    };

	    int[] to = {
			    R.id.Title,
			    R.id.Publisher,
			    R.id.Price
	    };

	    mSimpleCursorAdapter = new SimpleCursorAdapter(this, R.layout.list_item_book, null, from, to, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
	    mListView = (ListView)findViewById(R.id.ListView);
	    mListView.setAdapter(mSimpleCursorAdapter);
	    Log.v("test", "test");

	    // ローダの管理をするオブジェクト
	    LoaderManager loaderManager = getLoaderManager();
	    // ローダを初期化して非同期処理を開始する
	    loaderManager.initLoader(0, null, this);

        findViewById(R.id.Insert).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                insert();
            }
        });
    }

    private void insert() {
        ContentValues values = new ContentValues();
        for (int i = 0; i < 3; i++) {
            values.clear();
            values.put(Book.COLUMN_NAME_BOOK_TITLE, "TITLE" + i);
            values.put(Book.COLUMN_NAME_BOOK_PUBLISHER, "PUBLISHER" + i);
            values.put(Book.COLUMN_NAME_BOOK_PRICE, "PRICE" + i);

            Uri insert = getContentResolver().insert(Book.CONTENT_URI, values);
            Log.d(TAG, insert.toString());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle args) {
		return new CursorLoader(this, Book.CONTENT_URI, null, null, null, null);
	}

	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
		mSimpleCursorAdapter.swapCursor(data);
	}

	@Override
	public void onLoaderReset(Loader<Cursor> loader) {
		mSimpleCursorAdapter.swapCursor(null);
	}
}
