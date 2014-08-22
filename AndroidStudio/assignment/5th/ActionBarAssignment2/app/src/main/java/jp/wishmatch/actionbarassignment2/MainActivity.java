package jp.wishmatch.actionbarassignment2;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends Activity implements ActionBar.TabListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

	    ActionBar actionBar = getActionBar();
	    actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);

	    actionBar.addTab(actionBar.newTab().setText("Tab1").setTabListener(this));
	    actionBar.addTab(actionBar.newTab().setText("Tab2").setTabListener(this));
		actionBar.addTab(actionBar.newTab().setText("Tab3").setTabListener(this));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
	    MenuItem actionItem1 = menu.add("exit");
	    actionItem1.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
	    actionItem1.setIcon(R.drawable.ic_launcher);

	    MenuItem actionItem2 = menu.add("sub");
	    actionItem2.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
	    actionItem2.setIcon(android.R.drawable.arrow_up_float);
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
	    if (item.getTitle().toString().equals("exit")) {
		    finish();
	    } else {
		    Intent intent = new Intent(this, SubActivity.class);
		    startActivity(intent);
	    }
	    return true;
    }

	@Override
	public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {

	}

	@Override
	public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

	}

	@Override
	public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

	}
}
