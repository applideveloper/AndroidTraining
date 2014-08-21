package jp.mixi.practice.network.networkpractice1;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends FragmentActivity implements LoaderManager.LoaderCallbacks<String> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            StrictMode.setThreadPolicy(
                    new StrictMode.ThreadPolicy.Builder()
                    .detectNetwork()
                    .penaltyDeath()
                    .build());
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View buttonGet = findViewById(R.id.buttonGet);

	    final Context _this = this;
	    TextView accessUrl = (TextView)findViewById(R.id.accessUrl);
	    final String url = accessUrl.getText().toString();

        buttonGet.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                // http getの処理を書く
				LoaderManager manager = getSupportLoaderManager();
				Bundle args = new Bundle();
	            args.putString("accessUrl", url);
	            manager.initLoader(0, args, MainActivity.this);
            }
        });

        View buttonPost = findViewById(R.id.buttonPost);
        buttonPost.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                // http postの処理を書く
	            LoaderManager manager = getSupportLoaderManager();
	            Bundle args = new Bundle();
	            args.putString("accessUrl", url);
	            manager.initLoader(1, args, MainActivity.this);
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
	public Loader<String> onCreateLoader(int id, Bundle bundle) {
		switch (id) {
			case 0: //get
				return new HttpGetAsyncTaskLoader(this, bundle.getString("accessUrl"));
			case 1:
				return new HttpPostAsyncTaskLoader(this, bundle.getString("accessUrl"));
			default:
				return null;
		}
	}

	@Override
	public void onLoadFinished(Loader<String> stringLoader, String str) {
		TextView response = (TextView)findViewById(R.id.response);
//		TextView html = new TextView(this);
//		html.setText(str);
//		response.addView(html);
		response.setText(str);
	}

	@Override
	public void onLoaderReset(Loader<String> loader) {

	}
}
