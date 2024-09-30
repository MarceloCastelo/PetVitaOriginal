package com.example.petvitaoriginal;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class VacinaAdapter extends RecyclerView.Adapter<VacinaAdapter.VacinaViewHolder> {
    private List<String> vacinas;

    public VacinaAdapter(List<String> vacinas) {
        this.vacinas = vacinas;
    }

    @NonNull
    @Override
    public VacinaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        return new VacinaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VacinaViewHolder holder, int position) {
        holder.textView.setText(vacinas.get(position));
    }

    @Override
    public int getItemCount() {
        return vacinas.size();
    }

    static class VacinaViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        VacinaViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(android.R.id.text1);
        }
    }
}
