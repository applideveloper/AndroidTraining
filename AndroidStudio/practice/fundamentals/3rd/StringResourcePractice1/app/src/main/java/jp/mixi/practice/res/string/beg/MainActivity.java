
package jp.mixi.practice.res.string.beg;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {
    private final int WC = LinearLayout.LayoutParams.WRAP_CONTENT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

//        テキストビューを生成
//        TextView text1_en = new TextView(this);
//        text1_en.setText(R.string.text1_en);
//        // レイアウトにテキストビューを追加
//        LinearLayout layout1_en = new LinearLayout(this);
//        layout1_en.addView(text1_en, new LinearLayout.LayoutParams(
//                LinearLayout.LayoutParams.WRAP_CONTENT,
//                LinearLayout.LayoutParams.WRAP_CONTENT));
//        setContentView(layout1_en);

        // テキストビューを生成
//        TextView text1_ja = new TextView(this);
//        text1_ja.setText(R.string.text1_ja);
        // レイアウトにテキストビューを追加
//        LinearLayout layout1_ja = new LinearLayout(this);
//        layout1_ja.addView(text1_ja, new LinearLayout.LayoutParams(
//                LinearLayout.LayoutParams.WRAP_CONTENT,
//                LinearLayout.LayoutParams.WRAP_CONTENT
//        ));
//        setContentView(layout1_ja);

//        テキストビューを生成
        TextView text1_en = new TextView(this);
        text1_en.setText(R.string.text1_en);

        TextView text2_en = new TextView(this);
        text2_en.setText(R.string.text2_en);

        TextView text3_en = new TextView(this);
        text3_en.setText(R.string.text3_en);

        TextView text4_en = new TextView(this);
        text4_en.setText(R.string.text4_en);

        TextView text5_en = new TextView(this);
        text5_en.setText(R.string.text5_en);

        TextView text1_ja = new TextView(this);
        text1_ja.setText(R.string.text1_ja);

        TextView text2_ja = new TextView(this);
        text2_ja.setText(R.string.text2_ja);

        TextView text3_ja = new TextView(this);
        text3_ja.setText(R.string.text3_ja);

        TextView text4_ja = new TextView(this);
        text4_ja.setText(R.string.text4_ja);

        TextView text5_ja = new TextView(this);
        text5_ja.setText(R.string.text5_ja);

        String quantity2 = getResources().getQuantityString(R.plurals.my_eggs_ja, 2, 10);
        TextView text6 = new TextView(this);
        text6.setText(quantity2);

        String quantity1 = getResources().getQuantityString(R.plurals.my_eggs_ja, 1);
        TextView text7 = new TextView(this);
        text7.setText(quantity1);

        // レイアウトにテキストビューを追加
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);

        layout.addView(text1_en, new LinearLayout.LayoutParams(WC, WC));

        layout.addView(text2_en, new LinearLayout.LayoutParams(WC, WC));

        layout.addView(text3_en, new LinearLayout.LayoutParams(WC, WC));

        layout.addView(text4_en, new LinearLayout.LayoutParams(WC, WC));

        layout.addView(text5_en, new LinearLayout.LayoutParams(WC, WC));

        layout.addView(text1_ja, new LinearLayout.LayoutParams(WC, WC));

        layout.addView(text2_ja, new LinearLayout.LayoutParams(WC, WC));

        layout.addView(text3_ja, new LinearLayout.LayoutParams(WC, WC));

        layout.addView(text4_ja, new LinearLayout.LayoutParams(WC, WC));

        layout.addView(text5_ja, new LinearLayout.LayoutParams(WC, WC));

        layout.addView(text6, new LinearLayout.LayoutParams(WC, WC));

        layout.addView(text7, new LinearLayout.LayoutParams(WC, WC));

        setContentView(layout);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
