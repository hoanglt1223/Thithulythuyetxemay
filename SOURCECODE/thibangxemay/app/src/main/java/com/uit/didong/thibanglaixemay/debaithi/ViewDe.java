package com.uit.didong.thibanglaixemay.debaithi;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.WindowManager;
import android.widget.TextView;

import com.uit.didong.thibanglaixemay.R;
import com.uit.didong.thibanglaixemay.SQLiteCauHoi;
import com.uit.didong.thibanglaixemay.SQLiteDataController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;


public class ViewDe extends FragmentActivity {
    final CounterClass timer = new CounterClass(900000, 1000);
    public ArrayList<Integer> arrNumber = new ArrayList<Integer>();
    ArrayList<Fragment> fragmentList = new ArrayList<Fragment>();
    TextView textViewTime;
    List<String> titleList = new ArrayList<String>();
    ArrayList<cauhoi> cauhoilist;
    private Random random = new Random();

    private void createDB() {
// khởi tạo database
        SQLiteDataController sql = new SQLiteDataController(this);
        try {
            sql.isCreatedDatabase();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //code randomdilieu
    private void getListCauHoi() {
        SQLiteCauHoi cauhoi = new SQLiteCauHoi(this);
        cauhoilist = new ArrayList<>();
        cauhoilist = cauhoi.getListcauhoi();
    }

    //code random cau hoi
    public int randomLythuyet() {
        int n = random.nextInt(80);
        if (arrNumber.size() == 0) {
            arrNumber.add(n);
            return n;
        } else {
            if (arrNumber.contains(n)) {
                return randomLythuyet();
            } else {
                arrNumber.add(n);
                return n;
            }
        }
    }

    public int randomBienbao() {
        int a = 81;
        int b = 115;
        int n = random.nextInt(b - a) + a;
        if (arrNumber.size() == 0) {
            arrNumber.add(n);
            return n;
        } else {
            if (arrNumber.contains(n)) {
                return randomBienbao();
            } else {
                arrNumber.add(n);
                return n;
            }
        }
    }

    public int randomXahinh() {
        int c = 116;
        int d = 150;
        int n = random.nextInt(d - c) + c;
        if (arrNumber.size() == 0) {
            arrNumber.add(n);
            return n;
        } else {
            if (arrNumber.contains(n)) {
                return randomXahinh();
            } else {
                arrNumber.add(n);
                return n;
            }
        }
    }

    public void queryQuestion() {
        getListCauHoi();
        for (int i = 0; i < 10; i++) {
            int x = randomLythuyet();
            fragmentList.add(new FragmentLamDe(cauhoilist.get(x).getCauhoi(), cauhoilist.get(x), cauhoilist.get(x)));
        }
        for (int i = 10; i < 15; i++) {
            int x = randomBienbao();
            fragmentList.add(new FragmentLamDe(cauhoilist.get(x).getCauhoi(), cauhoilist.get(x), cauhoilist.get(x)));
        }
        for (int i = 15; i < 20; i++) {
            int x = randomXahinh();
            fragmentList.add(new FragmentLamDe(cauhoilist.get(x).getCauhoi(), cauhoilist.get(x), cauhoilist.get(x)));
        }
    }//hết code random

    public void onStart() {
        super.onStart();
        timer.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_de);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        ViewPager vp = findViewById(R.id.viewPager);
        createDB();
        textViewTime = findViewById(R.id.textViewthoigian);
        textViewTime.setText("15:00");
        queryQuestion();
        vp.setAdapter(new myPagerAdapter(getSupportFragmentManager(), fragmentList, titleList));
        Bundle b = getIntent().getExtras();

        if (b != null) {
            Integer j = (Integer) b.get("a1");
            vp.setCurrentItem(j);
        }
    }

    public void onBackPressed() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                ViewDe.this);
        alertDialog.setMessage("Bạn có muốn thoát ?");
        alertDialog.setPositiveButton("Thoát",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        finish();
                    }

                });
        alertDialog.setNegativeButton("Hủy",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,
                                        int which) {
                    }
                });
        alertDialog.show();
    }

    //code time count down
    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    @SuppressLint("NewApi")
    public class CounterClass extends CountDownTimer {
        public CounterClass(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {
            textViewTime.setText("Hết Giờ .");
            onPause();
        }

        @SuppressLint("NewApi")
        @TargetApi(Build.VERSION_CODES.GINGERBREAD)
        @Override
        public void onTick(long millisUntilFinished) {
            long millis = millisUntilFinished;
            String hms = String.format("%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)), TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
            System.out.println(hms);
            textViewTime.setText(hms);
        }
    }

    class myPagerAdapter extends FragmentPagerAdapter {

        private ArrayList<Fragment> fragmentList;
        private List<String> titleList;

        public myPagerAdapter(FragmentManager fm, ArrayList<Fragment> fragmentList, List<String> titleList) {
            super(fm);
            this.fragmentList = fragmentList;
            this.titleList = titleList;
        }

        @Override
        public Fragment getItem(int arg0) {

            return (fragmentList == null || fragmentList.size() == 0) ? null : fragmentList.get(arg0);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            position++;
            return "câu " + position;
        }

        @Override
        public int getCount() {
            return fragmentList == null ? 0 : fragmentList.size();
        }
    }
}
