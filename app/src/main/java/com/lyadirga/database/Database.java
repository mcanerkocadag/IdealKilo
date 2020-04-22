package com.lyadirga.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class Database extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 3;

    // Database Name
    private static final String DATABASE_NAME = "sqllite_db";//database adı

    private static final String TABLE_NAME = "sonuc";
    private static String SONUC_KEY = "key";
    private static String SONUC_ID = "sonuc_id";
    private static String SONUC_ADI = "sonuc_adı";
    private static String SONUC_ONERI = "sonuc_oneri";
    private static String SONUC_BOY = "sonuc_boy";
    private static String SONUC_KILO = "sonuc_kilo";

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {  // Databesi oluşturuyoruz.Bu methodu biz çağırmıyoruz. Databese de obje oluşturduğumuzda otamatik çağırılıyor.
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + SONUC_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + SONUC_KEY + " TEXT,"
                + SONUC_ADI + " TEXT,"
                + SONUC_ONERI + " TEXT,"
                + SONUC_BOY + " TEXT,"
                + SONUC_KILO + " TEXT" + ")";
        db.execSQL(CREATE_TABLE);
    }

    public void kitapSil(int id) { //id si belli olan row u silmek için

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, SONUC_ID + " = ?",
                new String[]{String.valueOf(id)});
        db.close();
    }

    public void sonucEkle(String sonuc_key, String sonuc_adı, String sonuc_oneri,String boy,String kilo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(SONUC_KEY, sonuc_key);
        values.put(SONUC_ADI, sonuc_adı);
        values.put(SONUC_ONERI, sonuc_oneri);
        values.put(SONUC_BOY, boy);
        values.put(SONUC_KILO, kilo);

        db.insert(TABLE_NAME, null, values);
        db.close(); //Database Bağlantısını kapattık*/
    }

    public ArrayList<HashMap<String, String>> sonuclar() {

        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(selectQuery, null);
        ArrayList<HashMap<String, String>> kitaplist = new ArrayList<HashMap<String, String>>();
        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                for (int i = 0; i < cursor.getColumnCount(); i++) {
                    map.put(cursor.getColumnName(i), cursor.getString(i));
                }

                kitaplist.add(map);
            } while (cursor.moveToNext());
        }
        db.close();
        return kitaplist;
    }

    public void resetTables() {
        //Bunuda uygulamada kullanmıyoruz. Tüm verileri siler. tabloyu resetler.
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete All Rows
        db.delete(TABLE_NAME, null, null);
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
    }

}
