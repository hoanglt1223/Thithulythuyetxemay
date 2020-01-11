package com.uit.didong.thibanglaixemay;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class SQLiteDataController extends SQLiteOpenHelper {
    // đường dẫn nơi chứa database
    private static final String DB_NAME = "dulieu.sqlite";
    private final Context mContext;
    public String DB_PATH = Environment.getDataDirectory().getPath() + "/data/com.uit.didong.thibanglaixemay/database";
    public SQLiteDatabase database;

    public SQLiteDataController(Context con) {
        super(con, DB_NAME, null, 1);
        DB_PATH = String.format(DB_PATH, con.getPackageName());
        this.mContext = con;
    }

    //chua su dung ham copy
    public boolean isCreatedDatabase() throws IOException {
        // Default là đã có DB
        boolean result = true;
        // Nếu chưa tồn tại DB thì copy từ Asses vào Data
        if (!checkExistDataBase()) {
            this.getReadableDatabase();
            try {
                copyDatabase();
                result = false;
            } catch (Exception e) {
                throw new Error("Error copying database");
            }
        }

        return result;
    }

    /**
     * check whether database exist on the device?
     *
     * @return true if existed
     */
    private boolean checkExistDataBase() {

        try {
            String myPath = DB_PATH + DB_NAME;
            File fileDB = new File(myPath);

            return fileDB.exists();
        } catch (Exception e) {
            return false;
        }
    }

    public void copyDatabase() {
        new File(DB_PATH).mkdir();
        File file = new File(DB_PATH + "/" + DB_NAME);
        if (file.exists()) {
            Log.i("TAG", "File is exits");
            return;
        } else {
            try {
                file.createNewFile();
                InputStream inputStream = mContext.getAssets().open(DB_NAME);
                FileOutputStream fos = new FileOutputStream(file);
                byte[] data = new byte[1024];
                int leng = 0;
                while ((leng = inputStream.read(data)) > 0) {
                    fos.write(data, 0, leng);
                }
                fos.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void openDataBase() throws SQLException {
        database = SQLiteDatabase.openDatabase(DB_PATH + "/" + DB_NAME, null,
                SQLiteDatabase.OPEN_READWRITE);
    }

    @Override
    public synchronized void close() {
        if (database != null)
            database.close();
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // do nothing
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // do nothing
    }

}
