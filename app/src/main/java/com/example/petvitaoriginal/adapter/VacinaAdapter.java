package com.example.petvitaoriginal.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petvitaoriginal.R;
import com.example.petvitaoriginal.classes.Vacina;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.List;

public class VacinaAdapter extends RecyclerView.Adapter<VacinaAdapter.VacinaViewHolder> {
    private List<Vacina> vacinas; // Alterado para usar a lista de Vacina

    public VacinaAdapter(List<Vacina> vacinas) {
        this.vacinas = vacinas;
    }

    @NonNull
    @Override
    public VacinaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_vacinas , parent, false); // Inflar o layout do item de vacina
        return new VacinaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VacinaViewHolder holder, int position) {
        Vacina vacina = vacinas.get(position);
        holder.vacName.setText(vacina.getTipo());
        holder.vacDate.setText(vacina.getData());
        holder.vacDesc.setText(vacina.getNotas());
        // Aqui você pode definir a imagem usando um carregador de imagens, como Glide ou Picasso
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
