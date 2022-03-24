package com.example.bible;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class MyBookListAdapter extends ArrayAdapter<Book> {
    List<Book> bookList;
    Context context;
    int resource;

    public MyBookListAdapter(@NonNull Context context, int resource, List<Book> bookList) {
        super(context, resource, bookList);
        this.context = context;
        this.resource = resource;
        this.bookList = bookList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        @SuppressLint("ViewHolder") View view = layoutInflater.inflate(resource, null, false);

        TextView bookTitle = view.findViewById(R.id.bookTitle);

        Book book = bookList.get(position);
        bookTitle.setText(book.getTitle());

        return view;
    }
}
