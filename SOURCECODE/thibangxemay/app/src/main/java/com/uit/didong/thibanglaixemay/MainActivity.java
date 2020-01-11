package com.uit.didong.thibanglaixemay;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.uit.didong.thibanglaixemay.debaithi.ViewCauHoi;
import com.uit.didong.thibanglaixemay.debaithi.ViewDe;
import com.uit.didong.thibanglaixemay.meothi.MeThiChinh;
import com.uit.didong.thibanglaixemay.meothi.MeoTH;

public class MainActivity extends AppCompatActivity {

    ImageView imgPicture_main;
    Button btnonThi, btnlamDe, btnmeoThi, btnTH;

    public void AnhXa() {
        imgPicture_main = findViewById(R.id.imageview);
        btnlamDe = findViewById(R.id.buttonLamDe);
        btnmeoThi = findViewById(R.id.buttonMeoThi);
        btnonThi = findViewById(R.id.buttonOnThi);
        btnTH = findViewById(R.id.btn4);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnhXa();
        imgPicture_main.setBackgroundResource(R.drawable.back);
        btnonThi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mhon = new Intent(getApplicationContext(), ViewCauHoi.class);
                startActivity(mhon);
            }
        });
        btnlamDe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chon();
            }
        });
        btnmeoThi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mhmeothi = new Intent(getApplicationContext(), MeThiChinh.class);
                startActivity(mhmeothi);
            }
        });
        btnTH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent thmeothi = new Intent(getApplicationContext(), MeoTH.class);
                startActivity(thmeothi);
            }
        });
    }

    //code menu share
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.share, menu);
        //menu share
        MenuItem item = menu.findItem(R.id.action_share);
        final ShareActionProvider myShareActionProvider = (ShareActionProvider) MenuItemCompat
                .getActionProvider(item);
        item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                //menu share
                try {
                    Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                    sharingIntent.setType("text/plain");
                    sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=com.uit.didong.thibanglaixemay");
                    sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject");
                    startActivity(Intent.createChooser(sharingIntent, "Chia sẻ"));
                    myShareActionProvider.setShareIntent(sharingIntent);

                } catch (Exception c) {
                }
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    //code thông báo bắt đâu làm đề thi
    public void chon() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                MainActivity.this);
        alertDialog.setTitle("    Bắt đầu?");
        alertDialog.setMessage("  Bạn đã sẵn sàng chưa?");
        alertDialog.setIcon(R.drawable.c);
        alertDialog.setPositiveButton("Sẵn sàng",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,
                                        int which) {

                        Intent intent = new Intent(
                                MainActivity.this, ViewDe.class);
                        startActivity(intent);
                        Log.d("s", "s");

                    }

                });
        alertDialog.setNegativeButton("Hủy Bỏ",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,
                                        int which) {
                    }
                });
        alertDialog.show();
    }

    public void onBackPressed() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                MainActivity.this);
        alertDialog.setIcon(R.drawable.comment);
        alertDialog.setTitle("        Kính Mời !");
        alertDialog.setMessage("Bạn hãy giúp đánh giá ứng dụng ");
        alertDialog.setPositiveButton("Lúc Khác",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        finish();
                    }

                });
        alertDialog.setNegativeButton("Bây Giờ",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        Intent i = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.uit.didong.thibanglaixemay"));
                        startActivity(i);
                    }
                });
        alertDialog.show();
    }
}
