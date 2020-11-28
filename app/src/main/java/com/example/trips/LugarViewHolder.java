package com.example.trips;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class LugarViewHolder extends RecyclerView.ViewHolder{

    TextView nomeTextView;
    TextView dataTextview;
    LugarViewHolder(View v){
        super (v);
        this.nomeTextView = v.findViewById(R.id.nomeTextView);
        this.dataTextview = v.findViewById(R.id.dataTextView);
    }
}