package com.uit.didong.thibanglaixemay;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;

import com.uit.didong.thibanglaixemay.debaithi.cauhoi;

import java.util.ArrayList;

public class SQLiteCauHoi extends SQLiteDataController {
    public SQLiteCauHoi(Context con) {
        super(con);
    }

    public ArrayList<cauhoi> getListcauhoi() {
        ArrayList<cauhoi> listcauhoi = new ArrayList<>();
        // mo ket noi
        try {
            openDataBase();
            Cursor cs = database.rawQuery("SELECT * FROM mQuestion", null);
            cauhoi cauhoi;
            cs.moveToFirst();
            while (!cs.isAfterLast()) {
                cauhoi = new cauhoi(cs.getString(0), cs.getString(1), cs.getString(2));
                cs.moveToNext();
                listcauhoi.add(cauhoi);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return listcauhoi;
    }
}
