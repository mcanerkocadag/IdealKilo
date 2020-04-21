package com.lyadirga.idealkilo;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.lyadirga.DilEkraniActivity;
import com.lyadirga.observer.ObserverDataRepository;
import com.lyadirga.observer.ObserverKanal;
import com.lyadirga.model.Dil;
import com.lyadirga.model.İnsan;

import java.util.Locale;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements ObserverKanal {

    private Pattern COMPILE = Pattern.compile("[-\\[\\]^/,'*:.!><~@#$₺%+=?|\"\\\\() ]+");

    CheckBox erkek_checkbox;
    CheckBox kadin_checkbox;

    EditText boy_et, kilo_et;
    TextView sonuc_tv, oneri_tv;
    Button hesapla_btn;
    ImageView dil_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ObserverDataRepository.getInstance().registerObserver(this);

        boy_et = findViewById(R.id.boy_et);
        kilo_et = findViewById(R.id.kilo_et);
        sonuc_tv = findViewById(R.id.sonuc_tv);
        oneri_tv = findViewById(R.id.oneri_tv);
        hesapla_btn = findViewById(R.id.hesapla_btn);
        dil_btn = findViewById(R.id.dil_btn);

        erkek_checkbox = findViewById(R.id.erkek_checkbox);
        kadin_checkbox = findViewById(R.id.kadin_checkbox);

        erkek_checkbox.setChecked(true);
        erkek_checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                kadin_checkbox.setChecked(false);
            }
        });
        kadin_checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                erkek_checkbox.setChecked(false);
            }
        });

        hesapla_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hesapla();
            }
        });

        dil_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent dil_ekrani = new Intent(MainActivity.this, DilEkraniActivity.class);
                startActivity(dil_ekrani);
            }
        });
    }

    private void hesapla() {
        İnsan i̇nsan = new İnsan();
        String boy_str = boy_et.getText().toString();
        boy_str = boy_str.replaceAll(String.valueOf(COMPILE),"");
        double boy = Double.parseDouble(boy_str) / 100;
        i̇nsan.setBoy(boy);

        String kilo_str = kilo_et.getText().toString();
        kilo_str = kilo_str.replaceAll(String.valueOf(COMPILE),"");
        int kilo = Integer.parseInt(kilo_str);
        i̇nsan.setKilo(kilo);

        if (erkek_checkbox.isChecked()) {
            i̇nsan.setCinsiyet(1);
        } else {
            i̇nsan.setCinsiyet(0);
        }

        double vke = kilo / (boy * boy);
        if (vke < 18.49) {
            sonuc_tv.setText(R.string.ideal_kilo_alti);
            oneri_tv.setText(R.string.ideal_kilo_alti_oneri);
        }
        if (vke > 18.5 || vke < 24.99) {
            sonuc_tv.setText(R.string.ideal_kilo);
            oneri_tv.setText(R.string.ideal_kilo_oneri);
        }
        if (vke > 25.0 || vke < 29.99) {
            sonuc_tv.setText(R.string.ideal_kilo_ustu);
            oneri_tv.setText(R.string.ideal_kilo_ustu_oneri);
        }
        if (vke > 30.0) {
            sonuc_tv.setText(R.string.obezite);
            oneri_tv.setText(R.string.obezite_oneri);
        }



        //Toast.makeText(this, "" + vke, Toast.LENGTH_LONG).show();
    }

    public void dileGoreAyarla(){
        TextView boyunuz_baslik_tv = findViewById(R.id.boyunuz_baslik_tv);
        boyunuz_baslik_tv.setText(getResources().getString(R.string.boy_tv));

        TextView kilonuz_baslik_tv = findViewById(R.id.kilonuz_baslik_tv);
        kilonuz_baslik_tv.setText(getResources().getString(R.string.kilo_tv));

        TextView baslik_tv = findViewById(R.id.baslik_tv);
        baslik_tv.setText(getResources().getString(R.string.vucut_kitle_endeksi));

        TextView erkek_baslik_tv = findViewById(R.id.erkek_baslik_tv);
        erkek_baslik_tv.setText(getResources().getString(R.string.erkek));

        TextView kadin_baslik_tv = findViewById(R.id.kadin_baslik_tv);
        kadin_baslik_tv.setText(getResources().getString(R.string.kadin));

        TextView cinsiyet_baslik_tv = findViewById(R.id.cinsiyet_baslik_tv);
        cinsiyet_baslik_tv.setText(getResources().getString(R.string.cinsiyet));

        hesapla_btn.setText(getResources().getString(R.string.hesapla));
        sonuc_tv.setText(getResources().getString(R.string.sonuc));
        kilo_et.setText(getResources().getString(R.string.kilonuzu_giriniz));
        boy_et.setText(getResources().getString(R.string.boyunuzu_giriniz));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        ObserverDataRepository.getInstance().removeObserver(this);
    }

    @Override
    public void onUpdate(Object object) {

        Dil dil = (Dil) object;
       // Toast.makeText(this, "" + dil.getKod(), Toast.LENGTH_SHORT).show();
        setApplicationLanguage(this, dil.getKod());
        dileGoreAyarla();
    }

    public static void setApplicationLanguage(Activity context, String newLanguage) {
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
