package com.lyadirga.sonuc;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.lyadirga.idealkilo.R;

import java.util.ArrayList;
import java.util.HashMap;

public class SonucAdapter extends RecyclerView.Adapter<MyViewHolder> {

    Context mContext;

    ArrayList<HashMap<String, String>> sonuclar;
    public SonucAdapter(Context context, ArrayList<HashMap<String, String>> galleryImages){
        this.sonuclar = galleryImages;
        mContext = context;
    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.sonuc_item, viewGroup, false);
        MyViewHolder pvh = new MyViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
        String durum = sonuclar.get(i).get("sonuc_adı");
        myViewHolder.durum.setText(durum);
        String boy = sonuclar.get(i).get("sonuc_boy");
        myViewHolder.boy.setText(boy+" m");
        String kilo = sonuclar.get(i).get("sonuc_kilo");
        myViewHolder.kilo.setText(kilo+" kg");

    }

    @Override
    public int getItemCount() {
        return sonuclar.size();
    }
}

