package jp.wishmatch.asynctasksample;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class MainAsyncTask extends AsyncTask<Void, Runnable, Void> {
	private Context mApplicationContext;
	private TextView textView;

	public MainAsyncTask(Context applicationContext, TextView textView) {
		super();
		mApplicationContext = applicationContext;
		this.textView = textView;
	}

	/**
	 * 非同期処理を実行する前に UI スレッドで実行する処理を書く
	 */
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		Toast.makeText(mApplicationContext, "onPreExecute", Toast.LENGTH_SHORT).show();
	}

	/**
	 * 非同期処理の進捗を受け取るコールバック。
	 */
	@Override
	protected void onProgressUpdate(Runnable... values) {
		super.onProgressUpdate(values);
		Toast.makeText(mApplicationContext, "onProgressUpdate", Toast.LENGTH_SHORT).show();
		for (Runnable runnable : values) {
			runnable.run();
		}
	}

	/**
	 * 非同期処理の本体
	 * 引数は非同期処理内容に渡すためのパラメータ配列。
	 */
	@Override
	protected Void doInBackground(Void... params) {
		// 5秒後にテキストを変更する
		try {
			Thread.sleep(5000L);
			Log.d("TAG", "doInBackground");

			publishProgress(new Runnable() {
				@Override
				public void run() {
					Toast.makeText(mApplicationContext, "doInBackground", Toast.LENGTH_SHORT).show();
					textView.setText("非同期処理実行");
				}
			});
		} catch (InterruptedException e) {
			Log.e(MainAsyncTask.class.getSimpleName(), "thread interrupted: ", e);
		}
		return null;
	}

	/**
	 * 非同期処理の実行後に、UI スレッドで実行する処理。
	 * 引数は {@link AsyncTask#execute(Object...)} の返り値。
	 */
	@Override
	protected void onPostExecute(Void result) {
		super.onPostExecute(result);
		Toast.makeText(mApplicationContext, "onPostExecute", Toast.LENGTH_SHORT).show();
	}
}