package jp.wishmatch.actionbarassignment1;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class SubActivity extends Activity implements ActionBar.OnNavigationListener{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sub);

		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setDisplayHomeAsUpEnabled(true);

		// ページ遷移
		Button toHome = (Button)findViewById(R.id.toHome);
		Button toSub1 = (Button)findViewById(R.id.toSub1);

		toHome.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(SubActivity.this, SubActivity.class);
				startActivity(intent);
			}
		});

		toSub1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(SubActivity.this, Sub1Activity.class);
				startActivity(intent);
			}
		});
	}

	@Override
	public boolean onNavigationItemSelected(int itemPosition, long itemId) {
		return false;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:
				Log.v("test", "home @sub");
				finish();
				return true;
			default:
				Log.v("test", "default");
				return super.onOptionsItemSelected(item);
		}
	}
}