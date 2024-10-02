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
import com.example.petvitaoriginal.activity.ProcedimentoDetailActivity;
import com.example.petvitaoriginal.classes.Procedimento;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.List;

public class ProcedimentoAdapter extends RecyclerView.Adapter<ProcedimentoAdapter.ProcedimentoViewHolder> {
    private List<Procedimento> procedimentos;

    public ProcedimentoAdapter(List<Procedimento> procedimentos) {
        this.procedimentos = procedimentos;
    }

    @NonNull
    @Override
    public ProcedimentoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_procedimentos, parent, false);
        return new ProcedimentoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProcedimentoViewHolder holder, int position) {
        Procedimento procedimento = procedimentos.get(position);
        holder.procedimentoName.setText(procedimento.getTipoProcedimento());
        holder.procedimentoDate.setText(procedimento.getDataProcedimento());
        holder.procedimentoDesc.setText(procedimento.getNotasAdicionais());

        // Define o clique do item
        holder.itemView.setOnClickListener(v -> {
            // Obtém o contexto do item
            Context context = holder.itemView.getContext();
            // Cria uma nova Intent para a ProcedimentoDetailActivity
            Intent intent = new Intent(context, ProcedimentoDetailActivity.class);
            // Passando os dados do procedimento para a Intent
            intent.putExtra("PROCEDIMENTO_TIPO", procedimento.getTipoProcedimento());
            intent.putExtra("PROCEDIMENTO_DATA", procedimento.getDataProcedimento());
            intent.putExtra("PROCEDIMENTO_VETERINARIO", procedimento.getNomeVeterinario());
            intent.putExtra("PROCEDIMENTO_NOTAS", procedimento.getNotasAdicionais());

            // Inicia a nova Activity
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return procedimentos.size();
    }

    static class ProcedimentoViewHolder extends RecyclerView.ViewHolder {
        TextView procedimentoName, procedimentoDate, procedimentoDesc;
        ShapeableImageView recImage;

        public ProcedimentoViewHolder(@NonNull View itemView) {
            super(itemView);
            procedimentoName = itemView.findViewById(R.id.procName);
            procedimentoDate = itemView.findViewById(R.id.procDate);
            procedimentoDesc = itemView.findViewById(R.id.procDesc);
            recImage = itemView.findViewById(R.id.recImage);
        }
    }
}
