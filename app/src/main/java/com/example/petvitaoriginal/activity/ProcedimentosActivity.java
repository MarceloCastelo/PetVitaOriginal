package com.example.petvitaoriginal.activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petvitaoriginal.R;
import com.example.petvitaoriginal.adapter.ProcedimentoAdapter; // Ensure you create this adapter
import com.example.petvitaoriginal.classes.Procedimento; // Ensure you create this class

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ProcedimentosActivity extends AppCompatActivity {
    private RecyclerView recyclerViewProcedimentos;
    private ProcedimentoAdapter procedimentoAdapter;
    private List<Procedimento> listaProcedimentos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_procedimentos);

        // Inicializando a lista de procedimentos
        listaProcedimentos = new ArrayList<>();
        procedimentoAdapter = new ProcedimentoAdapter(listaProcedimentos);

        // Configurando o RecyclerView
        recyclerViewProcedimentos = findViewById(R.id.recyclerViewProcedimentos);
        recyclerViewProcedimentos.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewProcedimentos.setAdapter(procedimentoAdapter);

        // Botão para adicionar novo procedimento
        Button buttonAddProcedimento = findViewById(R.id.buttonAddProcedimento);
        buttonAddProcedimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarDialogAdicionarProcedimento();
            }
        });
    }

    private void mostrarDialogAdicionarProcedimento() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Adicionar Procedimento");

        // Inflate o layout do diálogo
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_add_procedimentos, null);
        builder.setView(dialogView);

        // Referências para os EditTexts
        EditText editTextTipoProcedimento = dialogView.findViewById(R.id.editTextTipoProcedimento);
        EditText editTextDataProcedimento = dialogView.findViewById(R.id.editTextDataProcedimento);
        EditText editTextVeterinarioResponsavel = dialogView.findViewById(R.id.editTextNomeVeterinario);
        EditText editTextNotasAdicionais = dialogView.findViewById(R.id.editTextNotasAdicionais);

        // Abrindo o DatePickerDialog ao clicar no EditText da data
        editTextDataProcedimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirDatePicker(editTextDataProcedimento);
            }
        });

        // Botão Salvar
        builder.setPositiveButton("Salvar", null); // Não atribuir a ação ainda

        // Botão Cancelar
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss(); // Fecha o diálogo
            }
        });

        // Criar o diálogo
        AlertDialog dialog = builder.create();

        // Configurando a ação do botão "Salvar"
        dialog.setOnShowListener(dialogInterface -> {
            Button btnSalvar = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
            btnSalvar.setOnClickListener(v -> {
                String tipoProcedimento = editTextTipoProcedimento.getText().toString().trim();
                String dataProcedimento = editTextDataProcedimento.getText().toString().trim();
                String veterinarioResponsavel = editTextVeterinarioResponsavel.getText().toString().trim();
                String notasAdicionais = editTextNotasAdicionais.getText().toString().trim();

                // Verifica se os campos obrigatórios estão preenchidos
                if (tipoProcedimento.isEmpty() || dataProcedimento.isEmpty() ||
                        veterinarioResponsavel.isEmpty() || notasAdicionais.isEmpty()) {

                    // Exibe um aviso se algum campo obrigatório estiver vazio
                    AlertDialog.Builder warningBuilder = new AlertDialog.Builder(ProcedimentosActivity.this);
                    warningBuilder.setMessage("Por favor, preencha todos os campos obrigatórios.")
                            .setPositiveButton("OK", null);
                    warningBuilder.show();
                } else {
                    // Adiciona o procedimento na lista se todos os campos estiverem preenchidos
                    adicionarProcedimento(tipoProcedimento, dataProcedimento, veterinarioResponsavel, notasAdicionais);
                    dialog.dismiss(); // Fecha o diálogo se tudo estiver certo
                }
            });
        });

        dialog.show(); // Mostra o diálogo
    }

    private void abrirDatePicker(EditText editTextDataProcedimento) {
        // Cria um Calendar para pegar a data atual
        Calendar calendar = Calendar.getInstance();
        int ano = calendar.get(Calendar.YEAR);
        int mes = calendar.get(Calendar.MONTH);
        int dia = calendar.get(Calendar.DAY_OF_MONTH);

        // Cria o DatePickerDialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view, year, month, dayOfMonth) -> {
            String dataSelecionada = String.format("%02d/%02d/%04d", dayOfMonth, month + 1, year);
            editTextDataProcedimento.setText(dataSelecionada);
        }, ano, mes, dia);

        datePickerDialog.show(); // Mostra o DatePickerDialog
    }

    private void adicionarProcedimento(String tipo, String data, String veterinario, String notas) {
        // Cria uma nova instância de Procedimento
        Procedimento novoProcedimento = new Procedimento(tipo, data, veterinario, notas);
        listaProcedimentos.add(novoProcedimento);
        procedimentoAdapter.notifyItemInserted(listaProcedimentos.size() - 1);
        recyclerViewProcedimentos.scrollToPosition(listaProcedimentos.size() - 1); // Rolagem para o novo procedimento
    }
}
