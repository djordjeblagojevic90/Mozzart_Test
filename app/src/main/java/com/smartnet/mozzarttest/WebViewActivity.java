package com.smartnet.mozzarttest;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.smartnet.mozzarttest.ui.WebView;

public class WebViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_layout);
        showWebViewAnimation();
    }

    private void showWebViewAnimation() {
        WebView webView = findViewById(R.id.web_view);
        webView.enableJavaScript();
        webView.loadUrl();
        webView.setWebViewClient();
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finishAndRemoveTask();
        super.onBackPressed();
    }
}
