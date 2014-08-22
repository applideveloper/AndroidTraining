
package jp.mixi.practice.interaction.beg;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.action_settings:
				Toast.makeText(this, "press Settings", Toast.LENGTH_SHORT).show();
				return true;
			case R.id.action_login:
				Toast.makeText(this, "press Login", Toast.LENGTH_SHORT).show();
				return true;
			case R.id.action_refresh:
				Toast.makeText(this, "press Refresh", Toast.LENGTH_SHORT).show();
				return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
