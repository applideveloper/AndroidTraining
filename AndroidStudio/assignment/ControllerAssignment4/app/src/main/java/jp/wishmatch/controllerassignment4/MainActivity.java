package jp.wishmatch.controllerassignment4;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.lang.ref.WeakReference;

/**
 * TODO: 課題4
 * この Activity 内で、メモリリークを引き起こす原因を特定し、リークしないように修正してください。
 * (Activity のライフサイクルを超えた参照によってメモリリークが引き起こされます。
 * 画面回転や、アプリの終了、他のアプリへの遷移等で動作を見てみましょう。)
 *
 * Hint:
 * この Activity では、端末内全体に送られているメッセージを受け取るための仕組み（ブロードキャストレシーバ）
 * を利用しています。
 * ブロードキャスト等のメッセージングについての詳細は今後の研修で触れますが、
 * この Activity のライフサイクルの中でブロードキャストレシーバが動作している必要があります。
 *
 * {@link Activity#registerReceiver(android.content.BroadcastReceiver, android.content.IntentFilter)}
 *
 * @author keishin.yokomaku
 *
 */
public class MainActivity extends ActionBarActivity {
    public static final String TAG = MainActivity.class.getSimpleName();

    private MyBroadcastReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // ヘッドセットの接続状態を監視し、接続状態の変化があった時のブロードキャストメッセージを受信する
//        registerReceiver(new MyBroadcastReceiver(), new IntentFilter(Intent.ACTION_HEADSET_PLUG));
    }

    @Override
    protected void onStart() {
        super.onStart();

        // ヘッドセットの接続状態を監視し、接続状態の変化があったときのブロードキャストメッセージを受信する
        receiver = new MyBroadcastReceiver();
        WeakReference<MyBroadcastReceiver> wrReceiver = new WeakReference<MyBroadcastReceiver>(receiver);
        registerReceiver(wrReceiver.get(), new IntentFilter(Intent.ACTION_HEADSET_PLUG));
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (receiver != null) {
            unregisterReceiver(receiver);
        }
    }

    // ブロードキャストのメッセージを受け取るクラス
    private class MyBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            // ブロードキャストのメッセージが届いたらログに吐き出す
            Log.v(TAG, "Broadcast intent received.");
            Toast.makeText(MainActivity.this, "Headset broadcast received.", Toast.LENGTH_LONG).show();
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
