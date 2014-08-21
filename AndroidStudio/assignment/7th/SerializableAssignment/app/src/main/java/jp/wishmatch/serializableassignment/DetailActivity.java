package jp.wishmatch.serializableassignment;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;

public class DetailActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstance) {
		super.onCreate(savedInstance);
		setContentView(R.layout.activity_detail);

		User u = null;
		try {
			u = User.parseJson(new JSONObject(getIntent().getStringExtra("user").toString()));
		} catch (JSONException e) {
			Log.v("exception", e.toString());
		}

		// 各項目を表示
		TextView userIdView = (TextView)findViewById(R.id.userId);
		userIdView.setText(Integer.toString(u.getId()));

		TextView userNameView = (TextView)findViewById(R.id.userName);
		userNameView.setText(u.getName());

		TextView userAgeView = (TextView) findViewById(R.id.userAge);
		userAgeView.setText(Integer.toString(u.getAge()));

		TextView userKeywordView = (TextView)findViewById(R.id.userKeyword);
		userKeywordView.setText(u.getKeyword());

		TextView userStatusView = (TextView)findViewById(R.id.userStatusText);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		userStatusView.setText(u.getStatus().getText() + " " + simpleDateFormat.format(u.getStatus().getPostedDate()));

		TextView userJoinDateView = (TextView)findViewById(R.id.userJoinDateText);
		userJoinDateView.setText(u.getJoinDate().getYear() + "/" + u.getJoinDate().getMonth() + "/" + u.getJoinDate().getDate());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}