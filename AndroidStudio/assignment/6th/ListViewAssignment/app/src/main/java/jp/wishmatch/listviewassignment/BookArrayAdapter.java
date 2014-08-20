package jp.wishmatch.listviewassignment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class BookArrayAdapter extends ArrayAdapter<Book>{
    private LayoutInflater mLayoutInflater;

    public BookArrayAdapter(Context context, List<Book> objects) {
        super(context, 0, objects);
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;

        if (convertView == null) {
            view = mLayoutInflater.inflate(R.layout.list_item_book, parent, false);
        } else {
            view = convertView;
        }

        Book book = getItem(position);
        TextView title = (TextView)view.findViewById(R.id.Title);
        TextView Publisher = (TextView)view.findViewById(R.id.Publisher);
        TextView Price = (TextView)view.findViewById(R.id.Price);

        title.setText(book.getTitle());
        Publisher.setText(book.getPublisher());
        Price.setText(Integer.toString(book.getPrice()) + "円");
        return view;
    }

}