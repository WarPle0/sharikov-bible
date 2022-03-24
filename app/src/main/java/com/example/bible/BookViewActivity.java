package com.example.bible;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;

public class BookViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_view);

        PDFView pdfView = findViewById(R.id.pdfView);
        pdfView.fromAsset("Sharikov Songbook.pdf").load();
    }
}