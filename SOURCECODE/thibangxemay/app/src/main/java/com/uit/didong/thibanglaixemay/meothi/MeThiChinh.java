package com.uit.didong.thibanglaixemay.meothi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.uit.didong.thibanglaixemay.R;


public class MeThiChinh extends AppCompatActivity {
    TextView txtvlythuyet, txtvxahinh;
    ImageView img1, img2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me_thi_chinh);
        img1 = findViewById(R.id.imageViewlythuyet);
        img2 = findViewById(R.id.imageViewxahinh);

        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mhchonde = new Intent(getApplicationContext(), lythuyetwebview.class);
                startActivity(mhchonde);
            }
        });
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mhc = new Intent(getApplicationContext(), xahinhwebview.class);
                startActivity(mhc);

            }
        });
    }

}
