package com.uit.didong.thibanglaixemay.debaithi;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.uit.didong.thibanglaixemay.R;

@SuppressLint("ValidFragment")
public class OpenBook extends Fragment {
    cauhoi cauHoi;
    TextView txtvkq;
    cauhoi check;
    CheckBox A, B, C, D;
    Button btndapan;
    String dapan = "";
    String t;
    String kqdung;
    int diem = 0;
    private String text;
    private WebView wv;

    //chu nen tim hieu them codeding conversion va nameing conversion nha.
    public OpenBook(String text, cauhoi cauHoi, cauhoi check) {
        super();
        this.cauHoi = cauHoi;
        this.text = text;
        this.check = check;
        kqdung = cauHoi.getDapandung();
        t = check.getCheck();
    }

    private void getdapan() {
        A.setText("1");
        B.setText("2");
        C.setText("3");
        D.setText("4");
        if ("2".equals(t)) {
            C.setVisibility(View.INVISIBLE);
            D.setVisibility(View.INVISIBLE);
        } else if ("3".equals(t)) {
            D.setVisibility(View.INVISIBLE);
        }
    }

    private void KiemTra() {
        if (A.isChecked()) {
            dapan += "1";
        }
        if (B.isChecked()) {
            if ("".equals(dapan)) {
                dapan += "2";
            } else {
                dapan += "va2";
            }
        }
        if (C.isChecked()) {
            if ("".equals(dapan)) {
                dapan += "3";
            } else {
                dapan += "va3";
            }
        }
        if (D.isChecked()) {
            if ("".equals(dapan)) {
                dapan += "4";
            } else {
                dapan += "va4";
            }
        }
        if ("".equals(dapan)) {
            chon();
            //day la chua chon dap an. lam gi tuy chu
        } else {
            if (dapan.equals(kqdung)) {
                //day la ket qua da dung.lm gi tuy chu
                txtvkq.setText("Bạn đã làm đúng ! Chúc mừng !");
                diem++;
            } else {
                txtvkq.setTextColor(0xffff0000);
                txtvkq.setText("Bạn đã làm sai ! Đáp án đúng là : " + kqdung);
            }
        }
    }

    public void chon() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                getActivity());
        alertDialog.setIcon(R.drawable.warning);
        alertDialog.setTitle("      Thông Báo!");
        alertDialog.setMessage("  Bạn chưa chọn đáp án!");
        alertDialog.setNegativeButton("Hủy",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        A.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                            @Override
                            public void onCheckedChanged(CompoundButton arg0, boolean isChecked) {
                                // TODO Auto-generated method stub
                                if (isChecked) {
                                    A.setChecked(true);
                                }
                            }

                        });
                        B.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                            @Override
                            public void onCheckedChanged(CompoundButton arg0, boolean isChecked) {
                                // TODO Auto-generated method stub
                                if (isChecked) {
                                    B.setChecked(true);

                                }
                            }

                        });
                        C.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                            @Override
                            public void onCheckedChanged(CompoundButton arg0, boolean isChecked) {
                                // TODO Auto-generated method stub
                                if (isChecked) {
                                    C.setChecked(true);

                                }
                            }

                        });
                        D.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                            @Override
                            public void onCheckedChanged(CompoundButton arg0, boolean isChecked) {
                                // TODO Auto-generated method stub
                                if (isChecked) {
                                    D.setChecked(true);
                                }
                            }

                        });

                    }
                });
        alertDialog.show();
    }

    public void sukiencheck() {
        A.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton arg0, boolean isChecked) {
                // TODO Auto-generated method stub
                if (isChecked) {
                    A.setChecked(false);
                }
            }

        });
        B.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton arg0, boolean isChecked) {
                // TODO Auto-generated method stub
                if (isChecked) {
                    B.setChecked(false);

                }
            }

        });
        C.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton arg0, boolean isChecked) {
                // TODO Auto-generated method stub
                if (isChecked) {
                    C.setChecked(false);

                }
            }

        });
        D.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton arg0, boolean isChecked) {
                // TODO Auto-generated method stub
                if (isChecked) {
                    D.setChecked(false);
                }
            }

        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.openbook, container, false);
        wv = v.findViewById(R.id.webViewob);
        btndapan = v.findViewById(R.id.buttondapan);
        txtvkq = v.findViewById(R.id.textViewkq);
        A = v.findViewById(R.id.checkBoxA);
        B = v.findViewById(R.id.checkBoxB);
        C = v.findViewById(R.id.checkBoxC);
        D = v.findViewById(R.id.checkBoxD);
        wv.loadUrl(text);
        getdapan();
        btndapan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KiemTra();
                sukiencheck();
            }
        });
        return v;
    }

}
