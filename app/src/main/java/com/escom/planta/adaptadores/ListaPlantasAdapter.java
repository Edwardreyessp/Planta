package com.escom.planta.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.escom.planta.VerActivity;
import com.escom.planta.entidades.Plantas;

import java.util.ArrayList;

import escom.planta.R;

public class ListaPlantasAdapter extends RecyclerView.Adapter<ListaPlantasAdapter.PlantaViewHolder> {

    ArrayList<Plantas> listaPlantas;

    public ListaPlantasAdapter(ArrayList<Plantas> listaPlantas){
        this.listaPlantas = listaPlantas;
    }

    @NonNull
    @Override
    public PlantaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_planta, null, false);
        return new PlantaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlantaViewHolder holder, int position) {
        holder.viewNombre.setText(listaPlantas.get(position).getNombre());
        holder.viewRiego.setText(listaPlantas.get(position).getDias_riego());
        holder.viewTamano.setText(listaPlantas.get(position).getTamano());
    }

    @Override
    public int getItemCount() {
        return listaPlantas.size();
    }

    public class PlantaViewHolder extends RecyclerView.ViewHolder {

        TextView viewNombre, viewRiego, viewTamano;

        public PlantaViewHolder(@NonNull View itemView) {
            super(itemView);

            viewNombre = itemView.findViewById(R.id.viewNombre);
            viewRiego = itemView.findViewById(R.id.viewRiego);
            viewTamano = itemView.findViewById(R.id.viewTamano);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, VerActivity.class);
                    intent.putExtra("ID", listaPlantas.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });
        }
    }
}
