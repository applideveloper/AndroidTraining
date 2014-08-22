package jp.wishmatch.sqliteassignment;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

	AndroidCodeNameOpenHelper mHelper;
	SQLiteDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

	    mHelper = new AndroidCodeNameOpenHelper(this);
	    mDb = mHelper.getWritableDatabase();

	    // TODO:独自のSQLiteOpenHelperの作成、それに使用するカラム名などの定義
	    // TODO:insert処理、query処理の実装
	    Button insert = (Button) findViewById(R.id.insert);

	    insert.setOnClickListener(new View.OnClickListener() {
		    @Override
		    public void onClick(View v) {
			    insert();
		    }
	    });

	    final Button select = (Button) findViewById(R.id.select);

	    select.setOnClickListener(new View.OnClickListener() {
		    @Override
		    public void onClick(View v) {
			    Cursor cursor = select();
			    String[] from = {
					    AndroidCodeName.COLUMN_ID,
					    AndroidCodeName.COLUMN_NAME,
					    AndroidCodeName.COLUMN_VERSION,
			    };

			    int[] to = {
						R.id.id,
					    R.id.name,
					    R.id.version
			    };

			    SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(MainActivity.this, R.layout.list_item_android_code_name, cursor, from, to, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
			    ListView listView = (ListView) findViewById(R.id.listView);
			    listView.setAdapter(simpleCursorAdapter);
		    }
	    });
    }

	private Cursor select() {
		String[] projection = {
				AndroidCodeName.COLUMN_ID,
				AndroidCodeName.COLUMN_NAME,
				AndroidCodeName.COLUMN_VERSION
		};

		Cursor cursor = mDb.query(AndroidCodeName.TABLE_NAME, projection, null, null, null, null, null);
		return cursor;
	}

	private void insert() {
		List<ContentValues> list = new ArrayList<ContentValues>();
		for (int i = 0; i < 3; i++) {
			ContentValues values = new ContentValues();
			values.put(AndroidCodeName.COLUMN_NAME, "name-" + i);
			values.put(AndroidCodeName.COLUMN_VERSION, "version-" + i);
			list.add(values);
		}

		// 追加
		for(ContentValues value: list) {
			mDb.insert(AndroidCodeName.TABLE_NAME, null, value);
		}
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
}
