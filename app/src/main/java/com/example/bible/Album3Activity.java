package com.example.bible;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class Album3Activity extends AppCompatActivity {

    private WebView mWebView;
    private ProgressBar spinner;
    private String VisibilityWebViewInitialUse = "show";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album3);

        mWebView = (WebView) findViewById(R.id.webview);
        mWebView.loadUrl("https://sharikovministries.com/%D1%82%D1%8B-%D0%BC%D0%BE%D1%8F-%D0%B6%D0%B8%D0%B7%D0%BD%D1%8C/");

        // Enable Javascript
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        mWebView.setOverScrollMode(WebView.OVER_SCROLL_NEVER);

        spinner = findViewById(R.id.progressBar1);
        mWebView.setWebViewClient(new CustomWebViewClient());


    }

//    @Override
//    public void onBackPressed() {
//        if (mWebView.canGoBack()) {
//            mWebView.goBack();
//        }else {
//            super.onBackPressed();
//        }
//    }
//

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