package com.example.trips;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class LugarAdapter extends RecyclerView.Adapter <LugarViewHolder>{ private
    List<Lugar> lugares;
    private Context context;
    private CollectionReference lugarReference = null;
    Geocoder geocoder;
    List<Address> addresses;
    LugarAdapter (List<Lugar> lugares, Context context){
        this.lugares = lugares;
        this.context = context;
    }
    @NonNull
    @Override
    public LugarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int
            viewType) {
        LayoutInflater inflater = LayoutInflater.from(context); View v =
                inflater.inflate(R.layout.local_item, parent, false);
        return new LugarViewHolder(v);
    }
    @Override
    public void onBindViewHolder(@NonNull LugarViewHolder holder, int position) {
        lugarReference = FirebaseFirestore.getInstance().collection("lugares");
        Lugar l = lugares.get(position);
        holder.dataTextview.setText(l.getDataCadastro());
        holder.nomeTextView.setText(l.getNome());

        geocoder = new Geocoder(this.context, Locale.getDefault());
        try {
            addresses = geocoder.getFromLocation(Double.parseDouble(l.getLatitude()), Double.parseDouble(l.getLongitude()), 1);
            System.out.println(addresses.get(0).getThoroughfare() + addresses.get(0).getSubThoroughfare());
        } catch (IOException e) {
            e.printStackTrace();
        }
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v) {
                Lugar lugarRemovido = lugares.get(holder.getAdapterPosition());
                lugarReference.document(lugarRemovido.getId()).delete();
                lugares.remove(holder.getAdapterPosition());
                notifyItemRemoved(holder.getAdapterPosition());
                notifyItemRangeChanged(holder.getAdapterPosition(), lugares.size());
                return false;
            }
        });
    }
    @Override
    public int getItemCount() {
        return lugares.size();
    }
}
