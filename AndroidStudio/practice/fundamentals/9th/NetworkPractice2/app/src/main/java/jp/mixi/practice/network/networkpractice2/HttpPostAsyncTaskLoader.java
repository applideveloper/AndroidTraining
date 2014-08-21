package jp.mixi.practice.network.networkpractice2;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;

public class HttpPostAsyncTaskLoader extends AsyncTaskLoader<String> {

	private String accessUrl;

	public HttpPostAsyncTaskLoader(Context context, String accessUrl) {
		super(context);
		this.accessUrl = accessUrl;
	}

	@Override
	public String loadInBackground() {
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(this.accessUrl);
		String res = "";

		try {
			ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("quary", "aaaaa"));
			post.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));

			final HttpResponse response = client.execute(post);
			res = EntityUtils.toString(response.getEntity());

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