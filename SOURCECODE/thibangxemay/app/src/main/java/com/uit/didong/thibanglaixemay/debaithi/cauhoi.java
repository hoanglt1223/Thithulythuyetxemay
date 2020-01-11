package com.uit.didong.thibanglaixemay.debaithi;

public class cauhoi {
    private String cauhoi;
    private String dapandung;
    private String check;

    public cauhoi(String cauhoi, String dapandung, String check) {

        this.cauhoi = cauhoi;
        this.dapandung = dapandung;
        this.check = check;
    }

    public String getCauhoi() {
        return cauhoi;
    }

    public void setCauhoi(String cauhoi) {
        this.cauhoi = cauhoi;
    }

    public String getDapandung() {
        return dapandung;
    }

    public void setDapandung(String dapandung) {
        this.dapandung = dapandung;
    }

    public String getCheck() {
        return check;
    }

    public void setCheck(String check) {
        this.check = check;
    }
}

