package com.example.petvitaoriginal.activity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petvitaoriginal.adapter.ExamHistoryAdapter;
import com.example.petvitaoriginal.R;
import com.example.petvitaoriginal.classes.Exam;

import java.util.ArrayList;

public class ExamesActivity extends AppCompatActivity {
    private CalendarView calendarView;
    private RecyclerView examHistoryRecycler;
    private ExamHistoryAdapter adapter;
    private ArrayList<Exam> examHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exames);

        calendarView = findViewById(R.id.calendarView);
        examHistoryRecycler = findViewById(R.id.examHistoryRecycler);
        examHistoryRecycler.setLayoutManager(new LinearLayoutManager(this));

        examHistory = new ArrayList<>();
        adapter = new ExamHistoryAdapter(examHistory);
        examHistoryRecycler.setAdapter(adapter);

        // Setup listener for calendar date changes
        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            showAddExamDialog(year, month, dayOfMonth);
        });
    }

    private void showAddExamDialog(int year, int month, int dayOfMonth) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Adicionar Exame");

        // Inflate the dialog layout
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_add_exam, null);
        builder.setView(dialogView);

        EditText editTextTitle = dialogView.findViewById(R.id.editTextTitle);
        EditText editTextDescription = dialogView.findViewById(R.id.editTextDescription);

        builder.setPositiveButton("Salvar", (dialog, which) -> {
            String title = editTextTitle.getText().toString();
            String description = editTextDescription.getText().toString();
            String dateString = dayOfMonth + "/" + (month + 1) + "/" + year;

            // Adiciona o exame ao histórico
            if (!title.isEmpty()) {
                Exam exam = new Exam(title, dateString, description);
                examHistory.add(exam);
                adapter.notifyDataSetChanged();
                Toast.makeText(ExamesActivity.this, "Exame salvo!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(ExamesActivity.this, "Título não pode estar vazio!", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Cancelar", (dialog, which) -> dialog.dismiss());

        builder.show();
    }
}
