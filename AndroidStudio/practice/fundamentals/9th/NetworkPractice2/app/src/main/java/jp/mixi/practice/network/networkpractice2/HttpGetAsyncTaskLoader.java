package jp.mixi.practice.network.networkpractice2;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpGetAsyncTaskLoader extends AsyncTaskLoader<String> {
	private String accessUrl;

	public HttpGetAsyncTaskLoader(Context context, String accessUrl) {
		super(context);
		this.accessUrl = accessUrl;
	}

	@Override
	public String loadInBackground() {
		HttpClient client = new DefaultHttpClient();
		String res = "";

		try {
			res = client.execute(new HttpGet(this.accessUrl),
					new ResponseHandler<String>() {
						@Override
						public String handleResponse(HttpResponse httpResponse) throws ClientProtocolException, IOException {
							return EntityUtils.toString(httpResponse.getEntity());
						}
					});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	protected void onStartLoading() {
		forceLoad();
	}
}