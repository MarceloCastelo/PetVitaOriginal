package com.example.petvitaoriginal.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petvitaoriginal.R;

import java.util.ArrayList;

public class ConsultasAdapter extends RecyclerView.Adapter<ConsultasAdapter.ConsultaViewHolder> {

    private ArrayList<String> listaConsultas;

    public ConsultasAdapter(ArrayList<String> listaConsultas) {
        this.listaConsultas = listaConsultas;
    }

    @NonNull
    @Override
    public ConsultaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_consulta, parent, false);
        return new ConsultaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ConsultaViewHolder holder, int position) {
        String consulta = listaConsultas.get(position);
        holder.textViewConsulta.setText(consulta);

        // Botão para excluir uma consulta
        holder.buttonDeleteConsulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listaConsultas.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, listaConsultas.size());
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaConsultas.size();
    }

    public static class ConsultaViewHolder extends RecyclerView.ViewHolder {
        TextView textViewConsulta;
        ImageButton buttonDeleteConsulta;

        public ConsultaViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewConsulta = itemView.findViewById(R.id.textViewConsulta);
            buttonDeleteConsulta = itemView.findViewById(R.id.buttonDeleteConsulta);
        }
    }
}
