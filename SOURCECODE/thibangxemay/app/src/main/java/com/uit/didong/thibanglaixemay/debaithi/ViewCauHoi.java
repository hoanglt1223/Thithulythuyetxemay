package com.uit.didong.thibanglaixemay.debaithi;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.WindowManager;

import com.uit.didong.thibanglaixemay.R;
import com.uit.didong.thibanglaixemay.SQLiteCauHoi;
import com.uit.didong.thibanglaixemay.SQLiteDataController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class ViewCauHoi extends FragmentActivity {
    public ArrayList<Integer> arrNumber = new ArrayList<Integer>();
    ArrayList<Fragment> fragmentList = new ArrayList<Fragment>();
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

    //code lấy dữ liệu
    private void getListCauHoi() {
        SQLiteCauHoi cauhoi = new SQLiteCauHoi(this);
        cauhoilist = new ArrayList<>();
        cauhoilist = cauhoi.getListcauhoi();
    }

    //code random cau hoi
    public int randomQuestion() {
        int n = random.nextInt(150);
        if (arrNumber.size() == 0) {
            arrNumber.add(n);
            return n;
        } else {
            if (arrNumber.contains(n)) {
                return randomQuestion();
            } else {
                arrNumber.add(n);
                return n;
            }
        }
    }

    public void queryQuestion() {
        getListCauHoi();
        for (int i = 0; i < cauhoilist.size(); i++) {
            int x = randomQuestion();
            fragmentList.add(new OpenBook(cauhoilist.get(x).getCauhoi(), cauhoilist.get(x), cauhoilist.get(x)));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewcauhoi);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        ViewPager vp = findViewById(R.id.viewPager);
        createDB();
        queryQuestion();
        vp.setAdapter(new myPagerAdapter(getSupportFragmentManager(), fragmentList, titleList));
        Bundle b = getIntent().getExtras();

        if (b != null) {
            Integer j = (Integer) b.get("a1");
            vp.setCurrentItem(j);
        }
    }

    public void onBackPressed() {
        finish();
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
            return position + 1 + "/150";
        }

        @Override
        public int getCount() {
            return fragmentList == null ? 0 : fragmentList.size();
        }
    }
}
