package jp.wishmatch.networkassignment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class VideoListItemAdapter extends ArrayAdapter<Video> {

	private LayoutInflater mLayoutInflater;

	public VideoListItemAdapter(Context context, List<Video> objects) {
		super(context, 0, objects);
		mLayoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = null;

		if (convertView == null) {
			view = mLayoutInflater.inflate(R.layout.video_list_item, parent, false);
		} else {
			view = convertView;
		}

		Video video = getItem(position);

		TextView title = (TextView)view.findViewById(R.id.Title);
		TextView author = (TextView)view.findViewById(R.id.Author);

		title.setText(video.getTitle());
		author.setText(video.getAuthor());

		return view;
	}


}
