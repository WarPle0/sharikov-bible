package com.example.bible;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class SermonsActivity extends AppCompatActivity {

    public WebView mWebView;
    private ProgressBar spinner;
    private String VisibilityWebViewInitialUse = "show";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sermons);

        mWebView = (WebView) findViewById(R.id.webview);
        mWebView.loadUrl("https://www.youtube.com/channel/UCQ_1ClPcArkHtl3DHrvbqXg");

        // Enable Javascript
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        mWebView.setWebViewClient(new WebViewClient());

    }

    @Override
    public void onBackPressed() {
        if (mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }

}