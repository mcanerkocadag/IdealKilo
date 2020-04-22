package com.lyadirga.builder;

import android.content.Context;

import com.lyadirga.fecade.HelperFacade;
import com.lyadirga.idealkilo.R;

public class SonucBuilder {

    private Context context;
    private double boy;
    private int kilo;
    private BuilderListener builderListener;

    public SonucBuilder Builder(Context context) {
        this.context = context;
        return this;
    }

    public SonucBuilder Boy(double boy) {
        this.boy = boy;
        return this;
    }

    public SonucBuilder Kilo(int kilo) {
        this.kilo = kilo;
        return this;
    }

    public SonucBuilder BuilderListener(BuilderListener builderListener) {
        this.builderListener = builderListener;
        return this;
    }

    public void build() {

        String sonuc = "";
        String oneri = "";
        double vke = kilo / (boy * boy);
        if (vke < 18.49) {
            sonuc = context.getResources().getString(R.string.ideal_kilo_alti);
            oneri = context.getResources().getString(R.string.ideal_kilo_alti_oneri);
        }
        else if (vke > 18.5 || vke < 24.99) {
            sonuc = context.getResources().getString(R.string.ideal_kilo);
            oneri = context.getResources().getString(R.string.ideal_kilo_oneri);
        }
        else if (vke > 25.0 || vke < 29.99) {
            sonuc = context.getResources().getString(R.string.ideal_kilo_ustu);
            oneri = context.getResources().getString(R.string.ideal_kilo_ustu_oneri);
        }
        else if (vke > 30.0) {
            sonuc = context.getResources().getString(R.string.obezite);
            oneri = context.getResources().getString(R.string.obezite_oneri);
        }

        if (builderListener != null)
            builderListener.Sonuc(sonuc, oneri);
    }

}



