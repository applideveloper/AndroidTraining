package jp.wishmatch.sharedpreferences;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends Activity implements SharedPreferences.OnSharedPreferenceChangeListener {

	private SharedPreferences mSharedPreferences;
	private int mNum;
	private TextView mCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

	    // 初期値設定
	    mSharedPreferences = getSharedPreferences("counter", MODE_PRIVATE);
	    mNum = mSharedPreferences.getInt("count", 0);
	    mCount = (TextView)findViewById(R.id.count);
	    mCount.setText(Integer.toString(mNum));

	    // ボタンを押されたときの更新
	    Button countUp = (Button)findViewById(R.id.countup);
	    countUp.setOnClickListener(new View.OnClickListener() {
		    @Override
		    public void onClick(View v) {
			    SharedPreferences.Editor editor = MainActivity.this.mSharedPreferences.edit();
			    editor.putInt("count", ++MainActivity.this.mNum);
			    editor.commit();
		    }
	    });

	    mSharedPreferences.registerOnSharedPreferenceChangeListener(this);
    }

	@Override
	public void onDestroy() {
		mSharedPreferences.unregisterOnSharedPreferenceChangeListener(this);
		super.onDestroy();
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
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
		if(key.equals("count")) {
			int value = sharedPreferences.getInt("count", 0);
			mCount.setText(Integer.toString(value));
		}
	}
}
