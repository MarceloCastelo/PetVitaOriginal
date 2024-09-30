package com.example.petvitaoriginal;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ExamHistoryAdapter extends RecyclerView.Adapter<ExamHistoryAdapter.ExamViewHolder> {

    private List<Exam> examList;

    public ExamHistoryAdapter(List<Exam> examList) {
        this.examList = examList;
    }

    @NonNull
    @Override
    public ExamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_exames, parent, false);
        return new ExamViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExamViewHolder holder, int position) {
        Exam exam = examList.get(position);
        holder.vacName.setText(exam.getName());
        holder.vacDate.setText(exam.getDate());
        holder.vacDesc.setText(exam.getDescription());
        // Se você estiver usando imagens, adicione a lógica para a imagem aqui
    }

    @Override
    public int getItemCount() {
        return examList.size();
    }

    static class ExamViewHolder extends RecyclerView.ViewHolder {
        TextView vacName;
        TextView vacDate;
        TextView vacDesc;

        ExamViewHolder(View itemView) {
            super(itemView);
            vacName = itemView.findViewById(R.id.vacName);
            vacDate = itemView.findViewById(R.id.vacDate);
            vacDesc = itemView.findViewById(R.id.vacDesc);
        }
    }
}
