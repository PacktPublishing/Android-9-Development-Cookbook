package com.packtpub.webview;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    private class mWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (Uri.parse(url).getHost().equals("www.packtpub.com")) {
                return false;  //Don't override since it's the same host
            } else {
                return true; //Stop the navigation since it's a different site
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WebView webview = new WebView(this);
        setContentView(webview);
        webview.setWebViewClient(new mWebViewClient());
        webview.loadUrl("https://www.packtpub.com/");
    }


}
