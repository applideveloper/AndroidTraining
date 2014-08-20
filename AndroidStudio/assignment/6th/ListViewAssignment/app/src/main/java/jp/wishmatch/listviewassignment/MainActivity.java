package jp.wishmatch.listviewassignment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends Activity {

    private Activity mActivity;
    private ListView mListview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mActivity = this;

        // データの作成
        ArrayList<Book> list = new ArrayList<Book>();
        for (int i = 0; i < 20; i++) {
            list.add(new Book("タイトル" + i, "出版社" + i, i * 10));
        }

        mListview = (ListView)findViewById(R.id.BookList);
        // TODO:BookArrayAdapterを作成して下さい。
        // (リストアイテムのレイアウトは用意されているlist_item_book.xmlをしてください。)
//        BookArrayAdapter bookArrayAdapter = new BookArrayAdapter(mActivity, list);
        BookArrayAdapter bookArrayAdapter = new BookArrayAdapter(mActivity, list);
        mListview.setAdapter(bookArrayAdapter);

        // TODO:ListViewにBookArrayAdapterをセットしてください。
        // TODO:ListViewをタップしたとき、BookActivityに遷移するようにしてください。遷移するときにBookクラスのtitleを渡してください。
        // (BookActivityは用意されているものを使用してください)
        mListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Book book = (Book)parent.getItemAtPosition(position);
                Intent intent = new Intent(MainActivity.this, BookActivity.class);
                intent.putExtra("title", book.getTitle());
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

    @Override
    protected void onResume() {
        super.onResume();
        registerForContextMenu(mListview);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, view, menuInfo);
        // ContextMenuを設定
        getMenuInflater().inflate(R.menu.context_menu_main, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        if (item.getItemId() != R.id.DeleteListItem && item.getItemId() != R.id.AddListItem) {
            return false;
        }

        // MenuItemからContextMenuを取得し、AdapterContextMenuInfoにキャストします。
        ContextMenuInfo menuInfo = item.getMenuInfo();
        AdapterContextMenuInfo adapterContextMenuInfo = (AdapterContextMenuInfo) menuInfo;

        // AdapterContextMenuInfoから長押ししたリストアイテムのpositionを取得します。
        int position = adapterContextMenuInfo.position;

        // ListViewから長押しされたリストアイテムを取得します。
        Book book = (Book) mListview.getItemAtPosition(position);

        // ListViewからセットされているAdapterを取得します。
        BookArrayAdapter adapter = (BookArrayAdapter)mListview.getAdapter();

        if (item.getItemId() == R.id.DeleteListItem) {
            // TODO:Adapterを使用して長押ししたデータを削除してください
            Log.v("test", "delete");
            adapter.remove(book);
        } else if (item.getItemId() == R.id.AddListItem) {
            // TODO:Adapterを使用して長押ししたデータを追加してください
            Log.v("test", "add");
            adapter.add(book);
        }

        // TODO:Adapterを使用して表示されているデータを更新してください
        Toast.makeText(this, "更新しました", Toast.LENGTH_SHORT);

        return true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterForContextMenu(mListview);
    }
}
