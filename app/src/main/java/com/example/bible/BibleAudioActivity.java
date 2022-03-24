package com.example.bible;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class BibleAudioActivity extends AppCompatActivity {

    public WebView mWebView;
    private ProgressBar spinner;
    private String VisibilityWebViewInitialUse = "show";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bible_audio);

        mWebView = (WebView) findViewById(R.id.webview);
        mWebView.loadUrl("https://www.dropbox.com/sh/dpmwf1t4al78wez/AABDxe71WlAtn_xyks8yMVVEa?dl=0");

        // Enable Javascript
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        spinner = findViewById(R.id.progressBar1);
        mWebView.setWebViewClient(new CustomWebViewClient());

    }

    private class CustomWebViewClient extends WebViewClient {

        @Override
        public void onPageStarted(WebView mWebView, String url, Bitmap favicon) {
            // only make it invisible the FIRST time the app is run
            if (VisibilityWebViewInitialUse.equals("show")) {
                mWebView.setVisibility(mWebView.INVISIBLE);
            }
        }

        @Override
        public void onPageFinished(WebView view, String url) {

            VisibilityWebViewInitialUse = "hide";
            spinner.setVisibility(View.GONE);

            view.setVisibility(mWebView.VISIBLE);
            super.onPageFinished(view, url);

        }
    }
}