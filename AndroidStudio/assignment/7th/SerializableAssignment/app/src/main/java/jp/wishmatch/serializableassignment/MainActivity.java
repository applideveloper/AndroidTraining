package jp.wishmatch.serializableassignment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		NetworkClient client = new NetworkClient();
	    String user = client.getUser(123);
	    String friends = client.getFriends();

	    // 1. NetworkClientからgetUserでJSONのデータを取得し、取得したデータを適切なクラスを作成し、当てはめてください。
		User u = null;
	    try {
		    u = User.parseJson(new JSONObject(user));
	    } catch (JSONException e) {
		    Log.v("exception", e.toString());
	    }

	    // 2. さらにgetFriendsで友人の名前の一覧をListViewで表示してください。
	    final List<User> friendList = new ArrayList<User>();
	    List<String> friendNameList = new ArrayList<String>();
		JSONArray array = null;

	    try {
		    array = new JSONArray(friends);
		    // 使い回し用一時変数
		    User dummyUser = null;

		    for (int i = 0; i < array.length(); i++) {
			    JSONObject obj = array.getJSONObject(i);
				dummyUser = User.parseJson(obj);
			    friendList.add(dummyUser);
			    friendNameList.add(dummyUser.getName());
		    }
	    } catch (JSONException e) {
		    Log.v("exception", e.toString());
	    }
	    // 3. タップした友人の全情報をDetailActivityで表示してください。
		ListView listView = (ListView) findViewById(R.id.FriendList);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, friendNameList);
	    listView.setAdapter(adapter);
	    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
	        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent(MainActivity.this, DetailActivity.class);
				intent.putExtra("user", User.toJson(friendList.get(position)));
				startActivity(intent);
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
