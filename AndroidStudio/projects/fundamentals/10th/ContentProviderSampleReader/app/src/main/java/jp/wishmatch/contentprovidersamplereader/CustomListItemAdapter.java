package jp.wishmatch.contentprovidersamplereader;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class CustomListItemAdapter extends ArrayAdapter<Book> {

	private LayoutInflater mLayoutInflater;

	public CustomListItemAdapter(Context context, int resource, List<Book> objects) {
		super(context, resource, objects);
		mLayoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = null;

		if (convertView == null) {
			view = mLayoutInflater.inflate(R.layout.custom_list_item, parent, false);
		} else {
			view = convertView;
		}

		Book item = getItem(position);

		TextView _id = (TextView)view.findViewById(R.id._id);
		_id.setText(item.get_id());

		TextView title = (TextView)view.findViewById(R.id.title);
		title.setText(item.getTitle());

		TextView publisher = (TextView)view.findViewById(R.id.publisher);
		publisher.setText(item.getPublisher());

		TextView price = (TextView)view.findViewById(R.id.price);
		price.setText(item.getPrice());

		return view;
	}
}