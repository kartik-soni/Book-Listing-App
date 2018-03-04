package com.example.kartik.booklistingapp;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KARTIK on 3/4/2018.
 */

public class BooksAdapter extends ArrayAdapter<Books> {
    public BooksAdapter(Context context, List<Books> earthquakes){
        super(context,0,earthquakes);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }
        Books currentBooks = getItem(position);
        String title = currentBooks.getTitle();
        String author = currentBooks.getAuthor();

        TextView titleView = listItemView.findViewById(R.id.title);
        TextView authorView = listItemView.findViewById(R.id.author);
        titleView.setText(title);
        authorView.setText(author);
        return listItemView;
    }
}
