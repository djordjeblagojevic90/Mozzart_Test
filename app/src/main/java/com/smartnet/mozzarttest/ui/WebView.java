package com.smartnet.mozzarttest.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class WebView extends android.webkit.WebView {

    private final String URL = "https://ds.opap.gr/web_kino/kinoIframe.html?link=https://ds.opap.gr/web_kino\n" +
            "/kino/html/Internet_PRODUCTION/KinoDraw_201910.html&resolution=847x50\n" +
            "0\n";


    public WebView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    /**
     * Helper method to load url
     */
    public void loadUrl() {
        this.loadUrl(URL);
    }

    /**
     * Helper method to enable java script
     */
    @SuppressLint("SetJavaScriptEnabled")
    public void enableJavaScript() {
        WebSettings webSettings = this.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);

    }


    /**
     * Helper to set web view client
     */
    public void setWebViewClient() {
        this.setWebViewClient(new WebViewClient());
    }


    /**
     * Helper method to provide path of app cache
     *
     * @return
     */
    private String provideAppCachePath() {
        return getContext().getCacheDir().getAbsolutePath();
    }
}
