package jp.wishmatch.listviewassignment;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class BookActivity extends Activity {

    @SuppressWarnings("unused")
    private static final String TAG = BookActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_book);

        // TODO:MainActivityから送られてきたtitleを表示してください
        String title = getIntent().getStringExtra("title");

        TextView textView = (TextView)findViewById(R.id.BookTitle);
        textView.setText(title);
    }
}