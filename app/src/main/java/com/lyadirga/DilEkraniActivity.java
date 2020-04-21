package com.lyadirga;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.lyadirga.idealkilo.R;

public class DilEkraniActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dil_ekrani);
        ObserverManager.getInstance().updateValue("English");
    }
}
