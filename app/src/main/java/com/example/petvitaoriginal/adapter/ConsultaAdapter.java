package com.example.petvitaoriginal.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petvitaoriginal.R;
import com.example.petvitaoriginal.activity.ConsultaDetailActivity; // Atualizado para a nova activity
import com.example.petvitaoriginal.classes.Consulta; // Alterado para a nova classe
import com.google.android.material.imageview.ShapeableImageView;

import java.util.List;

public class ConsultaAdapter extends RecyclerView.Adapter<ConsultaAdapter.ConsultaViewHolder> {
    private List<Consulta> consultas; // Alterado para a nova lista

    public ConsultaAdapter(List<Consulta> consultas) {
        this.consultas = consultas; // Alterado para a nova lista
    }

    @NonNull
    @Override
    public ConsultaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_procedimentos, parent, false); // Alterado para o novo layout
        return new ConsultaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ConsultaViewHolder holder, int position) {
        Consulta consulta = consultas.get(position); // Alterado para a nova classe
        holder.consultaName.setText(consulta.getTipoConsulta()); // Alterado para o novo método
        holder.consultaDate.setText(consulta.getDataConsulta()); // Alterado para o novo método
        holder.consultaDesc.setText(consulta.getObservacoes()); // Alterado para o novo método

        // Define o clique do item
        holder.itemView.setOnClickListener(v -> {
            Context context = holder.itemView.getContext();
            Intent intent = new Intent(context, ConsultaDetailActivity.class); // Alterado para a nova activity
            intent.putExtra("CONSULTA_TIPO", consulta.getTipoConsulta()); // Alterado para o novo método
            intent.putExtra("CONSULTA_DATA", consulta.getDataConsulta()); // Alterado para o novo método
            intent.putExtra("CONSULTA_NOME_VETERINARIO", consulta.getNomeVeterinario()); // Alterado para o novo método
            intent.putExtra("CONSULTA_OBSERVACOES", consulta.getObservacoes()); // Alterado para o novo método
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return consultas.size(); // Alterado para a nova lista
    }

    static class ConsultaViewHolder extends RecyclerView.ViewHolder {
        TextView consultaName, consultaDate, consultaDesc; // Alterado para a nova classe
        ShapeableImageView recImage;

        public ConsultaViewHolder(@NonNull View itemView) {
            super(itemView);
            consultaName = itemView.findViewById(R.id.procName); // Alterado para o novo ID
            consultaDate = itemView.findViewById(R.id.procDate); // Alterado para o novo ID
            consultaDesc = itemView.findViewById(R.id.procDesc); // Alterado para o novo ID
            recImage = itemView.findViewById(R.id.recImage); // Mantido se você precisar de uma imagem
        }
    }
}
