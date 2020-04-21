package com.lyadirga;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.lyadirga.idealkilo.R;
import com.lyadirga.model.Dil;
import com.lyadirga.observer.ObserverDataRepository;
import com.lyadirga.observer.ObserverKanal;

import java.util.Locale;

public class DilEkraniActivity extends AppCompatActivity implements ObserverKanal {

    ConstraintLayout turkce_btn;
    ConstraintLayout ingilizce_btn;
    Button kapat_btn;
    ImageView turkce_tick_iv;
    ImageView ingilizce_tick_iv;

    TextView dil_baslik_tv;
    TextView turkce_tv;
    TextView ingilizce_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dil_ekrani);

        kapat_btn = findViewById(R.id.kapat_btn);
        turkce_btn = findViewById(R.id.turkce_btn);
        ingilizce_btn = findViewById(R.id.ingilizce_btn);
        turkce_tick_iv = findViewById(R.id.turkce_tick_iv);
        ingilizce_tick_iv = findViewById(R.id.ingilizce_tick_iv);

        dil_baslik_tv = findViewById(R.id.dil_baslik_tv);
        turkce_tv = findViewById(R.id.turkce_tv);
        ingilizce_tv = findViewById(R.id.ingilizce_tv);

        turkce_tick_iv.setVisibility(View.INVISIBLE);
        ingilizce_tick_iv.setVisibility(View.INVISIBLE);

        ObserverDataRepository.getInstance().registerObserver(this);
        dileGöreAyarla();

        turkce_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                turkce_tick_iv.setVisibility(View.VISIBLE);
                ingilizce_tick_iv.setVisibility(View.INVISIBLE);
                Dil dil = new Dil();
                dil.setKod("tr");
                ObserverDataRepository.getInstance().setUserData(dil);
            }
        });

        ingilizce_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                turkce_tick_iv.setVisibility(View.INVISIBLE);
                ingilizce_tick_iv.setVisibility(View.VISIBLE);
                Dil dil = new Dil();
                dil.setKod("en");
                ObserverDataRepository.getInstance().setUserData(dil);
            }
        });

        kapat_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    public void dileGöreAyarla() {
        if (getResources().getString(R.string.locale).equals("en")) {
            turkce_tick_iv.setVisibility(View.INVISIBLE);
            ingilizce_tick_iv.setVisibility(View.VISIBLE);
        } else {
            turkce_tick_iv.setVisibility(View.VISIBLE);
            ingilizce_tick_iv.setVisibility(View.INVISIBLE);
        }
        dil_baslik_tv.setText(getResources().getString(R.string.language_selection_screen));
        turkce_tv.setText(getResources().getString(R.string.turkısh));
        ingilizce_tv.setText(getResources().getString(R.string.english));
    }

    @Override
    public void onUpdate(Object object) {
        Dil dil = (Dil) object;
        setApplicationLanguage(this, dil.getKod());
        dileGöreAyarla();
    }

    public void setApplicationLanguage(Activity context, String newLanguage) {
        Resources activityRes = context.getResources();
        Configuration activityConf = activityRes.getConfiguration();
        Locale newLocale = new Locale(newLanguage);
        activityConf.setLocale(newLocale);
        activityRes.updateConfiguration(activityConf, activityRes.getDisplayMetrics());

        Resources applicationRes = context.getResources();
        Configuration applicationConf = applicationRes.getConfiguration();
        applicationConf.setLocale(newLocale);
        applicationRes.updateConfiguration(applicationConf,
                applicationRes.getDisplayMetrics());
    }
}
