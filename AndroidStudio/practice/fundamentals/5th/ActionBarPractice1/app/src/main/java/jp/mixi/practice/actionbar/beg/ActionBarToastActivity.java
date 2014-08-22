package jp.mixi.practice.actionbar.beg;

import android.content.Context;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;

public class ActionBarToastActivity extends SherlockActivity implements ActionBar.TabListener {
	private String tabName;
	private Context context;

	public ActionBarToastActivity(String tabName, Context context) {
		super();
		this.tabName = tabName;
		this.context = context;
	}

	// タブナビゲーションのTabtタブが選択された時のコールバック
	@Override
	public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
		Log.v("test", this.tabName);
		Toast.makeText(this.context, this.tabName, Toast.LENGTH_LONG).show();
	}

	// タブナビゲーションのTabが選択解除された時のコールバック
	@Override
	public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

	}

	// タブナビゲーションのTabが再度選択された時のコールバック
	@Override
	public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

	}
}