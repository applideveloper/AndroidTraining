package jp.mixi.practice.actionbar.beg;

import android.os.Bundle;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;

public class ActionBarSampleActivity extends SherlockActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setTheme(R.style.Theme_Sherlock);
		setContentView(R.layout.activity_main);

		ActionBar actionBar = getSupportActionBar();

		// ナビゲーションモードに設定
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// タブを生成して追加。タブの選択•解除•再選択をハンドリングするコールバックのTabListenerを設定しないと実行時例外でクラッシュする
		actionBar.addTab(getSupportActionBar().newTab().setText("Tab1").setTabListener(new ActionBarToastActivity("tab1", this)));
		actionBar.addTab(getSupportActionBar().newTab().setText("Tab2").setTabListener(new ActionBarToastActivity("tab2", this)));
		actionBar.addTab(getSupportActionBar().newTab().setText("Tab3").setTabListener(new ActionBarToastActivity("tab3", this)));
	}
}