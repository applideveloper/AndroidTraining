package jp.mixi.practice.network.networkpractice1;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpPostAsyncTaskLoader extends AsyncTaskLoader<String> {
	private String accessUrl;

	public HttpPostAsyncTaskLoader(Context context, String accessUrl) {
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
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);

			String postData = "?email=test&password=test";
			OutputStream os = connection.getOutputStream();
			os.write(postData.getBytes());
			os.flush();
			os.close();

			InputStream stream = connection.getInputStream();

			while (true) {
				byte[] line = new byte[1024];
				int size = stream.read(line);
				if (size < 0) {
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
	protected void onStartLoading() {
		forceLoad();
	}
}