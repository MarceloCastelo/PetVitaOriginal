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
import com.example.petvitaoriginal.adapter.VacinaAdapter;
import com.example.petvitaoriginal.classes.Vacina;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class VacinasActivity extends AppCompatActivity {
    private RecyclerView recyclerViewVacinas;
    private VacinaAdapter vacinaAdapter;
    private List<Vacina> listaVacinas; // Alterado para lista de objetos Vacina

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacinas);

        // Inicializando a lista de vacinas
        listaVacinas = new ArrayList<>();
        vacinaAdapter = new VacinaAdapter(listaVacinas);

        // Configurando o RecyclerView
        recyclerViewVacinas = findViewById(R.id.recyclerViewVacinas);
        recyclerViewVacinas.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewVacinas.setAdapter(vacinaAdapter);

        // Botão para adicionar nova vacina
        Button buttonAddVacina = findViewById(R.id.buttonAddVacina);
        buttonAddVacina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarDialogAdicionarVacina();
            }
        });
    }

    private void mostrarDialogAdicionarVacina() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Adicionar Vacina");

        // Inflate o layout do diálogo
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_add_vacina, null);
        builder.setView(dialogView);

        // Referências para os EditTexts
        EditText editTextNomeVacina = dialogView.findViewById(R.id.editTextNomeVacina);
        EditText editTextDescricaoVacina = dialogView.findViewById(R.id.editTextDescricaoVacina);
        EditText editTextDataVacina = dialogView.findViewById(R.id.editTextDataVacina);

        // Abrindo o DatePickerDialog ao clicar no EditText da data
        editTextDataVacina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirDatePicker(editTextDataVacina);
            }
        });

        // Botão Salvar
        builder.setPositiveButton("Salvar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String nomeVacina = editTextNomeVacina.getText().toString();
                String descricaoVacina = editTextDescricaoVacina.getText().toString();
                String dataVacina = editTextDataVacina.getText().toString();

                // Adiciona a vacina na lista
                adicionarVacina(nomeVacina, descricaoVacina, dataVacina);
            }
        });

        // Botão Cancelar
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss(); // Fecha o diálogo
            }
        });

        builder.show(); // Mostra o diálogo
    }

    private void abrirDatePicker(EditText editTextDataVacina) {
        // Cria um Calendar para pegar a data atual
        Calendar calendar = Calendar.getInstance();
        int ano = calendar.get(Calendar.YEAR);
        int mes = calendar.get(Calendar.MONTH);
        int dia = calendar.get(Calendar.DAY_OF_MONTH);

        // Cria o DatePickerDialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view, year, month, dayOfMonth) -> {
            String dataSelecionada = String.format("%02d/%02d/%04d", dayOfMonth, month + 1, year);
            editTextDataVacina.setText(dataSelecionada);
        }, ano, mes, dia);

        datePickerDialog.show(); // Mostra o DatePickerDialog
    }

    private void adicionarVacina(String nome, String descricao, String data) {
        // Cria uma nova instância de Vacina
        Vacina novaVacina = new Vacina(nome, data, descricao);
        listaVacinas.add(novaVacina);
        vacinaAdapter.notifyItemInserted(listaVacinas.size() - 1);
        recyclerViewVacinas.scrollToPosition(listaVacinas.size() - 1); // Rolagem para a nova vacina
    }
}
