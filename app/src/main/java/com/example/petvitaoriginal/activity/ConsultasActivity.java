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
import com.example.petvitaoriginal.adapter.ConsultaAdapter; // Alterado para o novo adaptador
import com.example.petvitaoriginal.classes.Consulta; // Alterado para a nova classe

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ConsultasActivity extends AppCompatActivity { // Alterado o nome da classe
    private RecyclerView recyclerViewConsultas; // Alterado para a nova RecyclerView
    private ConsultaAdapter consultaAdapter; // Alterado para o novo adaptador
    private List<Consulta> listaConsultas; // Alterado para a nova lista

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultas); // Alterado para o novo layout

        // Inicializando a lista de consultas
        listaConsultas = new ArrayList<>();
        consultaAdapter = new ConsultaAdapter(listaConsultas); // Alterado para o novo adaptador

        // Configurando o RecyclerView
        recyclerViewConsultas = findViewById(R.id.recyclerViewConsultas); // Alterado para a nova RecyclerView
        recyclerViewConsultas.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewConsultas.setAdapter(consultaAdapter); // Alterado para o novo adaptador

        // Botão para adicionar nova consulta
        Button buttonAddConsulta = findViewById(R.id.buttonAddConsulta); // Alterado para o novo botão
        buttonAddConsulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarDialogAdicionarConsulta(); // Alterado para a nova função
            }
        });
    }

    private void mostrarDialogAdicionarConsulta() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Adicionar Consulta");

        // Inflate o layout do diálogo
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_add_consultas, null); // Alterado para o novo layout
        builder.setView(dialogView);

        // Referências para os EditTexts
        EditText editTextTipoConsulta = dialogView.findViewById(R.id.editTextTipoConsulta); // Alterado para o novo EditText
        EditText editTextDataConsulta = dialogView.findViewById(R.id.editTextDataConsulta); // Alterado para o novo EditText
        EditText editTextVeterinarioResponsavel = dialogView.findViewById(R.id.editTextNomeVeterinario); // Mantido se for igual
        EditText editTextObservacoes = dialogView.findViewById(R.id.editTextObservacoes); // Alterado para o novo EditText

        // Abrindo o DatePickerDialog ao clicar no EditText da data
        editTextDataConsulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirDatePicker(editTextDataConsulta); // Alterado para a nova função
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
                String tipoConsulta = editTextTipoConsulta.getText().toString().trim();
                String dataConsulta = editTextDataConsulta.getText().toString().trim();
                String veterinarioResponsavel = editTextVeterinarioResponsavel.getText().toString().trim();
                String observacoes = editTextObservacoes.getText().toString().trim();

                // Verifica se os campos obrigatórios estão preenchidos
                if (tipoConsulta.isEmpty() || dataConsulta.isEmpty() ||
                        veterinarioResponsavel.isEmpty() || observacoes.isEmpty()) {

                    // Exibe um aviso se algum campo obrigatório estiver vazio
                    AlertDialog.Builder warningBuilder = new AlertDialog.Builder(ConsultasActivity.this);
                    warningBuilder.setMessage("Por favor, preencha todos os campos obrigatórios.")
                            .setPositiveButton("OK", null);
                    warningBuilder.show();
                } else {
                    // Adiciona a consulta na lista se todos os campos estiverem preenchidos
                    adicionarConsulta(tipoConsulta, dataConsulta, veterinarioResponsavel, observacoes); // Alterado para a nova função
                    dialog.dismiss(); // Fecha o diálogo se tudo estiver certo
                }
            });
        });

        dialog.show(); // Mostra o diálogo
    }

    private void abrirDatePicker(EditText editTextDataConsulta) {
        // Cria um Calendar para pegar a data atual
        Calendar calendar = Calendar.getInstance();
        int ano = calendar.get(Calendar.YEAR);
        int mes = calendar.get(Calendar.MONTH);
        int dia = calendar.get(Calendar.DAY_OF_MONTH);

        // Cria o DatePickerDialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view, year, month, dayOfMonth) -> {
            String dataSelecionada = String.format("%02d/%02d/%04d", dayOfMonth, month + 1, year);
            editTextDataConsulta.setText(dataSelecionada); // Alterado para o novo EditText
        }, ano, mes, dia);

        datePickerDialog.show(); // Mostra o DatePickerDialog
    }

    private void adicionarConsulta(String tipo, String data, String veterinario, String observacoes) {
        // Cria uma nova instância de Consulta
        Consulta novaConsulta = new Consulta(tipo, data, veterinario, observacoes); // Alterado para a nova classe
        listaConsultas.add(novaConsulta);
        consultaAdapter.notifyItemInserted(listaConsultas.size() - 1); // Alterado para o novo adaptador
        recyclerViewConsultas.scrollToPosition(listaConsultas.size() - 1); // Rolagem para a nova consulta
    }
}
