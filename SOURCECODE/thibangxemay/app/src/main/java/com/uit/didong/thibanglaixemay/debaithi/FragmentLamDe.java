package com.uit.didong.thibanglaixemay.debaithi;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.uit.didong.thibanglaixemay.R;
import com.uit.didong.thibanglaixemay.meothi.MeThiChinh;

@SuppressLint("ValidFragment")
public class FragmentLamDe extends DialogFragment {
    cauhoi cauHoi, check;
    CheckBox A, B, C, D;
    Button btndapan, btnnopbai;
    String dapan = "";
    TextView txtvkq;
    String kqdung, t;
    int diem = 16;
    private String text;
    private WebView wv;

    public FragmentLamDe(String text, cauhoi cauHoi, cauhoi check) {
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
        } else {
            if (dapan.equals(kqdung)) {
                txtvkq.setText("Bạn đã làm đúng ! Chúc mừng !");
                diem = diem + 1;
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

    //AlertDialog hiển thị kết qua
    public AlertDialog ketqua() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        //Thiết lập tiêu đề hiển thị
        builder.setTitle("KẾT QUẢ ");
        //Thiết lập thông báo hiển thị
        builder.setMessage("Số câu làm được :" + diem + "/20 câu");
        builder.setCancelable(false);
        //Tạo nút Activity2
        builder.setPositiveButton("Thoát",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        getActivity().finish();
                    }
                });
        builder.setNeutralButton("Học Mẹo",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        Intent onthi = new Intent(getActivity(), MeThiChinh.class);
                        startActivity(onthi);
                    }
                });
        builder.setNegativeButton("Làm Đề Khác",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        Intent myIntent = new Intent(getActivity(), ViewDe.class);
                        startActivity(myIntent);
                        getActivity().finish();
                    }
                });
        AlertDialog dialog = builder.create();
        return dialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_lam_de, container, false);
        btndapan = v.findViewById(R.id.buttondapan);
        btnnopbai = v.findViewById(R.id.buttonnopbai);
        txtvkq = v.findViewById(R.id.textViewkq);
        A = v.findViewById(R.id.checkBoxA);
        B = v.findViewById(R.id.checkBoxB);
        C = v.findViewById(R.id.checkBoxC);
        D = v.findViewById(R.id.checkBoxD);
        wv = v.findViewById(R.id.webViewob);

        wv.loadUrl(text);

        getdapan();
        btndapan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KiemTra();
            }
        });
        btnnopbai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog mykq = ketqua();
                mykq.show();
            }
        });
        return v;
    }

}
