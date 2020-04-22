package com.lyadirga.sonuc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.lyadirga.database.Database;
import com.lyadirga.idealkilo.R;

import java.util.ArrayList;
import java.util.HashMap;

public class SonucActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Button kapat_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sonuc);

        kapat_btn = findViewById(R.id.kapat_btn);
        recyclerView = findViewById(R.id.sonuc_recyclerview);

        Database db = new Database(this);
        ArrayList<HashMap<String, String>> sonuclar = db.sonuclar();
        SonucAdapter adapter = new SonucAdapter(getApplicationContext(),
                sonuclar);
        recyclerView.setAdapter(adapter);

        kapat_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
