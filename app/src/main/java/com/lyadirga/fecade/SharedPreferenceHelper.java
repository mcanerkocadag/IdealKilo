package com.lyadirga.fecade;

import android.content.Context;
import com.lyadirga.database.Database;
import java.util.UUID;

public class SharedPreferenceHelper {

    public static String getSharedPreferenceConnection() {
        return UUID.randomUUID().toString();
    }

    public void SaveHelper(Context context, String con, String sonuc, String oneri, double boy, int kilo) {

        Database db = new Database(context);
        db.sonucEkle(con, sonuc, oneri, ""+boy,""+kilo);
        db.close();
    }
}
