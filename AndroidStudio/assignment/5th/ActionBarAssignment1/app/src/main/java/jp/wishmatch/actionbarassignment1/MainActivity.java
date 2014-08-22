package jp.wishmatch.actionbarassignment1;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends Activity implements ActionBar.OnNavigationListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

	    setContentView(R.layout.activity_main);

	    // actionBarの設定
	    ActionBar actionBar = getActionBar();
	    actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
	    actionBar.setDisplayHomeAsUpEnabled(true);


	    // ページ遷移
	    Button toSub = (Button)findViewById(R.id.toSub);
	    Button toSub1 = (Button)findViewById(R.id.toSub1);

	    toSub.setOnClickListener(new View.OnClickListener() {
		    @Override
		    public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, SubActivity.class);
			    startActivity(intent);
		    }
	    });

	    toSub1.setOnClickListener(new View.OnClickListener() {
		    @Override
		    public void onClick(View v) {
			    Intent intent = new Intent(MainActivity.this, Sub1Activity.class);
			    startActivity(intent);
		    }
	    });
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
//        int id = item.getItemId();
//        if (id == R.id.action_settings) {
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
	    switch (item.getItemId()) {
		    case android.R.id.home:
			    Log.v("test", "home @main");
				finish();
			    return true;
		    default:
			    Log.v("test", "default");
			    return super.onOptionsItemSelected(item);
	    }
    }

	@Override
	public boolean onNavigationItemSelected(int itemPosition, long itemId) {
		return false;
	}
}
