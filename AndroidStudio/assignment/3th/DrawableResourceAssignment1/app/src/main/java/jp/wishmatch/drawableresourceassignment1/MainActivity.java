package jp.wishmatch.drawableresourceassignment1;

import android.content.res.Configuration;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

	    Configuration config = getResources().getConfiguration();
	    RelativeLayout main = (RelativeLayout)findViewById(R.id.main);

	    if(config.orientation == Configuration.ORIENTATION_LANDSCAPE) {
		    // Landscape(横長:青)
		    main.setBackgroundResource(R.drawable.landscape_style);
	    } else if (config.orientation == Configuration.ORIENTATION_PORTRAIT) {
		    // Portrait(縦長:緑)
		    main.setBackgroundResource(R.drawable.portrait_style);
	    }
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
}
