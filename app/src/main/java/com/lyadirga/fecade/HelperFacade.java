package com.lyadirga.fecade;

import android.content.Context;

public class HelperFacade {

    public static void saveDate(Context context, DBTypes dbType, String sonuc, String oneri, double boy, int kilo) {
        String con = null;
        switch (dbType) {
            case SQLITE:
                con = SharedPreferenceHelper.getSharedPreferenceConnection();
                SharedPreferenceHelper sharedPreferenceHelper = new SharedPreferenceHelper();
                sharedPreferenceHelper.SaveHelper(context,con, sonuc, oneri,boy,kilo);

                break;
            case WEBSERVICE:
                break;
        }

    }

    public enum DBTypes {
        SQLITE, WEBSERVICE;
    }

    public static enum ReportTypes {
        HTML, PDF;
    }
}
