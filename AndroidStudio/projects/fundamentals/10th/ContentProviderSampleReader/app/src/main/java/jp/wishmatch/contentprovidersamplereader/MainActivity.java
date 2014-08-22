package jp.wishmatch.contentprovidersamplereader;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity implements LoaderManager.LoaderCallbacks<Cursor>{

	// 一意となる識別子にする
//	public static final String AUTHORITY = "jp.wishmatch.contentprovidersamplereader.Book";
	public static final String AUTHORITY = "jp.mixi.sample.contentprovider.Book";

	// bookテーブル用のContentURI
	public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/book");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

	@Override
	protected void onResume() {
		super.onResume();
		show();
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle args) {
		return null;
	}

	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

	}

	@Override
	public void onLoaderReset(Loader<Cursor> loader) {

	}

	public void show() {
		List<Book> bookList = new ArrayList<Book>();
		Cursor cursor = getContentResolver().query(this.CONTENT_URI, null, null, null, null);
		while(cursor.moveToNext()) {
			Book book = new Book();
			book.set_id(cursor.getString(cursor.getColumnIndexOrThrow("_id")));
			book.setTitle(cursor.getString(cursor.getColumnIndexOrThrow("title")));
			book.setPublisher(cursor.getString(cursor.getColumnIndexOrThrow("publisher")));
			book.setPrice(cursor.getString(cursor.getColumnIndexOrThrow("price")));
			bookList.add(book);
		}
		cursor.close();

		ArrayAdapter<Book> arrayAdapter = new CustomListItemAdapter(this, 0, bookList);
		ListView listView = (ListView)findViewById(R.id.ListView);
		listView.setAdapter(arrayAdapter);
	}
}
