package com.uit.didong.thibanglaixemay.meothi;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import com.uit.didong.thibanglaixemay.R;

public class MeoTH extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meothwebview);
        WebView ww = findViewById(R.id.webViewTH);
        ww.getSettings().setJavaScriptEnabled(true);
        ww.getSettings().setBuiltInZoomControls(true);
        ww.loadUrl("file:///android_asset/TH.html");
    }
}
