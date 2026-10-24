package com.clipboardshield.app;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class LoginActivity extends Activity {

    private static final String FRONTEND_URL = "http://192.168.0.106:8080/index.html";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        WebView webView = findViewById(R.id.webView);
        WebSettings webSettings = webView.getSettings();

        // Enable JavaScript, DOM storage, and local asset support
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setDatabaseEnabled(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setAllowContentAccess(true);
        webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);

        // Keep navigation inside WebView instead of launching external browser
        webView.setWebViewClient(new WebViewClient());

        // Load the frontend from your PC server
        webView.loadUrl(FRONTEND_URL);
    }
}