package com.uit.didong.thibanglaixemay.meothi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import com.uit.didong.thibanglaixemay.R;

public class lythuyetwebview extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ythuyetwebview);
        WebView ww = findViewById(R.id.webViewxahinh);
        ww.getSettings().setJavaScriptEnabled(true);
        ww.getSettings().setBuiltInZoomControls(true);
        ww.loadUrl("file:///android_asset/0.html");
    }
}
