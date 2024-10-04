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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class ExamesActivity extends AppCompatActivity {
    private CalendarView calendarView;
    private RecyclerView examHistoryRecycler;
    private RecyclerView scheduledExamsRecycler;
    private ExamHistoryAdapter adapterHistory;
    private ExamHistoryAdapter adapterScheduled;
    private ArrayList<Exam> examHistory;
    private ArrayList<Exam> scheduledExams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exames);

        calendarView = findViewById(R.id.calendarView);
        examHistoryRecycler = findViewById(R.id.examHistoryRecycler);
        scheduledExamsRecycler = findViewById(R.id.scheduledExamsRecycler);

        // Configurar LayoutManager para os dois RecyclerViews
        examHistoryRecycler.setLayoutManager(new LinearLayoutManager(this));
        scheduledExamsRecycler.setLayoutManager(new LinearLayoutManager(this));

        // Inicializar as listas
        examHistory = new ArrayList<>();
        scheduledExams = new ArrayList<>();

        // Inicializar os adaptadores
        adapterHistory = new ExamHistoryAdapter(examHistory);
        adapterScheduled = new ExamHistoryAdapter(scheduledExams);

        // Configurar os adaptadores nos RecyclerViews
        examHistoryRecycler.setAdapter(adapterHistory);
        scheduledExamsRecycler.setAdapter(adapterScheduled);

        // Carregar exames e classificar entre agendados e históricos
        carregarExames();

        // Listener para quando a data no calendário for alterada
        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            showAddExamDialog(year, month, dayOfMonth);
        });
    }

    private void carregarExames() {
        // Exemplo de como carregar e classificar exames
        ArrayList<Exam> allExams = getAllExams(); // Simulação de exames carregados

        long today = System.currentTimeMillis(); // Data atual em milissegundos

        for (Exam exam : allExams) {
            long examDateMillis = convertStringToDate(exam.getDate()).getTime();

            if (examDateMillis < today) {
                examHistory.add(exam); // Exame no passado
            } else {
                scheduledExams.add(exam); // Exame no futuro
            }
        }

        // Atualizar os adaptadores
        adapterHistory.notifyDataSetChanged();
        adapterScheduled.notifyDataSetChanged();
    }

    private Date convertStringToDate(String dateString) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        try {
            return format.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return new Date(); // Retorna a data atual em caso de erro
        }
    }

    private void showAddExamDialog(int year, int month, int dayOfMonth) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Adicionar Exame");

        // Inflate o layout do diálogo
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_add_exam, null);
        builder.setView(dialogView);

        EditText editTextTitle = dialogView.findViewById(R.id.editTextTitle);
        EditText editTextDescription = dialogView.findViewById(R.id.editTextDescription);

        builder.setPositiveButton("Salvar", (dialog, which) -> {
            String title = editTextTitle.getText().toString();
            String description = editTextDescription.getText().toString();
            String dateString = dayOfMonth + "/" + (month + 1) + "/" + year;

            if (!title.isEmpty()) {
                Exam exam = new Exam(title, dateString, description);
                long examDateMillis = convertStringToDate(dateString).getTime();
                long today = System.currentTimeMillis();

                if (examDateMillis < today) {
                    examHistory.add(exam);
                    adapterHistory.notifyDataSetChanged();
                } else {
                    scheduledExams.add(exam);
                    adapterScheduled.notifyDataSetChanged();
                }

                Toast.makeText(ExamesActivity.this, "Exame salvo!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(ExamesActivity.this, "Título não pode estar vazio!", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Cancelar", (dialog, which) -> dialog.dismiss());

        builder.show();
    }

    // Simulação de carregamento de exames
    private ArrayList<Exam> getAllExams() {
        // Aqui você pode carregar de uma base de dados, API, etc.
        return new ArrayList<>();
    }
}
