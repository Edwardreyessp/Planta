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
        holder.viewTelefono.setText(listaPlantas.get(position).getTelefono());
        holder.viewCorreo.setText(listaPlantas.get(position).getCorreo_electornico());
    }

    @Override
    public int getItemCount() {
        return listaPlantas.size();
    }

    public class PlantaViewHolder extends RecyclerView.ViewHolder {

        TextView viewNombre, viewTelefono, viewCorreo;

        public PlantaViewHolder(@NonNull View itemView) {
            super(itemView);

            viewNombre = itemView.findViewById(R.id.viewNombre);
            viewTelefono = itemView.findViewById(R.id.viewTelefono);
            viewCorreo = itemView.findViewById(R.id.viewCorreo);

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
