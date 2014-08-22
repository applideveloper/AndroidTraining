package jp.wishmatch.interactioncallbackassignment2;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends Activity {

	private TextWatcher mTextWatcher = new TextWatcher() {
		@Override
		public void beforeTextChanged(CharSequence s, int start, int count, int after) {

		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {
			TextView counter = (TextView)findViewById(R.id.counter);
			counter.setText("counter : " + s.length());
		}

		@Override
		public void afterTextChanged(Editable s) {

		}
	};

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
	protected void onStart() {
		super.onStart();
		EditText form = (EditText)findViewById(R.id.form);
		form.addTextChangedListener(mTextWatcher);
	}

	@Override
	protected void onStop() {
		super.onStop();
		EditText form = (EditText)findViewById(R.id.form);
		form.addTextChangedListener(mTextWatcher);
	}
}
