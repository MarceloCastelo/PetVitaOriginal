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
import com.example.petvitaoriginal.classes.Vacina;
import com.example.petvitaoriginal.activity.VacinaDetailActivity;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.List;

public class VacinaAdapter extends RecyclerView.Adapter<VacinaAdapter.VacinaViewHolder> {
    private List<Vacina> vacinas;

    public VacinaAdapter(List<Vacina> vacinas) {
        this.vacinas = vacinas;
    }

    @NonNull
    @Override
    public VacinaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_vacinas, parent, false);
        return new VacinaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VacinaViewHolder holder, int position) {
        Vacina vacina = vacinas.get(position);
        holder.vacName.setText(vacina.getTipo());
        holder.vacDate.setText(vacina.getData());
        holder.vacDesc.setText(vacina.getNotas());

        // Define o clique do item
        holder.itemView.setOnClickListener(v -> {
            // Obtém o contexto do item
            Context context = holder.itemView.getContext();
            // Cria uma nova Intent para a VacinaDetailActivity
            Intent intent = new Intent(context, VacinaDetailActivity.class);
            // Passando os dados da vacina para a Intent
            intent.putExtra("VACINA_NOME", vacina.getTipo());
            intent.putExtra("VACINA_DATA", vacina.getData());
            intent.putExtra("VACINA_NUMERO_DOSES", vacina.getNumeroDoses());
            intent.putExtra("VACINA_DOSE_APLICADA", vacina.getDoseAplicada());
            intent.putExtra("VACINA_NOME_VETERINARIO", vacina.getNomeVeterinario());
            intent.putExtra("VACINA_LOTE", vacina.getLote());
            intent.putExtra("VACINA_LOCAL", vacina.getLocal());
            intent.putExtra("VACINA_NOTAS", vacina.getNotas());

            // Inicia a nova Activity
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return vacinas.size();
    }

    static class VacinaViewHolder extends RecyclerView.ViewHolder {
        TextView vacName, vacDate, vacDesc;
        ShapeableImageView recImage;

        public VacinaViewHolder(@NonNull View itemView) {
            super(itemView);
            vacName = itemView.findViewById(R.id.vacName);
            vacDate = itemView.findViewById(R.id.vacDate);
            vacDesc = itemView.findViewById(R.id.vacDesc);
            recImage = itemView.findViewById(R.id.recImage);
        }
    }
}
