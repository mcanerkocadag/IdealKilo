package com.lyadirga.sonuc;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.lyadirga.idealkilo.R;

public  class MyViewHolder extends RecyclerView.ViewHolder {

    TextView boy,kilo,durum;
    MyViewHolder(View itemView) {
        super(itemView);
        boy = (TextView)itemView.findViewById(R.id.boy_tv);
        kilo = (TextView)itemView.findViewById(R.id.kilo_tv);
        durum = (TextView)itemView.findViewById(R.id.durum_tv);
    }
}
