package jp.wishmatch.controllerlifecycleassignment2;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * TODO: 課題2
 * 画面回転や、他のアプリ・画面の起動等で、状態遷移が起こると、それ以前の状態で持っていたデータが失われてしまいます。
 * これを防ぐため、この Activity の中で状態管理をしてください。
 * @author keishin.yokomaku
 */
public class SubActivity extends Activity implements TextWatcher {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Hint: 状態遷移が何も起こっていない場合は、savedInstanceState は null です
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        Toast.makeText(this, "SubActivity\nonCreate()", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "SubActivity\nonStart()", Toast.LENGTH_SHORT).show();

        EditText text = (EditText) findViewById(R.id.Editor);
        text.addTextChangedListener(this);
    }

    /**
     * TODO: 復帰処理はこちらか onCreate
     */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        Log.d("Lifecycle","onRestoreInstanceState()");
        Toast.makeText(this, "SubActivity\nonRestoreInstanceState()", Toast.LENGTH_SHORT).show();

        //インスタンスの復帰
        String str = savedInstanceState.getString("EDITTEXT_KEY");
//        TextView tv = (TextView)findViewById(R.id.Editor);
//        tv.setText("onRestoreInstanceState:" + str);
        EditText text = (EditText)findViewById(R.id.Editor);
        text.setText(str);
    }

    /**
     * TODO: 保存処理はこちら
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        Log.d("Lifecycle","onSaveInstanceState()");
        Toast.makeText(this, "SubActivity\nonSaveInstanceState()", Toast.LENGTH_SHORT).show();

        //インスタンスの保存
        EditText text = (EditText)findViewById(R.id.Editor);
        Editable editable = text.getText();

        outState.putString("EDITTEXT_KEY", editable.toString() );
    }

    @Override
    protected void onStop() {
        super.onStop();
        EditText text = (EditText) findViewById(R.id.Editor);
        text.removeTextChangedListener(this);

        Toast.makeText(this, "SubActivity\nonStop()", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void afterTextChanged(Editable s) {

        Toast.makeText(this, "SubActivity\nafterTextChanged()", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        Toast.makeText(this, "SubActivity\nbeforeTextChanged()", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        TextView text = (TextView) findViewById(R.id.SyncedText);
        text.setText(s);

        Toast.makeText(this, "SubActivity\nonTextChanged()", Toast.LENGTH_SHORT).show();
    }
}