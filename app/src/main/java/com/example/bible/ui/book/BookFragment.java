package com.example.bible.ui.book;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.bible.Book;
import com.example.bible.BookViewActivity;
import com.example.bible.MyBookListAdapter;
import com.example.bible.R;
import com.example.bible.databinding.FragmentBookBinding;

import java.util.ArrayList;
import java.util.List;

public class BookFragment extends Fragment {

    private BookViewModel bookViewModel;
    private FragmentBookBinding binding;
    private List<Book> bookList;
    private ListView listView;

    String[] bookTitle;
    String[] bookLink;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        bookViewModel = new ViewModelProvider(this).get(BookViewModel.class);

        binding = FragmentBookBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        bookList = new ArrayList<>();
        listView = root.findViewById(R.id.listView);
        bookTitle = getResources().getStringArray(R.array.bookTitle);
        bookLink = getResources().getStringArray(R.array.bookLink);
//        RelativeLayout layout = root.findViewById(R.id.sharikovSongBook);

        for (int i = 0; i < bookTitle.length; i++) {
            Book list = new Book(bookTitle[i], bookLink[i]);
            bookList.add(list);
        }

        MyBookListAdapter adapter = new MyBookListAdapter(getActivity().getApplicationContext(), R.layout.listview, bookList);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String list = bookList.get(position).getTitle();
                String url = bookList.get(position).getLink();
                Uri link = Uri.parse(url);
                if (list.equals("Sharikov Songbook")) {
                    Intent myIntent = new Intent(getActivity(), BookViewActivity.class);
                    startActivity(myIntent);
                } else {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, link);
                    startActivity(browserIntent);
                }
            }
        });

//        layout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                layout.getContext().startActivity(new Intent(layout.getContext(), BookViewActivity.class));
//            }
//        });

        bookViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}