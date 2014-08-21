package jp.wishmatch.networkassignment;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.Loader;
import android.os.Build;
import android.os.StrictMode;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements LoaderManager.LoaderCallbacks<String> {
	LoaderManager mLoadManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
			StrictMode.setThreadPolicy(
				new StrictMode.ThreadPolicy.Builder()
					.detectNetwork()
					.penaltyDeath()
					.build()
			);
		}

	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_main);

		mLoadManager = getLoaderManager();
	    mLoadManager.initLoader(0, new Bundle(), this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//        if (id == R.id.action_settings) {
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }

	@Override
	public Loader<String> onCreateLoader(int id, Bundle args) {
		switch (id) {
			case 0:
				return new YoutubeAsyncTaskLoader(this);
		}
		return null;
	}

	@Override
	public void onLoadFinished(Loader<String> loader, String data) {
		// 結果をviewに表示する
		List<Video> list = new ArrayList<Video>();
		Gson gson = new Gson();
		list = gson.fromJson(data, new TypeToken<List<Video>>(){}.getType());

		ListView listView = (ListView)findViewById(R.id.ListView);
		ArrayAdapter<Video> adapter = new VideoListItemAdapter(this, list);
		listView.setAdapter(adapter);
		mLoadManager.destroyLoader(0);
	}

	@Override
	public void onLoaderReset(Loader<String> loader) {

	}
}

