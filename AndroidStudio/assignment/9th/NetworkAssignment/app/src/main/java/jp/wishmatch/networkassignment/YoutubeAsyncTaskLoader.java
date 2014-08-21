package jp.wishmatch.networkassignment;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class YoutubeAsyncTaskLoader extends AsyncTaskLoader<String> {
	String mChachedData;

	private static final String TOP_RATED_URL = "http://gdata.youtube.com/feeds/api/standardfeeds/JP/top_rated";

	public YoutubeAsyncTaskLoader(Context context) {
		super(context);
	}

	@Override
	public String loadInBackground() {
		Log.v("test", "start YoutubeAsyncTaskLoader");

		HttpURLConnection connection = null;;
		List<Video> list = new ArrayList<Video>();

		// 1 getで引っ張ってくる
		try {
			URL url = new URL(TOP_RATED_URL);
			connection = (HttpURLConnection) url.openConnection();
			connection.connect();
			InputStream stream = connection.getInputStream();

			// 2 parseする
			list = Video.parseXml(stream);

		} catch(MalformedURLException e) {
			Log.v("test", e.toString());
		} catch(IOException e) {
			Log.v("test", e.toString());
		}

		// 3 データをActivityに返す
		Gson gson = new Gson();
		mChachedData = gson.toJson(list);
		Log.v("test", mChachedData);
		return mChachedData;
	}

	@Override
	public void onStartLoading() {
		if (mChachedData != null) {
			deliverResult(mChachedData);
			return;
		}

		if(takeContentChanged() || mChachedData == null) {
			forceLoad();
		}
	}
}


