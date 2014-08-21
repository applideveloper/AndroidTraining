package jp.mixi.practice.network.networkpractice1;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpGetAsyncTaskLoader extends AsyncTaskLoader<String> {
	private String accessUrl;

	public HttpGetAsyncTaskLoader(Context context, String accessUrl) {
		super(context);
		this.accessUrl = accessUrl;
	}

	@Override
	public String loadInBackground() {
		HttpURLConnection connection = null;
		StringBuilder src = new StringBuilder();

		try {
			URL url = new URL(this.accessUrl);
			connection = (HttpURLConnection)url.openConnection();
			connection.setRequestMethod("GET");
			InputStream stream = connection.getInputStream();

			while (true) {
				byte[] line = new byte[1024];
				int size = stream.read(line);
				if (size <= 0) {
					break;
				}
				src.append(new String(line, "utf-8"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.disconnect();
		}

		return src.toString();
	};

	@Override
	protected void onStartLoading() { forceLoad(); }

}